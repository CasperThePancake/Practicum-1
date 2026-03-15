package filesystem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class for the filesystem.File class
 *
 * @author Casper Vermeeren; Loïck Sansen
 */
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
        File testFile = new File("myEpicMovie",2048,false,FileExtension.PDF);

        assertEquals("myEpicMovie",testFile.getName());
        assertEquals(2048,testFile.getSize());
        assertEquals(FileExtension.PDF,testFile.getFileExtension());
        assertFalse(testFile.isWritable());
    }

    @Test
    public void illegalNameTest() {
        File testFile = new File("Adam&Eve",FileExtension.TXT);

        assertEquals("AdamEve",testFile.getName());
    }

    @Test
    public void enlargeTest() {
        File testFile = new File("toBeEnlarged",90,true,FileExtension.PDF);
        testFile.enlarge(10);
        assertEquals(100,testFile.getSize());
    }

    @Test
    public void shortenTest() {
        File testFile = new File("toBeShortened",110,true,FileExtension.PDF);
        testFile.shorten(10);
        assertEquals(100,testFile.getSize());
    }

    @Test
    public void overlapTest1() throws InterruptedException {
        File testFile1 = new File("FirstFile",FileExtension.JAVA);
        sleep(50);
        File testFile2 = new File("SecondFile",FileExtension.JAVA);
        sleep(50);
        testFile2.changeSize(10);

        assertFalse(testFile1.hasOverlappingUsePeriod(testFile2));
    }

    @Test
    public void overlapTest2() throws InterruptedException {
        File testFile1 = new File("FirstFile",FileExtension.JAVA);
        sleep(50);
        File testFile2 = new File("SecondFile",FileExtension.JAVA);
        sleep(50);
        testFile2.changeSize(10);
        sleep(50);
        testFile1.changeSize(15);

        assertTrue(testFile1.hasOverlappingUsePeriod(testFile2));
    }

    @Test
    public void overlapTest3() throws InterruptedException {
        File testFile1 = new File("FirstFile",FileExtension.JAVA);
        sleep(50);
        testFile1.changeSize(15);
        sleep(50);
        File testFile2 = new File("SecondFile",FileExtension.JAVA);
        sleep(50);
        testFile2.changeSize(10);

        assertFalse(testFile1.hasOverlappingUsePeriod(testFile2));
    }

    @Test
    public void setWritableTest() {
        File testFile = new File("myWritableFile",FileExtension.PDF);
        testFile.setWritable(false);

        assertFalse(testFile.isWritable());
    }

    @Test
    public void illegalWritingTest() {
        assertThrows(WriteException.class, () -> {
            File testFile = new File("mySafeFile",44,false,FileExtension.JAVA);
            testFile.enlarge(50);
        });
    }

    @Test
    public void testConstructorDefault() {
        File testFile = new File("test",FileExtension.TXT);

        assertEquals("test",testFile.getName());
        assertEquals(0,testFile.getSize());
        assertTrue(testFile.isWritable());
        assertNull(testFile.getModifyTime());
        assertNotNull(testFile.getCreateTime());
    }
}
