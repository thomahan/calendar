package model;

import java.util.Date;

public class Interval {

	
	
	public Date startTime;
	public Date endTime;
	

	
	public Interval(Date startTime, Date endTime){
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	
	
	public boolean overlap(Interval databaseSjekk){
		System.out.println(this.startTime);
		System.out.println(this.endTime);
		System.out.println(databaseSjekk.startTime);
		System.out.println(databaseSjekk.endTime);
		if(this.endTime.before(databaseSjekk.startTime) && this.startTime.before(databaseSjekk.endTime)){
			System.out.println("Første if");
			return false;
		} else if (this.startTime.after(databaseSjekk.endTime) && this.endTime.after(databaseSjekk.startTime)){
			System.out.println("Andre if");
			return false;
		}System.out.println("true"); 
		return true;
	}
}
