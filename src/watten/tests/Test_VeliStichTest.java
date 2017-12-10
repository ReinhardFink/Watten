package watten.tests;

import java.util.ArrayList;

import watten.CONSTANTS;
import watten.FarbStichTest;
import watten.GuaterStichTest;
import watten.LinkerStichTest;
import watten.RechterStichTest;
import watten.Result;
import watten.Stich;
import watten.TrumpfStichTest;


import junit.framework.TestCase;

public class Test_VeliStichTest extends TestCase {

	CardDeck deck;
	ArrayList<Stich> stiche;

	public void setUp() {
		deck = new CardDeck();
		stiche = new ArrayList<Stich>(5);
	}

	public void test_VeliStich() {
		Result result = new Result(true);

		stiche.add(new Stich(deck.s6, deck.h7, deck.l7, deck.e9, 0));
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new RechterStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new GuaterStichTest(result, stiche).runTest());
		
		stiche.clear();
		result = new Result(true);
		stiche.add(new Stich(deck.s6, deck.h7, deck.h8, deck.e9, 0));
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new RechterStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new GuaterStichTest(result, stiche).runTest());

		stiche.clear();
		result = new Result(true);
		stiche.add(new Stich(deck.s6, deck.sA, deck.l7, deck.e9, 0));
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		//assertEquals(CONSTANTS.IMPOSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new RechterStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new GuaterStichTest(result, stiche).runTest());
	}
	
	public void test_nichtVeliStich() {
		Result result = new Result(false);

		stiche.add(new Stich(deck.s6, deck.h7, deck.l7, deck.e9, 1));
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new RechterStichTest(result, stiche).runTest());
		//assertEquals(CONSTANTS.POSSIBLE, new GuaterStichTest(result, stiche).runTest());
		
		stiche.clear();
		result = new Result(false);
		stiche.add(new Stich(deck.s6, deck.s7, deck.h8, deck.e9, 1));
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new RechterStichTest(result, stiche).runTest());
		//assertEquals(CONSTANTS.POSSIBLE, new GuaterStichTest(result, stiche).runTest());
	}
		/*
		stiche.clear();
		result = new Result(false);
		stiche.add(new Stich(e7, e9, l7, e10, 1));
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new RechterStichTest(result, stiche).runTest());
		
		stiche.clear();
		result = new Result(true);
		stiche.add(new Stich(e7, e9, l7, e10, 1));
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
		stiche.add(new Stich(e7, hA, l7, e8, 3));
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.POSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new RechterStichTest(result, stiche).runTest());
		stiche.add(new Stich(e9, sK, lU, e10, 3));
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
		stiche.add(new Stich(e7, hA, l7, e8, 1));
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new RechterStichTest(result, stiche).runTest());
		
		stiche.add(new Stich(e9, sK, lU, e10, 3));
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
		stiche.add(new Stich(e7, sU, sA, sO, 3));
		result.calcSchlag(stiche);
		System.out.println("Stich Nr. 1");
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new RechterStichTest(result, stiche).runTest());
		
		stiche.add(new Stich(e9, sK, lU, e10, 2));
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
		
		stiche.add(new Stich(e7, sO, lA, sK, 1));
		result.calcSchlag(stiche);
		System.out.println("Stich Nr.: 0 -----------------------");
		System.out.println(result.toString());
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new RechterStichTest(result, stiche).runTest());
		
		stiche.add(new Stich(e9, sA, sU, e10, 2));
		result.calcSchlag(stiche);
		System.out.println("Stich Nr.: 1 -----------------------");
		System.out.println(result.toString());
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new RechterStichTest(result, stiche).runTest());
		
		stiche.add(new Stich(e8, lK, lU, l8, 2));
		result.calcSchlag(stiche);
		System.out.println("Stich Nr.: 2 -----------------------");
		System.out.println(result.toString());
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.POSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new RechterStichTest(result, stiche).runTest());
		
		stiche.add(new Stich(s6, hK, l7, s10, 3));
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
		stiche.add(new Stich(e7, lA, l7, e8, 3));
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.SURE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new RechterStichTest(result, stiche).runTest());
		// Rechter sticht
		stiche.add(new Stich(e10, eA, hK, hO, 2));
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.SURE, new RechterStichTest(result, stiche).runTest());
		// Trumpf sticht
		stiche.add(new Stich(s6, hU, lO, s7, 1));
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.SURE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new RechterStichTest(result, stiche).runTest());
		// Linker sticht
		stiche.add(new Stich(sA, lK, l10, eU, 1));
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.SURE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new RechterStichTest(result, stiche).runTest());
		// Guater sticht
		stiche.add(new Stich(e9, sU, hA, h10, 2));
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new TrumpfStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new LinkerStichTest(result, stiche).runTest());
		assertEquals(CONSTANTS.IMPOSSIBLE, new RechterStichTest(result, stiche).runTest());
		
	}*/
}
