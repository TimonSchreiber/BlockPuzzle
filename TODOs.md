# TODOs

 - [ ] Add a boolean for showing the board at all (other than updating it between to moves)
 - [ ] Try find to find a way to move a "Block" twice before the next "Block" is tried. -> MovePattern.All_DIRECTIONS_PLUS with Arrays of Directions?
 - [ ] Clear all 'TODOs'.
 - [ ] Finish the 'Makefile', every package gets a Makefile and those get called in the src Makefile and so on...
 - [ ] Close Threads when finished.
 - [ ] Save the StartingPosition in a JSON-File and extract the data in the Game classes.

 - [X] Add a 'manual mode'.
 - [X] Check when and where to create copies (Foo newFoo = new Foo(foo)), maybe the "receiving"-end does not also need to create a copy?
 - [X] Make the Rabbits of 'Jumping Rabbits' jump over objects.
 - [X] Fix 'GameField#checkWinCondition()' to check more than one block (JumpingRabbits has up to 4 rabbits).
 - [X] Maybe implement a new class/record called 'WinCondition' to know where and how to win (squares and number/names of Blocks(Rabbits)).
 - [X] Implement other Games (RushHour and "JumpingRabbits").
 - [X] The Games need to have one common interface or inherit form the same abstract class (for now the solver has only one specific 'Game').
 - [X] Check the #equals and #hashCode methods of PositionList and others if they are implemented correctly.
 - [X] Fix the Tests.
 - [X] Change the Project so that the "Solver" is separate from the Game itself
 - [X] Implement #toString() methods for every class.
 - [X] Maybe change GameField class so that it is only the field and the BlockSet gets passed to it (maybe make it a singleton?).
 - [X] Change the way "Blocks" are created, so that the "name" and "color" can be set manually.
 - [X] Add Threads (ThreadPool) to "BFS".
 - [X] Use streams() when looking for a Block in the BlockSet (it seams that streams are slower than for-each loop -> check that).
