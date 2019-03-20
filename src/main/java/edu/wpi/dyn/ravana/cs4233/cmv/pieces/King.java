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

/**
 * Class representing kings.
 */
public class King extends ChessPieceDefined {

	/**
	 * @see ChessPieceDefined
	 */
	public King(PieceColor color) {
		super(color);
	}

	/**
	 * Verifies whether the King is moving one space only, in any direction.
	 *
	 * @see ChessPieceDefined
	 */
	@Override
	public boolean canMove(Square from, Square to, ChessBoard board) {
		final int dx = Math.abs(from.getColumn() - to.getColumn());
		final int dy = Math.abs(from.getRow() - to.getRow());
		return dx <= 1 && dy <= 1;
	}

	/**
	 * @see ChessPiece
	 */
	@Override
	public PieceType getPieceType() {
		return PieceType.KING;
	}
}
