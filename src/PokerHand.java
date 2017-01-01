import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 
 * @author chriswong
 *
 */
public class PokerHand {
	public enum Result { TIE, WIN, LOSS }
	
	private final String[] KINDS = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};

	private String[] cards;
	private int[] kinds = new int[5];
	private int rank;
	// store the kinds of cards descendingly
	private ArrayList<Integer> highKinds = new ArrayList<Integer>();	
	
	/**
	 * Constructor
	 * @param cards
	 */
	public PokerHand(String cardsString) {
		// cards = split the hand by space " "
		this.cards = cardsString.split(" ");
		
		// sort cards by kind
		Arrays.sort(cards, new Comparator<String>(){

			/**
			 * Sort by the kind
			 * @param o1
			 * @param o2
			 * @return
			 */
			public int compare(String o1, String o2) {
				// get the first character(as String), find the corresponding index in KINDS
				int kind1 = Arrays.asList(KINDS).indexOf(o1.substring(0, 1));
				int kind2 = Arrays.asList(KINDS).indexOf(o2.substring(0, 1)); 
				return kind1 - kind2;
			}
			
		});		
		
		for(int i = 0 ;i < this.cards.length; i++){
			this.kinds[i] = getKind(cards[i]);
		}
		
		boolean isStraight = isStraight();
		boolean isFlush = isFlush();
		
		if(isStraight && isFlush){
			this.rank = 8;
		} else if(isFourCard()){
			this.rank = 7;
		} else if(isThreeCard()){
			// check if the hand is full house
			if(isFullHouse()){
				this.rank = 6;
			} else {
				this.rank = 3;
			}
		} else if(isFlush){
			this.rank = 5;
			
			// this line is avoid special case
			this.highKinds.add(this.kinds[4]);
		} else if(isStraight){
			this.rank = 4;
		} else if(isTwoPairs()){
			this.rank = 2;
		} else if(isOnePair()){
			this.rank = 1;
		} else {
			this.rank = 0;
			for(int i = this.kinds.length - 1; i >= 0 ; i--){
				this.highKinds.add(this.kinds[i]);
			}
		}
	}
	
	/**
	 * Accessor of rank
	 * @return
	 */
	public int getRank(){
		return this.rank;
	}
	
	/**
	 * Accessor of high kinds
	 * @return
	 */
	public ArrayList<Integer> getHighKinds(){
		return this.highKinds;
	}
	
	/**
	 * Get the kind of a card, e.g. AS is 12, 2H is 1
	 * @param card
	 * @return
	 */
	private int getKind(String card){
		int kind = Arrays.asList(KINDS).indexOf(card.substring(0, 1));
		return kind;
	}
	
	/**
	 * Check if the hand is straight
	 * @return
	 */
	private boolean isStraight(){
		boolean isStraight = true;
		
		for(int i = 0; i < this.cards.length - 1; i++){
			// not the consecutive kind
			if(this.kinds[i + 1] != this.kinds[i] + 1){
				// special case: 2 3 4 5 A
				if(this.kinds[i] == 3 && this.kinds[i + 1] == 12){
					return true;
				}
				// normal cases
				return false;
			}
		}
		
		// get the card of highest kind
		if(this.kinds[3] == 3 && this.kinds[4] == 12){
			// special case: 2 3 4 5 A
			this.highKinds.add(this.kinds[3]);
		} else {
			// normal cases
			this.highKinds.add(this.kinds[4]);
		}
		
		return isStraight;
	}
	
	/**
	 * Check if the hand is straight
	 * @return
	 */
	private boolean isFlush(){
		boolean isFlush = true;
		for(int i = 0; i < this.cards.length - 1; i++){
			// not the same suit
			if(!cards[i].substring(1, 2).equals(cards[i + 1].substring(1, 2))){
				return false;
			}
		}
		return isFlush;
	}
	
	/**
	 * Check if the hand is four cards of a kind
	 * @return
	 */
	private boolean isFourCard(){
		// first case, 1 to 4 cards
		if(this.kinds[1] == this.kinds[0] && this.kinds[2] == this.kinds[0] &&
				this.kinds[3] == this.kinds[0]){
			this.highKinds.add(this.kinds[0]);
			this.highKinds.add(this.kinds[4]);
			return true;
		}
		
		// second case, 2 to 5 cards
		if(this.kinds[2] == this.kinds[1] && this.kinds[3] == this.kinds[1] &&
				this.kinds[4] == this.kinds[1]){
			this.highKinds.add(this.kinds[1]);
			this.highKinds.add(this.kinds[0]);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Check if the hand is three cards of a kind
	 * @return
	 */
	private boolean isThreeCard(){
		// first case, 1 to 3 cards
		if(this.kinds[1] == this.kinds[0] && this.kinds[2] == this.kinds[0]){
			this.highKinds.add(this.kinds[0]);
			return true;
		}
		
		// second case, 2 to 4 cards
		if(this.kinds[2] == this.kinds[1] && this.kinds[3] == this.kinds[1]){
			this.highKinds.add(this.kinds[1]);
			return true;
		}
		
		// third case, 3 to 5 cards
		if(this.kinds[3] == this.kinds[2] && this.kinds[4] == this.kinds[2]){
			this.highKinds.add(this.kinds[2]);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Check if the hand is Full House
	 * @return
	 */
	private boolean isFullHouse(){
		// first case, 1 to 3 cards
		if(this.highKinds.get(0) == this.kinds[0]){
			// another pair
			if(this.kinds[3] == this.kinds[4]){
				this.highKinds.add(this.kinds[3]);
				return true;
			} else {
				this.highKinds.add(this.kinds[4]);
				this.highKinds.add(this.kinds[3]);
				return false;
			}
		}
		
		// second case, 2 to 4 cards
		if(this.highKinds.get(0) == this.kinds[1]){
			this.highKinds.add(this.kinds[4]);
			this.highKinds.add(this.kinds[0]);
			return false;
		}
		
		// third case, 3 to 5 cards
		if(this.highKinds.get(0) == this.kinds[2]){
			// another pair
			if(this.kinds[0] == this.kinds[1]){
				this.highKinds.add(this.kinds[0]);
				return true;
			} else {
				this.highKinds.add(this.kinds[1]);
				this.highKinds.add(this.kinds[0]);
				return false;
			}
		}
		
		return false;
	}
	
	/**
	 * Check if the hand is Two Pairs
	 * @return
	 */
	private boolean isTwoPairs(){
		// 1st case: 1 & 2, 3 & 4
		if(this.kinds[0] == this.kinds[1] && this.kinds[2] == this.kinds[3]){
			this.highKinds.add(this.kinds[2]);
			this.highKinds.add(this.kinds[0]);
			this.highKinds.add(this.kinds[4]);
			return true;
		}
			
		// 2nd case: 1 & 2, 4 & 5
		if(this.kinds[0] == this.kinds[1] && this.kinds[3] == this.kinds[4]){
			this.highKinds.add(this.kinds[3]);
			this.highKinds.add(this.kinds[0]);
			this.highKinds.add(this.kinds[2]);
			return true;
		}
		
		// 3rd case: 2 & 3, 4 & 5
		if(this.kinds[1] == this.kinds[2] && this.kinds[3] == this.kinds[4]){
			this.highKinds.add(this.kinds[3]);
			this.highKinds.add(this.kinds[1]);
			this.highKinds.add(this.kinds[0]);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Check if the hand is One Pair
	 * @return
	 */
	private boolean isOnePair(){
		// 1st case: 1 & 2
		if(this.kinds[0] == this.kinds[1]){
			this.highKinds.add(this.kinds[0]);
			this.highKinds.add(this.kinds[4]);
			this.highKinds.add(this.kinds[3]);
			this.highKinds.add(this.kinds[2]);
			return true;
		}
		
		// 2nd case: 2 & 3
		if(this.kinds[1] == this.kinds[2]){
			this.highKinds.add(this.kinds[1]);
			this.highKinds.add(this.kinds[4]);
			this.highKinds.add(this.kinds[3]);
			this.highKinds.add(this.kinds[0]);
			return true;
		}
		
		// 3rd case: 3 & 4
		if(this.kinds[2] == this.kinds[3]){
			this.highKinds.add(this.kinds[2]);
			this.highKinds.add(this.kinds[4]);
			this.highKinds.add(this.kinds[1]);
			this.highKinds.add(this.kinds[0]);
			return true;
		}
		
		// 4th case: 4 & 5
		if(this.kinds[3] == this.kinds[4]){
			this.highKinds.add(this.kinds[3]);
			this.highKinds.add(this.kinds[2]);
			this.highKinds.add(this.kinds[1]);
			this.highKinds.add(this.kinds[0]);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Compare 2 lists of Integer, according to the specified length
	 * @param list1
	 * @param list2
	 * @param length
	 * @return
	 */
	private static Result compare(ArrayList<Integer> list1, ArrayList<Integer> list2, int length){
		for(int i = 0; i < length; i++){
			if(list1.get(i) > list2.get(i)){
				return Result.WIN;
			} else if(list1.get(i) < list2.get(i)){
				return Result.LOSS;
			}
		}
		
		return Result.TIE;
	}
	
	/**
	 * Compare this hand with another hand
	 * @param	hand another hand
	 * @return	result
	 */
	public Result compareWith(PokerHand hand){
		if(this.rank > hand.getRank()){
			return Result.WIN;
		} else if(this.rank < hand.getRank()){
			return Result.LOSS;
		} else {
			switch(this.rank){
				case 8:
				case 5:
				case 4:
					// for Straight Flush, Straight or Flush
					return compare(this.getHighKinds(), hand.getHighKinds(), 1);
				case 7:
				case 6:
					// for Four Cards of a Kind and Full House
					return compare(this.getHighKinds(), hand.getHighKinds(), 2);
				case 3:
				case 2:
					// for Three Cards of a Kind and // for Two Pairs
					return compare(this.getHighKinds(), hand.getHighKinds(), 3);					
				case 1:
					// for One Pair
					return compare(this.getHighKinds(), hand.getHighKinds(), 4);
				case 0:
					// for High Card
					return compare(this.getHighKinds(), hand.getHighKinds(), 5);
				default:
					return Result.TIE;
			}
		}
	}
}
