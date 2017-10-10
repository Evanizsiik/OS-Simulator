import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class OS {

	public int AC;
	public CPU cpu;
	public IODevice io;
	public boolean isCPUAvailable;
	public ProcessImage process_Table;
	public Queue<Process> New_Queue;
	public Queue<Process> Ready_Queue;
	public Queue<Process> Wait_Queue;
	public Queue<Process> Terminated_Queue;
	public int processNum = 0;
	 
	//Read the txt input file, for each line, create a process and record its arrival time
	//Put each process in New_Q queue initially then put them in Ready_Q
	//Always check whether the CPU is idle or not; if yes, use your scheduler algorithm to select a process from the Ready_Queue for CPU execution\
	// According to the return value of CPU execute(), put the process into the corresponding queue.
	//Record the time of every operation for computing your latency and response 
	
	public OS(){
		New_Queue = new LinkedList<Process>();
		Ready_Queue = new LinkedList<Process>();
		Wait_Queue = new LinkedList<Process>();
		Terminated_Queue = new LinkedList<Process>();
	}
	
	public void initialize(){
		
		readSequence();
		
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
		
		cpu = new CPU();
		io = new IODevice();
		
		
		
		
	}
	
	public void RR(){
		
		cpu = new CPU(2);
		io = new IODevice();
		
		while(Terminated_Queue.size() != processNum){
            if(!cpu.CPUisBusy()) {
                //get just completed process from the cup
                try {
                    Process p = cpu.getProcess();
                    cpu.addProcess(null);
                    if(p.getBurstValue() > 0){
                        Ready_Queue.add(p);
                    }
                    else {
                        //see if the process being pulled off is terminated
                        try {
                            p.updateBurst();
                            p.setState("Waiting");
                            Wait_Queue.add(p);
                        } catch (IndexOutOfBoundsException i) {
                            p.setState("Terminated");
                            p.getImage().getPcb_data().setEndTime();
                            Terminated_Queue.add(p);
                        }
                    }
                }
                catch (NullPointerException n){
                	System.err.print(n);
                }
            
            	if(!Ready_Queue.isEmpty()) {
            		//putting process on the cpu
            		Process p = Ready_Queue.peek(); //get the first in the queue and process in cpu
            		Ready_Queue.poll();

            		//run process
            		p.setState("Running");
            		cpu.addProcess(p);
            		cpu.run();
            	}
            }
            
            if (!io.BusyOrNot){

                //get just completed process from the io
                try {
                    Process p = io.getProcess();
                    io.addProcess(null);

                    try {
                        p.updateBurst();
                        p.setState("Ready");
                        Ready_Queue.add(p);
                    }
                    catch (IndexOutOfBoundsException i) {
                        p.setState("Terminated");
                        p.getImage().getPcb_data().setEndTime();
                        Terminated_Queue.add(p);
                    }
                }
                catch (NullPointerException n){
                	System.err.print(n);
                }

                if (!Wait_Queue.isEmpty()) {
                    //put process on the io
                    Process p = Wait_Queue.peek();
                    Wait_Queue.poll(); //get first in queue

                    //run
                    p.setState("Running");
                    io.addProcess(p);
                    io.run();
                }
            }
		}
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
				New_Queue.add(new Process(line));
			}
			fileReader.close();
			processNum = New_Queue.size();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
