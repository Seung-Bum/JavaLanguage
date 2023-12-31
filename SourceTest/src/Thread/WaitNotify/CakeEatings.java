package Thread.WaitNotify;
public class CakeEatings {
	public static void main(String[] args) {
		CakePlate cake=new CakePlate();//Cake ���� 以�鍮�
		CakeEater eater=new CakeEater(cake);//cake ���� 怨듭��
		CakeMaker baker=new CakeMaker(cake);//cake ���� 怨듭��
		// �곗������媛� ���쇰㈃ �� 留��� �몄�媛��μ��-> �� 癒쇱�� ���� 媛��μ�깆�� ����.
		//baker.setPriority(6); 
		baker.start(); //癒쇱�� 鍮듭�� 留��ㅺ린 ��������.
		eater.start();
	}
}
