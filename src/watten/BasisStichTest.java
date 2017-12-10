package watten;

import java.util.ArrayList;

public class BasisStichTest implements StichTestInterface {
	
	private final boolean VERBOSE = false;
	
	private Result result;
	private ArrayList<Stich> stiche;
	private Card keyCard;
	private int winnerNewNumberResult; 
	private int winnerNewColorResult;
	// Konstruktor
	public BasisStichTest(Result result, ArrayList<Stich> stiche) {
		try {
			this.result = result.clone();
		} 
		catch(CloneNotSupportedException e) {
			System.out.println("Result konnte nicht geklont werden!"); 
		}	
		this.stiche = stiche;
	}
	// Setters und Getters
	public Result getResult() { return result; }
	
	public ArrayList<Stich> getStiche() { return stiche; }
	
	public void setKeyCard(Card keyCard) { this.keyCard = keyCard; }
	
	public void setWinnerNewNumberResult(int winnerNewNumberResult) {
		this.winnerNewNumberResult = winnerNewNumberResult;
	}
		
	public void setWinnerNewColorResult(int winnerNewColorResult) { 
		this.winnerNewColorResult = winnerNewColorResult;
	}
	// runTest die Strategiemethode
	public int runTest() {
		if(blockVeli()) return CONSTANTS.IMPOSSIBLE;
		defineTest();
		if(result.isRechterFix()) return makeTestsWithRechterFix();
		if(setUpResult() != CONSTANTS.POSSIBLE) return setUpResult();
		if(setTestSpezials() != CONSTANTS.POSSIBLE) return setTestSpezials();
		if(testPreviousWinners() != CONSTANTS.POSSIBLE) return testPreviousWinners();
		return CONSTANTS.POSSIBLE;
	}
	
// Strategiemethoden, die zum Teil in den Unterklassen überschrieben werden müssen	
	protected boolean blockVeli() { 
		if(getStiche().get(getStiche().size() - 1).getWinner().equals(CONSTANTS.VELI)) return true;
		else return false;
	}
	protected void defineTest() { }
	protected int setTestSpezials() { return CONSTANTS.POSSIBLE; }
	
//	 private Strategiemethoden
	private int makeTestsWithRechterFix() { // behandelt alle Tests, wenn der Rechte fix ist.
		if(isGuaterInTrumpfStichTest()) return CONSTANTS.IMPOSSIBLE; // Spezialfall ausschliessen
		if(winnerCardFitsStichTest()) return CONSTANTS.SURE; // alle anderen Fälle
		else return CONSTANTS.IMPOSSIBLE;
	}
	
	private boolean isGuaterInTrumpfStichTest() {
		return winnerNewNumberResult == CONSTANTS.IMPOSSIBLE && 
			   winnerNewColorResult == CONSTANTS.SURE && 
			   result.isGuaterFix() &&
			   result.getGuater().equals(keyCard);
	}
	private boolean winnerCardFitsStichTest() { // Farbe, Trump
		return ((result.getNumberAt(keyCard.number) == winnerNewNumberResult) && 
		        (result.getColorAt(keyCard.color) == winnerNewColorResult));
	}
	
	private int setUpResult() {
		if(hasStichConfictWithResult()) return CONSTANTS.IMPOSSIBLE;
		else {
			result.setNumberAt(keyCard.number,winnerNewNumberResult);
			result.setColorAt(keyCard.color,winnerNewColorResult);
			return CONSTANTS.POSSIBLE;
		}
	}
	
	private boolean hasStichConfictWithResult() {
		return (result.getNumberAt(keyCard.number) == -winnerNewNumberResult) 
				|| 
			   ((winnerNewColorResult != 0) && (result.getColorAt(keyCard.color) == -winnerNewColorResult));
	}

	private int testPreviousWinners() {
		for(int stichNumber = 0; stichNumber < stiche.size(); stichNumber++) {
			if(findWinner(stichNumber) != stiche.get(stichNumber).winner) 
				if(!correctWinner(stichNumber)) 
					return CONSTANTS.IMPOSSIBLE;
		}
		return CONSTANTS.POSSIBLE;
	}
	// private Testmethoden
	// public fürs Testen
	public int findWinner(int stichNumber) {
		int currentWinnerPosition = 0;
		for(int cardNumber = 1; cardNumber < stiche.get(stichNumber).cards.length; cardNumber++) 
			if(!(new CardRelation(stiche.get(stichNumber).cards[currentWinnerPosition],result).beats(new CardRelation(stiche.get(stichNumber).cards[cardNumber],result)))) 
				currentWinnerPosition = cardNumber;
		return currentWinnerPosition;
	}

	private boolean correctWinner(int stichNumber) { 
		if((correctColor(stichNumber) == CONSTANTS.IMPOSSIBLE) &&
		   (correctNumber(stichNumber) == CONSTANTS.IMPOSSIBLE) &&
		   (correctToGuater(stichNumber) == CONSTANTS.IMPOSSIBLE))
		{
			if(VERBOSE) System.out.println("Stich Nr.: " + stichNumber + " weder Trumpf noch Schlag könnten erhöht werden!");
			return false;
		}
		return true;
	}
	
	private int correctColor(int stichNumber) {
		if(VERBOSE) System.out.println("entering correctColor in Stichnumber : " + stichNumber);
		int testValueColorUp = CONSTANTS.POSSIBLE;
		try {
			if(result.getColorAt(stiche.get(stichNumber).getWinner().color) == CONSTANTS.POSSIBLE) {
				Result saveResult = result.clone();
				result.setColorAt(stiche.get(stichNumber).getWinner().color,CONSTANTS.SURE);
				testValueColorUp = testPreviousWinners();
				result = saveResult;
				if(VERBOSE) System.out.println("Stich Nr.: " + stichNumber + " corrected Color: " + stiche.get(stichNumber).getWinner().color);
			} 
			else testValueColorUp = CONSTANTS.IMPOSSIBLE;
		} 
		catch(CloneNotSupportedException e) {
			if(VERBOSE) System.out.println("Result konnte nicht geklont werden!");
		}
		return testValueColorUp;
	}
	
	private int correctNumber(int stichNumber) {
		if(VERBOSE) System.out.println("entering correctNumber in Stichnumber : " + stichNumber);
		int testValueNumberUp = CONSTANTS.POSSIBLE;
		try {
			if(result.getNumberAt(stiche.get(stichNumber).getWinner().number) == CONSTANTS.POSSIBLE) {
				Result saveResult = result.clone();
				result.setNumberAt(stiche.get(stichNumber).getWinner().number,CONSTANTS.SURE);
				testValueNumberUp = testPreviousWinners();
				result = saveResult;
				if(VERBOSE) System.out.println("Stich Nr.: " + stichNumber + " corrected Number: " + stiche.get(stichNumber).getWinner().number);
			}
			else testValueNumberUp = CONSTANTS.IMPOSSIBLE;
		} 
		catch(CloneNotSupportedException e) {
			System.out.println("Result konnte nicht geklont werden!");
		}
		return testValueNumberUp;
	}
	
	private int correctToGuater(int stichNumber) {
		if(VERBOSE) System.out.println("entering correctToGuater in Stichnumber : " + stichNumber);
		if(!result.isMitGuatem()) return CONSTANTS.IMPOSSIBLE;
		int testGuaterUp = CONSTANTS.POSSIBLE;
		try {
			if(isGuaterUpgradePossible(stichNumber)) {
				Result saveResult = result.clone();
				result.setColorAt(stiche.get(stichNumber).getWinner().color,CONSTANTS.SURE);
				result.setNumberAt(stiche.get(stichNumber).getWinner().calcRechterFromGuater().number,CONSTANTS.SURE);
				testGuaterUp = testPreviousWinners();
				result = saveResult;
				if(VERBOSE) {
					System.out.println("Stich Nr.: " + stichNumber); 
					System.out.println("corrected Color: " + stiche.get(stichNumber).getWinner().color);
					System.out.println("corrected Number: " + stiche.get(stichNumber).getWinner().calcRechterFromGuater().number);
				}
			}
			else testGuaterUp = CONSTANTS.IMPOSSIBLE;
		}
		catch(CloneNotSupportedException e) {
			System.out.println("Result konnte nicht geklont werden!");
		}
		return testGuaterUp;
	}
	
	private boolean isGuaterUpgradePossible(int stichNumber) {
		// keyCard selbst darf nicht verändert werden, da sie ja den Testfall definiert!
		if(isKeyCardWinnerCardForUpgrade(stichNumber)) return false;
		if(isNotColorAndNumberBlocked(stichNumber) && isColorOrNumberPossible(stichNumber))
			return true;
		else return false;
	}
	
	private boolean isKeyCardWinnerCardForUpgrade(int stichNumber) {
		return ((keyCard.color == stiche.get(stichNumber).getWinner().color) && 
				   (keyCard.number == stiche.get(stichNumber).getWinner().number));
	}
	
	private boolean isNotColorAndNumberBlocked(int stichNumber) {
		// in Result: (¬-1 && ¬-1) &&  (0 || 0)
		return ((result.getColorAt(stiche.get(stichNumber).getWinner().color) != CONSTANTS.IMPOSSIBLE) &&
				(result.getNumberAt(stiche.get(stichNumber).getWinner().calcRechterFromGuater().number) != CONSTANTS.IMPOSSIBLE));
	}
	
	private boolean isColorOrNumberPossible(int stichNumber) {
		return ((result.getColorAt(stiche.get(stichNumber).getWinner().color) == CONSTANTS.POSSIBLE) ||
				(result.getNumberAt(stiche.get(stichNumber).getWinner().calcRechterFromGuater().number) == CONSTANTS.POSSIBLE));
	}
	
}
