package model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Group {
	final int groupId;
	private String groupName;
	ArrayList<User> members = new ArrayList<User>();
	private ArrayList<User> listeners = new ArrayList<User>();
	private static final AtomicInteger count = new AtomicInteger(0);
	
	public Group(int groupId, String name) {
		this.groupId = groupId;
		this.groupName = name;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) { //Legg til endring i db
		this.groupName = groupName;
	}

	public ArrayList<User> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<User> members) { //Legg til endring i db
		this.members = members;
		listeners.addAll(members);
	}

	public ArrayList<User> getListeners() {
		return listeners;
	}

	public void setListeners(ArrayList<User> listeners) {
		this.listeners = listeners;
	}
	
	public void addListener(User user){
		listeners.add(user);
	}
	
	public void removeListener(User user){
		listeners.remove(user);
	}
	
	public int getGroupID() {
		return groupId;
	}
	
	public void addUserToGroup(User user) { //Legg til endring i db
		members.add(user);
		user.addToGroups(this);
		listeners.add(user);
	}
	
	public void removeUserFromGroup(User user) { //Legg til endring i db
		members.remove(user);
	}
	
	public void changeInGroup() {
		for(User listener : listeners){
			listener.groupHasChanged(this);
		}
	}
	
	@Override
	public String toString() {
		return groupName;
		
	}
}
