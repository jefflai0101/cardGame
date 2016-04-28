package cardGame;

public class Card
{
	private int rank, suit;

	//private static String[] suits = { "hearts", "spades", "diamonds", "clubs" };
	//private static String[] ranks  = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
	private static String[] suits = { "h", "s", "d", "c" };
	private static String[] ranks  = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13" };


	Card(int suit, int rank)
	{
		this.rank=rank;
		this.suit=suit;
	}

	public @Override String toString()
	{
		//return ranks[rank] + " of " + suits[suit];
		return  suits[suit] + ranks[rank];
	}

	public int getRank() {
		 return rank;
	}

	public int getSuit() {
		return suit;
	}

}

