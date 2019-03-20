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

package edu.wpi.dyn.ravana.cs4233.cmv;

import edu.wpi.dyn.ravana.cs4233.cmv.pieces.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static edu.wpi.dyn.ravana.cs4233.cmv.ChessPiece.PieceColor.*;
import static edu.wpi.dyn.ravana.cs4233.cmv.ChessPiece.PieceType.*;
import static edu.wpi.dyn.ravana.cs4233.cmv.MoveValidator.canMove;
import static edu.wpi.dyn.ravana.cs4233.cmv.SquareFactory.makeSquare;
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
					sp.add(makeSquare(x, y));
					final ChessPiece.PieceColor color = Character.isUpperCase(symbol) ? WHITE : BLACK;

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
		Square from = makeSquare('a', 1);
		Square to = makeSquare((char)('a'-1), 1);
		assertFalse(canMove(board, from, to));

		// Queen at C8 to C9
		from = makeSquare('c', 8);
		to = makeSquare('c', 9);
		assertFalse(canMove(board, from, to));
	}

	@Test
	void bishop() {
		// Bishop at E1 to B4 -- valid
		Square from = makeSquare('e', 1);
		Square to = makeSquare('b', 4);
		assertEquals(BISHOP, board.getPieceAt(from).getPieceType());
		assertTrue(canMove(board, from, to));

		//Bishop at E1 to H4 -- valid
		to = makeSquare('h', 4);
		assertTrue(canMove(board, from, to));

		//Bishop at D6 to C5 -- valid
		from = makeSquare('d', 6);
		to = makeSquare('c', 5);
		assertTrue(canMove(board, from, to));

		//Bishop at D6 to D5 -- invalid
		to = makeSquare('d', 5);
		assertFalse(canMove(board, from, to));
	}

	@Test
	void rook() {
		// Rook at A1 to B1 -- valid
		Square from = makeSquare('a', 1);
		Square to = makeSquare('b', 1);
		assertEquals(ROOK, board.getPieceAt(from).getPieceType());
		assertTrue(canMove(board, from, to));

		// Rook at A1 to A2 -- valid
		to = makeSquare('a', 2);
		assertTrue(canMove(board, from, to));

		// Rook at A1 to B2 -- invalid
		to = makeSquare('b', 2);
		assertFalse(canMove(board, from, to));

		// Rook at A3 to H3 -- valid
		from = makeSquare('a', 3);
		to = makeSquare('h', 3);
		assertTrue(canMove(board, from, to));

		// Rook at A3 to H4 -- invalid (seriously, what the hell?)
		to = makeSquare('h', 4);
		assertFalse(canMove(board, from, to));
	}

	@Test
	void queen() {
		//Queen at C1 to A3 -- valid
		Square from = makeSquare('c', 1);
		Square to = makeSquare('a', 3);
		assertEquals(QUEEN, board.getPieceAt(from).getPieceType());
		assertEquals(WHITE, board.getPieceAt(from).getPieceColor());
		assertTrue(canMove(board, from, to));

		// Queen at C1 to C8 -- valid
		to = makeSquare('c', 8);
		assertTrue(canMove(board, from, to));

		//Queen at C1 to G3 -- invalid
		to = makeSquare('g', 3);
		assertFalse(canMove(board, from, to));
	}

	@Test
	void king() {
		// King at G5 to G6 -- valid
		Square from = makeSquare('g', 5);
		Square to = makeSquare('g', 6);
		assertEquals(KING, board.getPieceAt(from).getPieceType());
		assertTrue(canMove(board, from, to));

		//King at G5 to H5 -- valid
		to = makeSquare('h', 5);
		assertTrue(canMove(board, from, to));

		// King at G5 to H6 -- valid
		to = makeSquare('h', 6);
		assertTrue(canMove(board, from, to));

		// King at G5 to E5 -- invalid
		to = makeSquare('e', 5);
		assertFalse(canMove(board, from, to));
	}

	@Test
	void knight() {
		// Knight at E2 to D4 -- valid
		Square from = makeSquare('e', 2);
		Square to = makeSquare('d', 4);
		assertEquals(KNIGHT, board.getPieceAt(from).getPieceType());
		assertTrue(canMove(board, from, to));

		// Knight at E2 to F4 -- valid
		to = makeSquare('f', 4);
		assertTrue(canMove(board, from, to));

		//Knight at E2 to G1 -- valid
		to = makeSquare('g', 1);
		assertTrue(canMove(board, from, to));

		// Knight at E2 to E4 -- invalid
		to = makeSquare('e', 4);
		assertFalse(canMove(board, from, to));

		//Knight at E2 to G2 -- invalid
		to = makeSquare('g', 2);
		assertFalse(canMove(board, from, to));
	}

	@Test
	void pawn() {
		// Pawn at A8 to A7 -- valid straight motion for a black pawn
		Square from = makeSquare('a', 8);
		Square to = makeSquare('a', 7);
		assertEquals(PAWN, board.getPieceAt(from).getPieceType());
		assertEquals(BLACK, board.getPieceAt(from).getPieceColor());
		assertTrue(canMove(board, from, to));

		// Pawn at A8 to B7 -- capturing white pawn, valid
		to = makeSquare('b', 7);
		assertTrue(canMove(board, from, to));

		// Pawn at A8 to A6 -- invalid, moving too much
		to = makeSquare('a', 6);
		assertFalse(canMove(board, from, to));

		// Pawn at B7 to A8 -- white pawn capturing black pawn, valid
		from = makeSquare('b', 7);
		to =  makeSquare('a', 8);
		assertEquals(WHITE, board.getPieceAt(from).getPieceColor());
		assertTrue(canMove(board, from, to));

		// Pawn at B7 to B8 -- invalid, can't capture straight ahead
		to = makeSquare('b', 8);
		assertFalse(canMove(board, from, to));

		// Pawn at B7 to A7 -- invalid, can't move sideways
		to = makeSquare('a', 7);
		assertFalse(canMove(board, from, to));

		// Pawn at B7 to B6 -- invalid, white pawn can't move backwards
		to = makeSquare('b', 6);
		assertFalse(canMove(board, from, to));

		// Pawn at B8  to A7 -- invalid, diagonal move but none to capture
		from = makeSquare('b', 8);
		to = makeSquare('a', 7);
		assertFalse(canMove(board, from, to));
	}

	@Test
	void noPieceOnSource() {
		Object[] sp = {};
		assertThrows(CMVException.class, () -> canMove(makeBoard(sp), makeSquare('e', 2), makeSquare('e', 3)));
	}

	/**
	 * Test for moves that should be valid if the piece were of a different color
	 */
	@Test
	void colorCollision(){
		// Black rook at A3 to black pawn at A8
		Square to = makeSquare('a', 3);
		Square from = makeSquare('a', 8);
		assertFalse(canMove(board, from, to));
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
