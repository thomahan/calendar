package main;

import java.util.List;

import model.Group;
import db.AppointmentDBC;

public class GroupInviter {
	public static void inviteGroupList(int appointmentId, List<Group> groupList) {
		for (Group g : groupList) {
			System.out.println(g);
		}
		//groupList.addAll(AppointmentDBC.getSubGroupList(groupList));
		
	
		
	}

}
