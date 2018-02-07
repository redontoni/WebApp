package company.management.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the privileges database table.
 * 
 */
@Entity
@Table(name = "privileges")
@NamedQuery(name = "Privilege.findAll", query = "SELECT p FROM Privilege p")
public class Privilege implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int privilegesID;

	private String privilege;

	// // bi-directional many-to-one association to Role
	// @ManyToOne
	// @JoinColumn(name = "roleID")
	// bi-directional many-to-one association to Role
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleID", nullable = false)
	private Role role;

	private boolean enabled;

	public Privilege() {
	}

	public int getPrivilegesID() {
		return this.privilegesID;
	}

	public void setPrivilegesID(int privilegesID) {
		this.privilegesID = privilegesID;
	}

	public String getPrivilege() {
		return this.privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}