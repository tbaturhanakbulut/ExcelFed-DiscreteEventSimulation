


/**
 * Class for Physio
 * @author batur
 *
 */
public class Physiotherapy {
	/**
	 * Time holder for all waiting times of all physios in the queues
	 */
	public static double totalWaitingTime=0.0;
	/**
	 * Time holder for all event times of all physios
	 */
	public static double totalEventTime=0.0;
	/**
	 * Counter for accepted physios
	 */
	public static int queueCounter=0;
	/**
	 * Player in the physio
	 */
	public Player player;
	/**
	 * Arrival time of the player for the event
	 */
	public double arrivalTime;
	/**
	 * Last training time of the player before the physio
	 */
	public double lastTrainingTime;
	/**
	 * Physiotherapist of the physio
	 */
	public Physiotherapist phy;
	/**
	 * Finishing time of the physio
	 */
	public double finishTime;
	/**
	 * Constructor of the physio class
	 * @param player
	 * @param arrivalTime
	 * @param lastTrainingTime
	 */
	public Physiotherapy(Player player, double arrivalTime, double lastTrainingTime) {
		this.player=player;
		this.arrivalTime=arrivalTime;
		this.lastTrainingTime=lastTrainingTime;
	}
	/**
	 * Physio event start
	 * @param phy Physiotherapist of the physio
	 * @param currentTime Current time of the simulation
	 */
	public void inPhysio(Physiotherapist phy, double currentTime) {
		double waitingTime=currentTime-arrivalTime;
		this.phy=phy;
		queueCounter++;
		totalWaitingTime+=waitingTime;
		totalEventTime+=phy.serviceTime;
		player.inPhysio(waitingTime);
		//CALCULATING MOST TIME PASSED PLAYER IN PHYSIO QUEUE

				if(Player.mostTimePhysio<player.totalPhysioTime) {
					Player.mostTimePhysio=player.totalPhysioTime;
					Player.mostTimePhysioID=player.ID;
				}
				else if(Math.abs(Player.mostTimePhysio-player.totalPhysioTime)<0.0000000001) {
					if(player.ID<Player.mostTimePhysioID) {
						Player.mostTimePhysioID=player.ID;
					}
				}

		finishTime=currentTime+phy.serviceTime;
	}
	/**
	 * Physio event finish
	 */
	public void outPhysio() {
		player.outPhysio();
		phy.busy=false;
	}
}
