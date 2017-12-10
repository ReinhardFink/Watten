package watten;

import java.util.ArrayList;

public class FarbStichTest extends BasisStichTest {
	
	public FarbStichTest(Result result, ArrayList<Stich> stiche) {
		super(result,stiche);
	}
	
	protected void defineTest() {
		setKeyCard(getStiche().get(getStiche().size() - 1).getWinner());
		setWinnerNewNumberResult(CONSTANTS.IMPOSSIBLE);
		setWinnerNewColorResult(CONSTANTS.IMPOSSIBLE);
	}
	
	public int setTestSpezials() { return setAllColorsInLastStichToImpossible(); }
	
	// wird benötigt um den Fall 4 Farben im Stich z.B. e7, h7, l7, s8, 0 abzudecken
	// eine Farbe muss dann Trumpf sein!!
	private int setAllColorsInLastStichToImpossible() {
		for(int position = 0; position < getStiche().get(getStiche().size()-1).cards.length; position++) {
			if(getResult().getColorAt(getStiche().get(getStiche().size()-1).cards[position].color) == CONSTANTS.SURE) 
				return CONSTANTS.IMPOSSIBLE; 
			else 
				getResult().setColorAt(getStiche().get(getStiche().size()-1).cards[position].color,CONSTANTS.IMPOSSIBLE);
		}
		return CONSTANTS.POSSIBLE;
	}
}
