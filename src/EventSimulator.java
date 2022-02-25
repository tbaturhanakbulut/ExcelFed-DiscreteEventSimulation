
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Event Simulator Class which controls the simulator
 * @author batur
 *
 */
public class EventSimulator {
	public static final double epsilon=0.000000001;
	/**
	 * Time queue of all times of the events for DES.
	 */
	PriorityQueue<Double> time = new PriorityQueue<>();
	/**
	 * Massage queue of the massages
	 */
	PriorityQueue<Massage> massageQueue= new PriorityQueue<Massage>(new Comparator<Massage>(){	
		public int compare(Massage o1, Massage o2)
		{
			if(o1.player.skillLevel>o2.player.skillLevel) {
				return -3;
			}
			else if(o1.player.skillLevel==o2.player.skillLevel) {
				if(o1.arrivalTime<o2.arrivalTime) {
					return -2;
				}
				else if(o1.arrivalTime==o2.arrivalTime){
					if(o1.player.ID<o2.player.ID) {
    					return -1;
    				}
    				else {
    					return 0;
    				}
				}
				else {
					return 1;
				}
			}
			else {
				return 2;
			}
		}
	});
	/**
	 * Training queue of the trainings
	 */
	PriorityQueue<Training> trainingQueue= new PriorityQueue<Training>(new Comparator<Training>(){
		
		public int compare(Training o1, Training o2)
		{
			if(o1.arrivalTime<o2.arrivalTime) {
				return -2;
			}
			else if(o1.arrivalTime==o2.arrivalTime) {
				if(o1.player.ID<o2.player.ID) {
					return -1;
				}
				else {
					return 0;
				}
			}
			else {
				return 1;
			}
		}
	});
	/**
	 * Physio queue of the physios
	 */
	public PriorityQueue<Physiotherapy> physioQueue= new PriorityQueue<Physiotherapy>(new Comparator<Physiotherapy>(){
		
		public int compare(Physiotherapy o1, Physiotherapy o2)
		{
			if(o1.lastTrainingTime>o2.lastTrainingTime) {
				return -3;
			}
			else if(o1.lastTrainingTime==o2.lastTrainingTime){
				if(o1.arrivalTime<o2.arrivalTime && Math.abs(o1.arrivalTime-o2.arrivalTime) >= epsilon) {
					return -2;
				}
				else if(Math.abs(o1.arrivalTime-o2.arrivalTime) < epsilon) {
					if(o1.player.ID<o2.player.ID) {
						return -1;
					}
					else {
						return 0;
					}
				}
				else {
					return 1;
				}
			}
			else {
				return 2;
			}
			
		}
	});
	/**
	 * Current massages queue
	 */
	PriorityQueue<Massage> currentMassages= new PriorityQueue<Massage>(new Comparator<Massage>(){	
		public int compare(Massage o1, Massage o2)
		{
			if(o1.finishTime<o2.finishTime&& Math.abs(o1.finishTime-o2.finishTime)>=epsilon) {
				return -1;
			}
			else if(Math.abs(o1.finishTime-o2.finishTime)<epsilon) {
				return 0;
			}
			else {
				return 1;
			}
		}
	});
	/**
	 * Current trainings queue
	 */
	PriorityQueue<Training> currentTrainings= new PriorityQueue<Training>(new Comparator<Training>(){
		
		public int compare(Training o1, Training o2)
		{
			if(o1.finishTime<o2.finishTime&& Math.abs(o1.finishTime-o2.finishTime)>=epsilon) {
				return -1;
			}
			else if(Math.abs(o1.finishTime-o2.finishTime)<epsilon) {
				return 0;
			}
			else {
				return 1;
			}
		}
	});
	/**
	 * Current physios queue
	 */
	PriorityQueue<Physiotherapy> currentPhysios= new PriorityQueue<Physiotherapy>(new Comparator<Physiotherapy>(){
		
		public int compare(Physiotherapy o1, Physiotherapy o2)
		{
			if(o1.finishTime<o2.finishTime&& Math.abs(o1.finishTime-o2.finishTime)>=epsilon) {
				return -1;
			}
			else if(Math.abs(o1.finishTime-o2.finishTime)<epsilon) {
				return 0;
			}
			else {
				return 1;
			}
		}
	});
	/**
	 * Trainers list of the trainings
	 */
	public ArrayList<Trainer> trainersList=new ArrayList<>();
	/**
	 * Physiotherapists of the physios
	 */
	public ArrayList<Physiotherapist> physiosList=new ArrayList<>();
	/**
	 * Masseurs of the massages
	 */
	public ArrayList<Masseur> masseursList=new ArrayList<>();
	
	/**
	 * Event queue of all events
	 */
	PriorityQueue<Event> eventQueue= new PriorityQueue<Event>(new Comparator<Event>(){	
		public int compare(Event o1, Event o2)
		{
			if(o1.arrivalTime<o2.arrivalTime) {
				return -1;
			}
			else if(o1.arrivalTime==o2.arrivalTime) {
				return 0;
			}
			else {
				return 1;
			}
		}
	});
	/**
	 * Event adder for the main
	 * @param Event e
	 */
	public void add(Event e) {
		time.add(e.arrivalTime);
		eventQueue.add(e);
	}
	
	//YENI GELEN EN SON DAHIL
	
	/**
	 * Main simulation function which returns a class for the values
	 * @return
	 */
	public ReturningValues runSimulation() {	
		int canceledEvents=0;
		int invalidAttempts=0;
		int maxLengthTrainingQueue=0;
		int maxLengthPhysioQueue=0;
		int maxLengthMassageQueue=0;
		double endingTime=0.0;
		
		while(!time.isEmpty()) {
			Double currentTime=time.poll();
			endingTime=currentTime;
			if(!currentPhysios.isEmpty()) {
				while(Math.abs(currentPhysios.peek().finishTime-currentTime)<epsilon) {
					Physiotherapy p=currentPhysios.poll();
					p.outPhysio();	
					if(currentPhysios.peek()==null) {
						break;
					}
				}	
			}
			if(!currentTrainings.isEmpty()) {		
				while(Math.abs(currentTrainings.peek().finishTime-currentTime)<epsilon) {
					Training t=currentTrainings.poll();
					t.outTraining(this);	
					if(currentTrainings.peek()==null) {
						break;
					}
				}	
			}
			if(!currentMassages.isEmpty())	{			
				while(Math.abs(currentMassages.peek().finishTime-currentTime)<epsilon) {
					Massage t=currentMassages.poll();
					t.outMassage();
					if(currentMassages.peek()==null) {
						break;
					}
				}
			}
			//PHYSIO PART
			for(int i = 0;i<physiosList.size();i++) {
				if(physioQueue.isEmpty()) {
					break;
				}
				
				if(!physiosList.get(i).busy) {
					Physiotherapy p= physioQueue.poll();
					p.inPhysio(physiosList.get(i),currentTime);
					time.add(currentTime+p.phy.serviceTime);
					currentPhysios.add(p);
					physiosList.get(i).busy=true;
				}
			
			}
			
			
					
			//ADDING ELEMENTS TO THE QUEUES
			if(!eventQueue.isEmpty()) {
				if(Math.abs(currentTime-eventQueue.peek().arrivalTime)<epsilon) {
					boolean check=true;
					Event e=eventQueue.poll();
					while(check) {
						if(e.eventType.equals("t")) {
							if(e.player.busy==false){
								//adding training to the queue and checking if the player is busy.
								trainingQueue.add((Training)e);
								e.player.busy=true;
							}
							else {
								canceledEvents++;
							}					
						}
						else { 
							if(e.player.massageAmount<3) {
								if(e.player.busy==false) {
									//adding massage to the queue and checking if the player is busy.
									e.player.massageAmount+=1;
									massageQueue.add((Massage) e);
									e.player.busy=true;
								}
								else {
									canceledEvents++;
								}
							}
							else {
								invalidAttempts++;
							}		
						}
						if(eventQueue.peek()==null) {
							break;
						}
						else if(Math.abs(e.arrivalTime-eventQueue.peek().arrivalTime)<epsilon) {
							e=eventQueue.poll();
							check=true;
						}
						else {
							check=false;
						}
					}				
				}
			}
			
			for(int i=0;i<trainersList.size();i++) {
				if(trainingQueue.isEmpty()) {
					break;
				}
				if(!trainersList.get(i).busy) {
					Training t= trainingQueue.poll();
					t.inTraining(currentTime,trainersList.get(i));
					time.add(currentTime+t.duration);
					currentTrainings.add(t);		
					trainersList.get(i).busy=true;
				}

			}
			for(int i=0;i<masseursList.size();i++) {
				if(massageQueue.isEmpty()) {
					break;
				}
				if(!masseursList.get(i).busy) {
					Massage m= massageQueue.poll();
					m.inMassage(currentTime,masseursList.get(i));
					time.add(currentTime+m.duration);
					currentMassages.add(m);
					masseursList.get(i).busy=true;	
				}
			}	
			
			/*System.out.println("SELAMMMM"+" "+currentTime);
			System.out.println(trainingQueue.size());
				for(Training m: trainingQueue) {
					
					System.out.println(m.arrivalTime);
				}
				for(Training m: currentTrainings) {
					System.out.println("current  "+m.finishTime);
				}
			*/
			
			
			//MAXIMUM LENGTH CALCULATOR	
			if(trainingQueue.size()>maxLengthTrainingQueue) {
				maxLengthTrainingQueue=trainingQueue.size();
			}
			if(massageQueue.size()>maxLengthMassageQueue) {
				maxLengthMassageQueue=massageQueue.size();
			}
			if(physioQueue.size()>maxLengthPhysioQueue) {
				maxLengthPhysioQueue=physioQueue.size();
			}	
		}
		ReturningValues returning= new ReturningValues(maxLengthTrainingQueue, maxLengthPhysioQueue, maxLengthMassageQueue, invalidAttempts, canceledEvents, endingTime);
		return returning;
	}
	
}
