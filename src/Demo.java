
public class Demo {

	public static void main(String[] args) {
		PokerHand hand1 = new PokerHand("KS 2H 5C JD TD");
		PokerHand hand2 = new PokerHand("KS 2H 5C JD TD");
		
		System.out.println(hand1.compareWith(hand2));
		
	}

}
