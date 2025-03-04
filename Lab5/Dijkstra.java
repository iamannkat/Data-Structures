import java.io.* ;

// compute shortest paths from a source vertex s
public class Dijkstra
{
    private static DirectedEdge[] edgeTo;        // last edge in shortest path
    private static long[] distTo;                // distance from source
    //private static NaiveIndexMinPQ<Long> PQ;
    private static IndexMinPQ<Long> PQ;

    // update distances of neighbours of node v
    private static void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (PQ.contains(w)) PQ.change(w,distTo[w]);
                else PQ.insert(w,distTo[w]);
            }
        }
    }

    // Dijkstra's single source shorstest paths algorithm from source node s
    public static void Dijkstra(EdgeWeightedDigraph G, int s)
    {
        int N = G.nodes();
        edgeTo = new DirectedEdge[N];
        distTo = new long[N];
        for (int i=0; i<N; i++) {
            distTo[i] = Long.MAX_VALUE;
        }
        distTo[s] = 0;

        //PQ = new NaiveIndexMinPQ<Long>(N);
        PQ = new IndexMinPQ<Long>(N);
        PQ.insert(s,distTo[s]);
        int v=-1;
        while (!PQ.isEmpty()) {
            v = PQ.delMin();
            relax(G,v);
        }
        if (v!=-1) System.out.println("last settled node = " + v + ", distance from source node " + s + " = " + distTo[v]);
    }

    public static void main(String[] args)
    {
        In.init();
        int N = In.getInt();
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(N+1);
        int M = In.getInt();
        for (int i=0; i<M; i++)
        {
            int v = In.getInt();
            int w = In.getInt();
            long weight = In.getLong();
            DirectedEdge e = new DirectedEdge(v,w,weight);
            G.addEdge(e);
        }
        long startTime = System.currentTimeMillis();
        Dijkstra(G,1);
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("total time = " + totalTime);
    }
}
