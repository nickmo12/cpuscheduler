package project1;

public class Process implements Comparable<Process> {
	int pid;
	int arrivalTime;
	int burstTime;
	int waitTime;
	int responseTime;
	int turnaroundTime;
	int burstRemaining;
	boolean isSeen = false;
	final int originalArr;
	
	public Process(int pid, int arrivalTime, int burstTime) {
		this.pid = pid;
		this.arrivalTime = arrivalTime;
		this.originalArr = arrivalTime;
		this.burstTime = burstTime;
		this.burstRemaining = burstTime;
	}
	
	public Process(int pid, int arrivalTime, int burstTime, int waitTime, int responseTime, int turnaroundTime, boolean isSeen) {
		this.pid = pid;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		this.burstRemaining = burstTime;
		this.waitTime = waitTime;
		this.responseTime = responseTime;
		this.turnaroundTime = turnaroundTime;
		this.isSeen = isSeen;
		this.originalArr = arrivalTime;
	}
	
	public void setWait(int waitTime) {
		this.waitTime = waitTime;
	}
	
	public void setResponse(int responseTime) {
		this.responseTime = responseTime;
	}
	
	public void setTurnaround(int turnaroundTime) {
		this.turnaroundTime = turnaroundTime;
	}
	
	public void setBurstRemaining(int burstRemaining) {
		this.burstRemaining = burstRemaining;
	}
	
	public void decBurstRemaining(int time) {
		burstRemaining -= time;
	}
	
	public void incWait(int time) {
		waitTime += time;
	}
	
	@Override
	public int compareTo(Process p) {
		if(this.arrivalTime == p.arrivalTime) {
			return 0;
		}
		else if(this.arrivalTime > p.arrivalTime) {
			return 1;
		}
		else {
			return -1;
		}
	}
	
	@Override
	public String toString() {
		return ("Process  : PID = " + pid + " | Arrival = " + arrivalTime + " | Burst = " + burstTime);
	}
}
