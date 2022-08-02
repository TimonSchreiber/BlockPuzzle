# TODOs

 - [ ] The Games need to have one common interface or inherit form the same abstarct class (for now the solver has only one specific 'Game')

 - [ ] Check the #equals and #hashCode methods of PositionList and others if they are implemented correctly.
 - [ ] Maybe change GameField class so that it is only the field and the BlockSet gets passed to it (maybe make it a singleton)
 - [ ] Implement other Games (RushHour, "so huepft der Hase", ...)
 - [ ] Change the way "Blocks" are created, so that the "name" and "color" can be set manually.
 - [ ] Implement #toString() methods for every class.
 - [ ] Fix the Tests.
 - [ ] Save the StartingPosition in a JSON-File and extract the data in the Game class
 - [ ] Finish the 'Makefile', every package gets a Makefile and those get called in the src Makefile and so on...
 - [ ] Change the Project so that the "Solver" is separate from the Game itself
 - [ ] Close Threads when finished
 - [ ] Go over all "TODOs" in the src.
 - [X] Add Threads (ThreadPool) to "BFS".
 - [X] Use streams() when looking for a Block in the BlockSet (it seams that streams are slower than for-each loop -> check that)
 - [ ] Try find to ind a way to move a "Block" twice before the next "Block" is tried (mainly in "BFS"). Either "Move" gets a "Direction... direction" (check how reverse works), or accept "Move... moves" as arguments.
