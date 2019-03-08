// ANNA AIKATERINI TSIALIOU, 4188
// DIMITRIS PERKAS, 4156
// !!!IMPORTANT NOTE!!! In some previous assignments we forgot to write our names and AM 
// before turning them in. But we are still in the same team. Sorry for the inconvenience :) 

import java.io.*;
import java.util.Random;

// minimum index priority queue implemented with a binary heap
public class IndexMinPQ<Key extends Comparable<Key>>
{
    private int N;           // number of items on priority queue
    private int   pq[];      // binary heap of items with respect to their keys
    private int   index[];   // index[j] = position of item j in pq : pq[index[j]]=index[pq[j]]=j
    private Key[] keys;      // key[j]   
   

    public IndexMinPQ(int maxN)
    {
        keys   = (Key []) new Comparable[maxN+1];
        pq     = new int[maxN+1];
        index  = new int[maxN+1];
        for (int i = 0; i <= maxN; i++) index[i]=-1;
    }

    // check if priority queue is empty
    public boolean isEmpty() {
        return N == 0;
    }

    // check if priority queue contains item j
    public boolean contains(int j) {
        return index[j] != -1;
    }

    // return the key of item j
    public Key getKey(int j) {
        return keys[j];
    }

    // insert item j with a key
    public void insert(int j, Key key) {
        pq[++N] = j;
        keys[j] = key;
        index[pq[N]] = j;
        int k = index[j];
        fixUp(k);
    }

    // delete and return item with minimum key
    public int delMin() {
        int min = 0;
        for (int j = 0; j < N; j++){
            exch(min, N-1);
            fixDown(1);
        }
        return pq[--N];
    }

    // return item with minimum key
    public int minItem() {
        int j, minIndex = 0;
        for (j = 1; j < N; j++)  if ( less(j,minIndex) ) minIndex = j;
        return pq[minIndex];
    }

public void change(int j, Key key) {
        keys[j] = key;
    }

    // compare keys of items pq[i] and pq[j]
    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }


    // exchange the position of items pq[i] and pq[j] in array pq[]
    private void exch(int i, int j) {
        int t=pq[i]; 
        pq[i]=pq[j]; 
        pq[j]=t;
        index[pq[i]]=i;
        index[pq[j]]=j;
    }

    private void fixUp(int k){
        while(k>1 && less(k/2, k)){
            exch(k, k/2);
            k = k/2;
        }
    }

    private void fixDown(int k){
        int j;
        while(2*k <= N){
            j = 2*k;
            if(j<N && less(j, j+1)){
                j++;
            }
            if (!less(k,j)){
                break;
            }
            exch(k,j); k=j;
        }
    }    
    // print pq, key and index arrays
    public void printPQ()
    {
        for (int j=1; j<=N; j++)
            System.out.println("pq["+j+"]= " + pq[j] + ", key= " + keys[pq[j]]);

        for (int j=0; j<N; j++)
            System.out.println("index["+j+"]= " + index[j]);
    }

    public static void main(String[] args) {
        System.out.println("Test Index Min Priority Queue");

        int N = Integer.parseInt(args[0]);
        System.out.println("Number of items to be inserted = " + N);

        long startTime = System.currentTimeMillis();
        IndexMinPQ<Integer> PQ = new IndexMinPQ<Integer>(N);
        Random rand = new Random(0);

        for (int i=0; i<N; i++) {
            int key = rand.nextInt(N*N); // assign random keys
            System.out.println("insert item " + i + " key " + key);    
            PQ.insert(i,key);
        }
        PQ.printPQ();
        while ( !PQ.isEmpty() ) {
            int key = PQ.getKey(PQ.minItem());
            int k = PQ.delMin();
            System.out.println("delMin item " + k + " key " + key);
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("total time = " + totalTime);
    }
}
