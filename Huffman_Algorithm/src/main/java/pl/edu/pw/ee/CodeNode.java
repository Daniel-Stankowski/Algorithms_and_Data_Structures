package pl.edu.pw.ee;

public class CodeNode {
    private final char character;
    private final String code;

    public CodeNode(char character, String code) {
        this.character = character;
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public char getCharacter() {
        return this.character;
    }

    @Override
    public String toString() {
        return (int) this.character + ":" + this.code;
    }
}
