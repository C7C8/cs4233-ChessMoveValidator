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

/**
 * The MoveValidator has a single method that takes a ChessBoard instance
 * and two squares. It validates that the piece on the first square can move to
 * the second square on the given board.
 * Students must implement this method
 *
 * @version Feb 15, 2019
 */
public class MoveValidator {
	/**
	 * Determines if a move can be made
	 *
	 * @param board the board state
	 * @param from  the square the piece is moving from
	 * @param to    the square the piece is moving to
	 * @return true if the move can be made false otherwise
	 * @throws CMVException if there is an error, such as no piece on the from square
	 */
	public static boolean canMove(ChessBoard board, Square from, Square to) {
		// Idiot check: Make sure there's a piece on the 'from' square
		if (!board.isSquareOccupied(from))
			throw new CMVException("Square unoccupied");

		// Idiot check round 2: Check bounds
		Square[] squares = {from, to};
		for (Square square : squares) {
			if (square.getColumn() > 'h' || square.getColumn() < 'a')
				return false;
			if (square.getRow() > 8 || square.getRow() < 1)
				return false;
		}


		// Can't move a piece on top of another of the same color
		ChessPieceDefined piece = (ChessPieceDefined) board.getPieceAt(from);
		if (board.isSquareOccupied(to) && board.getPieceAt(to).getPieceColor() == piece.getPieceColor())
			return false;
		ChessPieceDefined at = (ChessPieceDefined) board.getPieceAt(to);

		// Leave the final decision of whether the piece can move or not up to the piece itself.
		// This helps prevent the MoveValidator class from becoming enormous and littered with
		// special-case code for each and every piece, and follows the object-oriented
		// paradigm by leaving the ultimate behavior of classes (or the objects they represent)
		// to the classes themselves.
		return piece.canMove(from, to, at);
	}
}
