package watten.tests;

import watten.CONSTANTS;
import watten.CardRelation;
import watten.Result;
import junit.framework.TestCase;

public class Test_CardRelation extends TestCase {
	
	CardDeck deck;

	public void setUp() {
		deck = new CardDeck();
	}


	public void test_beats() {
		Result result = new Result(true);
		result.setNumberAt(CONSTANTS.ZEHNER,CONSTANTS.SURE);
		result.setColorAt(CONSTANTS.LAUB,CONSTANTS.SURE);
		CardRelation guater = new CardRelation(deck.lU,result);
		CardRelation rechter = new CardRelation(deck.l10,result);
		CardRelation linker1 = new CardRelation(deck.e10,result);
		CardRelation linker2 = new CardRelation(deck.h10,result);
		CardRelation linker3 = new CardRelation(deck.s10,result);
		CardRelation _e7 = new CardRelation(deck.e7,result);
		@SuppressWarnings("unused")
		CardRelation _hU = new CardRelation(deck.hU,result);
		CardRelation _hK = new CardRelation(deck.hK,result);
		CardRelation _hA = new CardRelation(deck.hA,result);
		CardRelation _lO = new CardRelation(deck.lO,result);
		CardRelation _lK = new CardRelation(deck.lK,result);
		CardRelation veli = new CardRelation(deck.s6,result);
		@SuppressWarnings("unused")
		CardRelation _s9 = new CardRelation(deck.s9,result);
		CardRelation _sU = new CardRelation(deck.sU,result);
		CardRelation _sK = new CardRelation(deck.sK,result);
		@SuppressWarnings("unused")
		CardRelation _sA = new CardRelation(deck.sA,result);
		// Tests für Guater, Rechter(l10), Linker untereinander
		assertEquals(true,guater.beats(rechter)); 
		assertEquals(false,rechter.beats(guater));
		assertEquals(true,guater.beats(linker1)); 
		assertEquals(false,linker1.beats(guater));
		assertEquals(true,guater.beats(linker2)); 
		assertEquals(false,linker2.beats(guater));
		assertEquals(true,guater.beats(linker3)); 
		assertEquals(false,linker3.beats(guater));
		assertEquals(true,rechter.beats(linker1)); 
		assertEquals(false,linker1.beats(rechter));
		assertEquals(true,rechter.beats(linker2)); 
		assertEquals(false,linker2.beats(rechter));
		assertEquals(true,rechter.beats(linker3)); 
		assertEquals(false,linker3.beats(rechter));
		assertEquals(true,linker1.beats(linker2));
		assertEquals(true,linker1.beats(linker3));
		assertEquals(true,linker2.beats(linker1));
		assertEquals(true,linker2.beats(linker3));
		assertEquals(true,linker3.beats(linker1));
		assertEquals(true,linker3.beats(linker2));
		assertEquals(true,linker2.beats(veli));
		assertEquals(false,veli.beats(linker2));
		assertEquals(true,linker3.beats(veli));
		assertEquals(false,veli.beats(linker3));
		// Tests für Guater, Rechter(l10), Linker auf Trumpf
		assertEquals(true,guater.beats(_lO));
		assertEquals(false,_lO.beats(guater));
		assertEquals(true,rechter.beats(_lO)); 
		assertEquals(false,_lO.beats(rechter));
		assertEquals(true,linker3.beats(_lO)); 
		assertEquals(false,_lO.beats(linker3));
		// Tests für Guater, Rechter(l10), Linker auf Farbe
		assertEquals(true,guater.beats(_hK));
		assertEquals(false,_hK.beats(guater));
		assertEquals(true,rechter.beats(_hK)); 
		assertEquals(false,_hK.beats(rechter));
		assertEquals(true,linker3.beats(_hK)); 
		assertEquals(false,_hK.beats(linker3));
		// Tests Trumpf auf Trumpf
		assertEquals(true,_lK.beats(_lO));
		assertEquals(false,_lO.beats(_lK));
		assertEquals(true,rechter.beats(_hK)); 
		assertEquals(false,_hK.beats(rechter));
		assertEquals(true,linker3.beats(_hK)); 
		assertEquals(false,_hK.beats(linker3));
		assertEquals(true,_lK.beats(veli));
		assertEquals(false,veli.beats(_lK));
		// Trumpf auf Farbe
		assertEquals(true,veli.beats(_hK));
		assertEquals(false,_hK.beats(veli));
		assertEquals(true,_lK.beats(_hK)); 
		assertEquals(false,_hK.beats(_lK));
		assertEquals(true,_lO.beats(_hK)); 
		assertEquals(false,_hK.beats(_lO));
		// Farbe auf verschiedene Farbe
		assertEquals(true,_e7.beats(_hA));
		assertEquals(true,_hA.beats(_e7));
		// Farbe auf Farbe
		assertEquals(true,_sK.beats(_sU));
		assertEquals(false,_sU.beats(_sK));
		assertEquals(true,_sK.beats(_hK)); 
		assertEquals(true,_hK.beats(_sK));
		
	}
}
