package filesystem;

/**
 * A basic file extension enum for OGP Practicum
 *
 * @author Casper Vermeeren; Loïck Sansen
 */
public enum FileExtension {
    // =================================================================================
    // Enum options
    // =================================================================================
    TXT("txt"),
    PDF("pdf"),
    JAVA("java");

    // =================================================================================
    // Attributes
    // =================================================================================
    private final String suffix;

    // =================================================================================
    // Constructors
    // =================================================================================
    /**
     * Create a new FileExtension enum with given suffix
     *
     * @param suffix Suffix (file extension ending) for this FileExtension
     */
    private FileExtension(String suffix) {
        this.suffix = suffix;
    }

    // =================================================================================
    // Other methods
    // =================================================================================
    /**
     * Get the suffix for this file extension
     *
     * @return Suffix for this file extension
     */
    public String getSuffix() {
        return suffix;
    }
}