package nl.avans.SudokuDatastructure;

class Field {
	private int value = -1;
	private boolean editable = true;
	
	public Field(int value) {
		this.value = value;
	}
	
	public Field setEditable(boolean value) {
		editable = value;
		return this;
	}
	
	public int getValue(){
		return value;
	}
	
	public Field setValue(int value){
		this.value = value;
		return this;
	}
	
}
