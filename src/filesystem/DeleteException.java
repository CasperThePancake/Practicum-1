package filesystem;

/**
 * Simple exception for deletion errors
 */
public class DeleteException extends RuntimeException {
    /**
     * Create a DeleteException with given error message
     *
     * @param message Given error message
     */
    public DeleteException(String message) {
        super(message);
    }
}