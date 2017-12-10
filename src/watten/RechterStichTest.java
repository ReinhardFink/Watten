package watten;

import java.util.ArrayList;

public class RechterStichTest extends BasisStichTest {
 
	public RechterStichTest(Result result, ArrayList<Stich> stiche) {
		super(result,stiche);
	}
	
	protected boolean blockVeli() { 
		return false;
	}
	
	protected void defineTest() {
		setKeyCard(getStiche().get(getStiche().size() - 1).getWinner());
		setWinnerNewNumberResult(CONSTANTS.SURE);
		setWinnerNewColorResult(CONSTANTS.SURE);
	}
}
