

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

public class SerialTest implements SerialPortEventListener {
	// CommPortIdentifier commPortIdentifier;
	private static String TAG = "SerialTest :: ";
	private BufferedInputStream bin;
	private InputStream in;
	private OutputStream out;
	private SerialPort serialPort;
	private CommPortIdentifier portIdentifier;
	private CommPort commPort;

	private ClusterClient clusterClient;
	private IVIClient iviClient;
	
	public SerialTest() {
		clusterClient = new ClusterClient();
		clusterClient.start();
		iviClient = new IVIClient();
		iviClient.start();
	}

	public SerialTest(String portName) throws NoSuchPortException {
		this();
		portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		System.out.println("Connect Com Port");
		try {
			connectSerial();
			System.out.println("Connect OK !!");
			(new Thread(new SerialWriter())).start();
		} catch (Exception e) {
			System.out.println("Connect Fail !!");
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
	
	// 보내는 역할
	public void sendMsg(String msg) {
		// :W28 00000000 0000000000000000 53 \r	
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
			// checksum data 만드는 과정
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

	public static void main(String[] args) {
		System.out.println("SerialTest");

		SerialTest serialTest = null;
		// Try Connection with car  
		while (true) {
			try {
				System.out.println(TAG + "Try Connecting Car ..");
				serialTest = new SerialTest("COM5");
				System.out.println(TAG + "Connected Car ..");
				break;
			} catch (NoSuchPortException e) {
				System.out.println(TAG + "Connected Retry ..");
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
				System.out.println(buffer);
				if(buffer.equals(Common.CANINITCODE)) {
					System.out.println(TAG + "START CAN RECEIVER");
					break;
				}
				
				String header = buffer.substring(0, 4);
				String id = buffer.substring(5, 12);
				String data = buffer.substring(13, 13+16);
				System.out.println("Receive Low Data:" + buffer);

				iviClient.sendMsg(id+","+data);
				clusterClient.sendMsg(id+","+data);			
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}

}