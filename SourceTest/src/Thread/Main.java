package Thread;

public class Main {

	public static void main(String[] args) {
		
		MyRunnable myr = new MyRunnable();
		
		Thread t1 = new Thread(myr);
		t1.start();
		
		MyThread t2 = new MyThread();
		t2.start();
		
		new Thread( new Runnable() { // �͸� ���� Ŭ����(Anonymous Nested Class)
			@Override
			public void run() {
				// TODO Auto-generated method stub
			}
		}).start();
	}
	
	public static class MyRunnable implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
	}
	
	public static class MyThread extends Thread {
		@Override
		public void run() {
			// TODO Auto-generated method stub
		}
	}

}
