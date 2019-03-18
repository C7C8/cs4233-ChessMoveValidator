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

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static edu.wpi.dyn.ravana.cs4233.cmv.ChessPiece.PieceColor.BLACK;
import static edu.wpi.dyn.ravana.cs4233.cmv.ChessPiece.PieceColor.WHITE;
import static edu.wpi.dyn.ravana.cs4233.cmv.ChessPiece.PieceType.*;
import static edu.wpi.dyn.ravana.cs4233.cmv.ChessPieceFactory.makePiece;
import static edu.wpi.dyn.ravana.cs4233.cmv.MoveValidator.canMove;
import static edu.wpi.dyn.ravana.cs4233.cmv.SquareFactory.makeSquare;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This is a sample of the type of tests that will be run on your code. You should make sure
 * that these run on your assignment code before submitting your work.
 * @version Mar 9, 2019
 */
class ValidationTest
{
	@ParameterizedTest
	@MethodSource("testCaseProvider")
	void validationTest(ChessBoard board, Square source, Square target, boolean expected)
	{
		assertEquals(expected, canMove(board, source, target));
	}
	
	static Stream<Arguments> testCaseProvider()
	{
		return Stream.of(
			// Empty board
			Arguments.of(makeBoard(), makeSquare('e', 4), makeSquare('e', 5), false),
			
			// Rook tests
			Arguments.of(makeBoard(makeSquare('h', 1), makePiece(WHITE, ROOK)), 
					makeSquare('h', 1), makeSquare('e', 3), false),
			Arguments.of(makeBoard(makeSquare('h', 1), makePiece(WHITE, ROOK), makeSquare('h', 2), makePiece(WHITE, KNIGHT)), 
					makeSquare('h', 1), makeSquare('h', 4), false),
			
			// Bishop tests
			Arguments.of(makeBoard(makeSquare('c', 1), makePiece(BLACK, BISHOP)), 
					makeSquare('c', 1), makeSquare('e', 3), true),
			Arguments.of(makeBoard(makeSquare('e', 3), makePiece(WHITE, BISHOP)), 
					makeSquare('e', 3), makeSquare('h', 1), false)
			
			// Queen tests
			
			// King tests
			
			// Knight tests
			
			// Pawn tests
		);
	}

	// Helper methods
	/**
	 * Create the board configuration. 
	 * @param sp alternating squares and pieces
	 * @return the ChessBoard
	 */
	private static ChessBoard makeBoard(Object ...sp)
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
