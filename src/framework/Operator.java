package framework;

// index: 1-Plus; 2-Minus; 3-Multiply; 4-Divide;
public class Operator {

	// private Matrix result;
	// private Matrix error = null;

	public Matrix Plus(Matrix leftMatrix, Matrix rightMatrix) {
		// local data for easier use
		int lRow = leftMatrix.getRow();
		int lColumn = leftMatrix.getColumn();
		int rRow = leftMatrix.getRow();
		int rColumn = leftMatrix.getColumn();

		// Addition/Subtraction can only be performed on similar matrices
		if(lRow == rRow && lColumn == rColumn) {
			Matrix result = new Matrix(lRow, lColumn);

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

			result.setGrid(newGrid);
			return result;
		}
		return null;
	}

	public Matrix Minus(Matrix leftMatrix, Matrix rightMatrix) {
		// local data for easier use
		int lRow = leftMatrix.getRow();
		int lColumn = leftMatrix.getColumn();
		int rRow = leftMatrix.getRow();
		int rColumn = leftMatrix.getColumn();

		// Addition/Subtraction can only be performed on similar matrices
		if(lRow == rRow && lColumn == rColumn) {
			Matrix result = new Matrix(lRow, lColumn);

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

			result.setGrid(newGrid);
			return result;
		}
		return null;
	}

	public Matrix Multiply(Matrix leftMatrix, Matrix rightMatrix) {
		int lRow = leftMatrix.getRow();
		int lColumn = leftMatrix.getColumn();
		int rRow = leftMatrix.getRow();
		int rColumn = leftMatrix.getColumn();

		if(lColumn == rRow) {
			Matrix result = new Matrix(lColumn, rRow);

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
			
			result.setGrid(newGrid);
			return result;
		}
		return null;
	}

	public Matrix Divide(Matrix leftMatrix, Matrix rightMatrix) {
		// local data for easier use
		int lRow = leftMatrix.getRow();
		int lColumn = leftMatrix.getColumn();
		int rRow = leftMatrix.getRow();
		int rColumn = leftMatrix.getColumn();

		// Addition/Subtraction can only be performed on similar matrices
		if(lRow == rRow && lColumn == rColumn) {
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
			rightMatrix.setGrid(newGrid);
			return Multiply(leftMatrix, rightMatrix);
		}
		return null;
	}
}
