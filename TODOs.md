# TODOs

 - [ ] The Games need to have one common interface or inherit form the same abstract class (for now the solver has only one specific 'Game')

 - [ ] Maybe implement a new class/record called 'WinCondition' to know where and how to win (squares and number/names of Blocks(Rabbits)).
 - [ ] Check when and where to create copies (Foo newFoo = new Foo(foo)), maybe the "recieving" end does not also need to create a copy?
 - [ ] Check the #equals and #hashCode methods of PositionList and others if they are implemented correctly.
 - [ ] Maybe change GameField class so that it is only the field and the BlockSet gets passed to it (maybe make it a singleton?)
 - [ ] Try find to find a way to move a "Block" twice before the next "Block" is tried. -> MovePattern.All_DIRECTIONS_PLUS
 - [ ] Implement other Games (RushHour, "so huepft der Hase", ...)
 - [ ] Implement #toString() methods for every class.
 - [ ] Fix the Tests.
 - [ ] Save the StartingPosition in a JSON-File and extract the data in the Game class
 - [ ] Finish the 'Makefile', every package gets a Makefile and those get called in the src Makefile and so on...
 - [ ] Change the Project so that the "Solver" is separate from the Game itself
 - [ ] Close Threads when finished
 - [ ] Go over all 'TODO', 'FIXME', and 'XXX' in the src-folders.
 - [X] Change the way "Blocks" are created, so that the "name" and "color" can be set manually.
 - [X] Add Threads (ThreadPool) to "BFS".
 - [X] Use streams() when looking for a Block in the BlockSet (it seams that streams are slower than for-each loop -> check that)
