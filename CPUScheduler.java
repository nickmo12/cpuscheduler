package project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CPUScheduler {
	
	/*You will write a program to simulate a few CPU scheduling policies we
	discussed in class. You will be using Java or C or C++ to implement the simulator. The
	simulator selects a task to run from ready queue based on the scheduling algorithm. Since
	the project intends to simulate a CPU scheduler, it does not require any actual process
	creation or execution. When a task is scheduled, the simulator will simply print out what
	task is selected to run at a time. It outputs the way similar to Gantt chart style. 
	*/
	
	public static void main(String[] args) {
		
		Scanner scnr = new Scanner(System.in);
		
		System.out.println("Enter file name: ");
		String fileName = scnr.next();
		
		System.out.println("Enter time quantum: ");
		int timeQuantum = scnr.nextInt();
		
		ReadyQueue rQueueFCFS = new ReadyQueue();
		
		String processList = readFile(fileName);
		
		ArrayList<Process> p = stringToArrayList(processList);
 		
		sortArrayListByArrival(p);
		
		rQueueFCFS.loadProcesses(p);
	
		
		//FCFS
		System.out.println("------------------------- \n");
		System.out.println("First Come First Serve:");
		CPU cpuFCFS = new CPU(rQueueFCFS);
		
		while(rQueueFCFS.isEmpty() != true) {
			cpuFCFS.executeFCFS();
		}
		
		cpuFCFS.printGantt();
		cpuFCFS.printStatsFCFS();
		
		
		//RR
		ReadyQueue rQueueRR = new ReadyQueue();
		ArrayList<Process> p1 = stringToArrayList(processList);
		
		sortArrayListByArrival(p1);
		
		rQueueRR.loadProcesses(p1);
		
		System.out.println("------------------------- \n");
		System.out.println("Round Robin:");
		
		CPU cpuRR = new CPU(rQueueRR, timeQuantum);
		
		while(rQueueRR.isEmpty() != true) {
			cpuRR.executeRR(); 
			
		}
		
		System.out.println();
		System.out.print("Gantt Chart: \n" + cpuRR.gantt);
		cpuRR.printStatsFCFS();
		
		
		//SJF
		ReadyQueue rQueueSJF = new ReadyQueue();
		ArrayList<Process> p2 = stringToArrayList(processList);
		
		sortArrayListSJF(p2);
		
		rQueueSJF.loadProcesses(p2);
		
		
		System.out.println("------------------------- \n");
		System.out.println("Shortest Job First:");
		CPU cpuSJF = new CPU(rQueueSJF);
		
		while(rQueueSJF.isEmpty() != true) {
			cpuSJF.executeFCFS(); 
			
		}
		
		cpuSJF.printGantt();
		cpuSJF.printStatsFCFS();
	
		
		scnr.close();	
	}
	
	
	public static String readFile(String fileName) { //file name provided in command line
		Scanner inStream = null;
		String fileContents = "";
		
		try {

			File file = new File(fileName);
			inStream = new Scanner(file);
			while(inStream.hasNextLine()){
				fileContents += inStream.nextLine() + " ";
				
			}
			
		} catch (FileNotFoundException e) {
			return (fileContents + e.getMessage());
		}
		finally{
			if(inStream != null){
				inStream.close();
			}
		}
		
		fileContents.trim();
		
		return fileContents;
	}
	
	public static ArrayList<Process> stringToArrayList(String s) {
		String [] nums = s.split(" ");
		
		int [] s1 = new int[nums.length];
		
		ArrayList<Process> list = new ArrayList<Process>();
		
		for(int i = 0; i < nums.length; i++){
			s1[i] = Integer.parseInt(nums[i]);
		}
		
		for(int i = 0; i < nums.length; i+=3) {
			Process p = new Process(s1[i], s1[i+1], s1[i+2]);
			list.add(p);
		}
		
		return list;
	}
	
	
	public static ArrayList <Process> sortArrayListByArrival(ArrayList<Process> p) {
		
		Collections.sort(p, new ArrivalTimeComparator());
		
		return p;
	}
	
	public static ArrayList <Process> sortArrayListByBurst(ArrayList<Process> p) {
		
		Collections.sort(p, new BurstTimeComparator());
		
		return p;
	}
	
	public static ArrayList <Process> sortArrayListSJF(ArrayList<Process> p) {
		
		Collections.sort(p, new BurstTimeComparator());
		Collections.sort(p, new ArrivalTimeComparator());
		
		Process first = new Process(p.get(0).pid, p.get(0).arrivalTime, p.get(0).burstTime);
		p.remove(0);
		
		Collections.sort(p, new BurstTimeComparator());
		
		p.add(0, first);
		
		return p;
	}
	
}
	
	