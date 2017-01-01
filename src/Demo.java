
public class Demo {

	public static void main(String[] args) {
		PokerHand hand1 = new PokerHand("KS AS QS JS TS");
		PokerHand hand2 = new PokerHand("3S 2H 5C AD 4D");
		
		System.out.println(hand1.getRank());
		System.out.println(hand1.getHighCard1());
		System.out.println(hand2.getRank());
		System.out.println(hand2.getHighCard1());
		
	}

}
