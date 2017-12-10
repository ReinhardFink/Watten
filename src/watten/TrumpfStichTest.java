package watten;

import java.util.ArrayList;


public class TrumpfStichTest extends BasisStichTest {
 
	public TrumpfStichTest(Result result, ArrayList<Stich> stiche) {
		super(result,stiche);
	}
	
	protected boolean blockVeli() { return false; }
	
	protected void defineTest() {
		setKeyCard(getStiche().get(getStiche().size() - 1).getWinner());
		setWinnerNewNumberResult(CONSTANTS.IMPOSSIBLE);
		// s6, e7, h7, s7, 0 wird wegen s7 sclägt s6 nicht als Trumpfstich anerkannt! 
		// Für den Veli wird kein Trumpf gesetzt!
		if(!getStiche().get(getStiche().size() - 1).getWinner().equals(CONSTANTS.VELI))
			setWinnerNewColorResult(CONSTANTS.SURE);
	}
}
