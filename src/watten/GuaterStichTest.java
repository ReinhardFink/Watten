package watten;

import java.util.ArrayList;

public class GuaterStichTest extends BasisStichTest {
 
	public GuaterStichTest(Result result, ArrayList<Stich> stiche) {
		super(result,stiche);
	}
	
	protected void defineTest() {
		setKeyCard(getStiche().get(getStiche().size() - 1).getWinner().calcRechterFromGuater());
		setWinnerNewNumberResult(CONSTANTS.SURE);
		setWinnerNewColorResult(CONSTANTS.SURE);
	}
	
	protected int setTestSpezials() { 
		if(getResult().isMitGuatem()) return CONSTANTS.POSSIBLE;
		else return CONSTANTS.IMPOSSIBLE;
	}
}
