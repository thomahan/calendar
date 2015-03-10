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
		if(this.endTime.before(databaseSjekk.startTime) && this.startTime.before(databaseSjekk.endTime)){
			return false;
		} else if (this.startTime.after(databaseSjekk.endTime) && this.endTime.after(databaseSjekk.startTime)){
			return false;
		}
		return true;
	}
}
