
package company.management.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the department_sector database table.
 * 
 */
@Entity
@Table(name="department_sector")
@NamedQuery(name="DepartmentSector.findAll", query="SELECT d FROM DepartmentSector d")
public class DepartmentSector implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Sector
	@ManyToOne(cascade = { CascadeType.ALL})
	@JoinColumn(name="sectorId")
	private Sector sector;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="departmentId")
	private Department department;
	
	@Column(name="enabled",columnDefinition="BIT")
	private boolean enabled=true;

	public DepartmentSector() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Sector getSector() {
		return this.sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}