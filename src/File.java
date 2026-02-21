/*
DISCUSSION BOARD

__Implementatie__
- Functies ivm naamgeving: totaal uitwerken
- Functies ivm bestandsgrootte: nominaal uitwerken
- Functies ivm schrijfrecht(overtreding): defensief uitwerken
- Functies ivm tijdstippen: totaal uitwerken
- Al de rest: naar keuze
- We moeten de nodige JUnit tests maken! (FileTest.java)
 */

import java.util.Date;

public class File {

    private int size;
    private String name;
    private Date createTime;
    private Date modifyTime;
    private boolean writable;
    private static final int maxSize = Integer.MAX_VALUE;

    // Constructors
    public File(String name) {
        this.setName(name);
        this.createTime = new Date();
        this.modifyTime = new Date(); // WIP If not modified yet, this should be null (usePeriod = 0)
        this.writable = true;
        this.size = 0;
    }

    public File(String name, int size, boolean writable) {
        this.setName(name);
        this.createTime = new Date();
        this.modifyTime = new Date();
        this.setWritable(writable);
        this.setSize(size);
    }

    // Other methods

    /**
     * Set the name for a file
     *
     * @post If given name only contains letters, numbers, dots, dashes, and underscores and is not empty, name is given name
     * @post If given name contains illegal characters, name is given name filtered from these illegal characters
     * @post If given name or filtered given name is empty, name is 'New-File'
     *
     * @param name The given name of the file
     */
    public void setName(String name) { // TOTAAL PROGRAMMEREN
        // Using regex to check if all characters in string are legal
        if (name.matches("[a-zA-Z0-9_.\\-]*")) {
            if (!name.isEmpty()) {
                this.name = name;
            } else {
                this.name = "New-File";
            }
        } else {
            String cleaned = name.replaceAll("[^a-zA-Z0-9_.\\-]", "");
            if (!cleaned.isEmpty()) {
                this.name = cleaned;
            } else {
                this.name = "New-File";
            }
        }
        // In any case the file name will have been changed, so update modified time
        this.modifyTime = new Date();
    }

    private boolean canAcceptForSize(int size) {
        return size >= 0 && size <= maxSize;
    }

    /**
     * Set the size for a file
     *
     * @pre The given size is non-negative and less than maxSize
     *      | size >= 0 && size <= maxSize
     *
     *
     * @param size The given size of the file
     */
    private void setSize(int size) { // NOMINAAL PROGRAMMEREN
        if (canAcceptForSize(size)) {
            this.size = size;
            // Update modified time
            this.modifyTime = new Date();
        }
    }

    private void setWritable(boolean writable) {
        this.writable = writable;
    }

    /**
     * Increases file size by given amount
     *
     * @pre The given amount is strictly positive
     *      | amount > 0
     *
     * @param amount Amount to enlarge size by
     */
    public void enlarge(int amount) { // NOMINAAL PROGRAMMEREN
        if (amount > 0) {
            this.setSize(this.size + amount);
        }
    }

    /**
     * Decreases file size by given amount
     *
     * @pre The given amount is strictly positive
     *      | amount > 0
     *
     * @param amount Amount to shorten size by
     */
    public void shorten(int amount) { // NOMINAAL PROGRAMMEREN
        if (amount > 0) {
            this.setSize(this.size - amount);
        }
    }

    public void hasOverlappingUsePeriod(File other) { // TOTAAL PROGRAMMEREN
        // WIP
    }
}
