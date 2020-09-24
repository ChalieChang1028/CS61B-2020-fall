import java.util.ArrayList;
import java.util.List;

/**
 * Class that collects timing information about SLList getLast method.
 */
public class TimeSLList {
    private static void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        List<Integer> Ns = new ArrayList<>();
        List<Integer> Op = new ArrayList<>();
        List<Double> time = new ArrayList<>();
        Ns.add(1000);
        Op.add(10000);
        Ns.add(2000);
        Op.add(10000);
        Ns.add(4000);
        Op.add(10000);
        Ns.add(8000);
        Op.add(10000);
        Ns.add(16000);
        Op.add(10000);
        Ns.add(32000);
        Op.add(10000);
        Ns.add(64000);
        Op.add(10000);
        for (int i = 0; i < Ns.size(); i++) {
            SLList<Integer> test = new SLList<>();
            int c = Ns.get(i);
            while (c > 0) {
                test.addLast(1);
                c--;
            }
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < 10000; j++) {
                test.getLast();
            }
            double etime = sw.elapsedTime();
            time.add(etime);
        }
        printTimingTable(Ns, time, Op);
    }

}
