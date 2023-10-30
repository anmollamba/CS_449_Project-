package application;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import application.Board.Cell;

public class JunitTests {
	
//	MAKE SURE THE BOARD IS BEING ASSIGNED THE CORRECT NUMBER OF COLUMNS
	@Test
	public void checkCols() {
		Board test_board = new Board();
		test_board.setBoardSize(5);
		
		assertEquals(5, test_board.getCols());
	}

// MAKE SURE THE BOARD IS BEIGN ASSIGNED THE CORRECT NUMBER OF COLUMNS	
	@Test
	public void checkRows() {
		Board test_board = new Board();
		test_board.setBoardSize(3);
		
		assertEquals(3, test_board.getRows());
	}
	
// MAKE SURE THE BOARD IS BEING CREATED AS A 'NxN' BOARD
	@Test
	public void checkNxNBoard() {
		Board test_board = new Board();
		test_board.setBoardSize(6);
		
		assertEquals(6, test_board.getCols());
		assertEquals(6, test_board.getRows());
	}
	
	@Test
	public void invalidBoard() {
		Board test_board = new Board();
		test_board.setBoardSize(10);
		
		assertFalse(test_board.getRows() == 10);
		assertFalse(test_board.getCols() == 10);
	}
	
// TEST TO MAKE SURE THE INITIAL BOARD IS EMPTY
	@Test
	public void testInit() {
		Board test_board = new Board();
		test_board.setBoardSize(3);
		for (int row = 0; row < test_board.getRows(); row++) {
			for (int col = 0; col < test_board.getCols(); col++) {
				//grid[row][col] = Cell.EMPTY;
				assertEquals(Cell.EMPTY, test_board.getCell(row, col));
			}
		}
	}
	
// TEST TO MAKE SURE AN 'S' IS PLACE
	@Test
	public void sIsPlaced() {
		Board board = new Board();
		board.setBoardSize(6);
		board.makeSMove(5, 5);
		
		assertEquals(Cell.S, (board.getCell(5, 5)));
	}


// TEST TO MAKE SURE AN 'O' IS PLACED
	@Test
	public void oIsPlaced() {
		Board board = new Board();
		board.setBoardSize(8);
		board.makeOMove(3, 6);
		
		assertEquals((Cell.O), (board.getCell(3, 6)));
	}

	
	@Test
	public void simpleGameSOS() {
		Board board = new Board();
		board.setBoardSize(4);
		board.setMode(0);
		board.makeSMove(0, 0);
		board.makeOMove(0, 1);
		board.makeSMove(0, 2);
		
		assertEquals(true, board.sgSOSCheck(0,2));
	}
	
// Test to check and make sure that the checkIfFull() method properly returns true when the board is full
	@Test
	public void boardFullTest() {
		Board board = new Board();
		board.setBoardSize(3);
		board.makeSMove(0, 0);
		board.makeSMove(0, 1);
		board.makeOMove(0, 2);
		board.makeSMove(1, 0);
		board.makeSMove(1, 1);
		board.makeOMove(1, 2);
		board.makeSMove(2, 0);
		board.makeOMove(2, 1);
		board.makeSMove(2, 2);
		
		assertEquals(true, board.checkIfFull());
	}
	
// Test to check and make sure that the checkIfFull() method properly returns false when the board is not full
	@Test
	public void boardNotFull() {
		Board board = new Board();
		board.setBoardSize(5);
		board.makeSMove(1, 3);
		board.makeOMove(2, 0);
		
		assertEquals(false, board.checkIfFull());
	}
	
	
	//Checking for a horizontal SOS where an S is placed on the left side of an O
	@Test
	public void horCheck1() {
		Board board = new Board();
		board.setBoardSize(6);
		board.makeSMove(0, 5);
		assertEquals(false, board.checkHor(0, 5));
		board.makeOMove(0, 4);
		assertEquals(false, board.checkHor(0, 4));
		board.makeSMove(0, 3);
		assertEquals(true, board.checkHor(0, 3));
		
	}
	
	//Checking for a horizontal SOS where an S is placed on the right side of an O
	@Test 
	public void horCheck2() {
		Board board = new Board();
		board.setBoardSize(6);
		board.makeSMove(0, 3);
		assertEquals(false, board.checkHor(0, 3));
		board.makeOMove(0, 4);
		assertEquals(false, board.checkHor(0, 4));
		board.makeSMove(0, 5);
		assertEquals(true, board.checkHor(0, 5));
	}
	
	//Checking for a horizontal SOS where an O is placed between two S's
	@Test
	public void horCheck3() {
		Board board = new Board();
		board.setBoardSize(6);
		board.makeSMove(0, 3);
		assertEquals(false, board.checkHor(0, 3));
		board.makeSMove(0, 5);
		assertEquals(false, board.checkHor(0, 5));
		board.makeOMove(0, 4);
		assertEquals(true, board.checkHor(0, 4));

	}
	
	//Checking for a vertical SOS where an SOS is completed by placing an S above an O
	@Test
	public void vertCheck1() {
		Board board = new Board();
		board.setBoardSize(7);
		board.makeSMove(3, 3);
		assertEquals(false, board.checkVert(3, 3));
		board.makeOMove(2, 3);
		assertEquals(false, board.checkVert(2, 3));
		board.makeSMove(1, 3);
		assertEquals(true, board.checkVert(1, 3));
		
	}
	
	//Checking for a vertical SOS where an SOS is completed by placing an S below an O
	@Test
	public void vertCheck2() {
		Board board = new Board();
		board.setBoardSize(7);
		board.makeSMove(1, 3);
		assertEquals(false, board.checkVert(1, 3));
		board.makeOMove(2, 3);
		assertEquals(false, board.checkVert(2, 3));
		board.makeSMove(3, 3);
		assertEquals(true, board.checkVert(3, 3));
		
	}
	
	//Checking for a vertical SOS where an O is placed between two S's
	@Test
	public void vertCheck3() {
		Board board = new Board();
		board.setBoardSize(7);
		board.makeSMove(1, 3);
		assertEquals(false, board.checkVert(1, 3));
		board.makeSMove(3, 3);
		assertEquals(false, board.checkVert(3, 3));
		board.makeOMove(2, 3);
		assertEquals(true, board.checkVert(2, 3));
	}
	
	//Checking for a right-diagonal SOS where an S is placed in the upper right corner
	@Test
	public void rightDiagCheck1() {
		Board board = new Board();
		board.setBoardSize(8);
		board.makeSMove(5, 4);
		assertEquals(false, board.checkRightDiag(5, 4));
		board.makeOMove(4, 5);
		assertEquals(false, board.checkRightDiag(4, 5));
		board.makeSMove(3, 6);
		assertEquals(true, board.checkRightDiag(3, 6));
		
	}
	
	//Checking for a right-diagonal SOS where an S is placed in the lower left corner
	@Test
	public void rightDiagCheck2() {
		Board board = new Board();
		board.setBoardSize(8);
		board.makeSMove(3, 6);
		assertEquals(false, board.checkRightDiag(3, 6));
		board.makeOMove(4, 5);
		assertEquals(false, board.checkRightDiag(4, 5));
		board.makeSMove(5, 4);
		assertEquals(true, board.checkRightDiag(5, 4));
	}
	
	//Checking for a right-diagonal SOS where an O is placed between the S's
	@Test
	public void rightDiagCheck3() {
		Board board = new Board();
		board.setBoardSize(8);
		board.makeSMove(3, 6);
		assertEquals(false, board.checkRightDiag(3, 6));
		board.makeSMove(5, 4);
		assertEquals(false, board.checkRightDiag(5, 4));
		board.makeOMove(4, 5);
		assertEquals(true, board.checkRightDiag(4, 5));
	}
	
	//Checking for a left-diagonal SOS where an S is placed in the upper left corner
	@Test
	public void leftDiagCheck1() {
		Board board = new Board();
		board.setBoardSize(8);
		board.makeSMove(2, 7);
		assertEquals(false, board.checkLeftDiag(2, 7));
		board.makeOMove(1, 6);
		assertEquals(false, board.checkLeftDiag(1, 6));
		board.makeSMove(0, 5);
		assertEquals(true, board.checkLeftDiag(0, 5));
	}
	
	//Checking for a left-diagonal SOS where an S is placed in the lower right corner
	@Test
	public void leftDiagCheck2() {
		Board board = new Board();
		board.setBoardSize(8);
		board.makeSMove(0, 5);
		assertEquals(false, board.checkLeftDiag(0, 5));
		board.makeOMove(1, 6);
		assertEquals(false, board.checkLeftDiag(1, 6));
		board.makeSMove(2, 7);
		assertEquals(true, board.checkLeftDiag(2, 7));
	}
	
	//Checking for a left-diagonal SOS where an O is placed between the S's
	@Test
	public void leftDiagCheck3() {
		Board board = new Board();
		board.setBoardSize(8);
		board.makeSMove(0, 5);
		assertEquals(false, board.checkLeftDiag(0, 5));
		board.makeSMove(2, 7);
		assertEquals(false, board.checkLeftDiag(2, 7));
		board.makeOMove(1, 6);
		assertEquals(true, board.checkLeftDiag(1, 6));
	}
}
