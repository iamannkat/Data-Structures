class Queue<Item> {

    private Item[] q;
    private int N, head, tail;

    Queue(int maxN) {
        q = (Item[])new Object[maxN + 1];
        N = maxN+1; head = N; tail = 0;
    }

    boolean isEmpty(){
        return (head % N == tail);
    }

    void put(Item item) {   
        q[tail++] = item;
        tail = tail % N; 
    }
    
    Item get() {
        head = head % N; 
        return q[head++]; 
    }
}
