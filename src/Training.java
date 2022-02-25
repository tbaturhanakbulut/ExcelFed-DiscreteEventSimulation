

/**
 * Training class for the trainings
 * @author batur
 *
 */
public class Training extends Event{
	public static double totalWaitingTime=0.0;
	public static double totalEventTime=0.0;
	public static double queueCounter=0;
	public double finishTime;
	public Trainer trainer;
	public Training(String eventType, Player player, double arrivalTime, double duration) {
		super(eventType, player, arrivalTime, duration);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Training start
	 * @param currentTime Current time of the simulator
	 * @param trainer Trainer of the training
	 */
	public void inTraining(double currentTime,Trainer trainer) {
		totalWaitingTime+=(currentTime-arrivalTime);
		queueCounter++;
		finishTime=currentTime+duration;
		totalEventTime+=duration;
		this.trainer=trainer;
		player.inTraining();
		
		
	}
	/**
	 * Training finish
	 * @param simulator Simulator
	 */
	public void outTraining(EventSimulator simulator) {
		trainer.busy=false;
		player.outTraining();
		simulator.physioQueue.add(new Physiotherapy(player, finishTime,duration));
	}

	
	
	
}
