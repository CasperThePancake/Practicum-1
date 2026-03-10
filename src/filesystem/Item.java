package filesystem;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

import java.util.Date;

/**
 * Abstract item class to serve as foundation for File, Directory, and Link
 *
 * @author Casper Vermeeren; Loïck Sansen
 */
public abstract class Item {
    private String name;
    private Date createTime;
    private Date modifyTime;
    private boolean writable;

    public Item(String name, boolean writable) {
        this.setName(name,true);
        this.setWritable(writable);
        this.createTime = new Date();
        this.modifyTime = null;
    }

    private static boolean isValidName(String name) {
        return name.matches("[a-zA-Z0-9_.\\-]*");
    }

    private static String cleanIllegalName(String name) {
        return name.replaceAll("[^a-zA-Z0-9_.\\-]", "");
    }

    /**
     * Set the name for an item
     *
     * @post If given name only contains letters, numbers, dots, dashes, and underscores and is not empty, name is given name
     * @post If given name contains illegal characters, name is given name filtered from these illegal characters
     * @post If given name or filtered given name is empty, name is 'New-item'
     *
     * @throws WriteException If item is not writable
     *      | !isWritable()
     *
     * @param name The given name of the item
     */
    public void setName(String name) throws WriteException { // TOTAAL PROGRAMMEREN
        // Check if item writable
        if (this.isWritable()) {
            // Using regex to check if all characters in string are legal
            if (isValidName(name)) {
                if (!name.isEmpty()) {
                    this.name = name;
                } else {
                    this.name = "New-item";
                }
            } else {
                String cleaned = cleanIllegalName(name);
                if (!cleaned.isEmpty()) {
                    this.name = cleaned;
                } else {
                    this.name = "New-item";
                }
            }
            // In any case the item name will have been changed, so update modified time
            this.modifyTime = new Date();
        } else {
            throw new WriteException("This item is not writable!");
        }
    }

    // Private method to set the name while ignoring writability rules for use in constructors
    private void setName(String name, boolean ignoreWritability) {
        if (ignoreWritability) {
            // Using regex to check if all characters in string are legal
            if (isValidName(name)) {
                if (!name.isEmpty()) {
                    this.name = name;
                } else {
                    this.name = "New-item";
                }
            } else {
                String cleaned = cleanIllegalName(name);
                if (!cleaned.isEmpty()) {
                    this.name = cleaned;
                } else {
                    this.name = "New-item";
                }
            }
        } else {
            setName(name);
        }
    }

    /**
     * Set the writability of an item
     *
     * @post Writability is given writability
     *      | new.isWritable() == writable
     *
     * @param writable Writability of the item
     */
    public void setWritable(boolean writable) { // DEFENSIEF PROGRAMMEREN
        this.writable = writable;
    }

    /**
     * Set the creation time of the item
     *
     * @post If given date is invalid, set createTime to new date object
     *
     * @post createTime is given date otherwise
     *
     * @param createTime Creation date for the item
     */
    public void setCreateTime(Date createTime) { // TOTAAL PROGRAMMEREN
        if (createTime != null) {
            this.createTime = createTime;
        } else {
            this.createTime = new Date();
        }
    }

    /**
     * Set the modify time of the item
     *
     * @post If given date is invalid, set modifyTime to new date object
     *
     * @post modifyTime is given date otherwise
     *
     * @param modifyTime Modify date for the item
     */
    public void setModifyTime(Date modifyTime) { // TOTAAL PROGRAMMEREN
        if (modifyTime != null) {
            this.modifyTime = modifyTime;
        } else {
            this.modifyTime = new Date();
        }
    }

    /**
     * Get the name of the item
     *
     * @return Name of the item
     */
    @Basic
    @Raw
    public String getName() {
        return name;
    }

    /**
     * Get the creation time of the item
     *
     * @return Creation time of the item
     */
    @Basic @Raw
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * Get the last modified time of the item
     *
     * @return Last modified time of the item
     */
    @Basic @Raw
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * Get the writability of the item
     *
     * @return Writability of the item
     */
    @Basic @Raw
    public boolean isWritable() {
        return writable;
    }

    /**
     * Determines whether this item and another item have an overlapping use period
     *
     * @return False if the specified other item is invalid; False if either one of the items has not been modified yet after creation; True if modifyDate of oldest item is after creationDate of newest item; False otherwise
     *
     * @param other The other item to compare with
     */
    public boolean hasOverlappingUsePeriod(Item other) { // TOTAAL PROGRAMMEREN
        if (other == null) {
            return false;
        }
        // If one of the items hasn't been used yet, always return false
        if (this.getModifyTime() == null || other.getModifyTime() == null) {
            return false;
        }
        // First, determine which item is the oldest
        if (this.getCreateTime().after(other.getCreateTime())) { // Other is oldest
            return other.getModifyTime().after(this.getCreateTime());
        } else { // This is oldest
            return this.getModifyTime().after(other.getCreateTime());
        }
    }
}
