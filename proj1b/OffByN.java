public class OffByN implements CharacterComparator {
    private int num;
    OffByN(int n) {
        num = n;
    }
    @Override
    public boolean equalChars(char x, char y) {
        if (Math.abs(x - y) == num) {
            return true;
        }
        return false;
    }
}
