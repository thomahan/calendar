package main;

import java.util.ArrayList;
import java.util.List;

import model.Group;
import model.User;
import db.AppointmentDBC;
import db.UserDBC;

public class GroupInviter {
	public static void inviteGroupList(int appointmentId, List<Group> groupList) {
		// Expand groups into subgroups
		groupList = getAllSubGroupList(groupList);

		// Invite all groups and subgroups
		for (Group group : groupList) {
			AppointmentDBC.addGroupInvitation(appointmentId, group.getGroupID());
		}

		List<User> invitedUserList = new ArrayList<User>();
		for (Group group : groupList) {
			List<User> userList = UserDBC.getMemberList(group.getGroupID());
			for (User user : userList) {
				if (!invitedUserList.contains(user)) {
					invitedUserList.add(user);
				}
			}
		}

	
		for (User user : invitedUserList) {
			AppointmentDBC.addInvitation(appointmentId, user.getUsername(), "Not replied");
		}
}
	
	public static List<Group> getAllSubGroupList(List<Group> groupList) {
		List<Group> subGroupList = new ArrayList<Group>();
		
		for (Group group : groupList) {
			List<Group> gList = UserDBC.getSubGroupList(group.getGroupID());
			gList = getAllSubGroupList(gList);
			for (Group g : gList) {
				if (!groupList.contains(g)) {
					subGroupList.add(g);
				}
			}
		}

		groupList.addAll(subGroupList);
		return groupList;
	}
}
