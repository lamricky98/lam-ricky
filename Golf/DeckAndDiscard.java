
//This is the class that handles the deck and the discard pile.
//The deck contains a standard playing cards set plus two jokers.
//We have to create the methods requested plus any that we find needed through the program.

public class DeckAndDiscard {
	
	private char[] deck;
	private char[] discardPile;
	private int nextDiscard, deckCards, nextDeck; //There are the index variables. They are used to correctly place the next cards.
	private char topCard; //This is the card being held during the players' turns. I need it to control the flow of the cards.
	
	//This is the default constructor.
	public DeckAndDiscard() {
		char[] deckTemp = {'A', 'A', 'A', 'A', '2', '2', '2', '2', '3', '3', '3', '3', '4', '4', '4', '4', '5', '5', '5', '5', '6', '6', '6', '6', '7', '7', '7', '7', '8', '8', '8', '8', '9', '9', '9', '9', 'T', 'T', 'T', 'T', 'J', 'J', 'J', 'J', 'Q', 'Q', 'Q', 'Q', 'K', 'K', 'K', 'K', '?', '?'};
		deck = deckTemp;
		discardPile = new char[54];
		nextDiscard = 0;
		deckCards = 54;
		nextDeck = 0;
		shuffle();
	}
	
	public char pickACard() {
		topCard = deck[nextDeck];
		nextDeck++;
		deckCards--; //updating the value for remaining cards
		return topCard;
	}
	
	public void discard() { //Need to check that there is an existing card held by a player before discarding.
		if (topCard != '\u0000') {
		discardPile[nextDiscard] = topCard;
		topCard = '\u0000';
		nextDiscard++;
		}
	}
	
	public void shuffle() { //This method will randomize the order of the cards in our deck.
		int firstChoice = 0, secondChoice = 0;
		char temp;
		for (int i = 0; i < 1000; i++) {
			firstChoice = (int)(Math.random() * 54);
			secondChoice = (int)(Math.random() * 54);
			temp = deck[firstChoice];
			deck[firstChoice] = deck[secondChoice];
			deck[secondChoice] = temp;
		}
	}
	
	public void displayDeck() {
		int count = 0;
		System.out.println("The following cards (" + deckCards + ") are still in the deck, in this order:");
		for (int i = nextDeck; i < deck.length; i++) {
			System.out.print(deck[i] + " ");
			count++;
			if (count % 10 == 0)
				System.out.println();
		}
		System.out.println();
	}
	
	public void displayDiscardPile() {
		int count = 0;
		System.out.println("The following cards are in the discard pile, from bottom to top:");
		for (int i = 0; i < nextDiscard; i++) {
			System.out.print(discardPile[i] + " ");
			count++;
			if (count % 10 == 0)
				System.out.println();
		}
		System.out.println();
	}
	
	public int getDeckCards() { //I use this method to know how many cards are left in the deck. If there are zero cards left, I just end the game.
		return deckCards;
	}
	
	public void switchCard(char playersCard) { //I am using this method for whenever I need to discard a card that is on a player's board.
		topCard = playersCard;
	}
	
	public char getTopDiscardCard() { //This is used to display the topmost discarded card.
		return discardPile[nextDiscard-1];
	}
	
	public char getTopCard() { //topCard is the variable of the card on hand, if it wasn't clear enough. On hand means that it was just drawn and not yet in the discard or in a board.
		return topCard;
	}
	
	public void switchWithBoard(char card) { //This is called when the player is taking something from their board and swapping it with the topmost discarded card.
		discardPile[nextDiscard-1] = card;
	}

}
