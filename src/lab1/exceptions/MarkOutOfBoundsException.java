package lab1.exceptions;

public class MarkOutOfBoundsException extends Exception {
    public MarkOutOfBoundsException() {
        super();
    }

    public MarkOutOfBoundsException(int mark) {
        super(mark + "");
    }
}
