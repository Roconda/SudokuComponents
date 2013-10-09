package nl.avans.datastructure;

class UnknownColumnException extends Exception {

	public UnknownColumnException() { }
	
	public UnknownColumnException(String msg) {
		super(msg);
	}
}
