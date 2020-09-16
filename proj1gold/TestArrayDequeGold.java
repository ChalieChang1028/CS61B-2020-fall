import static org.junit.Assert.*;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class TestArrayDequeGold {

    @Test
    public void testDeque() {
        StudentArrayDeque<Integer> sarr = new StudentArrayDeque<>();
        int i = 0;
        while (i < 25){
            double randomNum = StdRandom.uniform();
            if (randomNum < 0.33) {
                sarr.addLast(i);
            } else if (randomNum < 0.66 && ) {
                sarr.addFirst(i);
            }
            else {

            }
        }
    }
}
