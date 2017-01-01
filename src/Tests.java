import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class Tests
{   
    private PokerHand.Result loss = PokerHand.Result.LOSS;
    private PokerHand.Result win = PokerHand.Result.WIN;
    private PokerHand.Result tie = PokerHand.Result.TIE;
    
    @Test
    public void HighestStraightFlushWins()
    {
        Test("Highest straight flush wins", loss, "2H 3H 4H 5H 6H", "KS AS TS QS JS");
    }
    
    @Test
    public void StraightFlushWinsFourCardsOfKind()
    {
    	Test("Straight flush wins 4 of a kind", win, "2H 3H 4H 5H 6H", "AS AD AC AH JD");
    }
    
    @Test
    public void HighestFourCardsOfKindWins()
    {
    	Test("Highest 4 of a kind wins", win, "AS AH 2H AD AC", "JS JD JC JH 3D");
    }
    
    @Test
    public void FourCardsOfKindWinsFullHouse()
    {
        Test("4 Of a kind wins of full house", loss, "2S AH 2H AS AC", "JS JD JC JH AD");
    }
    
    @Test
    public void FullHouseWinsFlush()
    {
    	Test("Full house wins of flush", win, "2S AH 2H AS AC", "2H 3H 5H 6H 7H");
    }
    
    @Test
    public void HighestFlushWins()
    {
        Test("Highest flush wins", win, "AS 3S 4S 8S 2S", "2H 3H 5H 6H 7H");
    }
    
    private void Test(String description, PokerHand.Result expected, String playerHand, String opponentHand)
    {
        PokerHand player = new PokerHand(playerHand);
        PokerHand opponent = new PokerHand(opponentHand);
        
        assertEquals(description + ":", expected.toString(), player.compareWith(opponent).toString());
    }
}