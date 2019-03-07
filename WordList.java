//ANNA AIKATERINI TSIALIOU, 4188
//DIMITRIS PERKAS, 4156

import java.io.*;

public class WordList {

    private class Node {
        String str;
        int count;   // occurrences of str in input file
        Node next;   // next node in linked list

        Node(String str) {
            this.str = str;
            this.next = null;
            this.count = 1;
        }
    }

    private Node first;     // first node of the linked list
    private int nodeCount;  // number of nodes

    public WordList() {
        this.nodeCount = 0;
    }

    int nodeCount() {
        return nodeCount;
    }

    // return the number of occurrences of string s in the input file
    public int contains(String s) {

        Node t = first;

        while(t!=null)
        {
                if(t.str.equals(s)==true)
                {
                        return t.count;
                }
                t=t.next;
        }
        return 0;
    }

    // add one more occurence of string s; insert new node if s is not in the linked list
    public void insert(String s) {
        //check if first node is in linked list
       
        if (first == null){
            first = new Node(s);
            nodeCount ++;
            return;
        }
        
        //check all the rest
        Node t = first;
        while(t.next != null)
        { 
            if (t.str.equals(s)==true){ 
                t.count++;
                return;
            }
            t = t.next; 
        }
        //if string not in linked list then create new one
        Node newNode = new Node(s);
        t.next = newNode ;
        nodeCount ++; 
    }

    // delete string s from the linked list
    public void delete(String s) {

        //store first node
        Node t = first; Node prev = null; //temps 
        //check if first node is the key
        if(t !=null && t.str == s){
            first = t.next.next; //move to the next node
        }
        while(t.next !=null && t.str != s)
        {
            prev = t;
            t = t.next;
            
        }
        prev.next = t.next;
    }

    // find the most frequent string in the linked list
    public String mostFrequent() {
        int max = 0;
        if (first != null){
            first.count = max;
        }
        Node t = first;
        String maxString = "";
        while(t.next != null){
            if(t.next.count > max){
                max = t.next.count;
                maxString = t.next.str;
            }
            t = t.next;
        }
        return maxString; 
    }

    // delete the most frequent string in the linked list
    public String deleteMostFrequent() {

        String mostfreq = this.mostFrequent();
        this.delete(mostfreq);
       
        return mostfreq; 
    }

    public static void main(String[] args) {
        System.out.println("Test WordList");

        WordList L = new WordList();

        In.init();
        long startTime = System.currentTimeMillis();
        while (!In.empty()) {
            String s = In.getString();
            L.insert(s);
        }
        long endTime = System.currentTimeMillis();
        long listTime = endTime - startTime;
        System.out.println("linked list construction time = " + listTime);
        System.out.println("number of linked list nodes  = " + L.nodeCount());

        System.out.println("contains 'and' " + L.contains("and") + " times");
        System.out.println("contains 'astonished' " + L.contains("astonished") + " times");
        System.out.println("contains 'boat' " + L.contains("boat") + " times");
        System.out.println("contains 'the' " + L.contains("the") + " times");
        System.out.println("contains 'train' " + L.contains("train") + " times");
        System.out.println("contains 'tom' " + L.contains("tom") + " times");
        System.out.println("contains 'wondered' " + L.contains("wondered") + " times");

        System.out.println("most frequent string = " + L.mostFrequent() + "");

        String s = L.deleteMostFrequent();
        System.out.println("deleted string " + s + "");
        System.out.println("next most frequent string = " + L.mostFrequent() + "");

        endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("total running time = " + totalTime);
    }
}
