import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FileTest {

    @BeforeAll
    public static void BeforeAllTests() {
        // Stuff to run once before all the tests begin
    }

    @BeforeEach
    public void BeforeEachTest() {
        // Stuff to run before each test begins
    }

    @Test
    public void fileCreationTest() {
        File testFile = new File("myEpicMovie.mp4",2048,false);

        assertEquals("myEpicMovie.mp4",testFile.getName());
        assertEquals(2048,testFile.getSize());
        assertFalse(testFile.isWritable());
    }

    @Test
    public void illegalNameTest() {
        File testFile = new File("Adam&Eve");

        assertEquals("AdamEve",testFile.getName());
    }

    @Test
    public void enlargeTest() {
        File testFile = new File("toBeEnlarged",90,true);
        testFile.enlarge(10);
        assertEquals(100,testFile.getSize());
    }

    @Test
    public void shortenTest() {
        File testFile = new File("toBeShortened",110,true);
        testFile.shorten(10);
        assertEquals(100,testFile.getSize());
    }

    @Test
    public void overlapTest1() throws InterruptedException {
        File testFile1 = new File("FirstFile");
        Thread.sleep(50);
        File testFile2 = new File("SecondFile");
        Thread.sleep(50);
        testFile2.enlarge(50);

        assertFalse(testFile1.hasOverlappingUsePeriod(testFile2));
    }

    @Test
    public void overlapTest2() throws InterruptedException {
        File testFile1 = new File("FirstFile");
        Thread.sleep(50);
        File testFile2 = new File("SecondFile");
        Thread.sleep(50);
        testFile2.enlarge(50);
        Thread.sleep(50);
        testFile1.setName("FirstFile-1");

        assertTrue(testFile1.hasOverlappingUsePeriod(testFile2));
    }

    @Test
    public void overlapTest3() throws InterruptedException {
        File testFile1 = new File("FirstFile");
        Thread.sleep(50);
        testFile1.enlarge(50);
        Thread.sleep(50);
        File testFile2 = new File("SecondFile");
        Thread.sleep(50);
        testFile2.setName("SecondFile-1");

        assertFalse(testFile1.hasOverlappingUsePeriod(testFile2));
    }

    @Test
    public void setWritableTest() {
        File testFile = new File("myWritableFile");
        testFile.setWritability(false);
        assertFalse(testFile.isWritable());
    }

    @Test
    public void illegalWritingTest() {
        assertThrows(WriteException.class, () -> {
            File testFile = new File("mySafeFile",44,false);
            testFile.enlarge(50);
        });
    }
}
