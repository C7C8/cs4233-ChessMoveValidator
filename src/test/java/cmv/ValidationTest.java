/*
 * Copyright (c) 2019 Christopher Myers.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * ======
 *
 * This file was developed as part of CS 4233: Object-Oriented Analysis &
 * Design, at Worcester Polytechnic Institute.
 */

package cmv;

import cmv.pieces.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static cmv.ChessPiece.PieceType.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a sample of the type of tests that will be run on your code. You should make sure
 * that these run on your assignment code before submitting your work.
 * @version Mar 9, 2019
 */
class ValidationTest
{
	private static ChessBoard board = null;

	@BeforeAll
	static void setup() {
		// Create a test board from an easily-parsed FEN string, because setting up a board by code is a pain.
		// Parsing algorithm is my own, the format for FEN strings can be found here:
		// https://en.wikipedia.org/wiki/Forsyth%E2%80%93Edwards_Notation

		final String testBoard = "ppq5/1P1b4/3B4/3b2K1/8/r7/4N3/R1Q1B3";
		ArrayList<Object> sp = new ArrayList<>();
		int y = 8;
		for (String row: testBoard.split("/")) {
			char x = 'a';
			for (int i = 0; i < row.length(); i++) {
				final char symbol = row.charAt(i);
				// Decode letter and add to board
				if (Character.isAlphabetic(symbol)) {
					sp.add(SquareFactory.makeSquare(x, y));
					final ChessPiece.PieceColor color = Character.isUpperCase(symbol) ? ChessPiece.PieceColor.WHITE : ChessPiece.PieceColor.BLACK;

					ChessPiece newPiece;
					switch (Character.toLowerCase(symbol)) {
						case 'p':
							newPiece = new Pawn(color);
							break;
						case 'n':
							newPiece = new Knight(color);
							break;
						case 'b':
							newPiece = new Bishop(color);
							break;
						case 'r':
							newPiece = new Rook(color);
							break;
						case 'q':
							newPiece = new Queen(color);
							break;
						default: // King, this just makes an IDE warning go away
							newPiece = new King(color);
							break;
					}
					sp.add(newPiece);
				}
				else {
					// Number; skip ahead that many spaces.
					x += Character.getNumericValue(symbol);
					x--; // Decrease to counter increment down below
				}
				x++;
			}
			y--;
		}
		board = makeBoard(sp.toArray());
	}

	/**
	 * Test to make sure pieces can't move outside the board
	 */
	@Test
	void outOfBounds() {
		// Rook at A1 to (A-1)1
		final Square from = SquareFactory.makeSquare('a', 1);
		final Square to = SquareFactory.makeSquare((char)('a'-1), 1);
		assertThrows(CMVException.class, () -> MoveValidator.canMove(board, from, to));

		// Queen at C8 to C9
		final Square from2 = SquareFactory.makeSquare('c', 8); // Locals in a lambda have to be final or effectively final
		final Square to2 = SquareFactory.makeSquare('c', 9);
		assertThrows(CMVException.class, () -> MoveValidator.canMove(board, from2, to2));
	}

	@Test
	void bishop() {
		// Bishop at E1 to B4 -- valid
		Square from = SquareFactory.makeSquare('e', 1);
		Square to = SquareFactory.makeSquare('b', 4);
		Assertions.assertEquals(BISHOP, board.getPieceAt(from).getPieceType());
		Assertions.assertTrue(MoveValidator.canMove(board, from, to));

		//Bishop at E1 to H4 -- valid
		to = SquareFactory.makeSquare('h', 4);
		Assertions.assertTrue(MoveValidator.canMove(board, from, to));

		//Bishop at D6 to C5 -- valid
		from = SquareFactory.makeSquare('d', 6);
		to = SquareFactory.makeSquare('c', 5);
		Assertions.assertTrue(MoveValidator.canMove(board, from, to));

		//Bishop at D6 to D5 -- invalid
		to = SquareFactory.makeSquare('d', 5);
		Assertions.assertFalse(MoveValidator.canMove(board, from, to));
	}

	@Test
	void rook() {
		// Rook at A1 to B1 -- valid
		Square from = SquareFactory.makeSquare('a', 1);
		Square to = SquareFactory.makeSquare('b', 1);
		Assertions.assertEquals(ChessPiece.PieceType.ROOK, board.getPieceAt(from).getPieceType());
		Assertions.assertTrue(MoveValidator.canMove(board, from, to));

		// Rook at A1 to A2 -- valid
		to = SquareFactory.makeSquare('a', 2);
		Assertions.assertTrue(MoveValidator.canMove(board, from, to));

		// Rook at A1 to B2 -- invalid
		to = SquareFactory.makeSquare('b', 2);
		Assertions.assertFalse(MoveValidator.canMove(board, from, to));

		// Rook at A3 to H3 -- valid
		from = SquareFactory.makeSquare('a', 3);
		to = SquareFactory.makeSquare('h', 3);
		Assertions.assertTrue(MoveValidator.canMove(board, from, to));

		// Rook at A3 to H4 -- invalid (seriously, what the hell?)
		to = SquareFactory.makeSquare('h', 4);
		Assertions.assertFalse(MoveValidator.canMove(board, from, to));
	}

	@Test
	void queen() {
		//Queen at C1 to A3 -- valid
		Square from = SquareFactory.makeSquare('c', 1);
		Square to = SquareFactory.makeSquare('a', 3);
		Assertions.assertEquals(ChessPiece.PieceType.QUEEN, board.getPieceAt(from).getPieceType());
		Assertions.assertEquals(ChessPiece.PieceColor.WHITE, board.getPieceAt(from).getPieceColor());
		Assertions.assertTrue(MoveValidator.canMove(board, from, to));

		// Queen at C1 to C8 -- valid
		to = SquareFactory.makeSquare('c', 8);
		Assertions.assertTrue(MoveValidator.canMove(board, from, to));

		//Queen at C1 to G3 -- invalid
		to = SquareFactory.makeSquare('g', 3);
		Assertions.assertFalse(MoveValidator.canMove(board, from, to));
	}

	@Test
	void king() {
		// King at G5 to G6 -- valid
		Square from = SquareFactory.makeSquare('g', 5);
		Square to = SquareFactory.makeSquare('g', 6);
		Assertions.assertEquals(KING, board.getPieceAt(from).getPieceType());
		Assertions.assertTrue(MoveValidator.canMove(board, from, to));

		//King at G5 to H5 -- valid
		to = SquareFactory.makeSquare('h', 5);
		Assertions.assertTrue(MoveValidator.canMove(board, from, to));

		// King at G5 to H6 -- valid
		to = SquareFactory.makeSquare('h', 6);
		Assertions.assertTrue(MoveValidator.canMove(board, from, to));

		// King at G5 to E5 -- invalid
		to = SquareFactory.makeSquare('e', 5);
		Assertions.assertFalse(MoveValidator.canMove(board, from, to));
	}

	@Test
	void knight() {
		// Knight at E2 to D4 -- valid
		Square from = SquareFactory.makeSquare('e', 2);
		Square to = SquareFactory.makeSquare('d', 4);
		Assertions.assertEquals(ChessPiece.PieceType.KNIGHT, board.getPieceAt(from).getPieceType());
		Assertions.assertTrue(MoveValidator.canMove(board, from, to));

		// Knight at E2 to F4 -- valid
		to = SquareFactory.makeSquare('f', 4);
		Assertions.assertTrue(MoveValidator.canMove(board, from, to));

		//Knight at E2 to G1 -- valid
		to = SquareFactory.makeSquare('g', 1);
		Assertions.assertTrue(MoveValidator.canMove(board, from, to));

		// Knight at E2 to E4 -- invalid
		to = SquareFactory.makeSquare('e', 4);
		Assertions.assertFalse(MoveValidator.canMove(board, from, to));

		//Knight at E2 to G2 -- invalid
		to = SquareFactory.makeSquare('g', 2);
		Assertions.assertFalse(MoveValidator.canMove(board, from, to));
	}

	@Test
	void pawn() {
		// Pawn at A8 to A7 -- valid straight motion for a black pawn
		Square from = SquareFactory.makeSquare('a', 8);
		Square to = SquareFactory.makeSquare('a', 7);
		Assertions.assertEquals(ChessPiece.PieceColor.BLACK, board.getPieceAt(from).getPieceColor());
		Assertions.assertTrue(MoveValidator.canMove(board, from, to));

		// Pawn at A8 to B7 -- capturing white pawn, valid
		to = SquareFactory.makeSquare('b', 7);
		Assertions.assertTrue(MoveValidator.canMove(board, from, to));

		// Pawn at A8 to A6 -- invalid, moving too much
		to = SquareFactory.makeSquare('a', 6);
		Assertions.assertFalse(MoveValidator.canMove(board, from, to));

		// Pawn at B7 to A8 -- white pawn capturing black pawn, valid
		from = SquareFactory.makeSquare('b', 7);
		to =  SquareFactory.makeSquare('a', 8);
		Assertions.assertEquals(ChessPiece.PieceColor.WHITE, board.getPieceAt(from).getPieceColor());
		Assertions.assertTrue(MoveValidator.canMove(board, from, to));

		// Pawn at B7 to B8 -- invalid, can't capture straight ahead
		to = SquareFactory.makeSquare('b', 8);
		Assertions.assertFalse(MoveValidator.canMove(board, from, to));

		// Pawn at B7 to A7 -- invalid, can't move sideways
		to = SquareFactory.makeSquare('a', 7);
		Assertions.assertFalse(MoveValidator.canMove(board, from, to));

		// Pawn at B7 to B6 -- invalid, white pawn can't move backwards
		to = SquareFactory.makeSquare('b', 6);
		Assertions.assertFalse(MoveValidator.canMove(board, from, to));

		// Pawn at B8  to A7 -- invalid, diagonal move but none to capture
		from = SquareFactory.makeSquare('b', 8);
		to = SquareFactory.makeSquare('a', 7);
		Assertions.assertFalse(MoveValidator.canMove(board, from, to));
	}

	@Test
	void noPieceOnSource() {
		Square from = SquareFactory.makeSquare('b', 1);
		Square to = SquareFactory.makeSquare('b', 2);
		assertThrows(CMVException.class, () -> MoveValidator.canMove(board, from, to));
	}

	/**
	 * Test for moves that should be valid if the piece were of a different color
	 */
	@Test
	void colorCollision(){
		// Black rook at A3 to black pawn at A8
		Square from = SquareFactory.makeSquare('a', 3);
		Square to = SquareFactory.makeSquare('a', 8);
		Assertions.assertFalse(MoveValidator.canMove(board, from, to));

		// White rook at A1 to white queen at C1
		from = SquareFactory.makeSquare('a', 1);
		to = SquareFactory.makeSquare('c', 1);
		Assertions.assertFalse(MoveValidator.canMove(board, from, to));
	}

	/**
	 * Test for moves that would be valid were there not a piece in the way
	 */
	@Test
	void pathClear() {
		// Queen at C1 to H1 -- invalid, bishop in the way (horizontal)
		Square from = SquareFactory.makeSquare('c', 1);
		Square to = SquareFactory.makeSquare('h', 1);
		Assertions.assertFalse(MoveValidator.canMove(board, from, to));

		// Queen at C1 to D1 -- valid, nothing in the way (horizontal)
		to = SquareFactory.makeSquare('d', 1);
		Assertions.assertTrue(MoveValidator.canMove(board, from, to));

		// Rook at A1 to A7 -- invalid, rook in the way (vertical)
		from = SquareFactory.makeSquare('a', 1);
		to = SquareFactory.makeSquare('a', 7);
		Assertions.assertFalse(MoveValidator.canMove(board, from, to));

		// Queen at C8 to A6 -- invalid, pawn in the way (diagonal)
		from = SquareFactory.makeSquare('c', 8);
		to = SquareFactory.makeSquare('a', 6);
		Assertions.assertFalse(MoveValidator.canMove(board, from, to));
	}

	@Test
	void rejectNonMovement() {
		// King at G5 to G5
		Square from = SquareFactory.makeSquare('g', 5);
		Assertions.assertFalse(MoveValidator.canMove(board, from, from));
	}

	/*
	Every test from here on out is a dumb test. They're not really useful. Why am I doing this?
	I WANTED 100% CODE COVERAGE. Just this ONE time, I thought it would be fun. Just to mess with
	whoever has to grade this, I guess!
	 */

	@Test
	void pieceDefaultMethods() {
		ChessPiece piece = new ChessPiece() {};
		assertThrows(MethodNotImplementedException.class, piece::getPieceColor);
		assertThrows(MethodNotImplementedException.class, piece::getPieceType);
	}

	@Test
	void exceptionConstruction() {
		MethodNotImplementedException ex = new MethodNotImplementedException("test");
		CMVException ex2 = new CMVException("test2");
		assertEquals("test", ex.getMessage());
		assertEquals("test2", ex2.getMessage());
	}

	@Test
	void squareEquals() {
		Square sq1 = SquareFactory.makeSquare('a', 1);

		// These tests must manually use Square.equals(), otherwise JUnit doesn't invoke the tested function correctly
		assertTrue(sq1.equals(sq1));
		assertFalse(sq1.equals(null));
		assertFalse(sq1.equals("this is the stupidest unit test I've ever written"));
	}

	@Test
	void pieceFactory() {
		assertEquals(BISHOP, ChessPieceFactory.makePiece(ChessPiece.PieceColor.WHITE, BISHOP).getPieceType());
		assertEquals(KING, ChessPieceFactory.makePiece(ChessPiece.PieceColor.WHITE, KING).getPieceType());
		assertEquals(KNIGHT, ChessPieceFactory.makePiece(ChessPiece.PieceColor.WHITE, KNIGHT).getPieceType());
		assertEquals(PAWN, ChessPieceFactory.makePiece(ChessPiece.PieceColor.WHITE, PAWN).getPieceType());
		assertEquals(QUEEN, ChessPieceFactory.makePiece(ChessPiece.PieceColor.WHITE, QUEEN).getPieceType());
		assertEquals(ROOK, ChessPieceFactory.makePiece(ChessPiece.PieceColor.WHITE, ROOK).getPieceType());
	}

	/**
	 * Create the board configuration. 
	 * @param sp alternating squares and pieces
	 * @return the ChessBoard
	 */
	private static ChessBoard makeBoard(Object[] sp)
	{
		Map<Square, ChessPiece> config = new HashMap<Square, ChessPiece>();
		int i = 0;
		int max = sp.length;
		while (i < max) {
			Square s = (Square)sp[i++];
			ChessPiece p = (ChessPiece)sp[i++];
			config.put(s, p);
		}
		return new ChessBoard(config);
	}
}
