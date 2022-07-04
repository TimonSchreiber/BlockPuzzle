JAVAC	= javac
JAVA	= java

default:: App.class

clean::
	-rm -f *.class

# kleines Testbeispiel
run:: App.class
	$(JAVA) App D 2

App.class: src/App.java
	$(JAVAC) $<
