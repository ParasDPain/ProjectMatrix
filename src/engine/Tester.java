package engine;

import java.util.Scanner;

import framework.Matrix;
import framework.Operator;

public class Tester {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner (System.in);
		Matrix m;
		Matrix n;
		
		m = inputMatrix(scanner);
		n = inputMatrix(scanner);
		
		displayMatrix(m);
		displayMatrix(n);
		
		test_Operations(m, n);
		
		scanner.close();
		
	}

	private static void test_Operations(Matrix m, Matrix n) {
		Operator test = new Operator();
		
		System.out.println("SUM");
		displayMatrix(test.Plus(m,n));
		
		System.out.println("DIFFERENCE");
		displayMatrix(test.Minus(m,n));
		
		System.out.println("PRODUCT");
		displayMatrix(test.Multiply(m,n));
		
		System.out.println("QUOTIENT");
		displayMatrix(test.Divide(m,n));
	}

	@SuppressWarnings("unused")
	private static void test_Transpose(Matrix m) {
		System.out.println("And it's transpose is: ");
		m.Transpose();
	}

	@SuppressWarnings("unused")
	private static void test_ChangeSize(Scanner scanner, Matrix m) {
		// Change grid size
		System.out.println("Enter the new row size: ");
		int newRow = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter the new column size: ");
		int newColumn = Integer.parseInt(scanner.nextLine());
		
		m.ChangeSize(newRow, newColumn);
	}

	// Input and Initialize matrix instance
	private static Matrix inputMatrix(Scanner scanner) {
		Matrix m;
		// Initialize matrix object
		System.out.println("Enter the number of rows: ");
		int row = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter the number of columns: ");
		int column = Integer.parseInt(scanner.nextLine());
		
		m = new Matrix(row, column);
		
		// Initialize matrix elements
		// User enters each row as a string until the last column is reached
		// String is split into array and plugged in the matrix after parsing
		for (int ro=0; ro<row; ro++) {
			System.out.println("Enter " + column + " values for this column, separated by commas: ");
			String input = scanner.nextLine();
			
			String[] thisRow = null;
				try {
					thisRow = input.split(",", column);
				} catch(IndexOutOfBoundsException e) {
					System.out.println(e.getMessage());			
				}
				
				for (int col=0; col<column; col++) {
					double value = Double.parseDouble(thisRow[col]);
					m.AddElement(ro, col, value);
				}// --Row loop end
		}// --Column loop end
		return m;
	}

	// Display object instance
	private static void displayMatrix(Matrix m) {
		double[][] myMatrix;
		int row = m.getRow();
		int column = m.getColumn();

		myMatrix = m.getGrid();
		System.out.println("Your matrix is: ");
		for (int i=0; i<row; i++) {
			for (int j=0; j<column; j++) {
				System.out.print(String.valueOf(myMatrix[i][j]) + "  ");
			}// --Column loop end
			
			System.out.println();
		}// --Row loop end
	}

}
