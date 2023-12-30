package Thread;
public class   MyRunMain{
	public static void main(String[] args) {
		MyRun mr1=new MyRun();      // Runnable implements
		Thread t1=new Thread(mr1);  
		MyThread t2=new MyThread(); // Thread extends
		// �듬� �대� 
		Thread t3=new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=0;i<500;i++){
					System.out.print("R");
				}
			}
		});
		// lambda
		new Thread( ()-> {
				for(int i=0;i<500;i++){
					System.out.print("U");
				}
			}
		).start();
		t1.start();
		t2.start();
		t3.start();
		
		for(int i=0;i<500;i++){
			System.out.print("M");
		}
	}
}
