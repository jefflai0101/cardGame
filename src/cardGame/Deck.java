package cardGame;

import java.util.Random;

public class Deck{

	private Card[] cards;
	int i;

	Deck()
	{
		i=51;
		cards = new Card[52];
		int x=0;
		for (int a=0; a<=3; a++)
		{
			for (int b=0; b<=12; b++)
			 {
			   cards[x] = new Card(a,b);
			   x++;
			 }
		}
	}

	public Card drawFromDeck()
	{
		Random generator = new Random();
		int index=0;

		do
		{
			index = generator.nextInt(52);
		} while (cards[index] == null);

		i--;
		Card temp = cards[index];
		cards[index]= null;
		return temp;
	}

	public int getTotalCards()
	{
		return i;
	}
}

/*
[code]

First we have a constructor which fills our deck will the cards of various suits and ranks. Every time we add a card at x we increment x to put us at a fresh spot.

drawFromDeck() returns a random card from our container. We need to get a random index and return that card. We have a post-test loop that finds an array index  (less than 52) that isn't null. When we find one, we return its card and set it to null. Our deck might need to give feedback on how many cards are left, (maybe to determine if there are enough for another round of black jack or if we need to add all the cards back), so we make a variable i that represents the number cards left in the deck. i starts at 51 and is decremented everytime we take a card out. So if i is -1, then we know we're out of cards.

Now we need to write a test for this program. We want to make sure the cards that get drawn really are drawn in a random order. So we have main:

[code]
*/