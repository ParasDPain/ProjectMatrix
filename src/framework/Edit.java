package framework;

import framework.Matrix;

public class Edit {

	private static Matrix error = new Matrix(0,0);

	// Multiplies the matrix with a scalar value
	public static Matrix ScalarProduct(Matrix m, double s) {
		// Pass -1 to change the matrix's sign
		if(m.getValidity()) {

			int row = m.getRow();
			int column = m.getColumn();
			double[][] grid = m.getGrid();
			double[][] newGrid = new double[row][column];

			// Simple cyclic increment
			// Cycle through the row for each column
			for(int ro = 0; ro < row; ro++) {
				for (int col = 0; col < column; col++) {
					newGrid[ro][col] = s * grid[ro][col];
				}// --Column loop end
			}// --Row loop end

			Matrix result = new Matrix(newGrid);
			return result;
		}
		return error;
	}

	// Swap rows with columns and update the element list
	public static Matrix Transpose(Matrix m) {
		if(m.getValidity()) {

			int row = m.getRow();
			int column = m.getColumn();
			double[][] grid = m.getGrid();
			double[][] newGrid = new double[column][row];

			// Simple cyclic increment
			// Cycle through the row for each column
			for(int ro = 0; ro < row; ro++) {
				for (int col = 0; col < column; col++) {
					newGrid[col][ro] = grid[ro][col];
				}// --Column loop end
			}// --Row loop end

			Matrix result = new Matrix(newGrid);
			return result;
		}
		return error;
	}

	// Method can't handle (0,0) due to the limitations in ChangeSize()
	// Find a better one
	public static Matrix Submatrix(Matrix m, int delRow, int delColumn) {
		if(m.getValidity()) {

			int row = m.getRow();
			int column = m.getColumn();
			double[][] grid = m.getGrid();
			double[][] newGrid = grid;


			// Shifting rows to top
			for(int ro = delRow; ro+1 < row; ro++) {
				for(int col = 0; col < column; col++) {
					newGrid[ro][col] = grid[ro+1][col];
				}
			}

			// Shifting columns to left
			for(int ro = 0; ro < row; ro++) {
				for(int col = delColumn; col+1 < column; col++) {
					newGrid[ro][col] = grid[ro][col+1];
				}
			}

			Matrix s = new Matrix(newGrid);
			s.ChangeSize(row-1, column-1);
			return s;
		}
		return error;
	}
	
	//Minor(first, second, etc) Cofactors, Determinant, Inverse
}
