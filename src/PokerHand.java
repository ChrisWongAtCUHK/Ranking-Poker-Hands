
public class PokerHand {
	public PokerHand(String cards) {

	}
	
	public Result compareWith(PokerHand hand){
		return Result.WIN;
	}
}


interface Rank{
	public int Ranking = 0;
	public int compare(Rank rank);
}
