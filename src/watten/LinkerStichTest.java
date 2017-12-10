package watten;

import java.util.ArrayList;

public class LinkerStichTest extends BasisStichTest {
 
	public LinkerStichTest(Result result, ArrayList<Stich> stiche) {
		super(result,stiche);
	}
	
	protected void defineTest() {
		setKeyCard(getStiche().get(getStiche().size() - 1).getWinner());
		setWinnerNewNumberResult(CONSTANTS.SURE);
		setWinnerNewColorResult(CONSTANTS.IMPOSSIBLE);
	}
}
