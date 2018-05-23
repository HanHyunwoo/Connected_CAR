
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class SerialConnection implements SerialPortEventListener {
	private static String TAG = "SerialConnection :: ";
	private BufferedInputStream bin;
	private InputStream in;
	private OutputStream out;
	private SerialPort serialPort;
	private CommPortIdentifier portIdentifier;
	private CommPort commPort;

	private ConnectionManager connectionManager;
	
	private int IDSTART = 4;
	private int DATASTART = 12;
	private int DATAEND = 28;
	public SerialConnection() {

	}

	public SerialConnection(String portName, ConnectionManager connectionManager) throws NoSuchPortException {
		this.connectionManager = connectionManager;
		this.portIdentifier = CommPortIdentifier.getPortIdentifier(portName);

		try {
			connectSerial();
			System.out.println(TAG + portName + " PORT connected");
			(new Thread(new SerialWriter())).start();
		} catch (Exception e) {
			System.out.println(TAG + "connect Fail");
			e.printStackTrace();
		}
	}

	public void connectSerial() throws Exception {

		if (portIdentifier.isCurrentlyOwned()) {
			System.out.println("Error: Port is currently in use");
		} else {
			commPort = portIdentifier.open(this.getClass().getName(), 5000);
			if (commPort instanceof SerialPort) {
				serialPort = (SerialPort) commPort;
				serialPort.addEventListener(this);
				serialPort.notifyOnDataAvailable(true);
				serialPort.setSerialPortParams(921600, // ��żӵ�
						SerialPort.DATABITS_8, // ������ ��Ʈ
						SerialPort.STOPBITS_1, // stop ��Ʈ
						SerialPort.PARITY_NONE); // �и�Ƽ
				in = serialPort.getInputStream();
				bin = new BufferedInputStream(in);
				out = serialPort.getOutputStream();
			} else {
				System.out.println("Error: Only serial ports are handled by this example.");
			}
		}
	}

	// ������ ����
	public void sendMsg(String msg) {
		SerialWriter sw = new SerialWriter(msg);
		new Thread(sw).start();
	}

	private class SerialWriter implements Runnable {
		String data;

		public SerialWriter() {
			this.data = ":G11A9\r";
		}

		public SerialWriter(String serialData) {
			this.data = sendDataFormat(serialData);
		}

		public String sendDataFormat(String serialData) {
			serialData = serialData.toUpperCase();
			char c[] = serialData.toCharArray();
			int cdata = 0;
			for (char cc : c) {
				cdata += cc;
			}
			// checksum data ����� ����
			cdata = (cdata & 0xFF);

			String returnData = ":";
			returnData += serialData + Integer.toHexString(cdata).toUpperCase();
			returnData += "\r";
			return returnData;
		}

		public void run() {
			try {
				byte[] inputData = data.getBytes();
				out.write(inputData);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		switch (event.getEventType()) {
		case SerialPortEvent.BI:
		case SerialPortEvent.OE:
		case SerialPortEvent.FE:
		case SerialPortEvent.PE:
		case SerialPortEvent.CD:
		case SerialPortEvent.CTS:
		case SerialPortEvent.DSR:
		case SerialPortEvent.RI:
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			break;
		case SerialPortEvent.DATA_AVAILABLE:
			byte[] readBuffer = new byte[128];

			try {
				while (bin.available() > 0) {
					int numBytes = bin.read(readBuffer);
				}

				String buffer = new String(readBuffer).trim();
				System.out.println("Receive Low Data:" + buffer);

				if (buffer.equals(Common.CANINITCODE)) {
					System.out.println(TAG + "START CAN RECEIVER");
					break;
				}

				String id = buffer.substring(IDSTART, DATASTART-1);
				String data = buffer.substring(DATASTART, DATAEND);
				
				connectionManager.SendToCluster(id + "," + data);
				connectionManager.SendToIVI(id + "," + data);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}

}