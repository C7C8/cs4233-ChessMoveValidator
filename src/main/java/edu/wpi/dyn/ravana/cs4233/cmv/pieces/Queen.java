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
import edu.wpi.dyn.ravana.cs4233.cmv.ChessPieceDefined;
import edu.wpi.dyn.ravana.cs4233.cmv.Square;

/**
 * Class representing queens.
 */
public class Queen extends ChessPieceDefined {
	/**
	 * @see ChessPieceDefined
	 */
	public Queen(PieceColor color) {
		super(color);
	}

	/**
	 * Verifies the queen's move is purely diagonal or purely rook-like
	 *
	 * @see ChessPieceDefined
	 */
	@Override
	public boolean canMove(Square from, Square to, ChessBoard board) {
		final int dx = from.getColumn() - to.getColumn();
		final int dy = from.getRow() - to.getRow();

		// Enforce both rook and bishop constraints
		if ((Math.abs(dx) != Math.abs(dy)) && (dx != 0 && dy != 0))
			return false;

		return pathClear(from, to, board);
	}

	@Override
	public PieceType getPieceType() {
		return PieceType.QUEEN;
	}
}
