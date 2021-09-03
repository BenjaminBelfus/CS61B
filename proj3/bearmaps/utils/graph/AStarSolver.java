package bearmaps.utils.graph;

import bearmaps.AugmentedStreetMapGraph;
import bearmaps.utils.graph.streetmap.StreetMapGraph;
import bearmaps.utils.pq.MinHeapPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    List<Vertex> sol = new ArrayList<>();
    SolverOutcome outcome_toReturn = null;

    double timeout;
    double total_solution_weight;
    double timeSpent;
    int number_of_vertex_explored = 0;

    List<Vertex> visited = new ArrayList<>();

    private HashMap<Vertex, Double> distTo = new HashMap<>();
    private HashMap<Vertex, Vertex> edgeTo = new HashMap<>();



    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        // Starting the stopwatch
        Stopwatch sw = new Stopwatch();

        //creating my priority queue and adding first element only with its corresponding weigth
        MinHeapPQ<Vertex> pq = new MinHeapPQ<>();
        pq.insert(start, 0);

        // adding first element to both distTO and edgeTo
        distTo.put(start, 0.0);


        while (pq.size() > 0) {
            //removing from queue the one with less value (taking into account the heuristic value), adding to the visited and increasing number of explored
            Vertex holdingTo = pq.poll();
            visited.add(holdingTo);
            number_of_vertex_explored += 1;

            // list of all possible weighted neighbours of my holdingTo (the one I am looking right now)
            List<WeightedEdge<Vertex>> l = input.neighbors(holdingTo);

            for (WeightedEdge<Vertex> v: l) {
                // need vertex from to grab all the previous weight
                Vertex from = v.from();

                //need vertex to so I can do my heuristic value
                Vertex to = v.to();

                double new_dist_for_now = distTo.get(from) + v.weight();
                double priority_for_now = new_dist_for_now + input.estimatedDistanceToGoal(to, end);

                //check if this hasn't been analyzed yet, by checking if my disTo does not contain the one I am trying to go to. if this is the
                //case, insert it to the queue.
                if (!distTo.containsKey(to)) {
                    distTo.put(to, new_dist_for_now);
                    edgeTo.put(to, from);
                    pq.insert(to, priority_for_now);
                }
                else {
                    //comparing new priorities to old ones to see if I need to relax
                    if (distTo.get(to) > new_dist_for_now) {
                        distTo.replace(to, new_dist_for_now);
                        edgeTo.replace(to, from);

                        //checking if it is still in the queue and has not been analyzed yet, if so change priority
                        if (pq.contains(to)) {
                            pq.changePriority(to, priority_for_now);
                        } else {
                            pq.insert(to, priority_for_now);
                        }
                    }
                }
            }
            if (holdingTo.equals(end) && sw.elapsedTime() < timeout) {
                total_solution_weight = distTo.get(end);
                while (holdingTo != null) {
                    sol.add(holdingTo);
                    holdingTo = edgeTo.get(holdingTo);
                }
                Collections.reverse(sol);
                outcome_toReturn = SolverOutcome.SOLVED;
                break;
            } else if (sw.elapsedTime() >= timeout) {
                outcome_toReturn = SolverOutcome.TIMEOUT;
                return;
            } else if (pq.size() == 0){
                outcome_toReturn = SolverOutcome.UNSOLVABLE;
                return;
            }
        }
        timeSpent = sw.elapsedTime();
    }


    @Override
    public SolverOutcome outcome() {
       return outcome_toReturn;
    }



    @Override
    public List<Vertex> solution() {
        return sol;
    }


    @Override
    public double solutionWeight() {
        if (this.outcome() == SolverOutcome.UNSOLVABLE) {
            return 0;
        } else if (this.outcome() == SolverOutcome.TIMEOUT) {
            return 0;
        } else {
            return total_solution_weight;
        }
    }


    @Override
    public int numStatesExplored() {
        return number_of_vertex_explored;
    }



    @Override
    public double explorationTime() {
        return timeSpent;
    }

}
