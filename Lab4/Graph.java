import java.io.*;

public class Graph {

    private final int N;                // number of vertices
    private int M;                      // number of edges
    private Collection<Integer>[] adj;  // adjacency lists
    private int[] parent ;
    private int last;

    public Graph(int N) {
        this.N = N;
        this.M = 0;
        adj = (Collection<Integer>[]) new Collection[N];  // array of references to collections
        for (int i = 0; i < N; i++) {
            adj[i] = new Collection<Integer>();  // initialize collections to be empty
        }
        parent = new int[N];
        this.last = 0;
    }

    public void addEdge(int v, int w) {
        adj[v].insert(w);
        adj[w].insert(v);
        M++;
    }

    // list of neighbors 
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    // print adjacency lists
    public void printGraph() {
        System.out.println("adjacency lists");
        for (int v = 0; v < N; v++) {
            System.out.print(v + " : ");
            for (int w : adj(v)) {
                System.out.print(w + " ");
            }
            System.out.println("");
        }
    }

    /* breadth-first search starting from node s */
    void bfs(int s){

        Queue<Integer> Q = new Queue<Integer>(N); //N=vertices
        boolean[] marked = new boolean[M];
        marked[s] = true; 
        Q.put(s);
        while (!Q.isEmpty())
        {
            Integer k = Q.get();            
            for (int v : adj(k)){        
                if (!marked[v]){  
                    marked[v] = true;
                    parent[v] = k;  
                    Q.put(v);
                }
            }
            this.last = k;

        }
    }
    
    /* compute distance (minimun number of edges) from v to w */
    int distance(int v, int w)
    {
        bfs(v);
        int count = 1; 
        while(parent[w]!= v){
            if (parent[w] == w){
                return -1;
            }
            w = parent[w]; 
            count ++; 
        } 
        
        return count; 
    }
    
    /* find and print shortest path from v to w */
    void shortestPath(int v, int w)
    {
        bfs(v);
        System.out.print("The nodes of the path are: " + v + ", ");
        while(parent[w]!= v){
            if (parent[w] == w){
                break;
            }
            
            w = parent[w];
            System.out.print(parent[w]+ ", ");
            }
         
        
        System.out.print(w + "\n");
        
    }
    
    /* compute graph diameter */
   int diameter()
   {
        int diam = N-1; //initialization
        bfs(0);
        int node1 = last;
        bfs(node1);
        int node2 = last;
        int dist = distance(node1,node2);
                
        return dist;
    }    
    
    public static void main(String[] args) {
        In.init();
        int N = In.getInt();
        long startTime = System.currentTimeMillis();
        Graph G = new Graph(N);
        int K = In.getInt();
        for (int i = 0; i < K; i++) {
            int v = In.getInt();
            int w = In.getInt();
            G.addEdge(v, w);
        }
        long constructTime = System.currentTimeMillis();
        System.out.println("Graph construction time = " + (constructTime - startTime));
        //G.printGraph();
        
        int dist = G.distance(0,(N-1)/2);
        System.out.println("Distance from 0 to " + (N-1)/2 + " = " + dist);
        
        dist = G.distance((N-1)/2,N-1);
        System.out.println("Distance from " + (N-1)/2 + " to " + (N-1) + " = " + dist);

        dist = G.distance(0,N-1);
        System.out.println("Distance from 0 to " + (N-1) + " = " + dist);
        
        G.shortestPath(0,N-1);
       
        int diamG = G.diameter();
        System.out.println("Diameter = " + diamG);
        
        long endTime = System.currentTimeMillis();
        System.out.println("Computation time = " + (endTime - constructTime));
    }
}
