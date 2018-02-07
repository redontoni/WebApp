package company.management.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int roleID;

	private String role;

	//bi-directional many-to-one association to Privilege
	@OneToMany(mappedBy="role")
	private List<Privilege> privileges;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="roles")
	private List<User> users;

	private boolean enabled;

	public Role() {
	}

	public int getRoleID() {
		return this.roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Privilege> getPrivileges() {
		return this.privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	public Privilege addPrivilege(Privilege privilege) {
		getPrivileges().add(privilege);
		privilege.setRole(this);

		return privilege;
	}

	public Privilege removePrivilege(Privilege privilege) {
		getPrivileges().remove(privilege);
		privilege.setRole(null);

		return privilege;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/*public User addUser(User user) {
		getUsers().add(user);
		user.setRole(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setRole(null);

		return user;
	}
*/
}