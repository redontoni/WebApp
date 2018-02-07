package comapany.management.bean;

import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import company.management.entity.Department;
import company.management.entity.DepartmentSector;
import company.management.entity.Sector;
import company.management.manager.DepartmentManager;

@SessionScoped
@ManagedBean(name = "department_ui")
public class DepartmentUI {

	private Department newDepartment;
	private Department departmentToDelete;
	private Department updateDepartment;
	private List<Department> listOfDepartments;
	private DepartmentManager departmentManager = new DepartmentManager();
	private List<Sector> listofDepartmentSectors;
	private boolean checkSectorPanel;
	String departmentID;

	public DepartmentUI() {
		listOfDepartments = departmentManager.getDepartment();
		checkSectorPanel = false;
	}

	// redirect to department view
	public String cancel() {
		listOfDepartments = departmentManager.getDepartment();
		return "/private/mains/superuser.xhtml";
	}

	// redirect to create department view
	public String createDepartment() {
		newDepartment = new Department();

		return "/private/management/departmentCreate.xhtm";
	}

	// creating role method and redirect to roleList view
	public String addNewDepartment() {
		newDepartment.setEnabled(true);
		departmentManager.createDepartment(newDepartment);
		listOfDepartments = departmentManager.getDepartment();
		return "/private/mains/superuser.xhtml";
	}

	// redirect to update view
	public String editDepartment() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String departmentID = getParam(fc);
		updateDepartment = departmentManager.getDepartmentById(departmentID);
		return "/private/management/departmentUpdate.xhtml";
	}

	// update department method and redirect to roleList
	public String saveEditDepartment() {
		departmentManager.updateDepartment(updateDepartment);
		listOfDepartments = departmentManager.getDepartment();
		return "/private/mains/superuser.xhtml";
	}

	// delete method
	public void deleteDepartment() {
		FacesContext fc = FacesContext.getCurrentInstance();
		departmentID = getParam(fc);
		listofDepartmentSectors = departmentManager.getAllDepartmentSectorsById(departmentID);
		if (listofDepartmentSectors.size() > 0) {
			checkSectorPanel = true;
		} else {
			departmentToDelete = departmentManager.getDepartmentById(departmentID);
			departmentManager.deleteDepartment(departmentToDelete);
			checkSectorPanel = false;
			listOfDepartments = departmentManager.getDepartment();
		}

	}

	// delete the sector
	public void deleteSector() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String sectorID = getParamSector(fc);
		DepartmentSector sectorToDelete = departmentManager.getDepartmentSectorByID(sectorID, departmentID);
		departmentManager.deleteSector(sectorToDelete);
		listofDepartmentSectors = departmentManager.getAllDepartmentSectorsById(departmentID);
		if (listofDepartmentSectors.size() > 0) {
			checkSectorPanel = true;
		} else {
			checkSectorPanel = false;
		}

	}

	// gets the department id
	public String getParam(FacesContext fc) {
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("departmentID");
	}

	// gets the sector id
	public String getParamSector(FacesContext fc) {
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("sectorID");
	}

	// getters and setters
	public Department getNewDepartment() {
		return newDepartment;
	}

	public void setNewDepartment(Department newDepartment) {
		this.newDepartment = newDepartment;
	}

	public Department getDepartmentToDelete() {
		return departmentToDelete;
	}

	public void setDepartmentToDelete(Department departmentToDelete) {
		this.departmentToDelete = departmentToDelete;
	}

	public List<Department> getListOfDepartments() {
		return listOfDepartments;
	}

	public void setListOfDepartments(List<Department> listOfDepartments) {
		this.listOfDepartments = listOfDepartments;
	}

	public List<Sector> getListofDepartmentSectors() {
		return listofDepartmentSectors;
	}

	public void setListofDepartmentSectors(List<Sector> listofDepartmentSectors) {
		this.listofDepartmentSectors = listofDepartmentSectors;
	}

	public boolean isCheckSectorPanel() {
		return checkSectorPanel;
	}

	public void setCheckSectorPanel(boolean checkSectorPanel) {
		this.checkSectorPanel = checkSectorPanel;
	}

	public Department getUpdateDepartment() {
		return updateDepartment;
	}

	public void setUpdateDepartment(Department updateDepartment) {
		this.updateDepartment = updateDepartment;
	}

	@PostConstruct
	public void postConstruct() {
		System.out.println("POST Costruct");
	}

	@PreDestroy
	public void preDestroid() {
		System.out.println("preDestroid");
	}

}
