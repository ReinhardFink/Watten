package watten.tests;

import java.util.ArrayList;

import watten.BasisStichTest;
import watten.CONSTANTS;
import watten.Result;
import watten.Stich;

import junit.framework.TestCase;

public class Test_BasisStichTest extends TestCase {

	CardDeck deck;
	ArrayList<Stich> stiche;
	BasisStichTest basisStichTest;
	
	public void setUp() {
		deck = new CardDeck();
		stiche = new ArrayList<Stich>(5);
	}
	
	public void test_testWinner_FarbStich() {
		Result result = new Result(false);
		stiche.add(new Stich(deck.h7,deck.h8,deck.hK,deck.hA,3));
		basisStichTest = new BasisStichTest(result,stiche);
		assertEquals(3,basisStichTest.findWinner(0));
		
		stiche.add(new Stich(deck.hA,deck.h8,deck.hK,deck.hU,0));
		basisStichTest = new BasisStichTest(result,stiche);
		assertEquals(0,basisStichTest.findWinner(1));
		
		stiche.add(new Stich(deck.h10,deck.h8,deck.hK,deck.hU,2));
		basisStichTest = new BasisStichTest(result,stiche);
		assertEquals(2,basisStichTest.findWinner(2));
	}
	
	public void test_testWinner_LinkerStich() {
		Result result = new Result(false);
		result.setNumberAt(CONSTANTS.ACHTER,CONSTANTS.SURE) ;
		stiche.add(new Stich(deck.h7,deck.h8,deck.hK,deck.hA,3));
		basisStichTest = new BasisStichTest(result,stiche);
		assertEquals(1,basisStichTest.findWinner(0));
		
		stiche.add(new Stich(deck.hA,deck.l9,deck.e8,deck.l8,2));
		basisStichTest = new BasisStichTest(result,stiche);
		assertEquals(2,basisStichTest.findWinner(1));
	}
	
	public void test_testWinner_RechterStichtLinkenOhneGuaten() {
		Result result = new Result(false);
		result.setNumberAt(CONSTANTS.ACHTER,CONSTANTS.SURE) ;
		result.setColorAt(CONSTANTS.SCHELL,CONSTANTS.SURE) ;
		
		stiche.add(new Stich(deck.h7,deck.h8,deck.hK,deck.hA,3));
		basisStichTest = new BasisStichTest(result,stiche);
		assertEquals(1,basisStichTest.findWinner(0));
		
		stiche.add(new Stich(deck.hA,deck.s9,deck.e8,deck.s8,3));
		basisStichTest = new BasisStichTest(result,stiche);
		assertEquals(3,basisStichTest.findWinner(1));
		
		stiche.add(new Stich(deck.s8,deck.l9,deck.e8,deck.h8,0));
		basisStichTest = new BasisStichTest(result,stiche);
		assertEquals(0,basisStichTest.findWinner(2));
		
		//System.out.println("------------");
		stiche.add(new Stich(deck.s8,deck.hU,deck.e10,deck.s9,0));
		basisStichTest = new BasisStichTest(result,stiche);
		//assertEquals(0,basisStichTest.testWinner(3));
		//System.out.println(result.getRechter().number + " " + result.getRechter().color);
		assertEquals(0,basisStichTest.findWinner(3));
		
		stiche.add(new Stich(deck.s8,deck.hU,deck.e10,deck.sK,0));
		basisStichTest = new BasisStichTest(result,stiche);
		assertEquals(0,basisStichTest.findWinner(4));
	}
	
	public void test_testWinner_RechterStichtLinkenMitGuaten() {
		Result result = new Result(true);
		result.setNumberAt(CONSTANTS.ACHTER,CONSTANTS.SURE) ;
		result.setColorAt(CONSTANTS.SCHELL,CONSTANTS.SURE) ;
		stiche.add(new Stich(deck.h7,deck.h8,deck.hK,deck.hA,3));
		basisStichTest = new BasisStichTest(result,stiche);
		assertEquals(1,basisStichTest.findWinner(0));
		
		stiche.add(new Stich(deck.hA,deck.l9,deck.e8,deck.s8,3));
		basisStichTest = new BasisStichTest(result,stiche);
		assertEquals(3,basisStichTest.findWinner(1));
		
		stiche.add(new Stich(deck.s8,deck.l9,deck.e8,deck.h8,0));
		basisStichTest = new BasisStichTest(result,stiche);
		assertEquals(0,basisStichTest.findWinner(2));
		
		stiche.add(new Stich(deck.s9,deck.h8,deck.e8,deck.s8,0));
		basisStichTest = new BasisStichTest(result,stiche);
		assertEquals(0,basisStichTest.findWinner(3));
	}
}
