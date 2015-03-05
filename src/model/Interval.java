package model;

import java.util.Date;

public class Interval {

	
	
	public static Date startTime;
	public static Date endTime;
	

	
	public Interval(Date startTime, Date endTime){
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	
	
	public boolean overlap(Interval databaseSjekk){
		System.out.println(this.startTime);
		System.out.println(this.endTime);
		System.out.println(databaseSjekk.startTime);
		System.out.println(databaseSjekk.endTime);
		if(this.endTime.compareTo(databaseSjekk.startTime) < 0){
			System.out.println("Første if");
			return false;
		} else if (this.startTime.compareTo(databaseSjekk.endTime) > 0){
			System.out.println("Andre if");
			return false;
		}System.out.println("true"); 
		return false;
	}
}
