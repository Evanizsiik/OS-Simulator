public void finalize(){
		
		double[] latValues = new double[Terminated_Queue.size()];
		double[] resValues = new double[Terminated_Queue.size()];
		String[] fin = new String[Terminated_Queue.size()+1];
		fin[0] = "ID\tPriority\tLatency (ms)\tResponse (ms)\n";
		
		double latency = 0;
		double response = 0;
		
		for(int i = 1; i <= Terminated_Queue.size(); i++){
			Process p = Terminated_Queue.poll();
			
			latency = latency + (p.getImage().getPcb_data().getEndTime()-p.getImage().getPcb_data().getStartTime());
			latValues[i-1] = (p.getImage().getPcb_data().getEndTime()-p.getImage().getPcb_data().getStartTime());
			
			response = response + (p.getImage().getPcb_data().getEndTimeIO()-p.getImage().getPcb_data().getStartTime());
			resValues[i-1] = p.getImage().getPcb_data().getEndTimeIO()-p.getImage().getPcb_data().getStartTime();
			
			fin[i] = p.getId()+"\t"+p.getPriority()+"\t"+(p.getImage().getPcb_data().getEndTime()-p.getImage().getPcb_data().getStartTime())+"\t"+(p.getImage().getPcb_data().getEndTimeIO()-p.getImage().getPcb_data().getStartTime())+"\n";
		}
		
		double latencyMod = 0;
		double responseMod = 0;
		
		for(int i = 0; i < Terminated_Queue.size(); i++){
			latValues[i] = (latValues[i]-latency)*(latValues[i]-latency);
			latencyMod = latencyMod + latValues[i];
			
			resValues[i] = (resValues[i]-response)*(resValues[i]-response);
			responseMod = responseMod + resValues[i];
		}
		
		for(int i = 0; i <= Terminated_Queue.size(); i++){
			System.out.print(fin[i]);
		}
		
		System.out.println("\nStandard Deviation (Latency):  " + Math.sqrt(latencyMod));
		System.out.println("Standard Deviation (Response): " + Math.sqrt(responseMod));
		
	}
}
