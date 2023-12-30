package Card.CardLamda;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class CardCase {
 // aggregation : CardCase�� Card濡� 援ъ�깅����.
 private List<Card> cards=new ArrayList<Card>();
 public CardCase() {
	cards.clear(); // �댁�� 吏��곌린
 }
 // List 諛���
 public List<Card> getCards() { 
	return cards;
 }
 // List�� ���λ�� Card�� 媛���
 public int count(){ 
	return cards.size(); // ���λ�� Card 媛���
 }
 // List�� index踰�吏� Card
 public Card getCard(int index){
	return cards.get(index);  // index踰�吏� Card
 }
 // ��濡� �ㅻⅨ 移대�� 20�� 留��ㅺ린
 public void make(){
	cards.clear(); // �댁�� 吏��곌린
	int suit=CardUtil.SUIT.length; // 0~3
	int valu=CardUtil.VALU.length; // 0~12
	int count=0;
	//��濡� �ㅻⅨ 20媛��� 移대��瑜� 留�����.
	while(count!=valu*suit){    // 20�μ�� �� ��源�吏�
		Card c=new Card();      // ������ 移대��瑜� 留�����
		if(!cards.contains(c)){ // contains -> equals()瑜� �댁�⑺�� 鍮�援�
			cards.add(c);       // 媛��� 媛�泥닿� �����쇰㈃ ����
			count++;
		}
	}
 }
 public void shuffle(){
	Collections.shuffle(cards); // 移대�� ��湲�
 }
 public void print(){
	int valu=CardUtil.VALU.length;
	for (int i = 0; i < cards.size(); i++) {
		Card c=cards.get(i);
		System.out.printf("%s ",c.toString());
		if((i+1)%valu==0){
			System.out.println();
		}
	}
 }
 // CardComp �대���ㅺ� ����.
 public void sort(){
	Comparator<Card> cmp=new Comparator<Card>() {
		@Override
		public int compare (Card c1, Card c2) {
			return c1.getCardVal().compareTo(c2.getCardVal());
		}
	};
	cards.sort(cmp); 
 }
 // CardRomp �대���ㅺ� ����.
 public void rsort(){
	// �듬� -anonymous 
	cards.sort(new Comparator<Card>() {
		@Override
		public int compare (Card c1, Card c2) {
			return - c1.getCardVal().compareTo(c2.getCardVal());
		}
	});
}
 // sort瑜� lambda濡� 援ы��
 public void lambdasort(){
	cards.sort((c1, c2) ->{return c1.getCardVal().compareTo(c2.getCardVal());});
 }
 public void lambdasort2(){
	cards.sort(Comparator.comparing(Card::getCardVal)); // comparing
 }
 public void lambdasort3(){
	cards.sort(Card::compareCard);  // static 
 }
 // rsort瑜� lambda濡� 援ы��
 public void lambdarsort(){
	cards.sort((c1, c2) ->{return - c1.getCardVal().compareTo(c2.getCardVal());});
 }
 public void lambdarsort2(){
	Comparator<Card> mycard = (c1, c2) ->{return c1.getCardVal().compareTo(c2.getCardVal());};
	cards.sort( mycard.reversed() );
 }
 public void rambdarsort3(){
	cards.sort(Card::compareRCard);  //static (type::method) 
 }
}


/*
Comparator<Card> cmp=new Comparator<Card>() {
	@Override
	public int compare (Card c1, Card c2) {
		return c1.getCardVal().compareTo(c2.getCardVal());
	}
};
cards.sort(cmp); 
*/