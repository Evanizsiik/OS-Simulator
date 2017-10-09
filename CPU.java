public class CPU {

	public boolean BusyOrNot;
	public int PC; //Your CPU only has one register PC
	public int timeslice = -1;
	public Process process;
	public long time;
	
	public CPU(){
		BusyOrNot=false;
	}
	
	public CPU(int settimeslice){
	 timeslice= settimeslice;
	 BusyOrNot=false;
	}
	
	 public void addProcess(Process process){
	     this.process = process;
	 }

	 public Process getProcess() {
	     return process;
	 }
	
	public Pair<Integer, String> execute(Process P){
	 
		long start = System.currentTimeMillis();
		BusyOrNot=true;
	 
	/* read the CPU burst number, say #, from the position
	PositionOfNextInstructionToExecute of P.
	 Repeat calling Bubble Sort() for # times and then continue.
	 If the code runs out, return (PositionOfNextInstructionToExecute,
	“terminated”), then OS put it back to the terminated queue.

	 If the slice of time (restricted number of calling Bubble sort() for a
	process each time) runs out, return (PositionOfNextInstructionToExecute+1,
	“ready”), then OS put it back to the ready queue.
	 Otherwise, return (PositionOfNextInstructionToExecute+1, “wait”)
	(namely, P has an I/O request and then OS remove it from the ready queue
	and sent it to I/O queue)
	*/
		
		int runs = 0;
		
        if (timeslice != -1) {
            runs = timeslice;
            P.updateBurst(timeslice);
        }else{
            runs = P.getBurst().getValue();
        }
        
        for (int k = 0; k <= runs; k++){
            BubbleSort();
        }

        BusyOrNot = false;

        time += System.currentTimeMillis() - start;
		
		return null;
	}
	
	public void BubbleSort(){
		
	}
	
	public Boolean CPUisBusy(){ return BusyOrNot; }
	
}
