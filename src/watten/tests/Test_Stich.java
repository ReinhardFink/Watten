package watten.tests;

import watten.CONSTANTS;
import watten.Stich;

import junit.framework.TestCase;

public class Test_Stich extends TestCase {
	CardDeck deck;
	
	public void setUp() {
		deck = new CardDeck();
	}
	
	public void test_findCard() {
		Stich stich = new Stich(deck.e7,deck.e8,deck.e9,deck.e10,1);
		assertEquals(true, stich.findCard(deck.e7));
		assertEquals(false, stich.findCard(deck.h7));
		assertEquals(true, stich.findCard(deck.e10));
	}
	
	public void test_findNumber() {
		Stich stich = new Stich(deck.e7,deck.e8,deck.e9,deck.e10,1);
		assertEquals(true, stich.findNumber(CONSTANTS.SIEBNER));
		assertEquals(true, stich.findNumber(CONSTANTS.ACHTER));
		assertEquals(true, stich.findNumber(CONSTANTS.NEUNER));
		assertEquals(true, stich.findNumber(CONSTANTS.ZEHNER));
		
		stich = new Stich(deck.h7,deck.e7,deck.s8,deck.l8,1);
		assertEquals(true, stich.findColor(CONSTANTS.SIEBNER));
		assertEquals(true, stich.findColor(CONSTANTS.LAUB));
	}
	
	public void test_findColor() {
		Stich stich = new Stich(deck.e7,deck.e8,deck.e9,deck.e10,1);
		assertEquals(true, stich.findColor(CONSTANTS.EICHEL));
		assertEquals(false, stich.findColor(CONSTANTS.LAUB));
		assertEquals(false, stich.findColor(CONSTANTS.HERZ));
		
		stich = new Stich(deck.h7,deck.e8,deck.s9,deck.l10,1);
		assertEquals(true, stich.findColor(CONSTANTS.EICHEL));
		assertEquals(true, stich.findColor(CONSTANTS.LAUB));
		assertEquals(true, stich.findColor(CONSTANTS.HERZ));
		assertEquals(true, stich.findColor(CONSTANTS.SCHELL));
	}
	/*
	public void test_isWinnerColorBeated() {
		Stich stich = new Stich(e7,e8,e9,e10,1);
		assertEquals(true, stich.isWinnerColorBeated());
		
		stich = new Stich(e7,e8,e9,e10,3);
		assertEquals(false, stich.isWinnerColorBeated());
		
		stich = new Stich(e7,e8,e9,h10,2);
		assertEquals(false, stich.isWinnerColorBeated());
		
		stich = new Stich(e7,h8,h9,h10,0);
		assertEquals(false, stich.isWinnerColorBeated());
	}
	*/
}
