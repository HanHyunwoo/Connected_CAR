public class PortableClient {
	private static String TAG = "PortableClient :: ";
	private static ConnectionManager connectionManager;

	public static void main(String[] args) {
		connectionManager = new ConnectionManager();
		t.start();

	}

	// thread로 무한 돌리면서 , 연결 끝나면 다시 시도
	static Thread t = new Thread(new Runnable() {
		@Override
		public void run() {
			while (true) {
				if (connectionManager.isStatus() == false) {
					System.out.println(TAG + " new connection manager ");
					connectionManager = new ConnectionManager();
				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	});
}
