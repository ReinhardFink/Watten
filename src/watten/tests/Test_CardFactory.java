package watten.tests;

import junit.framework.TestCase;

import watten.CardFactory;

public class Test_CardFactory extends TestCase {

	CardDeck deck;
	
	public void setUp() {
		deck = new CardDeck();
	}

	public void test_getCard() {
		StringBuffer errorMessage = new StringBuffer("");
		CardFactory cardFactory =  new CardFactory(errorMessage);
		assertEquals(true, deck.e7.equals(cardFactory.createCard("e7")));
		assertEquals(true, cardFactory.createCard("e7") == null);
		System.out.println(errorMessage.toString());
		
		errorMessage.delete(0,errorMessage.length());
		assertEquals(true, deck.e8.equals(cardFactory.createCard("8e")));
		assertEquals(true, cardFactory.createCard("e8") == null);
		System.out.println(errorMessage.toString());
		
		assertEquals(true, deck.e10.equals(cardFactory.createCard("e10")));
		assertEquals(true, cardFactory.createCard("10e") == null);
		
		assertEquals(true, deck.h10.equals(cardFactory.createCard("10h")));
		assertEquals(true, cardFactory.createCard("10e") == null);
		
		assertEquals(true, deck.s10.equals(cardFactory.createCard("10s")));
		assertEquals(true, cardFactory.createCard("10s") == null);
		
		assertEquals(true, deck.lK.equals(cardFactory.createCard("KL")));
		assertEquals(true, cardFactory.createCard("LK") == null);
		
		assertEquals(true, deck.sA.equals(cardFactory.createCard("SA")));
		assertEquals(true, cardFactory.createCard("aS") == null);
		
		errorMessage.delete(0,errorMessage.length());
		cardFactory.createCard("kl");
		System.out.println(errorMessage.toString()); 
	}
	
	public void test_resetCard() {
		StringBuffer errorMessage = new StringBuffer("");
		CardFactory cardFactory =  new CardFactory(errorMessage);
		assertEquals(true, deck.s9.equals(cardFactory.createCard("9s")));
		assertEquals(true, cardFactory.createCard("9s") == null);
		cardFactory.resetCard(deck.s9);
		assertEquals(true, deck.s9.equals(cardFactory.createCard("9s")));
		//Card card = cardFactory.createCard("g8");
		//System.out.println(card.color + " " + card.number);
	}
}
