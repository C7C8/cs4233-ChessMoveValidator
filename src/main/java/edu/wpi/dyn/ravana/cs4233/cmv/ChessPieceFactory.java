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

import edu.wpi.dyn.ravana.cs4233.cmv.ChessPiece.PieceColor;
import edu.wpi.dyn.ravana.cs4233.cmv.ChessPiece.PieceType;
import edu.wpi.dyn.ravana.cs4233.cmv.pieces.*;

/**
 * A simple factory for creating chess pieces. This needs to be implemented
 * by the student.
 *
 * @version Feb 15, 2019
 */
public class ChessPieceFactory {

	/**
	 * Make chess pieces!
	 *
	 * @param color Color of piece
	 * @param type  Type of piece
	 * @return Constructed piece object. Underlying class varies with type.
	 */
	public static ChessPiece makePiece(PieceColor color, PieceType type) {

		// To add more pieces (...not sure why you'd want to, but hey, flexibility! Right?) it's as easy as expanding
		// this switch statement. No breaks are needed since the only thing in each is a return statement anyways.
		switch (type) {
			case PAWN:
				return new Pawn(color);
			case KNIGHT:
				return new Knight(color);
			case BISHOP:
				return new Bishop(color);
			case ROOK:
				return new Rook(color);
			case QUEEN:
				return new Queen(color);
			default: // Should be case:king, but I wanted to get rid of a warning
				return new King(color);
		}
	}
}
