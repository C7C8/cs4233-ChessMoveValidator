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

package edu.wpi.dyn.ravana.cs4233.cmv.pieces;

import edu.wpi.dyn.ravana.cs4233.cmv.ChessBoard;
import edu.wpi.dyn.ravana.cs4233.cmv.ChessPiece;
import edu.wpi.dyn.ravana.cs4233.cmv.ChessPieceDefined;
import edu.wpi.dyn.ravana.cs4233.cmv.Square;

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
	 * @see ChessPieceDefined
	 */
	@Override
	public boolean canMove(Square from, Square to, ChessBoard board) {
		final int dx = from.getColumn() - to.getColumn();
		final int dy = from.getRow() - to.getRow();

		// Can immediately accept the simple one-forward movement case. Everything after this is for the capture case.
		final int dir = getPieceColor() == PieceColor.WHITE ? -1 : 1;
		if (dx == 0 && dy == dir && board.isSquareOccupied(to))
			return true;

		// Idiot check to make sure the pawn isn't moving too much
		if (Math.abs(dx) > 1 || Math.abs(dy) > 1)
			return false;

		// If a pawn is moving left/right, it has to be moving up/down too
		if (Math.abs(dx) == 1 || Math.abs(dy) != 1)
			return false;

		// ...but it has to be in the right direction, depending on color...
		if (dy != dir)
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
