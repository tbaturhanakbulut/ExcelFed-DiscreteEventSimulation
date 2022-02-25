

/**
 * Event class of all events.
 * @author batur
 *
 */
public class Event {
	/**
	 * Event type
	 */
	public String eventType;
	/**
	 * Player who does the event
	 */
	public Player player;
	/**
	 * Arrival time of the player for the event
	 */
	public double arrivalTime;
	/**
	 * Duration of the event after the start
	 */
	public double duration;
	
	/**
	 * Constructor of the event class
	 * @param eventType Event type
	 * @param player Player who does the event
	 * @param arrivalTime Arrival time of the player for the event
	 * @param duration Duration of the event after the start
	 */
	public Event(String eventType,Player player, double arrivalTime, double duration) {
		this.eventType=eventType;
		this.player=player;
		this.arrivalTime=arrivalTime;
		this.duration=duration;
	}
}
