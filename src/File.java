import java.util.Date;

public class File {

    private int size;
    private String name;
    private Date createTime;
    private Date modifyTime;
    private boolean writable;
    private static int maxSize = 2048;

    public File(String name) {

    }

    public File(String name, int size, boolean writable) {

    }

    public void rename(String name) {

    }

    public void enlarge(int amount) {

    }

    public void shorten(int amount) {

    }

    public void hasOverlappingUsePeriod(File other) {

    }
}
