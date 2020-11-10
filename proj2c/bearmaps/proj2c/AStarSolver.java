package bearmaps.proj2c;

import bearmaps.proj2ab.ExtrinsicMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import bearmaps.proj2ab.DoubleMapPQ;
import java.util.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {


    private ArrayList<Vertex> sol;
    private double weight;
    private SolverOutcome ans;
    private int count;
    private double time;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        weight = 0;
        count = 0;
        sol = new ArrayList<>();

        Map<Vertex, Double> distance = new HashMap<>();
        Map<Vertex, Vertex> edge = new HashMap<>();

        distance.put(start, 0.0);
        ExtrinsicMinPQ<Vertex> PQ = new DoubleMapPQ<>();
        PQ.add(start, input.estimatedDistanceToGoal(start, end));

        Stopwatch timer = new Stopwatch();
        while (PQ.size() > 0) {
            // get to goal state
            if (PQ.getSmallest().equals(end)) {
                ans = SolverOutcome.SOLVED;
                Vertex current = end;
                while (current != null) {
                    sol.add(0, current);
                    current = edge.get(current);
                }
                weight = distance.get(end);
                time = timer.elapsedTime();
                return;
            }

            // timeout
            if (timer.elapsedTime() > timeout) {
                time = timer.elapsedTime();
                ans = SolverOutcome.TIMEOUT;
                return;
            }

            // continue exploration
            Vertex cur = PQ.removeSmallest();
            count += 1;
            for (WeightedEdge<Vertex> e : input.neighbors(cur)) {
                Vertex tar = e.to();
                if (!distance.containsKey(tar)
                    || distance.get(cur) + e.weight() < distance.get(tar)) {
                    distance.put(tar, distance.get(cur) + e.weight());
                    edge.put(tar, cur);
                    if (PQ.contains(tar)) {
                        PQ.changePriority(tar, distance.get(tar)
                                + input.estimatedDistanceToGoal(tar, end));
                    } else {
                        PQ.add(e.to(), distance.get(tar)
                                + input.estimatedDistanceToGoal(tar, end));
                    }
                }
            }
        }
        time = timer.elapsedTime();
        ans = SolverOutcome.UNSOLVABLE;
        return;
    }

    @Override
    public SolverOutcome outcome() {
        return ans;
    }

    @Override
    public List<Vertex> solution() {
        return sol;
    }

    @Override
    public double solutionWeight() {
        return weight;
    }

    @Override
    public int numStatesExplored() {
        return count;
    }

    @Override
    public double explorationTime() {
        return time;
    }
}
