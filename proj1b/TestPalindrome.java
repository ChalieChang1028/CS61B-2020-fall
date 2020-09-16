import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    OffByOne cc = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
    @Test
    public void testisPalindrome() {
        String s1 = "";
        String s2 = null;
        String s3 = "aba";
        String s4 = "aaac";
        assertEquals(true, palindrome.isPalindrome(s1));
        assertEquals(false, palindrome.isPalindrome(s2));
        assertEquals(true, palindrome.isPalindrome(s3));
        assertEquals(false, palindrome.isPalindrome(s4));
    }

    @Test
    public void testisPalindromeOffByOne() {
        String s1 = "";
        String s2 = null;
        String s3 = "aba";
        String s4 = "abab";
        String s5 = "acac";
        String s6 = "&-1";
        assertEquals(true, palindrome.isPalindrome(s1, cc));
        assertEquals(false, palindrome.isPalindrome(s2, cc));
        assertEquals(true, palindrome.isPalindrome(s3, cc));
        assertEquals(true, palindrome.isPalindrome(s4, cc));
        assertEquals(false, palindrome.isPalindrome(s5, cc));
        assertEquals(false, palindrome.isPalindrome(s6, cc));
        assertEquals(false, palindrome.isPalindrome(s5, null));
    }
}