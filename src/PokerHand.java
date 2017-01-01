import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PokerHand
{      
    public enum Result { TIE, WIN, LOSS } 
    
    // Rank of hands
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int STRAIGHT = 4;
    public static final int FLUSH = 5;
    public static final int FULL_HOUSE = 6;
    public static final int FOUR = 7;
    public static final int STRAIGHT_FLUSH = 8;
    private int handRank;
    
    private List<Poker> pokers;
    private Map<Integer, Long> counter; // <Ranking, Count>
    
    PokerHand(String hand)
    {
        pokers = Arrays.stream(hand.split(" "))
                .map(Poker::new)
                .sorted(Comparator.comparingInt(Poker::ranking).reversed())
                .collect(Collectors.toList());

        boolean flush = isFlush();
        boolean straight = isStraight();
        if (straight) {
            handRank = flush ? STRAIGHT_FLUSH : STRAIGHT;
        } else if (flush) {
            handRank = FLUSH;
        } else {
            // LinkedHashMap to keep the sorted order of Poker
            counter = pokers.stream()
                    .collect(Collectors.groupingBy(Poker::ranking, LinkedHashMap::new, Collectors.counting()));
            
            if (counter.containsValue(4L)) {
                handRank = FOUR;
            } else {
                int pairCount = (int) counter.values().stream().filter(i -> i == 2).count();
                
                if (counter.containsValue(3L)) {
                    handRank = pairCount == 1 ? FULL_HOUSE : THREE;
                } else {
                    switch (pairCount) {
                    case 2:
                        handRank = TWO;
                        break;
                    case 1:
                        handRank = ONE;
                        break;
                    }
                }
            }
        }
    }

    public Result compareWith(PokerHand hand)
    {
        int comp = Integer.compare(handRank, hand.handRank);
        if (comp != 0)
            return comp > 0 ? Result.WIN : Result.LOSS;
        
        // Tie-breaker of same rank of hands
        switch (handRank) {
        case STRAIGHT_FLUSH:
        case STRAIGHT:
            // only need to compare the highest rank card
            comp = Integer.compare(pokers.get(0).ranking(), hand.pokers.get(0).ranking());
            return comp > 0 ? Result.WIN : comp < 0 ? Result.LOSS : Result.TIE;
        case FOUR:
            return compareRank(hand, 4L);
        case FULL_HOUSE:
        case THREE:
            return compareRank(hand, 3L);
        case TWO:
        case ONE:
            return compareRank(hand, 2L);
        default:
            // nothing, just compare all cards in descending order
            List<Integer> player = pokers.stream().map(Poker::ranking).collect(Collectors.toList());
            List<Integer> opponent = hand.pokers.stream().map(Poker::ranking).collect(Collectors.toList());
            return compareCards(player, opponent);
        }
    }
    
    private boolean isStraight() {
        // required to be sorted by RANKING
        IntPredicate predicate = i -> Math.abs(pokers.get(i).ranking() - pokers.get(i + 1).ranking()) == 1; 
        
        // A5432 is also a straight
        return pokers.get(0).rank() == 'A' && IntStream.range(1, pokers.size() - 1).allMatch(predicate)
                || IntStream.range(0, pokers.size() - 1).allMatch(predicate);
    }
    
    private boolean isFlush() {
        return IntStream.of('S', 'H', 'D', 'C').anyMatch(s -> pokers.stream().allMatch(p -> p.suit() == s));
    }

    // compare the "dominant" rank of the hand first, then compare the rest
    // e.g. AAABB is a full house hand with A as dominant rank
    private Result compareRank(PokerHand hand, long dominantCount) {
        Map<Boolean, List<Integer>> player = getRankFor(dominantCount);
        Map<Boolean, List<Integer>> opponent = hand.getRankFor(dominantCount);
        
        Result result = compareCards(player.get(true), opponent.get(true)); // dominant rank
        return result != Result.TIE ? result : compareCards(player.get(false), opponent.get(false));
    }
    
    // true -> ranks with dominant count
    // false -> all other ranks
    private Map<Boolean, List<Integer>> getRankFor(long dominantCount) {
        return counter.entrySet().stream()
                .collect(Collectors.partitioningBy(e -> e.getValue() == dominantCount, Collectors.mapping(Map.Entry::getKey, Collectors.toList())));
    }
    
    private static Result compareCards(List<Integer> player, List<Integer> opponent) {
        for (Iterator<Integer> p = player.iterator(), o = opponent.iterator(); p.hasNext(); ) {
            int comp = p.next().compareTo(o.next());
            if (comp != 0)
                return comp > 0 ? Result.WIN : Result.LOSS;
        }
        return Result.TIE;
    }
    
    static class Poker {
        private static final String RANKING = "23456789TJQKA";
        
        private String poker;
        
        public Poker(String poker) {
            this.poker = poker;
        }
        
        public char rank() {
            return poker.charAt(0);
        }
        
        public char suit() {
            return poker.charAt(1);
        }
        
        public int ranking() {
            return RANKING.indexOf(poker.charAt(0));
        }
    }
}