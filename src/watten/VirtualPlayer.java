package watten;

import java.util.ArrayList;
 
public class VirtualPlayer {
	
	private ArrayList<Stich> stiche;
	private Result result;
	private StichFactory stichFactory;
	private StichCalculator stichCalculator;
	private String message;
	private StringBuffer errorMessage;

	public VirtualPlayer(boolean mitGuatem) {
		this.errorMessage = new StringBuffer();
		this.stiche = new ArrayList<Stich>(CONSTANTS.NUMBER_OF_STICHE);
		this.result = new Result(mitGuatem);
		this.stichFactory = new StichFactory(errorMessage);
		this.stichCalculator = new StichCalculator(stiche, result);
		this.message = "";
	}
	
	public Result getResult() { return result; }
	
	public String getMessage() { return message.toString(); }
	
	public String getErrorMessage() { return errorMessage.toString(); }
	
	public boolean isAddStichPossible(String stichString) {
		Stich stich = stichFactory.createStich(stichString);
		if(stich == null) return false;
		stiche.add(stich);
		message = stichCalculator.getMessage();
		return true;
	}
}
