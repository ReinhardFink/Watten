package watten;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class StichCalculator implements Observer {
	
	private ArrayList<Stich> stiche;
	private Result result;
	
	private int farbStich;
	private int trumpfStich;
	private int linkerStich;
	private int rechterStich;
	private int guaterStich;
	private boolean resultChanged;
	
	public StichCalculator(ArrayList<Stich> stiche, Result result) {
		this.stiche = stiche;
		this.result = result;
		this.result.getSchlagPossibilityArray().addObserver(this);
		this.result.getTrumpfPossibilityArray().addObserver(this);
	}
	
	public void update(Observable observable, Object arg) {
		resultChanged = true;
	}
	
	// Zum Testen
	public int getFarbStich() { return farbStich; }
	//public void setFarbStich(int farbStich) { this.farbStich = farbStich; }
	
	public int getTrumpfStich() { return trumpfStich; }
	//public void setTrumpfStich(int trumpfStich) { this.trumpfStich = trumpfStich; }
	
	public int getLinkerStich() { return linkerStich;  }
	//public void setLinkerStich(int linkerStich) { this.linkerStich = linkerStich; }
	
	public int getRechterStich() { return rechterStich; }
	//public void setRechterStich(int rechterStich) { this.rechterStich = rechterStich;	}
	
	public int getGuaterStich() { return guaterStich; }
	//public void setGuaterStich(int guaterStich) { this.guaterStich = guaterStich; }
		
	public String getMessage() {
		calculateNewResult();
		StringBuffer message = new StringBuffer();
		message.append(CONSTANTS.messageHeader);
		message.append(stiche.size());
		message.append("\n");
		message.append(CONSTANTS.messageFarbStich);
		message.append(translate(farbStich));
		message.append(CONSTANTS.messageTrumpfStich);
		message.append(translate(trumpfStich));
		message.append(CONSTANTS.messageLinkerStich);
		message.append(translate(linkerStich));
		message.append(CONSTANTS.messageRechterStich);
		message.append(translate(rechterStich));
		if(result.isMitGuatem()) {
			message.append(CONSTANTS.messageGuaterStich);
			message.append(translate(guaterStich));
		}
		message.append(CONSTANTS.messageFooter);
		return message.toString();
	}
	
	private String translate(int posibility) {
		if(posibility == CONSTANTS.IMPOSSIBLE) return CONSTANTS.messageIMPOSSIBLE; 
		if(posibility == CONSTANTS.POSSIBLE) return CONSTANTS.messagePOSSIBLE; 
		else return CONSTANTS.messageSURE;
	}
	

	private void calculateNewResult() {
		result.calcSchlag(stiche);
		do {
			resultChanged = false;
			runTest();
			runRules();
			runExcept4Rules();
		} while(resultChanged);
		
	}

	private void runTest() {
		farbStich = new FarbStichTest(result,stiche).runTest();
		trumpfStich = new TrumpfStichTest(result,stiche).runTest();
		linkerStich = new LinkerStichTest(result,stiche).runTest();
		rechterStich = new RechterStichTest(result,stiche).runTest();
		guaterStich = new GuaterStichTest(result,stiche).runTest();
	}
	
	private void runRules() {
		if(isTrumpfImpossible()) lastWinnerIsNoTrumpf();
		if(isSchlagOrGuaterImpossible()) lastWinnerIsNoSchlag();		
	}
	
	private boolean isTrumpfImpossible() {
		return trumpfStich == CONSTANTS.IMPOSSIBLE && rechterStich == CONSTANTS.IMPOSSIBLE && guaterStich == CONSTANTS.IMPOSSIBLE;
	}
	
	private boolean isSchlagOrGuaterImpossible() {
		return linkerStich == CONSTANTS.IMPOSSIBLE && rechterStich == CONSTANTS.IMPOSSIBLE && guaterStich == CONSTANTS.IMPOSSIBLE;
	}
	
	private void lastWinnerIsNoTrumpf() {
		result.setColorAt(stiche.get(stiche.size()-1).getWinner().color,CONSTANTS.IMPOSSIBLE);
	}
	
	private void lastWinnerIsNoSchlag() {
		result.setNumberAt(stiche.get(stiche.size()-1).getWinner().number,CONSTANTS.IMPOSSIBLE);
	}
	
	private void runExcept4Rules() {
		if(justGuaterStichPossible()) lastWinnerIsGuater();
		if(justRechterStichPossible()) lastWinnerIsRechter();
		if(justLinkerStichPossible()) lastWinnerIsLinker();
		if(justTrumpfStichPossible()) lastWinnerIsTrumpf();
		if(justFarbStichPossible()) lastWinnerIsFarbe();	
	}
	// logical Tests
	private boolean justGuaterStichPossible() {
		return (farbStich == CONSTANTS.IMPOSSIBLE && trumpfStich == CONSTANTS.IMPOSSIBLE && linkerStich == CONSTANTS.IMPOSSIBLE && rechterStich == CONSTANTS.IMPOSSIBLE);
	}
	
	private boolean justRechterStichPossible() {
		return (farbStich == CONSTANTS.IMPOSSIBLE && trumpfStich == CONSTANTS.IMPOSSIBLE && linkerStich == CONSTANTS.IMPOSSIBLE && guaterStich == CONSTANTS.IMPOSSIBLE);
	}
	
	private boolean justLinkerStichPossible() {
		return (farbStich == CONSTANTS.IMPOSSIBLE && trumpfStich == CONSTANTS.IMPOSSIBLE && rechterStich == CONSTANTS.IMPOSSIBLE && guaterStich == CONSTANTS.IMPOSSIBLE);
	}
	
	private boolean justTrumpfStichPossible() {
		return (farbStich == CONSTANTS.IMPOSSIBLE && linkerStich == CONSTANTS.IMPOSSIBLE && rechterStich == CONSTANTS.IMPOSSIBLE && guaterStich == CONSTANTS.IMPOSSIBLE );
	}
	
	private boolean justFarbStichPossible() {
		return (trumpfStich == CONSTANTS.IMPOSSIBLE && linkerStich == CONSTANTS.IMPOSSIBLE && rechterStich == CONSTANTS.IMPOSSIBLE && guaterStich == CONSTANTS.IMPOSSIBLE );
	}
	
	// set Values in Result
	private void lastWinnerIsGuater() {
		result.setNumberAt(stiche.get(stiche.size()-1).getWinner().calcRechterFromGuater().number,CONSTANTS.SURE);
		result.setColorAt(stiche.get(stiche.size()-1).getWinner().color,CONSTANTS.SURE);
	}
	
	private void lastWinnerIsRechter() {
		result.setNumberAt(stiche.get(stiche.size()-1).getWinner().number,CONSTANTS.SURE);
		result.setColorAt(stiche.get(stiche.size()-1).getWinner().color,CONSTANTS.SURE);
	}
	
	private void lastWinnerIsLinker() {
		result.setNumberAt(stiche.get(stiche.size()-1).getWinner().number,CONSTANTS.SURE);
		result.setColorAt(stiche.get(stiche.size()-1).getWinner().color,CONSTANTS.IMPOSSIBLE);
	}
	
	private void lastWinnerIsTrumpf() {
		result.setNumberAt(stiche.get(stiche.size()-1).getWinner().number,CONSTANTS.IMPOSSIBLE);
		result.setColorAt(stiche.get(stiche.size()-1).getWinner().color,CONSTANTS.SURE);
	}
	
	private void lastWinnerIsFarbe() {
		result.setNumberAt(stiche.get(stiche.size()-1).getWinner().number,CONSTANTS.IMPOSSIBLE);
		result.setColorAt(stiche.get(stiche.size()-1).getWinner().color,CONSTANTS.IMPOSSIBLE);
	}
}

