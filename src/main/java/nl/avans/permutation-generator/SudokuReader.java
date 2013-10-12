import java.util.Random;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * The responsibility of the class SudokuReader is to read a sudoku from a xml-file and return it
 * as a multidimensional array of integers.
 */
public class SudokuReader extends DefaultHandler {
	private String sudoku;
	private int numberOfColumns;
	private int numberOfRows;
	
	public SudokuReader(int size, int difficulty) {
		getXML(size, difficulty);
	}
	
	/**
	 * The method getSudoku used the field 'String Sudoku' to convert it to and
	 * return a multidimensional array of integers.
	 * 
	 * @return a multidimensional array of integers
	 */
	public int[][] getSudoku() {
		int[][] temp = new int[numberOfRows][numberOfColumns];
		int leftOvers = 0;
		
		for(int i = 0; i < numberOfRows; i++) {
			for (int j = 0; j < numberOfColumns; j++) {
				temp[i][j] = Character.getNumericValue(sudoku.charAt(leftOvers++));
			}
		}
		
		return temp;
	}
	
	/**
	 * The method getXML used the file "XML/SudokuCollection.xml" to randomly search
	 * for a sudoku with the parameter size and parameter difficulty as constraints. 
	 * Once found the method sets the field 'String sudoku' with the sudoku of the file.
	 * 
	 * @param size	The size of a sudoku.
	 * @param difficulty	The level of difficulty of a sudoku.
	 */
	private void getXML(final int size, final int difficulty) {
		// BUG: What if the given value in parameter size doesn't exists in file?
		// BUG: What if the given value in parameter difficulty doesn't exists in file? 
		
		try {
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser saxParser = saxParserFactory.newSAXParser();
	
			DefaultHandler defaultHandler = new DefaultHandler() {
				boolean bSudoku = false;
				boolean bSize = false;
				boolean bDifficulty = false;
				int sudokuID = 0;
						
				public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
					if(qName.equalsIgnoreCase("size")) {
						int id = Integer.parseInt(attributes.getValue("id"));				
						bSize = size == id;
					}
					
					if(qName.equalsIgnoreCase("difficulty") && bSize) {
						int level = Integer.parseInt(attributes.getValue("level"));
	
						if(difficulty == level) {
							Random random = new Random();
							sudokuID = random.nextInt(Integer.parseInt(attributes.getValue("amount"))) + 1;
							bDifficulty = true;
						}
					}
					
					if (qName.equalsIgnoreCase("sudoku") && bSize && bDifficulty) {
						int id = Integer.parseInt(attributes.getValue("id"));
						bSudoku = id == sudokuID;
					}
				}
				
				public void characters(char ch[], int start, int length) throws SAXException {
					if (bSudoku) {
						sudoku = new String(ch, start, length).trim();
						numberOfColumns = size;
						numberOfRows = size;
					}
				}
		
				public void endElement(String uri, String localName, String qName) throws SAXException {
					if (qName.equalsIgnoreCase("sudoku") && bSudoku) {
						bSudoku = false;
						bSize = false;
						bDifficulty = false;
					}
				}
			};
			
			saxParser.parse("XML/SudokuCollection.xml", defaultHandler);
		} catch (Exception e) {
				e.printStackTrace();
			}
	 	}
}