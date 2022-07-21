# TODOs

 - [ ] Find a way to move a "Block" twice before the next "Block" is tried (mainly in "BFS"). Either "Move" gets a "Direction... direction" (check how reverse works), or accept "Move... moves" as arguments.
 - [ ] Change the way "Blocks" are created, so that the "name" and "color" can be set manually.
 - [ ] Change the Project so that the "Solver" is separate from the Game itself, so that "RushHour", "So huepft der Hase" and others can be programmed and solved with the same engine.
 - [X] Add Threads (ThreadPool) to "BFS".
 - [X] Fix the Tests (TODO#2 might be neccessary for this).
 - [ ] Go over all "TODOs" in the src.
 - [ ] Interfaces? (Searchable, Moveable?, ...).
 - [ ] Save the StartingPosition in a JSON-File and extract the data in the Game.java class
 - [ ] Maybe change GameField.java so that it is only the field and the BlockSet gets passed to it (maybe make it a singleton)
 - [ ] Finish the 'Makefile', every package gets a Makefile and those get called in the src Makefile and so on...
 - [ ] Close Threads when finished
 - [ ] Use streams() when looking for a Block in the BlockSet (it seams that streams are slower than for-each loop -> check that)