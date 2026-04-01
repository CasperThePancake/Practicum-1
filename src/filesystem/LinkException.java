package filesystem;

/**
 * Simple exception for linking errors
 */
public class LinkException extends RuntimeException {
    /**
     * Create a LinkException with given error message
     *
     * @param message Given error message
     */
    public LinkException(String message) {
        super(message);
    }
}