public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> ans = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            ans.addLast(word.charAt(i));
        }
        return ans;
    }
    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }
        if (word.length() == 1 || word.length() == 0) {
            return true;
        }
        int l = 0, r = word.length() - 1;
        while (l < r) {
            if (word.charAt(l) != word.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) {
            return false;
        }
        if (word.length() == 1 || word.length() == 0) {
            return true;
        }
        if (cc == null) {
            return isPalindrome(word);
        }
        int l = 0, r = word.length() - 1;
        while (l < r) {
            if (!cc.equalChars(word.charAt(l), word.charAt(r))) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

}
