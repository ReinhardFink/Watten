package watten;

import java.util.ArrayList;

public class Result implements Cloneable {
	
	private boolean mitGuatem;
	private PossibilityArray schlag;
	private PossibilityArray trumpf;
	
	public Result(boolean mitGuatem) {
		this.mitGuatem = mitGuatem;
		schlag = new PossibilityArray(CONSTANTS.ANZAHL_SCHLAEGE);  // alle automatisch auf 0 = POSSIBLE!
		trumpf = new PossibilityArray(CONSTANTS.ANZAHL_FARBEN);    // alle automatisch auf 0 = POSSIBLE!
	}
	
	public Result clone() throws CloneNotSupportedException {
		Result tempResult = new Result(mitGuatem);
		tempResult.setSchlagPossibilityArray(schlag.clone());
		tempResult.setTrumpfPossibilityArray(trumpf.clone());
		return tempResult;
	}

	private void setSchlagPossibilityArray(PossibilityArray schlag) { this.schlag = schlag; }

	private void setTrumpfPossibilityArray(PossibilityArray trumpf) { this.trumpf = trumpf; }
	
	public String toString() {
		StringBuffer tempString = new StringBuffer();
		tempString.append(CONSTANTS.messageColor);
		tempString.append(":\n");
		tempString.append(trumpf.toString());
		tempString.append("\n");
		tempString.append(CONSTANTS.messageNumber);
		tempString.append(":\n");
		tempString.append(schlag.toString());
		return tempString.toString();
	}
	
	public PossibilityArray getSchlagPossibilityArray () { return schlag; }
	
	public PossibilityArray getTrumpfPossibilityArray () { return trumpf; }
	
	public boolean isMitGuatem() { return this.mitGuatem; }
	
	public void setNumberAt(int position, int value) { schlag.setValueAt(position,value); }
	
	public void setColorAt(int position, int value) { trumpf.setValueAt(position,value); }
	
	public int getNumberAt(int position) { return schlag.get(position); }
	
	public int getColorAt(int position) { return trumpf.get(position); }
	
	public int getSchlag() { return schlag.findFirstSure(); }
	
	public int getTrumpf() { return trumpf.findFirstSure(); }
	
	public boolean isSchlagFix() { return getSchlag() != CONSTANTS.IMPOSSIBLE; }
	
	public boolean isTrumpfFix() { return getTrumpf() != CONSTANTS.IMPOSSIBLE; }
	
	public boolean isRechterFix() { return (isSchlagFix() && isTrumpfFix()); }
	
	public boolean isGuaterFix() { 
		if(getSchlag() == CONSTANTS.SECHSER) return false;
		return isRechterFix(); 
	}
	
	// getRechter() und getGater() liefern null falls Werte nicht feststehen
	public Card getRechter() { 
		if((getSchlag() == CONSTANTS.IMPOSSIBLE) || getTrumpf() == CONSTANTS.IMPOSSIBLE)
			return null;
		else return new Card(getTrumpf(), getSchlag()); }
	
	public Card getGuater() { 
		if(getRechter() == null) return null;
		else return getRechter().calcGuaterFromRechter(); }
	
	// nicht mehr mögliche Schläge auf IMPOSSIBLE setzen:
	public void calcSchlag(ArrayList<Stich> stiche) {
		setLooserToImpossible(stiche);
		if(mitGuatem) correctPreviousLeftOut(stiche);
	}
	
	private void setLooserToImpossible(ArrayList<Stich> stiche) {
		Stich lastStich = stiche.get(stiche.size() - 1);
		for(Card card : lastStich.cards) 
			if(!hasWinnerOrPreviousToWinnerNumber(card, lastStich))
				this.schlag.setValueAt(card.number,CONSTANTS.IMPOSSIBLE);
	}
	
	private boolean hasWinnerOrPreviousToWinnerNumber(Card card, Stich lastStich) {
		// hat selbe Number wie der Winner
		if(card.number == lastStich.getWinner().number) return true;
		// alle Fälle, falls mit dem Guaten gespielt wird
		if(mitGuatem && lastStich.getWinner().number != CONSTANTS.SECHSER) { 
			if(card.number == lastStich.getWinner().calcRechterFromGuater().number)
				return true;
		} 
		return false;
	}
	 
	private void correctPreviousLeftOut(ArrayList<Stich> stiche) {
		for(int stichNumber = 0; stichNumber < stiche.size(); stichNumber++) {
			// winner ist sicher kein Schlag, Rechter oder Guater
			if((schlag.get(stiche.get(stichNumber).getWinner().number) == CONSTANTS.IMPOSSIBLE) &&
			   (trumpf.get(stiche.get(stichNumber).getWinner().color) == CONSTANTS.IMPOSSIBLE))  
						for(Card card : stiche.get(stichNumber).cards) schlag.setValueAt(card.number,CONSTANTS.IMPOSSIBLE);
		}
	}
}
