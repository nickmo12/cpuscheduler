package project1;

import java.util.ArrayList;

public class CPU {
	
	ReadyQueue rQueue;
	ArrayList<Process> list = new ArrayList<Process>();
	int burstAccumulator = 0;
	int timeQuantum;
	String gantt = "0";
	
	
	public CPU(ReadyQueue rQueue) {
		this.rQueue = rQueue;
	}
	
	public CPU(ReadyQueue rQueue, int timeQuantum) {
		this.rQueue = rQueue;
		this.timeQuantum = timeQuantum;
	}
	
	public void executeFCFS() { //same for FCFS and SJF
		Process currProcess = rQueue.front();
		System.out.println("p" + currProcess.pid + " executes for " + currProcess.burstTime + "ms.");
		
		currProcess.setWait(burstAccumulator - currProcess.arrivalTime);
		currProcess.setResponse(burstAccumulator - currProcess.arrivalTime);
		burstAccumulator += currProcess.burstTime;
		currProcess.setTurnaround(burstAccumulator - currProcess.arrivalTime);
		
		list.add(currProcess);
		
		rQueue.dequeue();
	}
	
	public void executeRR() {
		Process currProcess = rQueue.front();
		rQueue.dequeue();
		
		currProcess.incWait(burstAccumulator - currProcess.arrivalTime);
		
		if(currProcess.isSeen == false) {
		currProcess.setResponse(burstAccumulator - currProcess.arrivalTime);
		}
		
		if(currProcess.burstRemaining > timeQuantum) {
			// The process still has more time remaining
			System.out.println("p" + currProcess.pid + " executes for " + timeQuantum + "ms.");
			
			gantt += "|";
			for(int i = 0; i < timeQuantum; i++) {
				gantt += "p" + currProcess.pid;
			}
			gantt += "|";
			
			currProcess.decBurstRemaining(timeQuantum);
			
			burstAccumulator += timeQuantum;
			currProcess.arrivalTime = burstAccumulator;
			rQueue.enqueue(currProcess);
		}
		else {
			// The process is done
			System.out.println("p" + currProcess.pid + " executes for " + currProcess.burstRemaining + "ms.");
			gantt += "|";
			for(int i = 0; i < currProcess.burstRemaining; i++) {
				gantt += "p" + currProcess.pid;
			}
			burstAccumulator += currProcess.burstRemaining;
			currProcess.setTurnaround(burstAccumulator - currProcess.originalArr);
			gantt += "|";
			list.add(currProcess);
		}
		
		gantt += burstAccumulator;
			
		currProcess.isSeen = true;	
	}
	
	
	public void printGantt() { //works for FCFS and SJF
		int burstAcc = 0;
		System.out.println();
		System.out.println("Gantt Chart:");
		for(int i = 0; i < list.size(); i++) {
			System.out.print(burstAcc + "|");
			for(int j = 0; j < list.get(i).burstTime; j++) {
				System.out.print("p" + list.get(i).pid);
			}
			burstAcc += list.get(i).burstTime;
			System.out.print("|");
		}
		System.out.print(burstAcc);
	}
	
	public void printStatsFCFS() {
		//wait time
		System.out.println("\n");
		System.out.println("Wait Time:");
		
		double waitAcc = 0.0;
		
		for(int i = 0; i < list.size(); i++) {
			for(int j = 0; j < list.size(); j++) {
				if(list.get(j).pid == i+1) {
					System.out.println("p" + (i+1) + " = " + list.get(j).waitTime);
					waitAcc += list.get(j).waitTime;
				}
			}
			
		}
		System.out.println("Average wait time = " + waitAcc/list.size() + "\n");
		
		//response time
		double responseAcc = 0.0;
		
		System.out.println("Response Time:");
		
		for(int i = 0; i < list.size(); i++) {
			for(int j = 0; j < list.size(); j++) {
				if(list.get(j).pid == i+1) {
					System.out.println("p" + (i+1) + " = " + list.get(j).responseTime);
					responseAcc += list.get(j).responseTime;
				}	
			}
		}
		System.out.println("Average response time = " + responseAcc/list.size() + "\n");
		
		//turnaround time
		double turnaroundAcc = 0.0;
		
		System.out.println("Turnaround Time:");
		
		for(int i = 0; i < list.size(); i++) {
			for(int j = 0; j < list.size(); j++) {
				if(list.get(j).pid == i+1) {
					System.out.println("p" + (i+1) + " = " + list.get(j).turnaroundTime);
					turnaroundAcc += list.get(j).turnaroundTime;
				}	
			}
		}
		System.out.println("Average turnaround time = " + turnaroundAcc/list.size() + "\n");
	}
	

}
