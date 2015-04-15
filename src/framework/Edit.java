package framework;

import framework.Matrix;

public class Edit {

	private static Matrix error = new Matrix(0,0);

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

	/* In construction
	 * Try parsing the grid to a string, manipulation will be easy
	 * If successful; implement it in Matrix.ChangeSize()
	 public static Matrix Submatrix(Matrix m, int delRow, int delColumn) {
		if(m.getValidity()) {

			int row = m.getRow();
			int column = m.getColumn();
			// double[][] grid = m.getGrid();
			boolean[][] marker = new boolean[row][column];
			double[][] newGrid = new double[row-1][column-1];

			// Marking the grid false for every (r,c) value needed to be removed
			for(int ro = 0; ro < row; ro++) {
				marker[ro][delColumn] = false;
			}
			for (int col = 0; col < column; col++) {
				marker[delRow][col] = false;
			}


			// Simple cyclic increment
			// Cycle through the row for each column
			int x = 0;
			for(int ro = 0; ro < row-1; ro++) {

				int y = 0;
				for (int col = 0; col < column-1; col++) {

					if(marker[ro][col]) {
						newGrid[ro][col] = grid[x][y];
					} else {
						y++;
						break;
					}
					y++;
				}// --Column loop end
			x++;
			}// --Row loop end


			Matrix s = new Matrix(newGrid);
			return s;
		}
		return error;
	} */
}
