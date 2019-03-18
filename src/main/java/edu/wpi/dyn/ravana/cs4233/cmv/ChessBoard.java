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

import java.util.*;

/**
 * This class represents the chess board for the move validation assignment.
 * It describes the methods that you can call to satisfy the assignment. All of the tests for
 * this assignment will pass a ChessBoard instance to the tests. The board will 
 * have the configuration that is to be tested.
 * 
 * NOTE: Students MAY NOT modify this class.
 * @version Jan 25, 2019
 */
public final class ChessBoard
{
	private final Map<Square, ChessPiece> theBoard;
	
	/**
	 * Constructor that takes the configuration.
	 * @param configuration the Map
	 */
	public ChessBoard(Map<Square, ChessPiece> configuration)
	{
		theBoard = configuration;
	}
	
	/**
	 * @param square the square to query
	 * @return the ChessPiece at the specified square or null if there is none
	 * @throws CMVException if the square is invalid
	 */
	public ChessPiece getPieceAt(Square square) 
	{
		return theBoard.get(square);
	}
	
	/**
	 * @param square
	 * @return true if the square has a piece on it, false otherwise
	 */
	public boolean isSquareOccupied(Square square)
	{
		return theBoard.get(square) != null;
	}
}
