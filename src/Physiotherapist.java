/**
 * Physiotherapist class for the physiotherapists
 * @author batur
 *
 */
public class Physiotherapist {
	public int ID;
	public double serviceTime;
	public boolean busy=false;
	public Physiotherapist(int ID,double serviceTime) {
		this.ID=ID;
		this.serviceTime=serviceTime;
	}
}
