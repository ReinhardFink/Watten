package watten.tests;

import java.util.ArrayList;

import watten.CONSTANTS;
import watten.FarbStichTest;
import watten.GuaterStichTest;
import watten.LinkerStichTest;
import watten.RechterStichTest;
import watten.Result;
import watten.Stich;
import watten.StichCalculator;
import watten.TrumpfStichTest;


import junit.framework.TestCase;

public class Test_StichCalculator extends TestCase {

	CardDeck deck;
	
	public void setUp() {
		deck = new CardDeck();	
	}
	
	public void test_runTest_1() {
		System.out.println();
		System.out.println("serien_Tests Nr. 1");
		System.out.println("##########################################################");
		System.out.println();
		System.out.println("Stich Nr. 1");
		System.out.println();
		ArrayList<Stich> stiche = new ArrayList<Stich>(CONSTANTS.CARDS_IN_STICH);
		Result result =  new Result(true);
		StichCalculator stichCalculator = new StichCalculator(stiche,result);
		stiche.add(new Stich(deck.e7, deck.hA, deck.l7, deck.e8, 3));
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.POSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new RechterStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new GuaterStichTest(result, stiche).runTest());
		System.out.println(stichCalculator.getMessage());
		//stichCalculator.calculateNewResult();
		assertEquals(CONSTANTS.POSSIBLE, stichCalculator.getFarbStich());
		assertEquals(CONSTANTS.POSSIBLE, stichCalculator.getTrumpfStich());
		assertEquals(CONSTANTS.POSSIBLE, stichCalculator.getLinkerStich());
		assertEquals(CONSTANTS.POSSIBLE, stichCalculator.getRechterStich());
		assertEquals(CONSTANTS.POSSIBLE, stichCalculator.getGuaterStich());
		System.out.println();
		System.out.println("Stich Nr. 2");
		System.out.println();
		stiche.add(new Stich(deck.e9, deck.sK, deck.lU, deck.e10, 3));
		result.calcSchlag(stiche);
		// hier folgt, dass im Falle eines Farbstiches e,s,l keine Trümpfe sein
		// können
		// => h = Trumpf und Widerspruch hA sticht nicht e8
		// e8 muss dann auf linken korrigiert werden!
		assertEquals(CONSTANTS.POSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new RechterStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new GuaterStichTest(result, stiche).runTest());
		System.out.println(stichCalculator.getMessage());
		//stichCalculator.calculateNewResult();
		assertEquals(CONSTANTS.POSSIBLE, stichCalculator.getFarbStich());
		assertEquals(CONSTANTS.POSSIBLE, stichCalculator.getTrumpfStich());
		assertEquals(CONSTANTS.POSSIBLE, stichCalculator.getLinkerStich());
		assertEquals(CONSTANTS.POSSIBLE, stichCalculator.getRechterStich());
		assertEquals(CONSTANTS.POSSIBLE, stichCalculator.getGuaterStich());
	}
	
	public void test_runTest_2() {
		System.out.println();
		System.out.println("Serien Test Nr. 2");
		System.out.println("##########################################################");
		System.out.println();
		System.out.println("Stich Nr. 1");
		System.out.println();
		ArrayList<Stich> stiche = new ArrayList<Stich>(CONSTANTS.CARDS_IN_STICH);
		Result result =  new Result(true);
		StichCalculator stichCalculator = new StichCalculator(stiche,result);
		stiche.add(new Stich(deck.e7, deck.hA, deck.l7, deck.e8, 1));
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new RechterStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new GuaterStichTest(result, stiche).runTest());
		System.out.println(stichCalculator.getMessage());
		//stichCalculator.calculateNewResult();
		assertEquals(CONSTANTS.IMPOSSIBLE, stichCalculator.getFarbStich());
		assertEquals(CONSTANTS.POSSIBLE, stichCalculator.getTrumpfStich());
		assertEquals(CONSTANTS.POSSIBLE, stichCalculator.getLinkerStich());
		assertEquals(CONSTANTS.POSSIBLE, stichCalculator.getRechterStich());
		assertEquals(CONSTANTS.POSSIBLE, stichCalculator.getGuaterStich());
		System.out.println();
		System.out.println("Stich Nr. 2");
		System.out.println();
		stiche.add(new Stich(deck.e9, deck.sK, deck.lU, deck.e10, 3));
		result.calcSchlag(stiche);
		//System.out.println(result.toString());
		assertEquals(CONSTANTS.POSSIBLE, new FarbStichTest(result, stiche).runTest());
		// ab hier ist das Programm bereits besser als der Programmierer (benötigte Debugger)!
		// wenn e=Trumpf muss hA im ersten Stich linker sein => e7 im erstenStich Guater!
		assertEquals(CONSTANTS.IMPOSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new RechterStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new GuaterStichTest(result, stiche).runTest());
		System.out.println(stichCalculator.getMessage());
		//stichCalculator.calculateNewResult();
		assertEquals(CONSTANTS.POSSIBLE, stichCalculator.getFarbStich());
		assertEquals(CONSTANTS.IMPOSSIBLE, stichCalculator.getTrumpfStich());
		assertEquals(CONSTANTS.POSSIBLE, stichCalculator.getLinkerStich());
		assertEquals(CONSTANTS.IMPOSSIBLE, stichCalculator.getRechterStich());
		assertEquals(CONSTANTS.IMPOSSIBLE, stichCalculator.getGuaterStich());
	}
	
	public void test_runTest_3() {
		System.out.println();
		System.out.println("Serien Test Nr. 3");
		System.out.println("##########################################################");
		System.out.println();
		ArrayList<Stich> stiche = new ArrayList<Stich>(CONSTANTS.CARDS_IN_STICH);
		Result result =  new Result(true);
		StichCalculator stichCalculator = new StichCalculator(stiche,result);
		// Idee Rechter: sU
		System.out.println();
		System.out.println("Stich Nr. 1");
		System.out.println();
		stiche.add(new Stich(deck.e7, deck.sU, deck.sA, deck.sO, 3));
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new RechterStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new GuaterStichTest(result, stiche).runTest());
		System.out.println(stichCalculator.getMessage());
		//stichCalculator.calculateNewResult();
		assertEquals(CONSTANTS.IMPOSSIBLE, stichCalculator.getFarbStich());
		assertEquals(CONSTANTS.IMPOSSIBLE, stichCalculator.getTrumpfStich());
		assertEquals(CONSTANTS.POSSIBLE, stichCalculator.getLinkerStich());
		assertEquals(CONSTANTS.POSSIBLE, stichCalculator.getRechterStich());
		assertEquals(CONSTANTS.POSSIBLE, stichCalculator.getGuaterStich());
		System.out.println();
		System.out.println("Stich Nr. 2");
		System.out.println();
		stiche.add(new Stich(deck.e9, deck.sK, deck.lU, deck.e10, 2));
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new RechterStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new GuaterStichTest(result, stiche).runTest());
		System.out.println(stichCalculator.getMessage());
		//stichCalculator.calculateNewResult();
		assertEquals(CONSTANTS.IMPOSSIBLE, stichCalculator.getFarbStich());
		assertEquals(CONSTANTS.POSSIBLE, stichCalculator.getTrumpfStich());
		assertEquals(CONSTANTS.POSSIBLE, stichCalculator.getLinkerStich());
		assertEquals(CONSTANTS.IMPOSSIBLE, stichCalculator.getRechterStich());
		assertEquals(CONSTANTS.IMPOSSIBLE, stichCalculator.getGuaterStich());
	}
	
	public void test_runTest_4() {
		System.out.println();
		System.out.println("Serien Test Nr. 4");
		System.out.println("##########################################################");
		System.out.println();
		ArrayList<Stich> stiche = new ArrayList<Stich>(CONSTANTS.CARDS_IN_STICH);
		Result result =  new Result(true);
		StichCalculator stichCalculator = new StichCalculator(stiche,result);
		// Idee Rechter: sU
	
		System.out.println();
		System.out.println("Stich Nr. 1");
		System.out.println();
		stiche.add(new Stich(deck.e7, deck.sO, deck.lA, deck.sK, 1));
		result.calcSchlag(stiche);
		//System.out.println(result.toString());
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new RechterStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new GuaterStichTest(result, stiche).runTest());
		System.out.println(stichCalculator.getMessage());
		//stichCalculator.calculateNewResult();
		assertEquals(CONSTANTS.IMPOSSIBLE, stichCalculator.getFarbStich());
		assertEquals(CONSTANTS.IMPOSSIBLE, stichCalculator.getTrumpfStich());
		assertEquals(CONSTANTS.POSSIBLE, stichCalculator.getLinkerStich());
		assertEquals(CONSTANTS.IMPOSSIBLE, stichCalculator.getRechterStich());
		assertEquals(CONSTANTS.POSSIBLE, stichCalculator.getGuaterStich());
		System.out.println();
		System.out.println("Stich Nr. 2");
		System.out.println();
		stiche.add(new Stich(deck.e9, deck.sA, deck.sU, deck.e10, 2));
		result.calcSchlag(stiche);
		System.out.println(result.toString());
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new RechterStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new GuaterStichTest(result, stiche).runTest());
		System.out.println(stichCalculator.getMessage());
		// ab hier Änderung weil Result ist nun fix!!
		//stichCalculator.calculateNewResult();
		System.out.println(result.toString());
		assertEquals(CONSTANTS.IMPOSSIBLE, stichCalculator.getFarbStich());
		assertEquals(CONSTANTS.IMPOSSIBLE, stichCalculator.getTrumpfStich());
		assertEquals(CONSTANTS.IMPOSSIBLE, stichCalculator.getLinkerStich());
		assertEquals(CONSTANTS.SURE, stichCalculator.getRechterStich());
		assertEquals(CONSTANTS.IMPOSSIBLE, stichCalculator.getGuaterStich());
		System.out.println();
		System.out.println("Stich Nr. 3");
		System.out.println();
		stiche.add(new Stich(deck.e8, deck.lK, deck.lU, deck.l8, 2));
		result.calcSchlag(stiche);
		System.out.println(result.toString());
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.SURE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new RechterStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new GuaterStichTest(result, stiche).runTest());
		System.out.println(stichCalculator.getMessage());
		//stichCalculator.calculateNewResult();
		assertEquals(CONSTANTS.IMPOSSIBLE, stichCalculator.getFarbStich());
		assertEquals(CONSTANTS.IMPOSSIBLE, stichCalculator.getTrumpfStich());
		assertEquals(CONSTANTS.SURE, stichCalculator.getLinkerStich());
		assertEquals(CONSTANTS.IMPOSSIBLE, stichCalculator.getRechterStich());
		assertEquals(CONSTANTS.IMPOSSIBLE, stichCalculator.getGuaterStich());
		System.out.println();
		System.out.println("Stich Nr. 4");
		System.out.println();
		stiche.add(new Stich(deck.s6, deck.hK, deck.l7, deck.s10, 3));
		result.calcSchlag(stiche);
		System.out.println(result.toString());
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.SURE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new RechterStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new GuaterStichTest(result, stiche).runTest());
		System.out.println(stichCalculator.getMessage());
		//stichCalculator.calculateNewResult();
		assertEquals(CONSTANTS.IMPOSSIBLE, stichCalculator.getFarbStich());
		assertEquals(CONSTANTS.SURE, stichCalculator.getTrumpfStich());
		assertEquals(CONSTANTS.IMPOSSIBLE, stichCalculator.getLinkerStich());
		assertEquals(CONSTANTS.IMPOSSIBLE, stichCalculator.getRechterStich());
		assertEquals(CONSTANTS.IMPOSSIBLE, stichCalculator.getGuaterStich());

	}
}
