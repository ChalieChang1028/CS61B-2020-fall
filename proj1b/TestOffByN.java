import org.junit.Test;
import static org.junit.Assert.*;


public class TestOffByN {

    static CharacterComparator offByN = new OffByN(2);
    @Test
    public void testOffByOne() {
        assertEquals(false, offByN.equalChars('a', 'b'));
        assertEquals(true, offByN.equalChars('a', 'c'));
        assertEquals(false, offByN.equalChars('b', 'c'));
        assertEquals(false, offByN.equalChars('c', 'b'));
        assertEquals(true, offByN.equalChars('a', 'c'));
        assertEquals(true, offByN.equalChars('c', 'a'));
        assertEquals(false, offByN.equalChars('a', 'a'));
    }
}
