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

package cmv.pieces;

import cmv.ChessBoard;
import cmv.ChessPiece;
import cmv.ChessPieceDefined;
import cmv.Square;

public class Pawn extends ChessPieceDefined {
	/**
	 * @see ChessPieceDefined
	 */
	public Pawn(PieceColor color) {
		super(color);
	}

	/**
	 * Verifies whether the pawn is either moving straight forward, or is going diagonally forward in one direction or
	 * the other to capture a piece.
	 *
	 * @see ChessPieceDefined
	 */
	@Override
	public boolean canMove(Square from, Square to, ChessBoard board) {
		final int dx = to.getColumn() - from.getColumn();
		final int dy = to.getRow() - from.getRow();

		// Can immediately accept the simple one-forward movement case. Everything after this is for the capture case.
		final int dir = getPieceColor() == PieceColor.WHITE ? 1 : -1;
		if (dx == 0 && dy == dir && !board.isSquareOccupied(to))
			return true;

		// Can also immediately accept the initial-move case where the pawn can move twice
		if (dx == 0 && dy == 2 * dir && !board.isSquareOccupied(to) &&
				from.getRow() == (getPieceColor() == PieceColor.WHITE ? 2 : 7))
			return true;

		// Idiot check to make sure the pawn isn't moving too much
		if (Math.abs(dx) > 1 || Math.abs(dy) > 1)
			return false;

		// Ensured that we're capturing at this point, make sure the pawn is going diagonal
		if (Math.abs(dx) != 1 || dy != dir)
			return false;

		// ...and ONLY when capturing
		return board.isSquareOccupied(to);
	}

	/**
	 * @see ChessPiece
	 */
	@Override
	public PieceType getPieceType() {
		return PieceType.PAWN;
	}
}
