This is a team project. Only one member of your team has to turn it in.

Assignment
Write an "artificially intelligent" opponent to play the game of Scrabble (Links to an external site.).

After downloading the files below, run Scrabble.java to play against a very weak opponent called Incrementalist. (You should have no trouble beating it, even if you are not a native English speaker.) It is important to play to refresh your knowledge of the rules and to become familiar with the somewhat unusual way in which moves are entered.

You can also run ScrabbleTournament.java to run a tournament between two instances of Incrementalist.

The class you write, like Incrementalist, should implement the ScrabbleAI interface.

Files
src

Board.java download
ExchangeTiles.java download
GateKeeper.java download
IllegalMoveException.java download
Incrementalist.java download
Location.java download
PlayWord.java download
Scrabble.java download
ScrabbleAI.java download
ScrabbleMove.java download
ScrabbleTournament.java download
test

BoardTest.java  download(These tests should all pass with the given code.)
resources

enable1.txt download
Hints and Clarifications
Our version of the game only supports two players.
The regular "challenge" mechanism in Scrabble is replaced by a rule that nonwords are simply illegal to play.
While enable1.txt is a widely-used public domain word list, it may contain some surprising inclusions or omissions. I was shocked that it does not include the very useful Scrabble word "QI".
Two reasonable strategies are to (a) find a good spot on the board, possibly one including a triple word score, and then find a word to put there, or (b) find a word from your hand and then try to place it on the board.
Your program need not find the very best move, but it should consistently trounce the Incrementalist.
You don't need to be clever about hand management (keeping a good balance of letters in your hand) or board position (not setting the opponent up to use valuable double and triple squares). Simply try to find a good, high-scoring word.
What to Hand In
Submit the .java file for your class that implements the ScrabbleAI interface and, in a separate document, your answers to the following questions:

How does your algorithm work? Explain clearly, using diagrams if they help.
What is a typical final game score for your class?
What difficulties did you encounter and how did you overcome them?
How did your team work together to solve this problem? How can you improve this process next time?
