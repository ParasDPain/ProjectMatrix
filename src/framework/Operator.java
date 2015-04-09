package framework;

import framework.Matrix;

public class Operator {
	
/* ------------------------
	   Class variables
 * ------------------------ */
	
	private static Matrix error = new Matrix(-1,-1);

/* ------------------------
	   Public Methods
 * ------------------------ */

	public static Matrix Plus(Matrix leftMatrix, Matrix rightMatrix) {
		// local data for easier use
		int lRow = leftMatrix.getRow();
		int lColumn = leftMatrix.getColumn();
		int rRow = rightMatrix.getRow();
		int rColumn = rightMatrix.getColumn();

		// Addition/Subtraction can only be performed on similar matrices
		if(lRow == rRow && lColumn == rColumn) {

			// Matrix grid extraction for the operation
			double[][] newGrid = new double[lRow][lColumn];
			double[][] leftGrid = leftMatrix.getGrid();
			double[][] rightGrid = rightMatrix.getGrid();

			// Simple cyclic increment
			for(int ro = 0; ro < lRow; ro++) {
				for (int col = 0; col < lColumn; col++) {
					newGrid[ro][col] = leftGrid[ro][col] + rightGrid[ro][col];
				}// --Column loop end
			}// --Row loop end

			Matrix result = new Matrix(newGrid);
			return result;
		}
		return error;
	}

	public static Matrix Minus(Matrix leftMatrix, Matrix rightMatrix) {
		// local data for easier use
		int lRow = leftMatrix.getRow();
		int lColumn = leftMatrix.getColumn();
		int rRow = rightMatrix.getRow();
		int rColumn = rightMatrix.getColumn();

		// Addition/Subtraction can only be performed on similar matrices
		if(lRow == rRow && lColumn == rColumn) {

			// Matrix grid extraction for the operation
			double[][] newGrid = new double[lRow][lColumn];
			double[][] leftGrid = leftMatrix.getGrid();
			double[][] rightGrid = rightMatrix.getGrid();

			// Simple cyclic increment
			for(int ro = 0; ro < lRow; ro++) {
				for (int col = 0; col < lColumn; col++) {
					newGrid[ro][col] = leftGrid[ro][col] - rightGrid[ro][col];
				}// --Column loop end
			}// --Row loop end

			Matrix result = new Matrix(newGrid);
			return result;
		}
		return error;
	}

	public static Matrix Multiply(Matrix leftMatrix, Matrix rightMatrix) {
		int lRow = leftMatrix.getRow();
		int lColumn = leftMatrix.getColumn();
		int rRow = rightMatrix.getRow();
		int rColumn = rightMatrix.getColumn();

		if(lColumn == rRow) {

			// Matrix grid extraction for the operation
			double[][] newGrid = new double[lRow][rColumn];
			double[][] leftGrid = leftMatrix.getGrid();
			double[][] rightGrid = rightMatrix.getGrid();

			// Extended cyclic increment
			for(int ro = 0; ro < lRow; ro++) {
				for (int col = 0; col < rColumn; col++) {
					for (int x = 0; x<lRow; x++) {
						newGrid[ro][col] += leftGrid[ro][x] * rightGrid[x][col];
					}// --X loop end
				}// --Column loop end
			}// --Row loop end
			
			Matrix result = new Matrix(newGrid);
			return result;
		}
		return error;
	}

	public static Matrix Divide(Matrix leftMatrix, Matrix rightMatrix) {
		// local data for easier use
		int lRow = leftMatrix.getRow();
		int lColumn = leftMatrix.getColumn();
		int rRow = rightMatrix.getRow();

		// Addition/Subtraction can only be performed on similar matrices
		if(lColumn == rRow) {
			// Matrix grid extraction for the operation
			double[][] newGrid = new double[lRow][lColumn];
			double[][] rightGrid = rightMatrix.getGrid();

			// Simple cyclic increment
			for(int ro = 0; ro < lRow; ro++) {
				for (int col = 0; col < lColumn; col++) {
					newGrid[ro][col] = 1/rightGrid[ro][col];
				}// --Column loop end
			}// --Row loop end

			// Forwards operation after preparing the rightMatrix
			Matrix result = new Matrix(newGrid);
			return Multiply(leftMatrix, result);
		}
		return error;
	}
}
