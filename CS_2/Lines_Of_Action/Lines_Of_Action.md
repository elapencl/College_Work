This is a pair programming project. Work with your assigned partner. Only one of you has to turn it in, but make sure you don't both think the other one was doing it.

Overview
You will implement a two-player version of the board game Lines of Action. The rules (Links to an external site.) https://en.wikipedia.org/wiki/Lines_of_Action are available online, as is a program you can play against (Links to an external site.) https://www.kongregate.com/games/geometricgames/lines-of-action. (The online game asks you to create an account, but you don't need to do so to play.)

Your version must:

Have a graphic user interface similar to the online version linked above.
Show legal moves.
Prohibit illegal moves.
Detect when the game is over and who has won. We use the version of the rules where the game is a tie if both players connect all of their pieces at the same time.
Be fully object-oriented.
You are implementing a game for two human players; you do not have to write an artificially intelligent opponent.

Hints
The design is up to you, but here's one reasonable option:

PairTest has-a Pair. BoardTest has-a Board. Board has-a Pair. LinesOfAction has-a Board and has-a Pair.

In this design a Pair is a pair of numbers (row and column), which we often need to represent either a location or a direction. The Board represents the state of the game (where the pieces are and whose turn it is). LinesOfAction handles the user interface. We try to put as little code in LinesOfAction as possible, because user interface code must be tested manually.

Don't go overboard creating objects. Even though pieces are physical "objects", it's simpler to represent the board as a two-dimensional array of, say, chars, with the entry at each location indicating whether it is black, white, or vacant.

Break down complicated methods, separate control from data, and take advantage of existing data structures. For example, here is my method to find all of the legal moves from a particular location:

    public Set<Pair> findAllLegalMovesFrom(Pair location) {
        Set<Pair> result = new HashSet<>();
        for (Pair direction : DIRECTIONS) {
            Pair there = findMoveInDirection(location, direction);
            if (there != null) {
                result.add(there);
            }
        }
        return result;
    }
Wins are detected using depth-first search, much like auto-clearing in Minesweeper.

What To Hand In
Hand in all of your .java files including tests.
