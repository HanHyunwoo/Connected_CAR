import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClusterClient extends Thread {
	String TAG = "ClusterClient ::: ";

	private ConnectionManager connectionManager;
	private Socket socket;

	boolean flag = true;

	public ClusterClient(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	@Override
	public void run() {
		// 재접속을 위한 while
		while (flag) {
			try {
				System.out.println(TAG + "Try Connecting Server ..");
				socket = new Socket(Common.clusterIP, Common.port);
				System.out.println(TAG + "Connected Server ..");
				break;
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println(TAG + "Connected Retry ..");
				try {
					Thread.sleep(Common.connectionRetry);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}

		try {
			new Receiver(socket).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean sendMsg(String msg) {
		try {
			if (socket == null) {
				System.out.println(TAG + " NOT Connected with Cluster");
				return false;
			}

			Sender sender = new Sender(socket);
			sender.setSendMsg(msg);
			new Thread(sender).start();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	class Sender implements Runnable {
		Socket socket;
		OutputStream out;
		DataOutputStream outw;
		String sendMsg;

		public Sender(Socket socket) throws IOException {
			this.socket = socket;
			out = socket.getOutputStream();
			outw = new DataOutputStream(out);
		}

		public void setSendMsg(String sendMsg) {
			this.sendMsg = sendMsg;
		}

		@Override
		public void run() {
			try {
				if (outw != null) {
					System.out.println(TAG + "sendMSG : " + sendMsg);
					outw.writeUTF(sendMsg);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	class Receiver extends Thread {
		Socket socket;
		InputStream in;
		DataInputStream din;

		public Receiver(Socket socket) throws IOException {
			this.socket = socket;
			in = socket.getInputStream();
			din = new DataInputStream(in);
		}

		@Override
		public void run() {
			try {
				while (flag == true && din != null) {
					String msg = din.readUTF();
					System.out.println(TAG + "recieve from server : " + msg);
					connectionManager.SendToCar(msg);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (din != null)
						din.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void stopClient() {
		try {
			flag = false;
			Thread.sleep(1000);
			if (socket != null)
				socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
