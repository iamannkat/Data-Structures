
public class Stack<Item> {

	private class Node {
		Item item; Node next;

		Node(Item item, Node next) { 
			this.item = item;
 			this.next = next; 
		}
	}

	private Node head;

    Stack() {
			head = null;
    }

    boolean isEmpty() {
			return head == null;
    }

    // insert new item on top of the stack
    void push(Item item) {
			head = new Node(item, head);
    }

    // extract most recent item from the top of the stack
    Item pop() {
			Item item = head.item;
			Node t = head.next;
			head = t;
			return item;
	}

	Item peek(){
		return head.item;
	}
	
}
