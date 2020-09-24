import java.security.AlgorithmConstraints;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that collects timing information about AList construction.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        List<Integer> Ns = new ArrayList<>();
        List<Integer> Op = new ArrayList<>();
        List<Double> time = new ArrayList<>();
        Ns.add(1000);
        Op.add(1000);
        Ns.add(2000);
        Op.add(2000);
        Ns.add(4000);
        Op.add(4000);
        Ns.add(8000);
        Op.add(8000);
        Ns.add(16000);
        Op.add(16000);
        Ns.add(32000);
        Op.add(32000);
        Ns.add(64000);
        Op.add(64000);
        for (int i = 0; i < Ns.size(); i++) {
            AList<Integer> test = new AList<>();
            int c = Ns.get(i);
            Stopwatch sw = new Stopwatch();
            while (c > 0) {
                test.addLast(1);
                c--;
            }
            double etime = sw.elapsedTime();
            time.add(etime);
        }
        printTimingTable(Ns, time, Op);
    }


}
