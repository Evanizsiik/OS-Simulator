import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

public class lasdjflkasdf {
	
	public Queue<String> queue = new LinkedList<String>();
	public Timer timer = new Timer();
	
	public lasdjflkasdf(){
		
	}
		
	/* First Come First Serve Algorithm */
	public void addProcess(String process){
		queue.add(process);
	}
	
	public void processComplete(){
		queue.poll();
	}
	//////////////////////////////////////
	
	
	/* round Robin Algorithm */
	public void RR(){
		
		while(queue != null){
			
			String temp = queue.peek();
			
			timer.scheduleAtFixedRate(new TimerTask() {
				  @Override
				  public void run() {
					  // run task
				  }
				}, 2*60*1000, 2*60*1000);
			
			// if task complete: remove task from queue
			// if task not complete: add task to queue and remove from head
		}
		
	}
	///////////////////////////
	
	
	/* Static Priority Algorithm */
	public void SP(){
		
		while(queue != null){
			
			String temp = queue.peek();
			
			// if priority < 3 run until complete
				//code for running task
			// else run this:
				timer.scheduleAtFixedRate(new TimerTask() {
					@Override
					public void run() {
					  // run task
					}
				  }, 2*60*1000, 2*60*1000);
			
				// if task complete: remove task from queue
				// if task not complete: add task to queue and remove from head
		}
		
	}
	///////////////////////////////
}
