
public class Demo {

	public static void main(String[] args) {
		PokerHand hand1 = new PokerHand("KS TH TD KC TS");
		PokerHand hand2 = new PokerHand("3S 4H 2C 3D 4D");
		
		System.out.println(hand1.getRank());
		System.out.println(hand1.getHighCard1());
		System.out.println(hand1.getHighCard2());
		System.out.println(hand2.getRank());
		System.out.println(hand2.getHighCard1());
		System.out.println(hand2.getHighCard2());
		System.out.println(hand2.getHighCard3());
		
	}

}
