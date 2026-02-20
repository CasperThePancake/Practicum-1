import java.util.Date;

// loick was here

// fuck off
public class File {

    private int size;
    private String name;
    private Date createTime;
    private Date modifyTime;
    private boolean writable;
    private static final int maxSize = 2048;

    public File(String name) {
        this.rename(name);
        this.createTime = new Date();
        this.modifyTime = new Date(); // WIP If not modified yet, this should be null (usePeriod = 0)
        this.writable = true;
        this.size = 0;
    }

    public File(String name, int size, boolean writable) {
        this.rename(name);
        this.createTime = new Date();
        this.modifyTime = new Date();
        this.writable = writable;
        this.setSize(size);
    }

    public void rename(String name) {
        this.name = name; // NAME CHECKS HERE
    }

    private void setSize(int size) {
        this.size = size; // WIP CHECKS
    }

    public void enlarge(int amount) {
        this.setSize(this.size + amount); // WIP POS CHECKS
    }

    public void shorten(int amount) {
        this.setSize(this.size - amount); // WIP POS CHECKS
    }

    public void hasOverlappingUsePeriod(File other) {
        // WIP
    }
}
