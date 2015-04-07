package framework;

// Add error checks (use false returns in boolean functions)
public class Matrix {

	public Matrix(int r, int c) {
		// Constructor
		if(r > 0 && c > 0) {
			setRow(r);
			setColumn(c);
			double[][] matrix = new double[row][column];
			setGrid(matrix);
			isValid = true;
		} else {
			setRow(0);
			setColumn(0);
			double[][] matrix = new double[row][column];
			setGrid(matrix);
			isValid = false;
		}

	}

	// Error check
	public boolean isValid;

	// Public property Row
	private int row;
	public int getRow() {
		return row;
	}
	public void setRow(int value) {
		row = value;
	}

	// Public property Column
	private int column;
	public int getColumn() {
		return column;
	}
	public void setColumn(int value) {
		column = value;
	}

	// Public property Grid
	private double[][] grid;
	public double[][] getGrid() {
		return grid;
	}
	public void setGrid(double[][] value) {
		grid = value;

	}

	public boolean AddElement(int posX, int posY, double value) {
		// Add new element to the grid at (x,y)

		double[][] matrix = getGrid();
		matrix[posX][posY] = value;
		setGrid(matrix);
		return true;
	}

	public boolean RemoveElement(int x, int y) {
		// Removes element at (x,y) in the grid

		double[][] matrix = getGrid();
		matrix[x][y] = 0;
		setGrid(matrix);
		return true;
	}

	public boolean ChangeSize(int newR, int newC) {
		// Changes row, column and grid size
		int oldCol = getColumn();
		int oldRow = getRow();
		double[][] oldMatrix = getGrid();
		double[][] newMatrix = new double[newR][newC];

		// Simple cyclic increment
		// Cycle through the row for each column	
		for(int ro = 0; ro < oldRow; ro++) {
			for (int col = 0; col < oldCol; col++) {
				// To catch changes when r or c or alternatively great n lower
				try {
					newMatrix[ro][col] = oldMatrix[ro][col];
				} catch(IndexOutOfBoundsException e) {
					continue;
				}
			}// --Column loop end
		}// --Row loop end
		setRow(newR);
		setColumn(newC);
		setGrid(newMatrix);
		return true;
	}

	public boolean Transpose() {
		// Swaps rows with columns and updates the elements list
		double[][] Matrix = getGrid();
		double[][] newMatrix = new double[column][row];

		// Simple cyclic increment
		// Cycle through the row for each column
		for(int ro = 0; ro < row; ro++) {
			for (int col = 0; col < column; col++) {
				newMatrix[col][ro] = Matrix[ro][col];
			}// --Column loop end
		}// --Row loop end
		setGrid(newMatrix);
		return true;
	}
}
