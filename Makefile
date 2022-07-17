#
# define compiler and compiler variables
#

JFLAGS	= -g
JC		= javac


#
# Clear any default targets for building .class files from .java files; we
# will provide our own target entry to do this in this makefile.
# make has a set of default targets for different suffixes (like .c.o)
# Currently, clearing the default for .java.class is not necessary since
# make dies not have a definition for this target, but later versions of
# make may, so it does not hurt to make sure we clear any default
# definitions for these
#

.SUFFIXES: .java .class


#
# Here is our target entry for createing .class files from .java files
# This is a target that uses the suffix rule syntax:
#	DSTS:
#			rule
# 'TS' is the suffix for the target file, 'DS' is the suffix for the dependency
# file, and 'rule' us the rule for building a target
# '$*' is a built-in macro that gets teh basename of the current target
# Remember that there must be a < tab > befroe the command line ('rule')
#

.java.class:
	$(JC) $(JFLAGS) $*.java


#
# CLASSES is a macro consisting of X words (one for each java source file)
#

CLASSES = \
		src/block/Block.java \
		src/block/BlockInfo.java \
		src/block/BlockTypes.java \
		src/block/Position.java \
		src/block/PositionList.java \
		src/field/BlockSet.java \
		src/field/Directions.java \
		src/field/GameField.java \
		src/field/GameState.java \
		src/field/Move.java \
		src/field/Zeichenblatt.java \
		src/game/Game.java \
		src/solver/BFS_WithThreads.java \
		src/solver/BreadthFirstSearch.java \
		src/solver/DepthFirstSearch.java \
		src/App.java
		

#
# the default make target entry
#

default: classes


#
# This target entry uses Suffix Replacement within a macro:
# $(name:string1=string2)
#		In the words in the macro named 'name' replace 'string1' with 'string2'
# Below we are replacing the suffix .java of all words in the macro CLASSES
# with the .class suffix
#

classes: $(CLASSES:.java=.class)


#
# RM  is a predefined macro in make (RM = rm -f)
#

clean:
	$(RM) bin/block/*.class