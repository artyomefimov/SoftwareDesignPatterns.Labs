package lab1.exceptions;

public class DuplicateSubjectException extends Exception {
    public DuplicateSubjectException() {
        super();
    }

    public DuplicateSubjectException(String message) {
        super(message);
    }
}
