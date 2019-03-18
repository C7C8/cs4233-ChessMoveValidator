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

import edu.wpi.dyn.ravana.cs4233.cmv.ChessPiece;
import edu.wpi.dyn.ravana.cs4233.cmv.ChessPieceDefined;
import edu.wpi.dyn.ravana.cs4233.cmv.Square;

public class Pawn implements ChessPieceDefined {
	/**
	 * Determines whether the piece can move from its current square to a new one
	 *
	 * @param from Source square
	 * @param to   Destination square
	 * @param at   What's at the square, if anything
	 * @return True if move is possible, false if otherwise.
	 */
	@Override
	public boolean canMove(Square from, Square to, ChessPiece at) {
		return false;
	}

	/**
	 * @return the piece type
	 */
	@Override
	public PieceType getPieceType() {
		return null;
	}

	/**
	 * @return the piece color
	 */
	@Override
	public PieceColor getPieceColor() {
		return null;
	}
}
