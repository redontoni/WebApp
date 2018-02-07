package comapany.management.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import company.management.entity.Department;
import company.management.entity.DepartmentSector;
import company.management.entity.Sector;
import company.management.manager.SectorManager;

@SessionScoped
@ManagedBean(name = "sector_ui")
public class SectorUI {
	private Sector newSector;
	private Sector sectorToDelete;
	private Sector updateSector;
	private SectorManager sectorManager = new SectorManager();
	private List<Sector> listOfSectors;
	private List<Department> listofDepartment = new ArrayList<>();
	private List<String> departmentName = new ArrayList<>();
	private String name = new String();

	public SectorUI() {
		listOfSectors = sectorManager.getSector();
	}

	// creating sector method and redirect to sectorList view
	public String addNewSector() {
		Department departmentID = sectorManager.findDepartmentId(name);
		newSector.setEnabled(true);
		sectorManager.createSector(newSector,departmentID);
		listOfSectors = sectorManager.getSector();
		listofDepartment.clear();
		return "/private/mains/superuser.xhtml";
	}

	// redirect to update view
	public String editSector() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String sectorID = getParamSector(fc);
		updateSector = sectorManager.getSectorById(sectorID);
		return "/private/management/sectorUpdate.xhtml";
	}

	// delete sector method
	public void deleteSector() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String sectorID = getParamSector(fc);
		sectorToDelete = sectorManager.getSectorById(sectorID);
		sectorManager.deleteSectorById(sectorToDelete);
		listOfSectors = sectorManager.getSector();
	}

	// gets the sector id
	public String getParamSector(FacesContext fc) {
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("sectorID");
	}

	// update department method and redirect to roleList
	public String saveEditSector() {
		sectorManager.updateSector(updateSector);
		listOfSectors = sectorManager.getSector();
		return "/private/mains/superuser.xhtml";
	}

	// redirect to sector view
	public String cancel() {
		listOfSectors = sectorManager.getSector();
		return "/private/mains/superuser.xhtml";
	}

	// redirect to create sector view
	public String createSector() {
		listofDepartment = sectorManager.getDepartment();
		for (Department d : listofDepartment) {
			departmentName.add(d.getDepartmentName());
		}
		newSector = new Sector();
		return "/private/management/sectorCreate.xhtml";
	}

	// getter and setters
	public Sector getNewSector() {
		return newSector;
	}

	public void setNewSector(Sector newSector) {
		this.newSector = newSector;
	}

	public Sector getSectorToDelete() {
		return sectorToDelete;
	}

	public void setSectorToDelete(Sector sectorToDelete) {
		this.sectorToDelete = sectorToDelete;
	}

	public Sector getUpdateSector() {
		return updateSector;
	}

	public void setUpdateSector(Sector updateSector) {
		this.updateSector = updateSector;
	}

	public List<Sector> getListOfSectors() {
		return listOfSectors;
	}

	public void setListOfSectors(List<Sector> listOfSectors) {
		this.listOfSectors = listOfSectors;
	}

	public List<Department> getListofDepartment() {
		return listofDepartment;
	}

	public void setListofDepartment(List<Department> listofDepartment) {
		this.listofDepartment = listofDepartment;
	}

	public List<String> getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(List<String> departmentName) {
		this.departmentName = departmentName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
