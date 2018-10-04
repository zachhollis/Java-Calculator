//**********************************************************************
//
// Zach Hollis 
// Data Structures (COSC 20803)
// Programming Project 3: Prefix/Infix/Postfix 
// October 26, 2017
// Instructor: Dr. Michael Scherger
//
//**********************************************************************
class Queue<T> {
	
	// stack private data members
	private QueueNode<T> front;
	private QueueNode<T> back;
	private int	size;
	
	//******************************************************************
	//
	// QueueNode inner class
	// 
	//******************************************************************
	private static class QueueNode<T> {
		//node constructor
		QueueNode(T value) {
			data = value;
			next = null;
			previous = null;
		}
		
		//node copy constructor
		QueueNode(QueueNode<T> other) {
			data = other.data;
			next = other.next;
			previous = other.previous;
		}
		
		//node private data members
		public T data;
		public QueueNode<T> next;
		public QueueNode<T> previous;
	}
	
	//******************************************************************
	//
	// Queue constructor
	//
	// default constructor
	// 
	//******************************************************************
	public Queue() {
		front = null;
		back = null;
		size = 0;
	}
	
	//******************************************************************
	//
	// Queue constructor
	//
	// copy constructor
	// 
	//******************************************************************
	public Queue(Queue<T> s) {
		if(!s.isEmpty()) {
			size = s.size();
			front = new QueueNode<T>(s.getFront());
			QueueNode<T> cur = front;
			while(cur.previous != null) {
				cur = cur.previous;
			}
			back = cur;
		} else {
			front = null;
			back = null;
			size = 0;
		}
	}
	
	//******************************************************************
	//
	// Queue enqueue method
	//
	// enqueues item and returns true if successful and else, otherwise
	// 
	//******************************************************************
	public boolean enqueue(T item) {
		QueueNode<T> newNode = new QueueNode<T>(item);
		newNode.data = item;
		if((front == null) && (back == null)) {
			front = newNode;
			back = newNode;
		} else {
			newNode.next = back;
			back.previous = newNode;
			back = newNode;
		}
		size++;
		return true;
	}
	
	//******************************************************************
	//
	// Queue dequeue method
	//
	// removes object at the front of the queue and returns it
	// 
	//******************************************************************
	public T dequeue() {
		if((front == null) && (back == null)) {
			return null;
		} else {
			T retVal = front.data;
			front = front.previous;
			size--;
			if(front == null)
				back = null;
			return retVal;
		}
	}
	
	//******************************************************************
	//
	// Queue peek method
	//
	// returns the object at the front of the queue
	// 
	//******************************************************************
	public T peek() {
		if(front != null)
			return front.data;
		else 
			return null;
	}
	
	//******************************************************************
	//
	// Queue is empty method
	//
	// returns true if the queue is empty and false, otherwise
	// 
	//******************************************************************
	public boolean isEmpty() {
		if(size == 0)
			return true;
		return false;
	}
	
	//******************************************************************
	//
	// Stack size method
	//
	// returns the size of the stack
	// 
	//******************************************************************
	public int size() {
		return size;
	}
	
	//******************************************************************
	//
	// Queue print all method
	//
	// prints all items in the queue with indeces
	// 
	//******************************************************************
	public void printAll() {
		Queue<T> temp = new Queue<T>(this);
		int i = 0;
		while(!temp.isEmpty()) {
			System.out.println(i + ":> " + temp.dequeue());
			i++;
		}
	}
	
	//******************************************************************
	//
	// Queue front getter method
	//
	// returns a reference to the front node in the queue
	// 
	//******************************************************************
	public QueueNode<T> getFront() {
		return front;
	}
}