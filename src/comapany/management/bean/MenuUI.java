package comapany.management.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@SessionScoped
@ManagedBean(name = "menu_ui")
public class MenuUI {

	private boolean checkAdmin;
	private boolean checkDepartmentManager;
	private String beanName;
	private String choise;

	public MenuUI() {
		System.out.println(" [ " + beanName + " ]");
	}

	public void initialize() {

		checkAdmin = false;
		checkDepartmentManager = false;
	}

	public void menuRendered() {

	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/private/mains/index.xhtml";
	}

	// getters and setters
	public boolean isCheckAdmin() {
		return checkAdmin;
	}

	public void setCheckAdmin(boolean checkAdmin) {
		this.checkAdmin = checkAdmin;
	}

	public boolean isCheckDepartmentManager() {
		return checkDepartmentManager;
	}

	public void setCheckDepartmentManager(boolean checkDepartmentManager) {
		this.checkDepartmentManager = checkDepartmentManager;
	}

	public String getChoise() {
		return choise;
	}

	public void setChoise(String choise) {
		this.choise = choise;
	}

}
