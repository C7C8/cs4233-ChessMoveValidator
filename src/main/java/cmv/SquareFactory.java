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
 * This class is a factory that creates Square instances. It has only one
 * static method that creates the instance of the Square or Square subclass.
 * Students must implement this method as appropriate for their solution.
 * The master tests used for grading will invoke this method to get the
 * Square instances used in tests.
 * NOTE: Students must implement the single static method. You may NOT
 * change the signature of the method.
 *
 * @version Mar 8, 2019
 */
public final class SquareFactory {
	/**
	 * Return the instance of the Square used in the project.
	 *
	 * @param column
	 * @param row
	 * @return the Square instance
	 */
	public static Square makeSquare(char column, int row) {
		return new Square(column, row);
	}
}
