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

import static edu.wpi.dyn.ravana.cs4233.cmv.ChessPiece.PieceColor.BLACK;
import static edu.wpi.dyn.ravana.cs4233.cmv.ChessPiece.PieceColor.WHITE;
import static edu.wpi.dyn.ravana.cs4233.cmv.MoveValidator.canMove;
import static edu.wpi.dyn.ravana.cs4233.cmv.SquareFactory.makeSquare;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
//					System.out.println(String.format("Placing piece %s at %s%d", symbol, x, y));

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
//					System.out.println("Skipping ahead " + Character.getNumericValue(symbol) + " spaces");
					x += Character.getNumericValue(symbol);
					x--; // Decrease to counter increment down below
				}
				x++;
			}
			y--;
		}
		board = makeBoard(sp.toArray());
	}

	@Test
	void outOfBounds() {

	}

	@Test
	void noPieceOnSource() {
		Object[] sp = {};
		assertThrows(CMVException.class, () -> canMove(makeBoard(sp), makeSquare('e', 2), makeSquare('e', 3)));
	}

	// Helper methods
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
