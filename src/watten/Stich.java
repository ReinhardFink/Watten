package watten;

public class Stich {
	public Card[] cards;
	public int winner;
	
	public Stich(Card card1, Card card2, Card card3, Card card4, int winner) {
		cards = new Card[4];
		cards[0] = card1;
		cards[1] = card2;
		cards[2] = card3;
		cards[3] = card4;
		this.winner = winner;
	}
	
	public Card getWinner() {
		return cards[winner];
	}
	
	public boolean findCard(Card card) {
		int position = 0;
		boolean cardFound = false;
		while(position < cards.length && !cardFound) {
			cardFound = cards[position].equals(card);
			position++;
		}
		return cardFound;
	}

	public boolean findNumber(int number) {
		int position = 0;
		boolean numberFound = false;
		while(position < cards.length && !numberFound) {
			numberFound = cards[position].number == number;
			position++;
		}
		return numberFound;
	}
	
	public boolean findColor(int color) {
		int position = 0;
		boolean colorFound = false;
		while(position < cards.length && !colorFound) {
			colorFound = cards[position].color == color;
			position++;
		}
		return colorFound;
	}
}
