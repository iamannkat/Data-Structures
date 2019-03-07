import java.util.Random;
import java.io.*;

public class RandomUnions {

    public static void main(String[] args) {
        
        int N = Integer.parseInt(args[0]);
        int j, k, Nunites;
        long startTime, endTime, totalTime;
        
        
        Random rand = new Random(0);
        System.out.println("Test SimpleUnionFind");
        startTime = System.currentTimeMillis();
        SimpleUnionFind SUF = new SimpleUnionFind(N);
        System.out.println("Number of items = " + N);
        Nunites = 0;
        while (SUF.setCount() > 1){
            j = rand.nextInt(N) + 1;
            k = rand.nextInt(N) + 1;
            //System.out.println("" + j + " " + k);
            SUF.unite(j,k);
            Nunites++;
        }
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        System.out.println("number of unites = " + Nunites);
        System.out.println("total time = " + totalTime);
        
        
        rand = new Random(0);
        System.out.println("Test UnionFind");
        startTime = System.currentTimeMillis();
        UnionFind UF = new UnionFind(N);
        System.out.println("Number of items = " + N);
        Nunites = 0;
        while (UF.setCount() > 1){
            j = rand.nextInt(N) + 1;
            k = rand.nextInt(N) + 1;
            //System.out.println("" + j + " " + k);
            UF.unite(j,k);
            Nunites++;
        }
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        System.out.println("number of unites = " + Nunites);
        System.out.println("total time = " + totalTime);
    }
}
