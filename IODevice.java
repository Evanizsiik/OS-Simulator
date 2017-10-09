import java.util.ArrayList;

public class IODevice {

	public boolean BusyOrNot;
	private Process process;
	
	
    public IODevice(){
        this.BusyOrNot = false;
    }

    public void addProcess(Process process){
        this.process = process;
    }

    public Process getProcess() {
        return process;
    }

	
	//Always pick one process from Wait_Queue to execute
	public String execute(Process process){
		BusyOrNot=true;
		//Call Bubble Sort() for IO_burst times and then return “ready”;
		
		for(int i = 0; i < 0; i++){
			BubbleSort();
		}
		
		return "ready";
	}
	
	public void BubbleSort(){
		
	}
	
	public void run(){
		execute(process);
	}
	
}
