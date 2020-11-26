package project1;

import java.util.Comparator;

public class ArrivalTimeComparator implements Comparator<Process> {

	@Override
	public int compare(Process p1, Process p2) {
		if(p1.arrivalTime == p2.arrivalTime) {
			return 0;
		}
		else if(p1.arrivalTime > p2.arrivalTime) {
			return 1;
		}
		else {
			return -1;
		}
		
	}

}
