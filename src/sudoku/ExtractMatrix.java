/**
 * 
 */
package sudoku;

import java.util.ArrayList;

/**
 * @author Justin Yeung
 *
 */
public interface ExtractMatrix {
	
	/*
	 * ArrayCopy from a 2D array to 1D array
	 */
	public Integer[] extract();
	
	/*
	 * Modified matrix to full matrix
	 */
	public Integer[] toMatrix(int currentIndex);

}

abstract class Extract implements ExtractMatrix
{
	protected int source[][];
	protected int rowStart;
	protected int colStart;
	protected int size;
	
	public Extract(int[][] source, int rowStart, int colStart, int size)
	{
		this.source = source;
		this.rowStart = rowStart;
		this.colStart = colStart;
		this.size = size;
	}
}

class Extract3x3 extends Extract
{
	
	
	// Constructor for 3x3
	public Extract3x3(int[][] source, int rowStart, int colStart, int size)
	{
		super(source, rowStart, colStart, size);
	}
	
	@Override
	public Integer[] extract() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for (int i = rowStart; i < 3 + rowStart; i++) // Starting row
		{
			for (int j = colStart; j < 3 + colStart; j++) // Starting col
			{
				list.add(source[i][j]);
			}
		}
		return list.toArray(new Integer[size]);
	}

	@Override
	public Integer[] toMatrix(int currentIndex) {


		Integer[] matrix = new Integer[size];
		matrix = extract(); // Source, start, dest, size
		
		switch (colStart) // Move to the next 3 columns
		{
		case 0:
			colStart = 3;
			break;
		case 3:
			colStart = 6;
			break;
		}
		
		if (currentIndex+1 == 3) // Move to the next 3 rows and move columns back to 1;
		{
			rowStart = 3;
			colStart = 0;
		}
		else if (currentIndex+1 == 6) // Move to the next 3 rows and move columns back to 1;
		{
			rowStart = 6;
			colStart = 0;
		}
		return matrix;
	}
	
}

class Extract4x3 extends Extract
{

	// Constructor for 4x3
	public Extract4x3(int[][] source, int rowStart, int colStart, int size)
	{
		super(source, rowStart, colStart, size);
	}
	
	@Override
	public Integer[] extract() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		// Loop 4x3
		for (int i = rowStart; i < 4 + rowStart; i++) // Starting row
		{
			for (int j = colStart; j < 3 + colStart; j++) // Starting col
			{
				list.add(source[i][j]);
			}
		}
		return list.toArray(new Integer[size]);
	}

	@Override
	public Integer[] toMatrix(int currentIndex) {


		Integer[] matrix = new Integer[size];
		matrix = extract(); // Source, start, dest, size
		
		switch (colStart) // Move to the next 3 columns
		{
		case 0:
			colStart = 3;
			break;
		case 3:
			colStart = 6;
			break;
		case 6:
			colStart = 9;
			break;
		}
		
		
		
		if (currentIndex+1 == 4) // Move to the next 4 rows and move columns back to 1;
		{
			rowStart = 4;
			colStart = 0;
		}
		else if (currentIndex+1 == 8) // Move to the next 4 rows and move columns back to 1;
		{
			rowStart = 8;
			colStart = 0;
		}
		return matrix;
	}
	
}
