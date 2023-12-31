package Thread.WaitNotify;
public class CakePlate {
	private int breadCount=0;
	public CakePlate() {
	}
	public synchronized void makeBread(){
		if(breadCount>=10){
			try{
				System.out.println("鍮듭�� �⑤����.");
				wait();
			}catch(InterruptedException ire){}
		}
		breadCount++;  // 鍮듭�� 10媛�媛� ����硫� �� 留��ㅼ��.
		System.out.println("鍮듭�� 1媛� �� 留���  珥� : "+breadCount+"媛�");
		this.notifyAll();
	}
	public synchronized void eatBread(){
		if(breadCount<1){
			try{
				System.out.println("鍮듭�� 紐⑥���� 湲곕�ㅻ┝");
				wait();
			}catch(InterruptedException ire){}
		}
		breadCount--;  // 鍮듭�� ���쇰�� 癒뱀��.
		System.out.println("鍮듭�� 1媛� 癒뱀��  珥� : "+breadCount+"媛�");
		this.notifyAll();
	}
}
