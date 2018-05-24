import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import gnu.io.NoSuchPortException;

public class ConnectionManager {
	private static String TAG = "ConnectionManager :: ";
	private boolean status;
	ClusterClient clusterClient;
	IVIClient iviClient;

	SerialConnection serialConnection;
	Queue<msg> msgQue;
	
	public enum DES {CAR, CLUSTER, IVI};

	private class msg{
		DES des;
		String msg;
		
		public msg(DES des, String msg) {
			this.des = des;
			this.msg = msg;			
		}
	}
	
	public ConnectionManager() {
		status = true;
		msgQue = new LinkedList<>();
		ConnectToCar();
		
		// CAN 연결이 확인된 후 server에 접속  		
		clusterClient = new ClusterClient(this);
		clusterClient.start();
		iviClient = new IVIClient(this);
		iviClient.start();
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void ConnectToCar() {
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
	}
	
	//CAN connected
	public void SendStartSignal() {
		clusterClient.sendMsg("0,1");
		iviClient.sendMsg("0,1");
	}
	
	//CAN disconnected
	public void SendStopSignal() {
		clusterClient.sendMsg("0,0");
		iviClient.sendMsg("0,0");
		
		clusterClient.stopClient();
		iviClient.stopClient();
		status = false;
	}
	
	public void SendToCluster(String msg) {
		boolean res = clusterClient.sendMsg(msg);
		if(!res) {
			msgQue.add(new msg(DES.CLUSTER, msg));
		}
	}

	public void SendToIVI(String msg) {		
		boolean res = iviClient.sendMsg(msg);
		if(!res) {
			msgQue.add(new msg(DES.IVI, msg));
		}
	}

	/*
	 * device, value
	 * motor : 5 / led : 6
	 * value : 0 - 1
	 * Change to CAN serial protocol 
	 */
	public void SendToCar(String msg) {
		StringTokenizer st = new StringTokenizer(msg, ",");
		String id = "";
		String data = "";
		
		if(st.hasMoreTokens()) {
			id = st.nextToken();
		}		
		if(st.hasMoreTokens()) {			
			data = st.nextToken();	
		}
		
		msg = String.format("W28%08d%016d",Integer.parseInt(id), Integer.parseInt(data));
		serialConnection.sendMsg(msg);
	}
}
