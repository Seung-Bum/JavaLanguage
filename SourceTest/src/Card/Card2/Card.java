package Card.Card2;
public class Card {
	private String cardVal; //H8
	public String getCardVal() {
		return cardVal;
	}
	public Card() {
		int deck=(int)(Math.random()*CardUtil.SUIT.length); // 0~3
		int suit=(int)(Math.random()*CardUtil.VALU.length); // 0~12
		//������ 移대�� 媛��� 媛�����. H6
		cardVal=CardUtil.SUIT[deck]+CardUtil.VALU[suit];
	}
	// ���ㅽ�몄��
	public Card(String s) {
		this.cardVal=s;  // 臾몄���댁�� 媛� 蹂듭��
	}
	// 蹂듭�� ���깆��
	public Card(Card c) {
		this(c.getCardVal());  // 臾몄���댁�� 媛� 蹂듭��
		// this.cardVal=c.getCardVal(); //����
	}
	@Override
	public String toString() {
		return "[" + cardVal + "]";
	}
    // Eclipse-Source-Generate hashCode() and equals()瑜� �댁�⑺���� �����쇰� 留��ㅼ��
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardVal == null) ? 0 : cardVal.hashCode());
		return result;
	}
    // ���ㅻ�� ��由ы��硫�  移대��媛�怨� 移대��媛��� �쇰���쇰� 鍮�援�����.
	@Override
	public boolean equals(Object obj) {
		Card cb=(Card)obj; 
		if(cardVal.equals(cb.getCardVal())){
			return true;
		}else{
			return false;
		}
	}
}//
