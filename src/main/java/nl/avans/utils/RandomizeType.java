package nl.avans.utils;

public enum RandomizeType {
	
	/**
	 * No randomizing
	 */
	NONE,
	
	/**
	 * Inverses all numbers
	 */
	INVERSE,
	
	/**
	 * Inverses all rows
	 */
	SWAP_HORIZONTAL,
	
	/**
	 * Inverses all columns
	 */
	SWAP_VERTICAL
};