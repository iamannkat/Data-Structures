//ANNA AIKATERINI TSIALIOU, 4188
//DIMITRIS PERKAS,4156

import java.io.*;

public class SimpleUnionFind {

    private int[] Pi;     // parent array
    private int N;        // number of items

    SimpleUnionFind(int N) {
        this.N = N;
        Pi = new int[N + 1];
        for (int k = 0; k <= N; k++) {
            Pi[k] = k;
        }
    }

    int find(int v) {
        int k=v;
	    while(k!=Pi[k]){
            k=Pi[k];
        }
        return k;
    }

    void unite(int v, int u) {
        int a=find(v);
	    int b=find(u);
	    while(b!=a){
            Pi[b]=a;
        }
        N--;
    }
    
    int setCount() {
	    return N;  
    }
    
    void print() {
        System.out.println("Simple Set Union Data Structure");
        for (int k=1; k<=N; k++) {
            System.out.println("Pi["+k+"]="+Pi[k]);
        }
    }

    public static void main(String[] args) {
        System.out.println("Test SimpleUnionFind");

        int N = 16; 
        SimpleUnionFind SUF = new SimpleUnionFind(N);
        
        for (int k=1; k<=3; k++){
            SUF.unite(k+1,k);
            SUF.unite(k+5,k+4);
            SUF.unite(k+9,k+8);
            SUF.unite(k+13,k+12);
        }
        SUF.unite(1,5);
        SUF.unite(9,13);
        SUF.print();
        SUF.unite(1,13);
        SUF.find(2);
        SUF.print();
    }
}
