package watten;

import java.util.Observable;

public class PossibilityArray extends Observable implements Cloneable {
	
	private int[] array;
	
	public PossibilityArray(int size) {
		this.array = new int[size];
	}
	
	public PossibilityArray clone() throws CloneNotSupportedException {
		PossibilityArray tempPossibilityArray = new PossibilityArray(array.length);
		for(int i = 0; i < array.length; i++) tempPossibilityArray.setValueAt(i,array[i]);
		return tempPossibilityArray;
	}
	
	public String toString() {
		StringBuffer tempString = new StringBuffer();
		tempString.append(super.toString());
		tempString.append("\n Values: ");
		for(int i = 0; i < array.length; i++) {
			tempString.append(" v[" + i + "]=" + array[i]);
		}
		return tempString.toString();
	}
	
	public int getLength() { return array.length; }
	
	public void setValueAt(int position, int value) {
		if(value != array[position]) setChanged();
		if(value == CONSTANTS.SURE) {
			for(int pos = 0; pos < array.length; pos++) array[pos] = CONSTANTS.IMPOSSIBLE;
			array[position] = value;
		} else {
			array[position] = value;
			correctIfOnePossibleLeft();
		}
		notifyObservers(array.clone());
	}
	
	public int get(int position) {
		return array[position];
	}
	
	private void correctIfOnePossibleLeft() {
		int countPossible = 0;
		int possiblePosition = 0;
		for(int position = 0; position < array.length; position++)
			if(array[position] == CONSTANTS.POSSIBLE) {
				countPossible++;
				possiblePosition = position;
			}
		if(countPossible == 1) array[possiblePosition] = CONSTANTS.SURE;
	}

	public int findFirstSure() {
		int position = array.length - 1;
		while(position >= 0 && array[position] != CONSTANTS.SURE) 
			position--;
		return position;
	}
}
