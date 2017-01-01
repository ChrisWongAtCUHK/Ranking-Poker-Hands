import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;


/**
 * 
 * @author chriswong
 *
 */
public class PokerHand {
	private final String[] KINDS = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
	private final String[] SUITS = {"S", "H", "D", "C"};
	private String[] cards;
	private int rank;
	private String highCard1;
	
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
	
		// if kinds are all consecutive, it is straight
		if(isStraight()){
			this.rank = 4;
			
			if(getKind(cards[3]) == 3 && getKind(cards[4]) == 12){
				// special case: 2 3 4 5 A
				this.highCard1 = cards[3];
			} else {
				// normal cases
				this.highCard1 = cards[4];
			}
			
		} else {
			this.rank = 0;
			this.highCard1 = cards[4];
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
	 * Accessor of highCard1
	 * @return
	 */
	public String getHighCard1(){
		return this.highCard1;
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
			int kind1 = getKind(cards[i]);
			int kind2 = getKind(cards[i + 1]);
			if(kind2 != kind1 + 1){
				// special case: 2 3 4 5 A
				if(kind1 == 3 && kind2 == 12){
					return true;
				}
				// normal cases
				return false;
			}
		}
		
		return isStraight;
	}
	
	/**
	 * Check if the hand is straight
	 * @return
	 */
	private boolean isFlush(){
		boolean isFlush = true;
		return isFlush;
	}
	
	/**
	 * Compare this hand with another hand
	 * @param	hand another hand
	 * @return	result
	 */
	public Result compareWith(PokerHand hand){
		return Result.WIN;
	}
	
}

/**
 * Rank
 * @author chriswong
 *
 */
interface Rank{
	public int Ranking = 0;
	public int compare(Rank rank);
}
