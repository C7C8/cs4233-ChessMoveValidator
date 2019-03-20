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
 * This interface contains the methods that you may use for the chess pieces in the
 * chess move validator program. The master tests will supply a board with pieces that
 * implement this interface positioned on it.
 * <p>
 * NOTE: Students must implement an instance of this interface. Tests will only depend upon
 * this interface, but the students can add any behavior needed to their implementation.
 *
 * @version Jan 25, 2019
 */
public interface ChessPiece {
	enum PieceType {KING, QUEEN, BISHOP, KNIGHT, ROOK, PAWN}

	enum PieceColor {WHITE, BLACK}

	/**
	 * @return the piece type
	 */
	default PieceType getPieceType() {
		throw new MethodNotImplementedException("ChessPiece.getPieceType()");
	}

	/**
	 * @return the piece color
	 */
	default PieceColor getPieceColor() {
		throw new MethodNotImplementedException("ChessPiece.getPieceColor()");
	}
}
