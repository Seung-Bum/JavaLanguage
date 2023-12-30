package Card.Card1;
public class CardCaseMain {
	public static void main(String[] args) {
		CardCase cc=new CardCase();
		cc.make();     // ��濡� �ㅻⅨ 移대�� 20�� 留��ㅺ린
		cc.print();
		System.out.println("--------------------------");
		cc.shuffle();   // 移대�� ��湲�
		cc.print();
		System.out.println("--------------------------");
		cc.shuffle();   // 移대�� ��湲�
		cc.print();
	}
}
