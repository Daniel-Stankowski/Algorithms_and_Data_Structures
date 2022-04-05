package pl.edu.pw.ee;

public class Node {
    private char character;
    private int nOfEncounters;
    private Node left;
    private Node right;

    public Node(char character) {
        this.character = character;
        nOfEncounters = 1;
        left = null;
        right = null;
    }

    public Node(int nOfEncounters, Node left, Node right) {
        this.character = 0;
        this.nOfEncounters = nOfEncounters;
        this.left = left;
        this.right = right;
    }

    public Node getLeft() {
        return this.left;
    }

    public Node getRight() {
        return this.right;
    }

    public char getCharacter() {
        return this.character;
    }

    public int getNOfEncounters() {
        return this.nOfEncounters;
    }

    public int compareToCharacter(char character) {
        return this.character - character;
    }

    public void incrementNOfEncounters() {
        nOfEncounters++;
    }

    @Override
    public String toString() {
        return (int) character + ":" + nOfEncounters;
    }

}
