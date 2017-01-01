kinds = {2, 3, 4, 5, 6, 7, 8, 9, T, J, Q, K, A}
suits = {S, H, D, C}

1. cards = split the hand by space " "
2. sort cards by rank
	a. special case: 2 3 4 5 A
	3. getRank(hand)
a. if(kinds are consecutive)
	rank = straight
	largest card = hand[4]
if(suits are all equal)
	rank = straight flush
	largest card = hand[4]
else if(suits are all equal)
	rank = flush
	largest card = hand[4]

if(four cards with same kind)
	rank = four of a kind
	largest card = the kind
	last card = the card with different kind
	else if(three cards with same kind)
if(the remaining two cards are same kind)
	rank = full house
	largest card = the kind
	pair = remaining two cards
	largest card of pair = kind of pair
	else 
	rank = three of a kind
	largest card = the kind
	largest card of remaining = largest card of remaining

	else if(two cards with same kind)
if(two cards of remaining with same kind)
	rank = two pairs
	else 
	rank = one pairs

	interface Rank(){
		public int Ranking;
		public int HighestKind;
	}

class StraightFlush implements Rank {

	public StraightFlush(String[] hand){
		this.Ranking = 9;
		this.HighestKind = hand[4];
	}
}

class FourKind implements Rank {
	public StraightFlush(String[] hand){
		this.Ranking = 8;
		this.HighestKind = 
	}
} 




