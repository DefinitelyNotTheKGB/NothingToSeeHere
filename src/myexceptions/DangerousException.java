package myexceptions;

public class DangerousException extends RuntimeException {
    public DangerousException(String message) {
        super(message);
    }
}
