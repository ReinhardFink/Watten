package watten.tests;

import junit.framework.TestCase;

import watten.StichFactory;

public class Test_StichFactory extends TestCase {

	CardDeck  deck;

	public void setUp() {
		deck = new CardDeck();
	}

	public void test_createStich() {
		StringBuffer errorMessage = new StringBuffer("");
		StichFactory stichFactory = new StichFactory(errorMessage);
		assertEquals(true,deck.hA.equals(stichFactory.createStich("hk,ha,l7,s9,2").getWinner()));
		assertEquals(true,stichFactory.createStich("hk,ha,l7,s9,1") == null);
		System.out.println(errorMessage.toString());
		
		assertEquals(true,deck.eO.equals(stichFactory.createStich("9h,eK,oh,eo,4").getWinner()));
		assertEquals(true,stichFactory.createStich("9h,eK,oh,Oe,3") == null);
		System.out.println(errorMessage.toString());
		
		stichFactory.createStich("ll9h,nnn,ohgg,Oehh,3");
		System.out.println(errorMessage.toString());
	}
	
	public void test_reset_Cards_In_Stich() {
		StringBuffer errorMessage = new StringBuffer("");
		StichFactory stichFactory = new StichFactory(errorMessage);
		assertEquals(true,deck.hA.equals(stichFactory.createStich("hk,ha,l7,s9,2").getWinner()));
		assertEquals(true,stichFactory.createStich("ek,ea,s7,s19,1") == null);
		
		assertEquals(true,deck.eA.equals(stichFactory.createStich("ek,ea,s7,s10,2").getWinner()));
	}
}
