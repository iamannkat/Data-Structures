// ANNA AIKATERINI TSIALIOU, 4188
// DIMITRIS PERKAS, 4156

import java.io.*;
import java.util.Random;

public class RedBlackTree<Key extends Comparable<Key>, Item> {

    BSTreeNode root;        // root of binary search tree

    class BSTreeNode {

        Key key;            // key associated with the item stored at node
        Item item;          // item stored at node
        BSTreeNode left;    // left child
        BSTreeNode right;   // right child
        BSTreeNode parent;  // node's parent
        int height;         // node's height
        boolean isRed;      // true if node is red


        // create new node
        BSTreeNode(Key key, Item item, BSTreeNode parent) {
            this.key = key;
            this.item = item;
            this.parent = parent;
            this.height = 1;
            this.isRed = true;
        }
    }

    // search for item with key; returns the last node on the search path 
    BSTreeNode searchNode(Key key) {
        BSTreeNode v = root;
        BSTreeNode pv = null; // parent of v
        while (v != null) {
            int c = key.compareTo(v.key);  // compare with the key of node v
            pv = v;
            if (c < 0) {
                v = v.left;
            } else if (c > 0) {
                v = v.right;
            } else {
                return v; // item found; return node that contains it
            }
        }
        return pv; // item not found; return last node on the search path
    }
    
    public BSTreeNode rotateLeft(BSTreeNode x){
    	BSTreeNode y = x.right;
    	x.right = y.left;
    	
    	if(x.right != null){
    		x.right.parent = x;
    	}
    	
    	y.left = x;
    	y.parent = x.parent;
    	if(x.parent != null){
    		if(x.parent.left == x){
    			x.parent.left = y;
    		}
    		else{
    			x.parent.right = y;
    		}
    	}
    	x.parent = y;
    	updateNodeHeight(x);
    	updateNodeHeight(y);
    	return y;
    }
    
    public BSTreeNode rotateRight(BSTreeNode y){
    	BSTreeNode x = y.left;
    	y.left = x.right;
    	
    	if(y.left != null){
    		y.left.parent = y;
    	}
    	
    	x.right = y;
    	x.parent = y.parent;
    	if(y.parent != null){
    		if(y.parent.left == y){
    			y.parent.left = x;
    		}
    		else{
    			y.parent.right = x;
    		}
    	}
    	y.parent = x;
    	updateNodeHeight(y);
    	updateNodeHeight(x);
    	return x;
    }
    
    private void fix(BSTreeNode x){
    	if(x == root) return;
    	
    	BSTreeNode y,z,uncle,w;
    	
    	y=x.parent;
    	if(!y.isRed)
    	{
    		updateHeights(y);
    		return;
    	}
        //z = w = y.parent;
    	//uncle = sibling(y); //returns brother of y, uncle of x

    }
    

    // search for item with key
    public Item search(Key key) {
        if (root == null) {
            return null; // tree is empty
        }
        BSTreeNode v = searchNode(key);
        int c = key.compareTo(v.key);
        if (c == 0) {
            return v.item;    // item found
        } else {
            return null;      // item not found
        }
    }

    // return the height of a node x; if x is null return 0
    private int getHeight(BSTreeNode x) {
        if (x == null) {
            return 0;
        } else {
            return x.height;
        }
    }

    // update the height of a node
    private void updateNodeHeight(BSTreeNode x) {
        int leftHeight = getHeight(x.left);
        int rightHeight = getHeight(x.right);
        int bf = leftHeight - rightHeight; // balance factor
        if (bf < 0) {
            x.height = rightHeight + 1;
        } else {
            x.height = leftHeight + 1;
        }
    }
    
    // update the height v's ancestors
    private void updateHeights(BSTreeNode v) {
        BSTreeNode u = v;
        while (u != null) {
            updateNodeHeight(u);
            u = u.parent;
        }
    }
    
    // return the height of the binary search tree
    int getTreeHeight() {
        return getHeight(root);
    }

    // insert item with key and return inserted node
    BSTreeNode insertNode(Key key, Item item) {
        if (root == null) { // tree is empty
            root = new BSTreeNode(key, item, null);
            return root;
        }

        BSTreeNode v = searchNode(key); // v is the last node on the search path
        int c = key.compareTo(v.key);
        if (c == 0) { // key already exists in v; overwrite node's item with new item
            v.item = item;
            return v;
        }

        BSTreeNode u = new BSTreeNode(key, item, v); // new node becomes child of v
        if (c < 0) {
            v.left = u;
        } else {
            v.right = u;
        }
        
        return u;
    }

    // insert item with key
    public void insert(Key key, Item item) {
        BSTreeNode v = insertNode(key, item);
        updateHeights(v); 
    }

    // inorder traversal: prints the key of each node
    void printNode(BSTreeNode v, int level) {
        if (v == null) {
            return;
        }
        printNode(v.right, level + 1);
        for (int i = 0; i < level; i++) {
            System.out.print("\t");
        }
        System.out.println("" + v.key + "[" + v.height + "]");
        printNode(v.left, level + 1);
    }

    // print binary tree
    public void print() {
        System.out.println("Printing binary search tree");
        System.out.println("");
        printNode(root, 0);
        System.out.println("");
    }

    public static void main(String[] args) {
        System.out.println("Test Red Black Tree");
        int n = Integer.parseInt(args[0]);
        System.out.println("number of keys n = " + n);

        RedBlackTree T = new RedBlackTree<Integer, String>();

        int[] keys = new int[n];
        for (int i = 1; i <= n; i++) { // store numbers from 1 to n
            keys[i - 1] = i;
        }

        // random shuffle of keys
        Random rand = new Random(1);
        for (int i = 0; i < n; i++) {
            int k = i + rand.nextInt(n - i);
            int swap = keys[k];
            keys[k] = keys[i];
            keys[i] = swap;
        }

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            String item = "item" + i;
            T.insert(keys[i], item);
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("construction time = " + totalTime);
        //T.print();
        System.out.println("tree height = " + T.getTreeHeight());
        
        startTime = System.currentTimeMillis();
        for (int i = 1; i <= n; i++) {
            if (T.search(i) == null) {
                System.out.println("key " + i + " not found!");
            }
        }
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        System.out.println("search time = " + totalTime);
    }
}

