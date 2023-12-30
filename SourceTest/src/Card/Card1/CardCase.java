package Card.Card1;
import java.util.ArrayList;
import java.util.Collections;
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
		int suit=CardUtil.SUIT.length; // 0~1
		int valu=CardUtil.VALU.length; // 0~9
		int count=0;
		//��濡� �ㅻⅨ 20媛��� 移대��瑜� 留�����.
		while(count!=valu*suit){    // 20�μ�� �� ��源�吏�
			Card c=new Card();      // ������ 移대��瑜� 留�����
			if(!cards.contains(c)){ // contains -> equals()를 이용해 비교
				cards.add(c);       // 같은 객체가 아니라면 저장
				count++;
			}
		}
	}
	// 移대�� ��湲�
	public void shuffle(){
		Collections.shuffle(cards); // 카드섞기
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
}//
