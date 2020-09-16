import static org.junit.Assert.*;
import org.junit.Test;
import java.nio.charset.StandardCharsets;

public class TestArrayDequeGold {

    @Test
    public void testDeque() {
        StudentArrayDeque<Integer> sarr = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> sol = new ArrayDequeSolution<>();
        int i = 0;
        String record = "";

        while (i < 100){
            double randomNum = StdRandom.uniform();
            if (randomNum <= 0.25) {
                sarr.addLast(i);
                sol.addLast(i);
                record += "addLast(" + i + ")\n";
            } else if (randomNum <= 0.5 && sarr.size() > 0) {
                Integer stu = sarr.removeFirst(), act = sol.removeFirst();
                record += "removeFirst()\n";
                assertEquals(record, stu, act);
            } else if (randomNum <= 0.75 && sarr.size() > 0) {
                Integer stu = sarr.removeLast(), act = sol.removeLast();
                record += "removeLast()\n";
                assertEquals(record, stu, act);
            } else {
                sarr.addFirst(i);
                sol.addFirst(i);
                record += "addFirst(" + i + ")\n";
            }
            i++;
        }
    }
}
