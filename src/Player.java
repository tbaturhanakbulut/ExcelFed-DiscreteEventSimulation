
/**
 * Player of the simulations
 * @author batur
 *
 */
public class Player {
	public int ID;
	public double skillLevel;
	public boolean busy=false;
	public boolean inEvent=false;
	public static double mostTimePhysio=0.0;
	public static int mostTimePhysioID=0;
	public double totalPhysioTime=0.0;
	public double totalMassageTime=0.0;
	public int massageAmount=0;
	public Player(int ID, double skillLevel){
		this.ID=ID;
		this.skillLevel=skillLevel;
	}
	
	
	
	public void inTraining() {
		inEvent=true;
	}
	public void outTraining() {
		inEvent=false;
	}
	public void inPhysio(double waitingTime) {
		totalPhysioTime+=waitingTime;
		inEvent=true;
	}
	public void outPhysio() {
		inEvent=false;

		busy=false;
	}
	public void inMassage(double waitingTime) {
		
		inEvent=true;
		totalMassageTime+=waitingTime;
		//massageAmount+=1;
	}
	public void outMassage() {
		inEvent=false;

		busy=false;
	}
}
