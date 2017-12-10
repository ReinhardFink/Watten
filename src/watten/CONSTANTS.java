package watten;

public class CONSTANTS {
	
	public final static int ANZAHL_FARBEN = 4;
	public final static int EICHEL = 0;
	public final static int HERZ = 1;
	public final static int LAUB = 2;
	public final static int SCHELL = 3;
	
	public final static String[] colorNames = {"Eichel","Herz","Laub","Schell"};
	
	public final static int ANZAHL_SCHLAEGE = 9;
	public final static int SECHSER = 0;
	public final static int SIEBNER = SECHSER + 1;
	public final static int ACHTER = SECHSER + 2;
	public final static int NEUNER = SECHSER + 3;
	public final static int ZEHNER = SECHSER + 4;
	public final static int UNTER = SECHSER + 5;
	public final static int OBER = SECHSER + 6;
	public final static int KOENIG = SECHSER + 7;
	public final static int AS = SECHSER + 8;
	
	public final static String[] numberNames = {"Sechser","Siebner",
												"Achter","Neuner",
												"Zehner","Unter",
												"Ober","König","As"};
	
	public final static int IMPOSSIBLE = -1;
	public final static int POSSIBLE = 0;
	public final static int SURE = 1;
	
	public final static int CARDS_IN_STICH = 4;
	public final static int NUMBER_OF_STICHE = 5;
	
	public final static String QUOTE_STRING = ",";
	
	public final static Card VELI = new Card(SCHELL,SECHSER);
	
	// Error Messages
	public final static String ERROR_Could_Not_Create_Card1 ="Karte \"";
	public final static String ERROR_Could_Not_Create_Card2 ="\" konnte nicht erzeugt werden!\n";
	public final static String ERROR_Could_Not_Create_Stich ="Stich konnte nicht erzeugt werden!\n";
	public final static String ERROR_Wrong_WinnerNumber = "Die Nummer des Siegers muss 1,2,3 oder 4 sein!\n";
	public final static String ERROR_Game_Status_Unchangeable = "\n Spiel Status +/- Guater\nkann nur zu Beginn veraendert werden!\n";
	
	// Messages
	public final static String messageHeader = "Stich Nr: ";
	public final static String messageFooter = "\n";
	public final static String messageIMPOSSIBLE = "unmoeglich!\n";
	public final static String messagePOSSIBLE = "moeglich!\n";
	public final static String messageSURE = "sicher!\n";
	
	public final static String messageFarbStich =  "Farbstich:\t\t";
	public final static String messageTrumpfStich = "Trumpfstich:\t\t";
	public final static String messageLinkerStich = "Stich mit Linkem:\t";
	public final static String messageRechterStich = "Stich mit Rechtem:\t";
	public final static String messageGuaterStich = "Stich mit Guatem:\t";
	
	public final static String messageColor ="Farbe";
	public final static String messageNumber ="Schlag";
	
	// Labels
	public final static String labelProgramHeader = ">> Schlag & Trumpf <<";
	public final static String labelGameWithGuatem = "Spiel mit Guatem:";
	public final static String labelNewGame ="Neues Spiel !";
	
	public final static String labelColor = "Farbe =>";
	public final static String labelNumber = "Schlag =>";
	
	public final static String[] labelColors = {"Eichel","Herz","Laub","Schell"};
	public final static String[] labelNumbers = {"6","7","8","9","10","U","O","K","A"};
}
