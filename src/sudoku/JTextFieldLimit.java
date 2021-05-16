/**
 * 
 */
package sudoku;


import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * @author Justin Yeung
 * Set the character limit of JTextField
 */
public final class JTextFieldLimit extends PlainDocument {

	 private int limit;
	 public JTextFieldLimit(int limit) {
	    super();
	    this.limit = limit;
	  }

	  public JTextFieldLimit(int limit, boolean upper) {
	    super();
	    this.limit = limit;
	  }

	  @Override
	  public void insertString(int offs, String str, AttributeSet a)
	      throws BadLocationException {
	    if (str == null)
	      return;

	    if (((getLength() + str.length()) <= limit) && (str.matches(".*\\d.*"))) { // Check whether it exceeds the limit or numbers only
	      super.insertString(offs, str, a);
	    }
	  }
	

}
