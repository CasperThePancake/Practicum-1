package filesystem;

/**
 * Simple exception for writability errors
 */
public class WriteException extends RuntimeException {
    /**
     * Create a WriteException with given error message
     *
     * @param message Given error message
     */
    public WriteException(String message) {
        super(message);
    }
}