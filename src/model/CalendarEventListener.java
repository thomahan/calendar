package model;

import java.sql.Date;
import java.util.ArrayList;

public interface CalendarEventListener {
	
	void eventHasChanged(CalendarEvent event);

}
