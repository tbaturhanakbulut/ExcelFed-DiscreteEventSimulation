



public class Massage extends Event {
	/**
	 * Time holder for all waiting times of all massages in the queues
	 */
	public static double totalWaitingTime=0.0;
	/**
	 * Time holder for all event times of all massages
	 */
	public static double totalEventTime=0.0;
	/**
	 * Counter for accepted massages
	 */
	public static int queueCounter=0;
	/**
	 * Finish time of the event
	 */
	public double finishTime;
	/**
	 * Masseur of the massage
	 */
	private Masseur masseur;
	/**
	 * Constructor of the event class
	 * @param eventType Event type
	 * @param player Player who does the event
	 * @param arrivalTime Arrival time of the player for the event
	 * @param duration Duration of the event after the start
	 */
	public Massage(String eventType, Player player, double arrivalTime, double duration) {
		super(eventType, player, arrivalTime, duration);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Massage start function
	 * @param currentTime Current time in the simulation
	 * @param masseur Masseur of the massage
	 */
	public void inMassage(double currentTime,Masseur masseur) {
		double waitingTime=currentTime-arrivalTime;
		totalWaitingTime+=waitingTime;
		queueCounter++;
		finishTime=currentTime+duration;
		totalEventTime+=duration;
		this.masseur=masseur;
		player.inMassage(waitingTime);
		
		
	}
	/**
	 * Massage finish function
	 */
	public void outMassage() {
		masseur.busy=false;
		player.outMassage();
	}
	
}