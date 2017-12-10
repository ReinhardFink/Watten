package watten;

public class CardRelation {
	
	private Card card;
	private boolean trumpf;
	private boolean schlag;
	private boolean rechter;
	private boolean guater;
	
	public CardRelation(Card card, Result result) {
		this.card = card;
		if(card.number == CONSTANTS.SECHSER) trumpf = true;
		else trumpf = (result.getTrumpf() == card.color);
		schlag = (result.getSchlag() == card.number);
		rechter = schlag && trumpf;
		if(result.isMitGuatem() && result.isGuaterFix() && card.equals(result.getGuater())) guater = true;
	}

	public boolean beats(CardRelation testCard) {
		if(this.guater) return true;
		if(testCard.guater) return false;
		// ab hier kein "feindlicher" Guater mehr möglich!
		if(this.rechter) return true;
		if(testCard.rechter) return false;
		// ab hier kein "feindlicher" Rechter mehr möglich!
		if(this.schlag) return true;  // erster Schlag sieg über Schlag in der Klammer!
		if(testCard.schlag) return false;
		// ab hier kein "feindlicher" Linker mehr möglich!
		// Trumpf schlägt Trumpf;         
		if(this.trumpf && testCard.trumpf && (this.card.number > testCard.card.number)) return true;
		if(testCard.trumpf) return false;
		// ab hier kein "feindlicher" Trumpf mehr möglich
		// Trumpf schlägt Farbe
		if(this.trumpf) return true;
		// Farbe schlägt Farbe, erste Farbe siegt über verschiedene zweite Farbe
		if(this.card.color != testCard.card.color) return true;
		// Farbe schlägt gleiche Farbe
		if(this.card.number >= testCard.card.number) return true;
		return false;
	}
}
