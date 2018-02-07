package company.management.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the company_departments database table.
 * 
 */
@Entity
@Table(name="company_departments")
@NamedQuery(name="CompanyDepartment.findAll", query="SELECT c FROM CompanyDepartment c")
public class CompanyDepartment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Company
	@ManyToOne
	@JoinColumn(name="companyId")
	private Company company;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="departmentId")
	private Department department;

	public CompanyDepartment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}