package model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Group {
	
	private String groupName;
	final int groupID;
	ArrayList<User> members = new ArrayList<User>();
	private ArrayList<User> listeners = new ArrayList<User>();
	private static final AtomicInteger count = new AtomicInteger(0);
	
	public Group(String name) {
		this.groupName = name;
		this.groupID = count.incrementAndGet();
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
		return groupID;
	}
	
	public void addUserToGroup(User user) {
		members.add(user);
		user.addToGroups(this);
		listeners.add(user);
	}
	
	public void removeUserFromGroup(User user) {
		members.remove(user);
	}
	
	public void changeInGroup() {
		for(User listener : listeners){
			listener.groupHasChanged(this);
		}
		
	}

}
