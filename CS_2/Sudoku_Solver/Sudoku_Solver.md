This is a pair programming project. Work with your assigned partner. Only one of you has to turn it in, but make sure you don't both think the other one was doing it.

Overview
You will complete a program to solve Sudoku (Links to an external site.) puzzles. If you are not familiar with this type of puzzle, you can try some online (Links to an external site.).

I have provided the following files:

Location.java  download, defining the Location class of objects
Square.java  download, defining the Square class of objects
Sudoku.java  download, a partial skeleton of the main program
SudokuTest.java  download, a JUnit test file for Sudoku
sudoku1.txt  download, a sample puzzle
Put SudokuTest.java in your test directory, sudoku1.txt in your project (but not inside any other directory), and the rest in your src directory.

Your job is simply to complete Sudoku.java. Do not modify any of the other classes.

Hints
Run all of the tests first. They should all fail.

Work through the tests in the order in which they appear in SudokuTest.java. Figure out which methods you need to complete to get each one to pass. Pass each test before moving on to the next one.

As you write each method, consider calling one or more of the previous methods. That's why they're there!

Note that there are two createSquares methods: one that takes no arguments and one that takes a String. This is called overloading. When you call one, Java looks at the types of any arguments you passed in to determine which version to use.

What To Hand In
Hand in your main program Sudoku.java.
