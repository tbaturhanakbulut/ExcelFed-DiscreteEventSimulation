import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

import java.util.Scanner;

/**
 * Main class of the event
 * @author batur
 *
 */
public class project2main {
	
	
	
	
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		//Declarations for the file operations
			Training.totalWaitingTime=0;
			Training.queueCounter=0;
			Physiotherapy.totalWaitingTime=0;
			Physiotherapy.queueCounter=0;
			Massage.totalWaitingTime=0;
			Massage.queueCounter=0;
			Training.totalEventTime=0;
			Training.queueCounter=0;
		    Physiotherapy.totalEventTime=0;
		    Physiotherapy.queueCounter=0;
			Massage.totalEventTime=0;
			Massage.queueCounter=0;
			Player.mostTimePhysioID=0;
			Player.mostTimePhysio=0;	
			File myOutputFile = new File(args[1]);
			File myInputFile = new File(args[0]);
			
			PrintStream out = new PrintStream(myOutputFile);	
			Scanner sc=new Scanner(myInputFile);
			ArrayList<Player> playersList=new ArrayList<>();
			
			EventSimulator simulator= new EventSimulator();
			
			//READING THE N INPUTS
			int N=sc.nextInt();
			sc.nextLine();
			while(N>0) {
				String[] splitted=sc.nextLine().split(" ");
				Player player= new Player(Integer.parseInt(splitted[0]),Double.parseDouble(splitted[1]));
				playersList.add(player);
				N--;
			}
			
			//READING THE A INPUTS
			int A=sc.nextInt();
			sc.nextLine();
			while(A>0) {
				String[] splitted=sc.nextLine().split(" ");
				
				if(splitted[0].equals("t")) {
					Training training = new Training("t",playersList.get(Integer.parseInt(splitted[1])),Double.parseDouble(splitted[2]),Double.parseDouble(splitted[3]));
					simulator.add(training);
				}
				else if(splitted[0].equals("m")) {
					Massage massage = new Massage("m",playersList.get(Integer.parseInt(splitted[1])),Double.parseDouble(splitted[2]),Double.parseDouble(splitted[3]));
					simulator.add(massage);
				}
				A--;
			}
			
			int physioNumber=sc.nextInt();
			for(int i =0;i<physioNumber;i++) {
				double serviceTime=sc.nextDouble();
				simulator.physiosList.add(new Physiotherapist(i,serviceTime));
			}
			int trainerNumber=sc.nextInt();
			int masseurNumber=sc.nextInt();
			for(int i =0;i<trainerNumber;i++) {
				simulator.trainersList.add(new Trainer(i));
			}
			for(int i =0;i<masseurNumber;i++) {
				simulator.masseursList.add(new Masseur(i));
			}
			//RUN THE SIMUULATON
			ReturningValues returned=simulator.runSimulation();
			
			
			
			double leastMassageTime=1000000.0;
			int leastMassageTimeID=-1;
			for(int i=0;i<playersList.size();i++) {
				Player player= playersList.get(i);
				if(player.massageAmount==3) {
					if(player.totalMassageTime<leastMassageTime) {
						leastMassageTime=player.totalMassageTime;
						leastMassageTimeID=player.ID;
					}
					else if(Math.abs(player.totalMassageTime-leastMassageTime)<0.0000000001) {
						if(player.ID<leastMassageTimeID) {
							leastMassageTimeID=player.ID;
						}
					}
				}
			}
			if(leastMassageTime==1000000.0) {
				leastMassageTime=-1;
			}
			out.println(returned.a);
			out.println(returned.b);
			out.println(returned.c);
			
					
			out.println(Training.queueCounter==0?String.format("%.3f", 0.000):String.format("%.3f",Training.totalWaitingTime/Training.queueCounter));
			out.println(Physiotherapy.queueCounter==0?String.format("%.3f", 0.000):String.format("%.3f",Math.abs( Physiotherapy.totalWaitingTime)/Physiotherapy.queueCounter));
			out.println(Massage.queueCounter==0?String.format("%.3f", 0.000):String.format("%.3f",Massage.totalWaitingTime/Massage.queueCounter));
			out.println(Training.queueCounter==0?String.format("%.3f", 0.000):String.format("%.3f",Training.totalEventTime/Training.queueCounter));
			out.println(Physiotherapy.queueCounter==0?String.format("%.3f", 0.000):String.format("%.3f",Math.abs( Physiotherapy.totalEventTime)/Physiotherapy.queueCounter));
			out.println(Massage.queueCounter==0?String.format("%.3f", 0.000):String.format("%.3f",Massage.totalEventTime/Massage.queueCounter));
			out.println(Training.queueCounter==0?String.format("%.3f", 0.000):String.format("%.3f",(Training.totalWaitingTime+Training.totalEventTime+Physiotherapy.totalWaitingTime+Physiotherapy.totalEventTime)/Training.queueCounter));		
			
				
			/*		
			out.println(String.format("%.3f",Training.totalWaitingTime/Training.queueCounter));
			out.println(String.format("%.3f",Physiotherapy.totalWaitingTime/Physiotherapy.queueCounter));
			out.println(String.format("%.3f",Massage.totalWaitingTime/Massage.queueCounter));
			out.println(String.format("%.3f",Training.totalEventTime/Training.queueCounter));
			out.println(String.format("%.3f",Physiotherapy.totalEventTime/Physiotherapy.queueCounter));
			out.println(String.format("%.3f",Massage.totalEventTime/Massage.queueCounter));
			out.println(String.format("%.3f",(Training.totalWaitingTime+Training.totalEventTime+Physiotherapy.totalWaitingTime+Physiotherapy.totalEventTime)/Training.queueCounter));
			*/
			
			out.println(Player.mostTimePhysioID + " "+ String.format("%.3f",Player.mostTimePhysio));
			
			if(leastMassageTime==-1.000) {
				int intLeastMassageTime=(int)leastMassageTime;
				out.println(leastMassageTimeID+" " + intLeastMassageTime);
			}
			else {
				out.println(leastMassageTimeID+" " + String.format("%.3f",leastMassageTime));
			}
			
			out.println(returned.d);
			out.println(returned.e);
			out.println(String.format("%.3f",returned.f));
			

			
			
			
			
			
			
			
			sc.close();
			out.close();
			
			
		
		
		
		
	}

}
