package model;

public class Group {
	private final int groupId;
	private String groupName;
	
	public Group(int groupId, String name) {
		this.groupId = groupId;
		this.groupName = name;
	}
	
	public int getGroupId() {
		return groupId;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Group group = (Group) obj;
		return this.getGroupId() == group.getGroupId();
	}

	@Override
	public String toString() {
		return groupName;
		
	}
}
