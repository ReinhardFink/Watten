package watten;

public class CardFactory {
	
	private Card[][] createdCards;
	private StringBuffer errorMessage;
	
	public CardFactory(StringBuffer errorMessage) {
		this.errorMessage = errorMessage;
		createdCards = new Card[CONSTANTS.ANZAHL_FARBEN][];
		//for(Card[] colors : createdCards) colors = new Card[CONSTANTS.ANZAHL_SCHLAEGE];
		for(int i = 0; i < createdCards.length; i++) createdCards[i] = new Card[CONSTANTS.ANZAHL_SCHLAEGE];
		createdCards[CONSTANTS.HERZ][CONSTANTS.SECHSER] = new Card(CONSTANTS.HERZ,CONSTANTS.SECHSER);
		createdCards[CONSTANTS.EICHEL][CONSTANTS.SECHSER] = new Card(CONSTANTS.EICHEL,CONSTANTS.SECHSER);
		createdCards[CONSTANTS.LAUB][CONSTANTS.SECHSER] = new Card(CONSTANTS.LAUB,CONSTANTS.SECHSER);
	}
	
	public Card createCard(String cardShortCut) {
		if(cardShortCut == null)  return null;
		int tokenReadCount = 0;
		char[] tokenArray = cardShortCut.toLowerCase().toCharArray();
		int number = CONSTANTS.IMPOSSIBLE;
		int color = CONSTANTS.IMPOSSIBLE;
		while(tokenReadCount < tokenArray.length) {
			char token = tokenArray[tokenReadCount];
			switch (token) {
				// setUp Color
				case 'e': color = CONSTANTS.EICHEL; break;
				case 'h': color = CONSTANTS.HERZ; break;
				case 'l': color = CONSTANTS.LAUB; break;
				case 's': color = CONSTANTS.SCHELL; break;
				// setUp Number
				case '0':  break;
				case '6': number = CONSTANTS.SECHSER; break;
				case '7': number = CONSTANTS.SIEBNER; break;
				case '8': number = CONSTANTS.ACHTER; break;
				case '9': number = CONSTANTS.NEUNER; break;
				case '1': number = CONSTANTS.ZEHNER; break;
				case 'u': number = CONSTANTS.UNTER; break;
				case 'o': number = CONSTANTS.OBER; break;
				case 'k': number = CONSTANTS.KOENIG; break;
				case 'a': number = CONSTANTS.AS; break;
		
				default: System.out.println("Oje hierher sollten wir nicht kommen"); break;
			}
			tokenReadCount++;
		}
		if(isCardCreatable(color, number)) 
			return (createdCards[color][number] = new Card(color,number));
		else {
			errorMessage.append(CONSTANTS.ERROR_Could_Not_Create_Card1);
			errorMessage.append(cardShortCut);
			errorMessage.append(CONSTANTS.ERROR_Could_Not_Create_Card2);
			return null;
		}
	}
	
	private boolean isCardCreatable(int color, int number) {
		return color != CONSTANTS.IMPOSSIBLE && 
		       number != CONSTANTS.IMPOSSIBLE &&
		       createdCards[color][number] == null;
	}
	
	public void resetCard(Card card) {
		createdCards[card.color][card.number] = null;
	}
}