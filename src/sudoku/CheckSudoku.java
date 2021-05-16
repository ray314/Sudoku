/**
 * 
 */
package sudoku;

/**
 * @author Justin Yeung
 * Check if the submitted board is correct
 */
public class CheckSudoku {
	public final static boolean check(Integer[] submission, int[] answer)
	{
		int size = submission.length;
		
		for (int i = 0; i < size; i++) // Check all elements to match
		{
			
			if (submission[i] != answer[i])
			{
				return false; // If one value does not match, return false
			}

		}
		
		return true;
	}
}
