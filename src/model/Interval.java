package model;

import java.util.Date;

public class Interval {

	
	
	public static Date startTime;
	public static Date endTime;
	

	
	public Interval(Date startTime, Date endTime){
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	
	
	public boolean overlap(Interval onsketIntervall, Interval databaseSjekk){
		if((onsketIntervall.startTime.compareTo(databaseSjekk.endTime))>0 && (onsketIntervall.endTime.compareTo(databaseSjekk.startTime)) > 0){
			System.out.println("Første if");
			return false;
		} else if ((onsketIntervall.startTime.compareTo(databaseSjekk.endTime))<0 && (onsketIntervall.endTime.compareTo(databaseSjekk.startTime)) < 0){
			System.out.println("Andre if");
			return false;
		} return false;
	}
}
