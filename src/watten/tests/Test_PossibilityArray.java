package watten.tests;

import watten.CONSTANTS;
import watten.PossibilityArray;
import junit.framework.TestCase;

public class Test_PossibilityArray extends TestCase {
	
	public void test_clone() {
		PossibilityArray pa = new PossibilityArray(10);
		pa.setValueAt(2,CONSTANTS.IMPOSSIBLE);
		pa.setValueAt(5,CONSTANTS.IMPOSSIBLE);
		pa.setValueAt(9,CONSTANTS.IMPOSSIBLE);
		PossibilityArray paClone;
		try {
			paClone = pa.clone();
			// Test ob Klone nach klonen gleich!
			for(int i = 0; i < pa.getLength(); i++)
				assertEquals(paClone.get(i),pa.get(i));
			// Test ob Klone nach Veraenderung verschieden!
			paClone.setValueAt(0,CONSTANTS.IMPOSSIBLE);
			assertEquals(CONSTANTS.POSSIBLE,pa.get(0));
			assertEquals(CONSTANTS.IMPOSSIBLE,paClone.get(0));
		} catch(CloneNotSupportedException e) { 
			System.out.println("PossibilityArray konnte nicht geklont werden!");
		}
	}
	
	public void test_setValueAt_All_0() {
		PossibilityArray pa = new PossibilityArray(4);
		for(int i = 0; i < pa.getLength(); i++)
			assertEquals(0,pa.get(i));
	}
	
	public void test_setValueAt_correct_If_Just_1_Possibly_Sure_Left() {
		PossibilityArray pa = new PossibilityArray(4);
		// test if 0 is CONSTANTS.IMPOSSIBLE
		pa.setValueAt(0,CONSTANTS.IMPOSSIBLE);
		assertEquals(CONSTANTS.IMPOSSIBLE,pa.get(0));
		// test if 1 is CONSTANTS.IMPOSSIBLE
		pa.setValueAt(1,CONSTANTS.IMPOSSIBLE);
		assertEquals(CONSTANTS.IMPOSSIBLE,pa.get(1));
		// test if 2 is CONSTANTS.IMPOSSIBLE
		pa.setValueAt(2,CONSTANTS.IMPOSSIBLE);
		assertEquals(CONSTANTS.IMPOSSIBLE,pa.get(2));
		// test if 3 is CONSTANTS.SURE
		assertEquals(CONSTANTS.SURE,pa.get(3));
	}
	
	public void test_setValueAt_set_1_Sure() {
		PossibilityArray pa = new PossibilityArray(4);
		pa.setValueAt(2,CONSTANTS.SURE);
		assertEquals(CONSTANTS.SURE,pa.get(2));
		assertEquals(CONSTANTS.IMPOSSIBLE,pa.get(0));
		assertEquals(CONSTANTS.IMPOSSIBLE,pa.get(1));
		assertEquals(CONSTANTS.IMPOSSIBLE,pa.get(3));
	}
	
}
