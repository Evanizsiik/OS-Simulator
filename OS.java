import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class OS {

	public int AC;
	public CPU cpu;
	public IODevice io;
	public boolean isCPUAvailable;
	public ProcessImage process_Table;
	public ArrayList<Process> New_Queue;
	public ArrayList<Process> Ready_Queue;
	public ArrayList<Process> Wait_Queue;
	public ArrayList<Process> Terminated_Queue;
	public ArrayList<String> sequences;
	 
	//Read the txt input file, for each line, create a process and record its arrival time
	//Put each process in New_Q queue initially then put them in Ready_Q
	//Always check whether the CPU is idle or not; if yes, use your scheduler algorithm to select a process from the Ready_Queue for CPU execution\
	// According to the return value of CPU execute(), put the process into the corresponding queue.
	//Record the time of every operation for computing your latency and response 
	
	public void initialize(){
		
		switch(AC){
		
		case 1:
			FCFS();break;
		case 2:
			RR();break;
		case 3:
			SP();break;
		case 4:
			break;
		}
		
	}
	
	public void FCFS(){
		
	}
	
	public void RR(){
		
	}
	
	public void SP(){
		
	}
	
	public void readSequence(){
		try {
			File file = new File("test.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				sequences.add(line);
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
