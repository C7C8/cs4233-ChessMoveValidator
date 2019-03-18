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
 * A data structure that contains the row (a-h) and column (1-8) of a square on
 * the chess board.
 * 
 * NOTE: Students may modify this class through subclassing or adding protected, private,
 * and package protected (no modifiers), but not by adding public methods.
 * 
 * @version Feb 15, 2019
 */
public class Square
{
	private final char column;
	private final int row;
	
	/**
	 * Default constructor
	 */
	public Square(char column, int row)
	{
		this.row = row;
		this.column = column;
	}

	/**
	 * @return the column
	 */
	public char getColumn()
	{
		return column;
	}

	/**
	 * @return the row
	 */
	public int getRow()
	{
		return row;
	}

	/*
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}

	/*
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Square)) {
			return false;
		}
		Square other = (Square) obj;
		if (column != other.column) {
			return false;
		}
		if (row != other.row) {
			return false;
		}
		return true;
	}
}
