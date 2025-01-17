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
 * Exception that is used to indicate any error when determining whether a move may be
 * made or not.
 *
 * @version Feb 18, 2019
 */
public class CMVException extends RuntimeException {
	/**
	 * Sole constructor.
	 *
	 * @param message the reason for the error
	 */
	public CMVException(String message) {
		super(message);
	}
}
