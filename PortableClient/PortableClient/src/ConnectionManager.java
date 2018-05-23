import gnu.io.NoSuchPortException;

public class ConnectionManager {
	private static String TAG = "ConnectionManager :: ";

	ClusterClient clusterClient;
	IVIClient iviClient;

	SerialConnection serialConnection;

	public ConnectionManager() {
		// CAN 연결이 확인된 후 server에 접속  
		while (true) {
			try {
				serialConnection = new SerialConnection(Common.CANPORT, this);
				System.out.println(TAG + " CAN connected");
				break;
			} catch (NoSuchPortException e) {
				System.out.println(TAG + " CAN connection retry . . .");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
		
		clusterClient = new ClusterClient(this);
		clusterClient.start();
		iviClient = new IVIClient(this);
		iviClient.start();
	}

	public void SendToCluster(String msg) {
		clusterClient.sendMsg(msg);
	}

	public void SendToIVI(String msg) {
		iviClient.sendMsg(msg);
	}

	public void SendToCar(String msg) {
		/*
		 * device, value
		 * motor : 5 , led : 6
		 * value : 0 - 1
		 * Change to CAN serial protocol 
		*/
		serialConnection.sendMsg(msg);
	}
}
