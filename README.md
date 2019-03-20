# Chess Move Validator

This chess move validator will validate chess moves, excluding certain special
rules *en passant*

## Implementation

To solve the problem, an abstract class `ChessPieceDefined` was created that
implements the `ChessPiece` interface and provides some utility functions.
Classes for each individual piece were created that inherit `ChessPieceDefined`,
each with their own `canMove()` function that `MoveValidator` calls.
`ChessPieceFactory` was modified to return an object of the correct class
depending on what piece type enumeration was passed in. `MoveValidator` is
ultimately only responsible for base sanity checks (can't capture the same
color, can't move outside the board, etc.) to simplify the code for each piece
to validate its move.

## Rationale

This project was designed with the object-oriented paradigm in mind; it made
the most sense for chess piece to define its own behavior in one place, rather
than rely on the `MoveValidator` class to validate everything and effectively
become a "god" class.

## Build

The project can be built with Gradle, projects based upon which Eclipse is
easily capable of importing. Alternatively, the project can be run without
need for an IDE by simply running `gradle test` in the root directory.
