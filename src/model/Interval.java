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
		if((onsketIntervall.startTime.compareTo(databaseSjekk.endTime))>0 && (onsketIntervall.endTime.compareTo(databaseSjekk.startTime)) < 0){
			return false;
		} else if ((overlap(databaseSjekk, onsketIntervall))){
			return false;
		} return true;
	}
}
