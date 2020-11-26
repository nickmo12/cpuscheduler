package project1;

import java.util.ArrayList;

public class ReadyQueue {
	private NodeProcess front = null;
	private NodeProcess back = null;
	
	
	public void enqueue(Process p) {
		if(isEmpty()) {
			back = new NodeProcess(p , null);
			front = back;
		}
		else {
			back.next = new NodeProcess(p, null);
			back = back.next;
		}
	}
	
	public void dequeue() {
		if(!isEmpty()) {
			front = front.next;
		}
	}
	
	public Process front() {
		return front.process;
	}
	
	public boolean isEmpty() {
		return (front == null);
	}
	
	public void loadProcesses(ArrayList<Process> p) {
		
		//pass each process into Ready Queue
		for(int i = 0; i < p.size(); i++) {
			enqueue(p.get(i));
		}
	}
	
	

}
