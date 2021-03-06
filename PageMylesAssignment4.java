import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/*
Myles Page
Cs 1450 002
Monday - Wednesday
Due 02-24-2021
Assignment 4
Compare to 
*/


public class PageMylesAssignment4 {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//reads in the files
		File read = new File("Venue.txt");
		Scanner venueRead = new Scanner(read);
		
		File athletes = new File("athletes.txt");
		Scanner athleteRead = new Scanner(athletes);
		
		
		//reads in the venue and makes the object 
		String venueName = venueRead.nextLine();
		int readyRoom = venueRead.nextInt();
		int pool = venueRead.nextInt();
		AquaticCenter venue = new AquaticCenter(venueName, readyRoom, pool);
		Athlete athlete = null;
		
		//make the array for the print function
		ArrayList<Athlete> athleteArray = new ArrayList<Athlete>();
		
		
		//reads the athlete function
		while(athleteRead.hasNext()) {
			int x = 0;
			int seat = athleteRead.nextInt();
			String team = athleteRead.next();
			double time = athleteRead.nextDouble();
			String event = athleteRead.next();
			String name = athleteRead.nextLine();
			athlete = new Athlete(team, time, event, name);
			venue.addAthleteToReadyRoom(seat, athlete);
			athleteArray.add(new Athlete(team, time, event, name));
			x++;
		}
		
		//prints the readyroom and the pool
		venue.displayReadyRoom();
		venue.displayPool();
	
		//print the sorted array 
		printReadyAthletes(venue, athleteArray);
		
		
	}
	
	//print array
	public static void printReadyAthletes(AquaticCenter venue, ArrayList<Athlete> athleteArray){
		
		//sort
		Collections.sort(athleteArray);
		
		//print
		System.out.println("\n\n\t\t    ATHLETES IN READY ROOM");
		System.out.println("************************************************************************");
		System.out.println(" Name \t\t   Time\t\tEvent		Team");
		System.out.println("------------------------------------------------------------------------");
		for(int i = 0; i < athleteArray.size(); i++) {
			System.out.println(athleteArray.get(i).toString());
		}
	}
}




class AquaticCenter{
	
	//private variables
	private String name;
	private int numberReadyRoomSeats;
	private String[] readyRoom;
	private int numberLanes;
	private String[] pool;
	
	//constructor
	public AquaticCenter (String name, int numberReadyRoomSeats, int numberLanes) {
		this.name = name;
		this.numberReadyRoomSeats = numberReadyRoomSeats;
		this.numberLanes = numberLanes;
		readyRoom = new String[numberReadyRoomSeats];
		pool = new String[numberLanes];
	}
	
	//getters
	public String getName() {
		return name;
	}
	
	public int getNumberReadyRoomSeats() {
		return numberReadyRoomSeats;
	}
	
	public int getNumberLanes() {
		return numberLanes;
	}
	
	//ad athlete to ready room
	public void addAthleteToReadyRoom (int seat, Athlete athlete) {
		readyRoom[seat] = athlete.getName();
 	}
	
	public void addAthleteToPool (int lane, Athlete athlete) {
		
	}
	
	//display ready room
	public void displayReadyRoom() {
		System.out.println("Loading " + getName() + "venue ready room with athletes...");
		System.out.println("\nReady Room");
		System.out.println("_______________________");
		System.out.println("Seat\t Athlete");
		System.out.println("_______________________");
		for(int i = 0; i < readyRoom.length; i++) {
			if(readyRoom[i] != null) {
				System.out.println(i + "\t" +readyRoom[i]);
			}
			else {
				System.out.println(i + "\t ----------");
			}
		}
	}
	
	//diplay pool
	public void displayPool() {
		System.out.println("\n\nPool");
		System.out.println("_______________________");
		System.out.println("Seat\t Athlete");
		System.out.println("_______________________");
		for(int i = 0; i < pool.length; i++) {
			if(pool[i] != null) {
				System.out.println(i + "\t" +pool[i]);
			}
			else {
				System.out.println(i + "\t ----------");
			}
		}
	}
}


//athlete class
class Athlete implements Comparable<Athlete>{
	
	//private variables
	private String team;
	private double time;
	private String event;
	private String name;
	
	//construtors
	public Athlete (String team, double time, String event, String name) {
		this.team = team;
		this.time = time;
		this.event = event;
		this.name = name;
	}
	
	//getters
	public String getTeam() {
		return team;
	}
	
	public double getTime() {
		return time;
	}
	
	public String getEvent() {
		return event;
	}
	
	public String getName() {
		return name;
	}
	
	//return the variables in formated way 
	public String toString() {
		return String.format("%s\t   %.2f\t%-10s\t%-10s",   name,  time,  event,  team);
	}
	
	//campare a to b based on time
	public int compareTo(Athlete otherAthlete) {
		return (int) (this.time - otherAthlete.time);
	}
}
