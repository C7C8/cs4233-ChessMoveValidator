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
 * Extension of the ChessPiece interface to provide a canMove() function.
 * Implemented as an abstract class since I wanted to provide a constructor
 * and a definition for getPieceColor, but require a canMove() function.
 *
 * Outside of this assignment, I would've put this stuff in the ChessPiece
 * interface, but I didn't want to touch it.
 */
public abstract class ChessPieceDefined implements ChessPiece {
	PieceColor color;

	public ChessPieceDefined(PieceColor color) {
		this.color = color;
	}

	@Override
	public PieceColor getPieceColor() {
		return color;
	}

	/**
	 * Determines whether the piece can move from its current square to a new one
	 * @param from Source square
	 * @param to Destination square
	 * @param at What's at the square, if anything
	 * @return True if move is possible, false if otherwise.
	 */
	public abstract boolean canMove(Square from, Square to, ChessPiece at);
}
