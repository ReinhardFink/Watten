package watten.tests;

import java.util.ArrayList;

import watten.CONSTANTS;
import watten.FarbStichTest;
import watten.LinkerStichTest;
import watten.RechterStichTest;
import watten.Result;
import watten.Stich;
import watten.TrumpfStichTest;


import junit.framework.TestCase;

public class Test_RechterStichTest extends TestCase {

	CardDeck deck;
	ArrayList<Stich> stiche;

	public void setUp() {
		deck = new CardDeck();
		stiche = new ArrayList<Stich>(5);
	}

	public void test_runTest_erster_Stich_Rechterstich() {
		Result result = new Result(false);

		stiche.add(new Stich(deck.e7, deck.h7, deck.l7, deck.e9, 3));
		assertEquals(CONSTANTS.POSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new RechterStichTest(result, stiche).runTest());
		
		stiche.clear();
		stiche.add(new Stich(deck.e7, deck.h7, deck.l7, deck.e9, 0));
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new RechterStichTest(result, stiche).runTest());

		stiche.clear();
		result = new Result(false);
		stiche.add(new Stich(deck.e7, deck.h7, deck.l7, deck.e9, 1));
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new RechterStichTest(result, stiche).runTest());
		
		stiche.clear();
		result = new Result(false);
		stiche.add(new Stich(deck.e7, deck.e9, deck.l7, deck.e10, 1));
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new RechterStichTest(result, stiche).runTest());
		
		stiche.clear();
		result = new Result(true);
		stiche.add(new Stich(deck.e7, deck.e9, deck.l7, deck.e10, 1));
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new LinkerStichTest(result, stiche).runTest());
		// möglicher Guater e10 wäre dann im Stich
		assertEquals(CONSTANTS.IMPOSSIBLE, new RechterStichTest(result, stiche).runTest());
	}
	
	public void test_runTest_serien_runTests() {
		System.out.println();
		System.out.println("serien_Tests Nr. 1");
		System.out.println("##########################################################");
		Result result = new Result(true);
		stiche.clear();
		stiche.add(new Stich(deck.e7, deck.hA, deck.l7, deck.e8, 3));
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.POSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new RechterStichTest(result, stiche).runTest());
		stiche.add(new Stich(deck.e9, deck.sK, deck.lU, deck.e10, 3));
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.POSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new RechterStichTest(result, stiche).runTest());
		
		System.out.println();
		System.out.println("serien_Tests Nr. 2");
		System.out.println("##########################################################");
		result = new Result(true);
		stiche.clear();
		stiche.add(new Stich(deck.e7, deck.hA, deck.l7, deck.e8, 1));
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new RechterStichTest(result, stiche).runTest());
		
		stiche.add(new Stich(deck.e9, deck.sK, deck.lU, deck.e10, 3));
		result.calcSchlag(stiche);
		System.out.println(result.toString());
		assertEquals(CONSTANTS.POSSIBLE, new FarbStichTest(result, stiche).runTest());
		// ab hier ist das Programm bereits besser als der Programmierer (benötigte Debugger)!
		// wenn e=Trumpf muss hA im ersten Stich linker sein => e7 im erstenStich Guater!
		assertEquals(CONSTANTS.IMPOSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new RechterStichTest(result, stiche).runTest());
		
		System.out.println();
		System.out.println("serien_Tests Nr. 3");
		System.out.println("##########################################################");
		result = new Result(true);
		// Idee Rechter: sU
		stiche.clear();
		stiche.add(new Stich(deck.e7, deck.sU, deck.sA, deck.sO, 3));
		result.calcSchlag(stiche);
		System.out.println("Stich Nr. 1");
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new RechterStichTest(result, stiche).runTest());
		
		stiche.add(new Stich(deck.e9, deck.sK, deck.lU, deck.e10, 2));
		result.calcSchlag(stiche);
		System.out.println("Stich Nr. 2");
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new RechterStichTest(result, stiche).runTest());
		
		System.out.println();
		System.out.println("serien_runTests Nr. 4");
		System.out.println("##########################################################");
		result = new Result(true);
		// Idee Rechter: sU
		stiche.clear();
		
		stiche.add(new Stich(deck.e7, deck.sO, deck.lA, deck.sK, 1));
		result.calcSchlag(stiche);
		System.out.println("Stich Nr.: 0 -----------------------");
		System.out.println(result.toString());
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new RechterStichTest(result, stiche).runTest());
		
		stiche.add(new Stich(deck.e9, deck.sA, deck.sU, deck.e10, 2));
		result.calcSchlag(stiche);
		System.out.println("Stich Nr.: 1 -----------------------");
		System.out.println(result.toString());
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new RechterStichTest(result, stiche).runTest());
		
		stiche.add(new Stich(deck.e8, deck.lK, deck.lU, deck.l8, 2));
		result.calcSchlag(stiche);
		System.out.println("Stich Nr.: 2 -----------------------");
		System.out.println(result.toString());
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new RechterStichTest(result, stiche).runTest());
		
		stiche.add(new Stich(deck.s6, deck.hK, deck.l7, deck.s10, 3));
		result.calcSchlag(stiche);
		System.out.println("Stich Nr.: 3 -----------------------");
		System.out.println(result.toString());
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new RechterStichTest(result, stiche).runTest());
	
	}
	
	public void test_runTest_Rechter_is_Fix() {
		System.out.println("Rechter_is_Fix_Tests Nr. 1");
		// Rechter: hK
		Result result = new Result(true);
		result.setNumberAt(CONSTANTS.KOENIG,CONSTANTS.SURE);
		result.setColorAt(CONSTANTS.HERZ,CONSTANTS.SURE);
		stiche.clear();
		// reiner Farbstich
		stiche.add(new Stich(deck.e7, deck.lA, deck.l7, deck.e8, 3));
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.SURE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new RechterStichTest(result, stiche).runTest());
		// Rechter sticht
		stiche.add(new Stich(deck.e10, deck.eA, deck.hK, deck.hO, 2));
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.SURE, new RechterStichTest(result, stiche).runTest());
		// Trumpf sticht
		stiche.add(new Stich(deck.s6, deck.hU, deck.lO, deck.s7, 1));
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.SURE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new RechterStichTest(result, stiche).runTest());
		// Linker sticht
		stiche.add(new Stich(deck.sA, deck.lK, deck.l10, deck.eU, 1));
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.SURE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new RechterStichTest(result, stiche).runTest());
		// Guater sticht
		stiche.add(new Stich(deck.e9, deck.sU, deck.hA, deck.h10, 2));
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new RechterStichTest(result, stiche).runTest());
	}
}
