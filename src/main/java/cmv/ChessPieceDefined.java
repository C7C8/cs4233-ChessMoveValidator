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

/**
 * Extension of the ChessPiece interface to provide a canMove() function.
 * Implemented as an abstract class since I wanted to provide a constructor
 * and a definition for getPieceColor, but require a canMove() function.
 *
 * Outside of this assignment, I would've put this stuff in the ChessPiece
 * interface, but I didn't want to touch it.
 */
public abstract class ChessPieceDefined implements ChessPiece {
	protected PieceColor color;

	public ChessPieceDefined(PieceColor color) {
		this.color = color;
	}

	/**
	 * @return Color of the piece
	 */
	@Override
	public PieceColor getPieceColor() {
		return color;
	}

	/**
	 * Determines whether the piece can move from its current square to a new one
	 *
	 * @param from  Source square
	 * @param to    Destination square
	 * @param board Chess board
	 * @return True if move is possible, false if otherwise.
	 */
	public abstract boolean canMove(Square from, Square to, ChessBoard board);

	/**
	 * Determines whether the given path is clear or not. Note that this does NOT perform bounds checks or color checks.
	 *
	 * @param from  Source square, not included in the path.
	 * @param to    Destination square, not included in the path.
	 * @param board Board to search for.
	 * @return True if the path is clear (no piece in the way), false otherwise.
	 */
	protected boolean pathClear(Square from, Square to, ChessBoard board) {
		int dx = to.getColumn() - from.getColumn();
		int dy = to.getRow() - from.getRow();

		// Accept the simple case where movement distance is one square in any direction
		if (Math.abs(dx) <= 1 && Math.abs(dy) <= 1)
			return true;

		dx /= (dx == 0 ? 1 : Math.abs(dx)); // Reduce numbers down to 0, 1, or -1
		dy /= (dy == 0 ? 1 : Math.abs(dy));

		// Loop over the entire path, from start to end (exclusive) looking for pieces in the way.
		char x = (char) (from.getColumn() + dx);
		int y = from.getRow() + dy;
		do {
			Square s = SquareFactory.makeSquare(x, y);
			if (board.isSquareOccupied(s))
				return false;

			x += dx;
			y += dy;
		} while ((x != to.getColumn()) || (y != to.getRow()));

		return true;
	}
}
