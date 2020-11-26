package project1;

public class NodeProcess {
	
	Process process;
	NodeProcess next;
	
	public NodeProcess(Process process, NodeProcess next) {
		this.process = process;
		this.next = next;
		
	}

}
