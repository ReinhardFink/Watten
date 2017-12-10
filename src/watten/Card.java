package watten;

public class Card {
	public int color;
	public int number;
	
	public Card(int color, int number) {
		this.color = color;
		this.number = number;
	}
	
	public boolean equals(Card card) {
		return (card.color == this.color && card.number == this.number);
	}
	
	public String toString() {
		return new String(CONSTANTS.colorNames[color] + " " + CONSTANTS.numberNames[number]);
	}
	
	public Card calcGuaterFromRechter() {
		if(this.number == CONSTANTS.SECHSER) return null;
		int newNumber = (this.number + 1) % (CONSTANTS.AS + 1);
		if(newNumber == CONSTANTS.SECHSER) newNumber = CONSTANTS.SIEBNER;
		return new Card(color, newNumber);
	}
	
	public Card calcRechterFromGuater() {
		if(this.number == CONSTANTS.SECHSER) return null;
		int newNumber = (this.number - 1 + CONSTANTS.AS + 1) % (CONSTANTS.AS + 1);
		if(newNumber == CONSTANTS.SECHSER) newNumber = CONSTANTS.AS;
		return new Card(color, newNumber);
	}
}
