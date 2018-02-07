package company.management.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the departments database table.
 * 
 */
@Entity
@Table(name="departments")
@NamedQuery(name="Department.findAll", query="SELECT d FROM Department d")
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int departmentId;

	private String departmentName;

	//bi-directional many-to-one association to CompanyDepartment
	@OneToMany(mappedBy="department")
	private List<CompanyDepartment> companyDepartments;

	//bi-directional many-to-one association to DepartmentSector
	@OneToMany(mappedBy="department")
	private List<DepartmentSector> departmentSectors;
	
	@Column(name="enabled",columnDefinition="BIT")
	private boolean enabled=true;

	public Department() {
	}

	public int getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<CompanyDepartment> getCompanyDepartments() {
		return this.companyDepartments;
	}

	public void setCompanyDepartments(List<CompanyDepartment> companyDepartments) {
		this.companyDepartments = companyDepartments;
	}

	public CompanyDepartment addCompanyDepartment(CompanyDepartment companyDepartment) {
		getCompanyDepartments().add(companyDepartment);
		companyDepartment.setDepartment(this);

		return companyDepartment;
	}

	public CompanyDepartment removeCompanyDepartment(CompanyDepartment companyDepartment) {
		getCompanyDepartments().remove(companyDepartment);
		companyDepartment.setDepartment(null);

		return companyDepartment;
	}

	public List<DepartmentSector> getDepartmentSectors() {
		return this.departmentSectors;
	}

	public void setDepartmentSectors(List<DepartmentSector> departmentSectors) {
		this.departmentSectors = departmentSectors;
	}

	public DepartmentSector addDepartmentSector(DepartmentSector departmentSector) {
		getDepartmentSectors().add(departmentSector);
		departmentSector.setDepartment(this);

		return departmentSector;
	}

	public DepartmentSector removeDepartmentSector(DepartmentSector departmentSector) {
		getDepartmentSectors().remove(departmentSector);
		departmentSector.setDepartment(null);

		return departmentSector;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}