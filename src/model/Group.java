package model;

import java.awt.List;
import java.util.ArrayList;

public class Group {
	
	String groupName;
	final int groupID;
	ArrayList<User> members;
	ArrayList<User> listeners;
	
	private Group(String name, int groupID) {
		this.groupName = name;
		this.groupID = groupID;
		ArrayList<User> members = new ArrayList<User>();
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public ArrayList<User> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<User> members) {
		this.members = members;
	}

	public ArrayList<User> getListeners() {
		return listeners;
	}

	public void setListeners(ArrayList<User> listeners) {
		this.listeners = listeners;
	}

	public int getGroupID() {
		return groupID;
	}
	
	private void addUserToGroup(User user) {
		members.add(user);
	}
	
	private void removeUserFromGroup(User user) {
		members.remove(user);
	}
	
	private void changeInGroup() {
		
	}

}
