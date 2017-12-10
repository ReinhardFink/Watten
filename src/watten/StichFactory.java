package watten;

import java.util.StringTokenizer;

public class StichFactory {
	
	private CardFactory cardFactory;
	private StringBuffer errorMessage;

	public StichFactory(StringBuffer errorMessage) {
		this.errorMessage = errorMessage;
		this.cardFactory = new CardFactory(errorMessage);
	}
	
	public Stich createStich(String inputString) {
		int createdCardNumber = 0;
		Card[] cardArray = new Card[CONSTANTS.CARDS_IN_STICH];
		int winnerNumber = -1;
		StringTokenizer stringTokenizer = new StringTokenizer(inputString,CONSTANTS.QUOTE_STRING);
			while(createdCardNumber < cardArray.length) {
				cardArray[createdCardNumber] = cardFactory.createCard(stringTokenizer.nextToken());
				if(cardArray[createdCardNumber] == null) {
					errorMessage.append(CONSTANTS.ERROR_Could_Not_Create_Stich);
					for(int i = 0; i < createdCardNumber; i++) cardFactory.resetCard(cardArray[i]);
					return null;
				}
				createdCardNumber++;
			}
			try {
				winnerNumber = new Integer(stringTokenizer.nextToken()).intValue() - 1;
			} catch(NumberFormatException e) { winnerNumber = CONSTANTS.IMPOSSIBLE; }
			if(winnerNumber < 0 || winnerNumber > 3) {
				for(int i = 0; i < cardArray.length; i++) cardFactory.resetCard(cardArray[i]);
				errorMessage.append(CONSTANTS.ERROR_Wrong_WinnerNumber);
				errorMessage.append(CONSTANTS.ERROR_Could_Not_Create_Stich);
				return null;
			}
		return  new Stich(cardArray[0],cardArray[1],cardArray[2],cardArray[3], winnerNumber);
	}
}
