all: StarMadeExtractor.class

flatfile: classes.txt

classes.txt: StarMadeExtractor.class StarMade.jar
	java StarMadeExtractor StarMade.jar > classes.txt

StarMadeExtractor.class: StarMadeExtractor.java
	javac StarMadeExtractor.java

clean:
	rm StarMadeExtractor.class
