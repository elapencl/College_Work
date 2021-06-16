import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.DijkstraSP;

/**
 * Shortest cycle in a weighted digraph not going through a given edge
 *
 * Time for Depth first search starting at a particular place is Order(v+e) if its done from 1 vertex
 * In this case, however, the Graph could be disconnected. Thus here it's Order(v*(v+e))
 */


public class shortestCycle {
    private int tip;  // tip of the edge in question
    private int tail;  // tail of the edge in question
    private int weight; // weight of the edge in question

    public static void main(String[] args) {
        new shortestCycle().run();
    }


    public void run() {
        In in = new In("data.txt");
        int n = in.readInt(); // number of graphs
        for (int i = 0; i < n; i++) {
            solve(in);
        }
    }

    /**
     * Reads a weighted digraph from in and prints the solution
     */
    public void solve(In in) {
        EdgeWeightedDigraph g = readGraph(in);
        DijkstraSP shortest = new DijkstraSP(g, tip);
        double length = shortest.distTo(tail);
        if (length == Double.POSITIVE_INFINITY) {
            StdOut.print("-1 ");
        } else {
            StdOut.print((int) length + weight + " ");
        }
    }

    private EdgeWeightedDigraph readGraph(In in) {
        int v = in.readInt();
        int e = in.readInt();
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(v + 1);
        tail = in.readInt();
        tip = in.readInt();
        weight = in.readInt();
        for (int i = 0; i < e - 1; i++) {
            g.addEdge(new DirectedEdge(in.readInt(), in.readInt(), in.readInt()));
        }
        return g;
    }
}

