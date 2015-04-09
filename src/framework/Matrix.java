package framework;

// Add error checks (use false returns in boolean functions)
public class Matrix {

	/* ------------------------
	   Class variables
	 * ------------------------ */

	// Private property Row
	private int row;
	public int getRow() {
		return row;
	}
	private void setRow(int value) {
		this.row = value;
	}

	// Private property Column
	private int column;
	public int getColumn() {
		return column;
	}
	private void setColumn(int value) {
		this.column = value;
	}

	// Private property Grid
	private double[][] grid;
	public double[][] getGrid() {
		return grid;
	}
	private void setGrid(double[][] value) {
		this.grid = value;

	}

	// Private property Validity
	// Validity of the object
	private boolean Validity = true;
	public boolean getValidity() {
		return Validity;
	}
	private void setValidity(boolean value) {
		this.Validity = value;
	}

	/* ------------------------
	   Constructors
	 * ------------------------ */

	// Construct a matrix of zeros with r rows and c columns
	public Matrix(int r, int c) {
		// Constructor
		if (IsSizeValid(r,c)) {

			setRow(r);
			setColumn(c);
			double[][] matrix = new double[row][column];
			setGrid(matrix);
		}
	}

	// Construct a matrix r rows and c columns with a repeated value
	public Matrix(int r, int c, double elementValue) {
		// Constructor
		if(IsSizeValid(r,c)) {

			setRow(r);
			setColumn(c);

			// Simple cyclic increment
			// Cycle through the row for each column	
			for(int ro = 0; ro < row; ro++) {
				for (int col = 0; column < c; col++) {
					grid[ro][col] = elementValue;
				}// --Column loop end
			}// --Row loop end
		}

	}

	// Construct a matrix with a given 2-D array grid
	public Matrix(double[][] grid) {
		// Constructor
		if(IsSizeValid(grid.length, grid[0].length) && IsGridValid(grid)) {

			setRow(grid.length);
			setColumn(grid[0].length);
			setGrid(grid);
		}

	}

	/* ------------------------
	   Public Methods
	 * ------------------------ */

	// Add new element to the grid at (x,y)
	// Returns false if the grid coordinates (x,y) did not exist
	public boolean AddElement(int posX, int posY, double value) {

		try {
			grid[posX][posY] = value;
		} catch (IndexOutOfBoundsException e) {;
		return false;
		}

		return true;
	}

	// Removes element at (x,y) in the grid
	// Returns false if the grid coordinates (x,y) did not exist
	public boolean RemoveElement(int posX, int posY) {

		try {
			grid[posX][posY] = 0;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}

		return true;
	}

	// Change row, column and grid size
	public boolean ChangeSize(int newR, int newC) {
		int oldCol = getColumn();
		int oldRow = getRow();
		double[][] oldGrid = getGrid();
		double[][] newGrid = new double[newR][newC];

		// Check for the smallest r,c pair and set that as the limit
		// Always cycle till the smallest limit otherwise the array will overflow
		if (newR >= oldRow || newC >= oldCol) {

			// Simple cyclic increment
			// Cycle through the row for each column	
			for(int ro = 0; ro < oldRow; ro++) {
				for (int col = 0; col < oldCol; col++) {
					newGrid[ro][col] = oldGrid[ro][col];
				}// --Column loop end
			}// --Row loop end

		} else {

			// Simple cyclic increment
			// Cycle through the row for each column	
			for(int ro = 0; ro < newR; ro++) {
				for (int col = 0; col < newC; col++) {
					newGrid[ro][col] = oldGrid[ro][col];
				}// --Column loop end
			}// --Row loop end

		}
		if(IsSizeValid(newR, newC)) {
			setRow(row);
			setColumn(column);
			setGrid(newGrid);
			return true;
		} else
			return false;
	}

	// Swap rows with columns and update the element list
	public boolean Transpose() {
		double[][] Matrix = getGrid();
		double[][] newGrid = new double[column][row];

		// Simple cyclic increment
		// Cycle through the row for each column
		for(int ro = 0; ro < row; ro++) {
			for (int col = 0; col < column; col++) {
				newGrid[col][ro] = Matrix[ro][col];
			}// --Column loop end
		}// --Row loop end

		setRow(column);
		setColumn(row);
		setGrid(newGrid);
		return true;
	}

	/* ------------------------
	   Private Methods
	 * ------------------------ */

	// Only accepts positive values for the row and column values
	private boolean IsSizeValid(int r, int c) {
		if(r > 0 || c > 0) {
			return true;
		}
		setValidity(false);
		return false;

	}

	// Only accepts array grids with equal sized rows
	private boolean IsGridValid(double[][] grid) {
		for (int ro=0; ro<row; ro++) {
			if (grid[ro].length != column) {
				setValidity(false);
				return false;
			}
		}
		return true;
	}
}
