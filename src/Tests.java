import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class Tests
{   
    private PokerHand.Result loss = PokerHand.Result.LOSS;
    private PokerHand.Result win = PokerHand.Result.WIN;
    private PokerHand.Result tie = PokerHand.Result.TIE;
    
    @Test
    public void HighestStraightFlushWins(){
        Test("Highest straight flush wins", loss, "2H 3H 4H 5H 6H", "KS AS TS QS JS");
    }
    
    @Test
    public void StraightFlushWinsFourCardsOfKind(){
    	Test("Straight flush wins 4 of a kind", win, "2H 3H 4H 5H 6H", "AS AD AC AH JD");
    }
    
    @Test
    public void HighestFourCardsOfKindWins(){
    	Test("Highest 4 of a kind wins", win, "AS AH 2H AD AC", "JS JD JC JH 3D");
    }
    
    @Test
    public void FourCardsOfKindWinsFullHouse(){
        Test("4 Of a kind wins of full house", loss, "2S AH 2H AS AC", "JS JD JC JH AD");
    }
    
    @Test
    public void FullHouseWinsFlush(){
    	Test("Full house wins of flush", win, "2S AH 2H AS AC", "2H 3H 5H 6H 7H");
    }
    
    @Test
    public void HighestFlushWins(){
        Test("Highest flush wins", win, "AS 3S 4S 8S 2S", "2H 3H 5H 6H 7H");
    }

    @Test
    public void FlushWinsStraight(){
        Test("Flush wins of straight", win, "2H 3H 5H 6H 7H", "2S 3H 4H 5S 6C");
    }
    
    @Test
    public void EqualStraightIsTie(){
	      Test("Equal straight is tie", tie, "2S 3H 4H 5S 6C", "3D 4C 5H 6H 2S");
    }
    
    @Test
    public void StraightWinsThreeCardsOfKind(){
        Test("Straight wins of three of a kind", win, "2S 3H 4H 5S 6C", "AH AC 5H 6H AS");
    }
    
    @Test
    public void ThreeCardsOfKindWinsTwoPairs(){
        Test("3 Of a kind wins of two pair", loss, "2S 2H 4H 5S 4C", "AH AC 5H 6H AS");
    }
     
    @Test
    public void TwoPairsWinsOnePair(){
        Test("2 Pair wins of pair", win, "2S 2H 4H 5S 4C", "AH AC 5H 6H 7S");
    }
    
    @Test
    public void HighestPairWins(){
    	Test("Highest pair wins", loss, "6S AD 7H 4S AS", "AH AC 5H 6H 7S");
    }
    
    @Test
    public void PairWinsNothing(){
        Test("Pair wins of nothing", loss, "2S AH 4H 5S KC", "AH AC 5H 6H 7S");
    }
    
    @Test
    public void HighestCardLoses(){
        Test("Highest card loses", loss, "2S 3H 6H 7S 9C", "7H 3C TH 6H 9S");
    }
    
    @Test
    public void HighestCardWins(){
        Test("Highest card wins", win, "4S 5H 6H TS AC", "3S 5H 6H TS AC");
    }
    
    @Test
    public void EqualCardsTies(){
	    Test("Equal cards is tie", tie, "2S AH 4H 5S 6C", "AD 4C 5H 6H 2C");
    }
    
    @Test
    public void RandomizedTest1(){
	    Test("Random wins", win, "TS KS 5S 9S AC", "JH 8S TH AH QH");
    }

    @Test
    public void RandomizedTest2(){
	    Test("Random wins", win, "7C 7S KH 2H 7H", "7C 7S 3S 7H 5S");
    }
    
    @Test
    public void RandomizedTest3(){
	    Test("Random wins", win, "4C 5C 9C 8C KC", "3S 8S 9S 5S KS");
    }
    
    private void Test(String description, PokerHand.Result expected, String playerHand, String opponentHand)
    {
        PokerHand player = new PokerHand(playerHand);
        PokerHand opponent = new PokerHand(opponentHand);
        
        assertEquals(description + ":", expected.toString(), player.compareWith(opponent).toString());
    }
}
