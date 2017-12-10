package watten.tests;

import java.util.ArrayList;

import watten.CONSTANTS;
import watten.FarbStichTest;
import watten.Result;
import watten.Stich;
import junit.framework.TestCase;

public class Test_FarbStichTest extends TestCase {

	CardDeck deck; 
	ArrayList<Stich> stiche;

	public void setUp() {
		deck = new CardDeck();
		stiche = new ArrayList<Stich>(5);
	}

	public void test_runTest_erster_Stich_Farbstich() {
		Result result = new Result(false);

		stiche.add(new Stich(deck.e7, deck.h7, deck.l7, deck.e9, 3));
		assertEquals(CONSTANTS.POSSIBLE, new FarbStichTest(result, stiche).runTest());

		result = new Result(false);
		stiche.add(new Stich(deck.eA, deck.h7, deck.l7, deck.e9, 0));
		assertEquals(CONSTANTS.POSSIBLE, new FarbStichTest(result, stiche).runTest());
		
		result = new Result(false);
		stiche.add(new Stich(deck.e10, deck.eA, deck.l7, deck.eU, 3));
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
	}

	public void test_runTest_one_Color_is_Trumpf() {
		Result result = new Result(false);

		stiche.add(new Stich(deck.e7, deck.h7, deck.l7, deck.l8, 0));
		result.setColorAt(CONSTANTS.EICHEL, CONSTANTS.SURE);
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());

		result.setColorAt(CONSTANTS.HERZ, CONSTANTS.SURE);
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());

		result.setColorAt(CONSTANTS.LAUB, CONSTANTS.SURE);
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());

		stiche.clear();
		stiche.add(new Stich(deck.e7, deck.h7, deck.s7, deck.s8, 3));
		result.setColorAt(CONSTANTS.SCHELL, CONSTANTS.SURE);
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
	}

	public void test_runTest_4_Colors() {
		Result result = new Result(false);
		stiche.clear();
		stiche.add(new Stich(deck.e7, deck.h7, deck.l7, deck.s8, 0));
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
	}

	public void test_runTest_correct() {
		Result result = new Result(false);
		stiche.clear();
		stiche.add(new Stich(deck.h7, deck.hA, deck.l7, deck.e8, 3));
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
	}

	public void test_runTest_serien_runTests() {
		System.out.println("serien_Tests Nr. 1");
		Result result = new Result(true);
		stiche.clear();
		stiche.add(new Stich(deck.e7, deck.hA, deck.l7, deck.e8, 3));
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.POSSIBLE, new FarbStichTest(result, stiche).runTest());
		// hier folgt, dass im Falle eines Farbstiches e,s,l keine Trümpfe sein
		// können
		// => h = Trumpf und Widerspruch hA sticht nicht e8
		// e8 muss dann auf linken korrigiert werden!
		stiche.add(new Stich(deck.e9, deck.sK, deck.lU, deck.e10, 3));
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.POSSIBLE, new FarbStichTest(result, stiche).runTest());
		
		System.out.println("serien_Tests Nr. 2");
		result = new Result(true);
		stiche.clear();
		stiche.add(new Stich(deck.e7, deck.hA, deck.l7, deck.e8, 1));
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		
		stiche.add(new Stich(deck.e9, deck.sK, deck.lU, deck.e10, 3));
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.POSSIBLE, new FarbStichTest(result, stiche).runTest());
		
		System.out.println("serien_Test()()s Nr. 3");
		result = new Result(true);
		// Idee Rechter: sU
		stiche.clear();
		stiche.add(new Stich(deck.e7, deck.sU, deck.sA, deck.sO, 3));
		result.calcSchlag(stiche);
		System.out.println("Stich Nr. 1");
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		
		stiche.add(new Stich(deck.e9, deck.sK, deck.lU, deck.e10, 2));
		result.calcSchlag(stiche);
		System.out.println("Stich Nr. 2");
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		
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
		
		stiche.add(new Stich(deck.e9, deck.sA, deck.sU, deck.e10, 2));
		result.calcSchlag(stiche);
		System.out.println("Stich Nr.: 1 -----------------------");
		System.out.println(result.toString());
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		
		stiche.add(new Stich(deck.e8, deck.lK, deck.lU, deck.l8, 2));
		result.calcSchlag(stiche);
		System.out.println("Stich Nr.: 2 -----------------------");
		System.out.println(result.toString());
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		
		stiche.add(new Stich(deck.s6, deck.hK, deck.l7, deck.s10, 3));
		result.calcSchlag(stiche);
		System.out.println("Stich Nr.: 3 -----------------------");
		System.out.println(result.toString());
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
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
		// Rechter sticht
		stiche.add(new Stich(deck.e10, deck.eA, deck.hK, deck.hO, 2));
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		// Trumpf sticht
		stiche.add(new Stich(deck.s6, deck.hU, deck.lO, deck.s7, 1));
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
		// Linker sticht
		stiche.add(new Stich(deck.lA, deck.lK, deck.l10, deck.eU, 1));
		result.calcSchlag(stiche);
		assertEquals(CONSTANTS.IMPOSSIBLE, new FarbStichTest(result, stiche).runTest());
	}

}
