import org.junit.Test;
import static org.junit.Assert.*;


public class TestOffByOne {
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testOffByOne() {
        assertEquals(true, offByOne.equalChars('a', 'b'));
        assertEquals(false, offByOne.equalChars('a', 'c'));
        assertEquals(true, offByOne.equalChars('b', 'c'));
        assertEquals(true, offByOne.equalChars('c', 'b'));
        assertEquals(false, offByOne.equalChars('a', 'c'));
        assertEquals(false, offByOne.equalChars('c', 'a'));
        assertEquals(false, offByOne.equalChars('a', 'a'));

        assertEquals(false, offByOne.equalChars('A', 'a'));
        assertEquals(false, offByOne.equalChars('a', 'A'));
        assertEquals(true, offByOne.equalChars('A', 'B'));
        assertEquals(true, offByOne.equalChars('B', 'A'));
        assertEquals(false, offByOne.equalChars('A', 'C'));
        assertEquals(false, offByOne.equalChars('A', 'C'));

        assertEquals(false, offByOne.equalChars('@', 'a'));
        assertEquals(false, offByOne.equalChars('a', '@'));
        assertEquals(false, offByOne.equalChars('@', '#'));
        assertEquals(false, offByOne.equalChars('#', '@'));
        assertEquals(true, offByOne.equalChars('@', 'A'));
        assertEquals(true, offByOne.equalChars('A', '@'));
        assertEquals(true, offByOne.equalChars('#', '$'));
        assertEquals(true, offByOne.equalChars('$', '#'));
        assertEquals(false, offByOne.equalChars('#', '%'));
        assertEquals(false, offByOne.equalChars('%', '#'));

    }
}
