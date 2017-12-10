package watten.tests;

import java.util.ArrayList;

import watten.CONSTANTS;
import watten.Card;
import watten.Result;
import watten.Stich;

import junit.framework.TestCase;

public class Test_Result extends TestCase {
	
	Card hA, hK, hO, hU, h10, h9, h8, h7;
	Card sA, sK, sO, sU, s10, s9, s8, s7, s6;
	Card eA, eK, eO, eU, e10, e9, e8, e7;
	Card lA, lK, lO, lU, l10, l9, l8, l7;
	ArrayList<Stich> stiche;
	
	public void setUp() {
		hA = new Card(CONSTANTS.HERZ, CONSTANTS.AS);
		hK = new Card(CONSTANTS.HERZ, CONSTANTS.KOENIG);
		hO = new Card(CONSTANTS.HERZ, CONSTANTS.OBER);
		hU = new Card(CONSTANTS.HERZ, CONSTANTS.UNTER);
		h10 = new Card(CONSTANTS.HERZ, CONSTANTS.ZEHNER);
		h9 = new Card(CONSTANTS.HERZ, CONSTANTS.NEUNER);
		h8 = new Card(CONSTANTS.HERZ, CONSTANTS.ACHTER);
		h7 = new Card(CONSTANTS.HERZ, CONSTANTS.SIEBNER);
		
		sA = new Card(CONSTANTS.SCHELL, CONSTANTS.AS);
		sK = new Card(CONSTANTS.SCHELL, CONSTANTS.KOENIG);
		sO = new Card(CONSTANTS.SCHELL, CONSTANTS.OBER);
		sU = new Card(CONSTANTS.SCHELL, CONSTANTS.UNTER);
		s10 = new Card(CONSTANTS.SCHELL, CONSTANTS.ZEHNER);
		s9 = new Card(CONSTANTS.SCHELL, CONSTANTS.NEUNER);
		s8 = new Card(CONSTANTS.SCHELL, CONSTANTS.ACHTER);
		s7 = new Card(CONSTANTS.SCHELL, CONSTANTS.SIEBNER);
		s6 = new Card(CONSTANTS.SCHELL, CONSTANTS.SECHSER);
		
		eA = new Card(CONSTANTS.EICHEL, CONSTANTS.AS);
		eK = new Card(CONSTANTS.EICHEL, CONSTANTS.KOENIG);
		eO = new Card(CONSTANTS.EICHEL, CONSTANTS.OBER);
		eU = new Card(CONSTANTS.EICHEL, CONSTANTS.UNTER);
		e10 = new Card(CONSTANTS.EICHEL, CONSTANTS.ZEHNER);
		e9 = new Card(CONSTANTS.EICHEL, CONSTANTS.NEUNER);
		e8 = new Card(CONSTANTS.EICHEL, CONSTANTS.ACHTER);
		e7 = new Card(CONSTANTS.EICHEL, CONSTANTS.SIEBNER);
		
		lA = new Card(CONSTANTS.LAUB, CONSTANTS.AS);
		lK = new Card(CONSTANTS.LAUB, CONSTANTS.KOENIG);
		lO = new Card(CONSTANTS.LAUB, CONSTANTS.OBER);
		lU = new Card(CONSTANTS.LAUB, CONSTANTS.UNTER);
		l10 = new Card(CONSTANTS.LAUB, CONSTANTS.ZEHNER);
		l9 = new Card(CONSTANTS.LAUB, CONSTANTS.NEUNER);
		l8 = new Card(CONSTANTS.LAUB, CONSTANTS.ACHTER);
		l7 = new Card(CONSTANTS.LAUB, CONSTANTS.SIEBNER);
		
		stiche = new ArrayList<Stich>(5);
	}
	
	public void test_clone() {
		try {
			Result result = new Result(false);
			
			result.setNumberAt(CONSTANTS.KOENIG,CONSTANTS.SURE);
			assertEquals(true,result.isSchlagFix());
			assertEquals(CONSTANTS.KOENIG,result.getSchlag());
			// klonen
			Result resultClone = result.clone();
			// hat der Clone die Daten mitbekommen?
			assertEquals(true,resultClone.isSchlagFix());
			assertEquals(CONSTANTS.KOENIG,resultClone.getSchlag());
			// resultClone wird nun verändert!
			resultClone.setNumberAt(CONSTANTS.OBER,CONSTANTS.SURE);
			// Test, ob result unverändert!
			assertEquals(true,result.isSchlagFix());
			assertEquals(CONSTANTS.KOENIG,result.getSchlag());
			
			// Test, ob resultClone verändert
			assertEquals(true,resultClone.isSchlagFix());
			assertEquals(CONSTANTS.OBER,resultClone.getSchlag());
			
		} catch(CloneNotSupportedException e) { 
			System.out.println("PossibilityArray konnte nicht geklont werden!");
		}
	}
	
	public void test_isSchlagFix_getSchlag() {
		Result result = new Result(false);
		result.setNumberAt(CONSTANTS.KOENIG,CONSTANTS.SURE);
		assertEquals(true,result.isSchlagFix());
		assertEquals(CONSTANTS.KOENIG,result.getSchlag());
		
		result = new Result(false);
		result.setNumberAt(CONSTANTS.SECHSER,CONSTANTS.IMPOSSIBLE);
		result.setNumberAt(CONSTANTS.SIEBNER,CONSTANTS.IMPOSSIBLE);
		assertEquals(false,result.isSchlagFix());
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getSchlag());
		
		result.setNumberAt(CONSTANTS.SECHSER,CONSTANTS.SURE);
		assertEquals(true,result.isSchlagFix());
		assertEquals(CONSTANTS.SECHSER,result.getSchlag());
		
		result.setNumberAt(CONSTANTS.SECHSER,CONSTANTS.IMPOSSIBLE);
		result.setNumberAt(CONSTANTS.AS,CONSTANTS.SURE);
		assertEquals(true,result.isSchlagFix());
		assertEquals(CONSTANTS.AS,result.getSchlag());
	}
	
	public void test_isTrumpfFix_getTrumpf() {
		Result result = new Result(false);
	
		assertEquals(false,result.isTrumpfFix());
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getTrumpf());
		
		result.setColorAt(CONSTANTS.EICHEL,CONSTANTS.IMPOSSIBLE);
		result.setColorAt(CONSTANTS.HERZ,CONSTANTS.IMPOSSIBLE);
		assertEquals(false,result.isTrumpfFix());
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getTrumpf());
		
		result.setColorAt(CONSTANTS.EICHEL,CONSTANTS.SURE);
		assertEquals(true,result.isTrumpfFix());
		assertEquals(CONSTANTS.EICHEL,result.getTrumpf());
		
		result.setColorAt(CONSTANTS.SCHELL,CONSTANTS.SURE);
		assertEquals(true,result.isTrumpfFix());
		assertEquals(CONSTANTS.SCHELL,result.getTrumpf());
	}
	
	public void test_isRechterFix() {
		Result result = new Result(false);
		
		assertEquals(false,result.isRechterFix());
		
		result.setColorAt(CONSTANTS.EICHEL,CONSTANTS.SURE);
		assertEquals(false,result.isRechterFix());
		
		result.setNumberAt(CONSTANTS.AS,CONSTANTS.SURE);
		assertEquals(true,result.isRechterFix());
	}
	
	public void test_getRechter() {
		Result result = new Result(false);
		
		result.setColorAt(CONSTANTS.EICHEL,CONSTANTS.SURE);
		result.setNumberAt(CONSTANTS.AS,CONSTANTS.SURE);
		Card rechter = new Card(CONSTANTS.EICHEL,CONSTANTS.AS);
		assertEquals(true,result.getRechter().equals(rechter));
		
		result.setColorAt(CONSTANTS.SCHELL,CONSTANTS.SURE);
		result.setNumberAt(CONSTANTS.SECHSER,CONSTANTS.SURE);
		rechter = new Card(CONSTANTS.SCHELL,CONSTANTS.SECHSER);
		assertEquals(true,result.getRechter().equals(rechter));
	}
	
	public void test_getGuater() {
		Result result = new Result(false);
		
		result.setColorAt(CONSTANTS.EICHEL,CONSTANTS.SURE);
		result.setNumberAt(CONSTANTS.AS,CONSTANTS.SURE);
		Card guater = new Card(CONSTANTS.EICHEL,CONSTANTS.SIEBNER);
		assertEquals(true,result.getGuater().equals(guater));
		
		result.setColorAt(CONSTANTS.SCHELL,CONSTANTS.SURE);
		result.setNumberAt(CONSTANTS.AS,CONSTANTS.SURE);
		guater = new Card(CONSTANTS.SCHELL,CONSTANTS.SIEBNER);
		assertEquals(true,result.getGuater().equals(guater));
	}
	
	public void test_calcSchlag_without_Guater() {
		
		Result result = new Result(false);
		stiche.clear();
		// Rechter eK
		stiche.add(new Stich(hK,sU,sA,h9,0));	//6~,7~,8~,9-,10~,U-,O~,K~,A-
		result.calcSchlag(stiche);
		assertEquals(false,result.isSchlagFix());
		stiche.add(new Stich(lO,s6,e8,e10,3));	//6-,7~,8-,9-,10~,U-,O-,K~,A-
		result.calcSchlag(stiche);
		assertEquals(false,result.isSchlagFix());
		stiche.add(new Stich(l7,s10,eA,eK,3));	//6-,7-,8-,9-,10-,U-,O-,K~,A-
		result.calcSchlag(stiche);
		assertEquals(true,result.isSchlagFix());
		
		result = new Result(false);
		stiche.clear();
		// Rechter eK
		stiche.add(new Stich(hO,sU,sA,h9,0));	//6~,7~,8~,9-,10~,U-,O~,K~,A-
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.UNTER));
		assertEquals(false,result.isSchlagFix());
	}
	
	public void test_calcSchlag_with_Guater_SingleTests() {
		Result result = new Result(true);
		stiche.clear();
		// Rechter eK
		stiche.add(new Stich(eO,sU,sA,h9,0));	//6~,7~,8~,9-,10~,U~,O~,K~,A-
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.SECHSER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.SIEBNER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.ACHTER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.NEUNER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.ZEHNER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.UNTER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.OBER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.KOENIG));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.AS));
		assertEquals(false,result.isSchlagFix());
		//---------------------------------------
		result = new Result(true);
		stiche.clear();
		// Guater l7 Rechter lA
		stiche.add(new Stich(l7,s6,e8,e10,0));	//6-,7~,8-,9~,10-,U~,O~,K~,A~
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.SECHSER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.SIEBNER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.ACHTER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.NEUNER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.ZEHNER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.UNTER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.OBER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.KOENIG));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.AS));
		//---------------------------------------
		result = new Result(true);
		stiche.clear();
		// Guater l7 Rechter lA
		stiche.add(new Stich(l7,s6,e8,eA,0));	//6-,7~,8-,9~,10~,U~,O~,K~,A~
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.SECHSER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.SIEBNER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.ACHTER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.NEUNER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.ZEHNER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.UNTER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.OBER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.KOENIG));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.AS));
		//---------------------------------------
		result = new Result(true);
		result.setColorAt(CONSTANTS.SCHELL,CONSTANTS.POSSIBLE);
		stiche.clear();
		//  Rechter s7
		stiche.add(new Stich(s7,s6,e8,eA,0));	//6-,7~,8-,9~,10~,U~,O~,K~,A~
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.SECHSER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.SIEBNER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.ACHTER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.NEUNER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.ZEHNER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.UNTER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.OBER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.KOENIG));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.AS));
		//---------------------------------------
		result = new Result(true);
		result.setColorAt(CONSTANTS.SCHELL,CONSTANTS.POSSIBLE);
		stiche.clear();
		// Rechter s7
		stiche.add(new Stich(s7,h7,e8,eA,0));	//6-,7~,8-,9~,10~,U~,O~,K~,A~
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.SECHSER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.SIEBNER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.ACHTER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.NEUNER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.ZEHNER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.UNTER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.OBER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.KOENIG));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.AS));
		
		stiche.clear();
		stiche.add(new Stich(s7,s8,s9,s10,0));	//6~,7~,8-,9-,10-,U~,O~,K~,A~
		result = new Result(true);
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.SECHSER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.SIEBNER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.ACHTER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.NEUNER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.ZEHNER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.UNTER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.OBER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.KOENIG));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.AS));
	}
	
	public void test_calcSchlag_with_Guater_SerienTests() {
		Result result = new Result(true);
		stiche.clear();
		// Rechter eK
		stiche.add(new Stich(eO,sU,sA,h9,0));	//6~,7~,8~,9-,10~,U~,O~,K~,A-
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.UNTER));
		assertEquals(false,result.isSchlagFix());
		stiche.add(new Stich(lO,s6,e8,e10,3));	//6-,7~,8-,9-,10~,U~,O-,K~,A-
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.SECHSER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.SIEBNER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.ACHTER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.NEUNER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.ZEHNER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.UNTER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.OBER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.KOENIG));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.AS));
		assertEquals(false,result.isSchlagFix());
		stiche.add(new Stich(l7,s10,eU,eK,3));	//6-,7-,8-,9-,10-,U~,O-,K+,A-
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.SECHSER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.SIEBNER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.ACHTER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.NEUNER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.ZEHNER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.UNTER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.OBER));
		assertEquals(CONSTANTS.SURE,result.getNumberAt(CONSTANTS.KOENIG));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.AS));
		assertEquals(true,result.isSchlagFix());
		//---------------------------------------
		// neues Spiel
		result = new Result(true);
		stiche.clear();
		// Guater l7 Rechter lA
		stiche.add(new Stich(l7,sU,hA,h9,0));	//6~,7~,8~,9-,10~,U-,O~,K~,A~
		result.calcSchlag(stiche);
		// As bleibt durch 7 geschützt
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.AS));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.SECHSER));
		assertEquals(false,result.isSchlagFix());
		
		stiche.add(new Stich(h8,s6,eO,e9,1));	//6~,7~,8-,9-,10~,U-,O-,K~,A~
		result.calcSchlag(stiche);
		assertEquals(false,result.isSchlagFix());
		
		stiche.add(new Stich(s8,s10,sA,lK,2));	//6~,7~,8-,9-,10-,U-,O-,K~,A~
		result.calcSchlag(stiche);
		assertEquals(false,result.isSchlagFix());
		
		stiche.add(new Stich(lO,l10,eO,eK,0));	//6~,7~,8-,9-,10-,U-,O-,K-,A~
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.SECHSER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.SIEBNER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.ACHTER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.NEUNER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.ZEHNER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.UNTER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.OBER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.KOENIG));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.AS));
		assertEquals(false,result.isSchlagFix());
		
		stiche.add(new Stich(eA,h10,e7,eK,0));	//6~,7-,8-,9-,10-,U-,O-,K-,A~
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.SECHSER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.SIEBNER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.ACHTER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.NEUNER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.ZEHNER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.UNTER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.OBER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.KOENIG));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.AS));
		assertEquals(false,result.isSchlagFix());
		
		//---------------------------------------
		// neues Spiel
		result = new Result(true);
		stiche.clear();
		// Guater l7 Rechter lA
		stiche.add(new Stich(eU,sO,e7,eK,3));	//6~,7-,8~,9~,10~,U-,O~,K~,A~
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.SECHSER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.SIEBNER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.ACHTER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.NEUNER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.ZEHNER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.UNTER));
		// Ober wird von König geschützt		
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.OBER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.KOENIG));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.AS));
		assertEquals(false,result.isSchlagFix());
		
		stiche.add(new Stich(lK,s6,eA,h8,2));	//6-,7-,8-,9~,10~,U-,O~,K~,A~
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.SECHSER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.SIEBNER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.ACHTER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.NEUNER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.ZEHNER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.UNTER));
		// Ober wird von König beschützt, der wiederum von As beschützt wird!
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.OBER));
		// König wird von As beschütz
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.KOENIG));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.AS));
		assertEquals(false,result.isSchlagFix());
		
		stiche.add(new Stich(s9,h10,hK,l7,3));	//6-,7-,8-,9-,10-,U-,O-,K-,A+
		result.setColorAt(CONSTANTS.LAUB,CONSTANTS.SURE);
		result.calcSchlag(stiche);
		
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.SECHSER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.SIEBNER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.ACHTER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.NEUNER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.ZEHNER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.UNTER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.OBER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.KOENIG));
		assertEquals(CONSTANTS.SURE,result.getNumberAt(CONSTANTS.AS));
		assertEquals(true,result.isSchlagFix());
		
		//---------------------------------------
		// neues Spiel
		result = new Result(true);
		stiche.clear();
		// Guater lK Rechter lO
		stiche.add(new Stich(eK,lO,e7,lK,3));	//6~,7-,8~,9~,10~,U~,O~,K~,A~
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.SECHSER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.SIEBNER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.ACHTER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.NEUNER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.ZEHNER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.UNTER));	
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.OBER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.KOENIG));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.AS));
		assertEquals(false,result.isSchlagFix());
		
		stiche.add(new Stich(hK,lU,e8,s10,1));	//6~,7-,8-,9~,10~,U~,O~,K-,A~
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.SECHSER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.SIEBNER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.ACHTER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.NEUNER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.ZEHNER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.UNTER));	
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.OBER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.KOENIG));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.AS));
		assertEquals(false,result.isSchlagFix());
		
		//---------------------------------------
		// neues Spiel
		result = new Result(true);
		stiche.clear();
		// Guater lK Rechter lO
		stiche.add(new Stich(e7,s10,e8,sK,2));	//6~,7~,8~,9~,10-,U~,O~,K-,A~
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.SECHSER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.SIEBNER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.ACHTER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.NEUNER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.ZEHNER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.UNTER));	
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.OBER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.KOENIG));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.AS));
		assertEquals(false,result.isSchlagFix());
		
		stiche.add(new Stich(l8,lU,h9,lO,3));	//6~,7~,8-,9-,10-,U~,O~,K-,A~
		//result.setColorAt(CONSTANTS.LAUB,CONSTANTS.SURE);
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.SECHSER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.SIEBNER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.ACHTER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.NEUNER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.ZEHNER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.UNTER));	
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.OBER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.KOENIG));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.AS));
		assertEquals(false,result.isSchlagFix());
		
		//selbe Auswerung wie oben, aber Laub als Schlag fix; 
		//7 muss nun wegen erstem 8 schlägt 7 herausfliegen
		result.setColorAt(CONSTANTS.LAUB,CONSTANTS.SURE);
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.SECHSER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.SIEBNER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.ACHTER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.NEUNER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.ZEHNER));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.UNTER));	
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.OBER));
		assertEquals(CONSTANTS.IMPOSSIBLE,result.getNumberAt(CONSTANTS.KOENIG));
		assertEquals(CONSTANTS.POSSIBLE,result.getNumberAt(CONSTANTS.AS));
		assertEquals(false,result.isSchlagFix());
		
	}
}
