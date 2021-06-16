This is an individual assignment. Do it on your own! If you have questions, ask them on the course email list.

This assignment applies game search techniques to the game of Othello (also known as Reversi https://en.wikipedia.org/wiki/Reversi).

Complete all of the indicated functions in othello_rules.py  downloadand othello_minimax.py  download. To determine what to do, examine the docstring for each method as well as the unit tests in test_othello_rules.py  downloadandtest_othello_minimax.py  download. When completed, your code should pass all of the unit tests. You'll also be able to run othello_minimax.py to play against the computer; adjust the number 5 to change the search depth; a higher depth is stronger, but takes longer to move.

You will find the Tic-tac-toe code we wrote in class useful, especially in the minimax part. The key difference here is that, since Othello is too big to search out entirely, you'll only search to a fixed depth and you'll use a static evaluation function.

Hand in your updated versions of othello_rules.py and othello_minimax.py.

You will get full credit if your code passes all of the tests, as long as it does so "honestly" (and not by, e.g., hard-coding the specific value asked for in the test).
