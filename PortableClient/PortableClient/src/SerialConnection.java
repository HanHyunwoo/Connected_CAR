
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

	private ConnectionManager connectionManager;

	private BufferedInputStream bin;
	private InputStream in;
	private OutputStream out;
	private CommPort commPort;
	private CommPortIdentifier portIdentifier;
	private SerialPort serialPort;

	private boolean flag = true;

	private String FROM = "W";
	private int WHERE = 1;
	private int IDSTART = 4;
	private int DATASTART = 12;
	private int DATAEND = 28;
	private int BUFFERLEN = 31;

	public SerialConnection() {

	}

	public SerialConnection(String portName, ConnectionManager connectionManager) throws Exception {
		this.connectionManager = connectionManager;
		this.portIdentifier = CommPortIdentifier.getPortIdentifier(portName);

		connectSerial();
		System.out.println(TAG + portName + " PORT connected");
		(new Thread(new SerialWriter())).start();

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
				serialPort.setSerialPortParams(921600, // 통신속도
						SerialPort.DATABITS_8, // 데이터 비트
						SerialPort.STOPBITS_1, // stop 비트
						SerialPort.PARITY_NONE); // 패리티
				in = serialPort.getInputStream();
				bin = new BufferedInputStream(in);
				out = serialPort.getOutputStream();
			} else {
				System.out.println("Error: Only serial ports are handled by this example.");
			}
		}
	}

	public void sendMsg(String msg) {
		SerialWriter sw = new SerialWriter(msg);
		new Thread(sw).start();
	}

	private class SerialWriter implements Runnable {
		String data;

		public SerialWriter() {
			// CAN Connection start signal
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
			// checksum data 만드는 과정
			cdata = (cdata & 0xFF);

			String returnData = ":";
			returnData += serialData + Integer.toHexString(cdata).toUpperCase();
			returnData += "\r";
			System.out.println(TAG + " send :: " + returnData);
			return returnData;
		}

		public void run() {
			try {
				byte[] inputData = data.getBytes();
				out.write(inputData);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		switch (event.getEventType()) {
		case SerialPortEvent.BI:
			System.out.println(TAG + "BI");
			break;
		case SerialPortEvent.OE:
			System.out.println(TAG + "OE");
			break;
		case SerialPortEvent.FE:
			System.out.println(TAG + "FE");
			break;
		case SerialPortEvent.PE:
			System.out.println(TAG + "PE");
			break;
		case SerialPortEvent.CD:
			System.out.println(TAG + "CD");
			break;
		case SerialPortEvent.CTS:
			System.out.println(TAG + "CTS");
			break;
		case SerialPortEvent.DSR:
			System.out.println(TAG + "DSR");
			break;
		case SerialPortEvent.RI:
			System.out.println(TAG + "RI");
			break;
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			System.out.println(TAG + "OUTPUT_BUFFER_EMPTY");
			break;
		case SerialPortEvent.DATA_AVAILABLE:
			byte[] readBuffer = new byte[128];

			try {
				int numBytes = 0;
				if (flag == true && bin.available() > 0) {
					numBytes = bin.read(readBuffer);
				}

				String buffer = new String(readBuffer).trim();
				System.out.println("Receive Low Data:" + numBytes + " : " + buffer);
				if (buffer.equals(Common.CANINITCODE)) {
					connectionManager.SendStartSignal();
					System.out.println(TAG + "START CAN RECEIVER");
					break;
				}

				// message가 32 bit 보다 짧은 경우
				if (numBytes < BUFFERLEN) {
					System.out.println("It can not be send. " + numBytes + " : " + buffer);
					break;
				}

				// W : echo message
				if (buffer.substring(WHERE, WHERE + 1).equals(FROM)) {
					break;
				}

				String id = buffer.substring(IDSTART, DATASTART);
				String data = buffer.substring(DATASTART, DATAEND);

				connectionManager.SendToCluster(id + "," + data);
				connectionManager.SendToIVI(id + "," + data);
			} catch (IOException e) {
				e.printStackTrace();
				destroy();
			} catch (Exception e) {
				e.printStackTrace();
			}

			break;
		}
	}

	public void destroy() {
		System.out.println(TAG + " dstroy . . .");

		flag = false;

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		serialPort.removeEventListener();
		connectionManager.SendStopSignal();

		try {
			bin.close();
			in.close();
			out.close();
			commPort.close();
			serialPort.close();
			connectionManager = null;
			System.out.println(TAG + " dstroy complete! ");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}