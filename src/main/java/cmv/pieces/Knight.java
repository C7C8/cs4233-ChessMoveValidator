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

/**
 * Class representing a knight
 */
public class Knight extends ChessPieceDefined {
	/**
	 * @see ChessPieceDefined
	 */
	public Knight(PieceColor color) {
		super(color);
	}

	/**
	 * Validates the knight's move. Knights must always move either 1 forward and 2 to the side, or 2 forward and 1
	 * to the side. They can also skip anything they like.
	 *
	 * @see ChessPieceDefined
	 */
	@Override
	public boolean canMove(Square from, Square to, ChessBoard board) {
		final int dx = Math.abs(from.getColumn() - to.getColumn());
		final int dy = Math.abs(from.getRow() - to.getRow());
		return (dx == 1 && dy == 2) || (dx == 2 && dy == 1);
	}

	/**
	 * @see ChessPiece
	 */
	@Override
	public PieceType getPieceType() {
		return PieceType.KNIGHT;
	}
}
