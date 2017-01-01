
public class Demo {

	public static void main(String[] args) {
		PokerHand hand1 = new PokerHand("KS TH TD KC TS");
		PokerHand hand2 = new PokerHand("3S AH 7C 3D 4D");
		
		System.out.println(hand1.compareWith(hand2));

		
	}

}
