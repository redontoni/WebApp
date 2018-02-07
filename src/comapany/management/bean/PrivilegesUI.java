package comapany.management.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import company.management.entity.Privilege;
import company.management.entity.Role;
import company.management.manager.PrivilegeManager;

@SessionScoped
@ManagedBean(name = "privilegelist_ui")
public class PrivilegesUI {
	private Privilege newPrivilege;
	private Privilege privilegeToDelete;
	private Privilege updatePrivilege;
	private PrivilegeManager privilegeManager = new PrivilegeManager();
	private String name = new String();
	private List<Privilege> privileges;
	private List<String> roleName = new ArrayList<>();
	private List<Role> roles = new ArrayList<>();

	public PrivilegesUI() {
		privileges = privilegeManager.getPrivileges();
	}

	// redirect to create privilegeCreate view
	public String create() {
		newPrivilege = new Privilege();
		roles = privilegeManager.getRoles();
		for (Role r : roles) {
			roleName.add(r.getRole());
		}
		return "/private/management/privilegesCreate.xhtml";
	}

	// redirects to privilegeList view
	public String cancel() {
		privileges = privilegeManager.getPrivileges();
		roleName.clear();
		return "/private/mains/superuser.xhtml";
	}

	// creating privilege method and redirect to privilegeList view
	public String addNewPrivilege() {
		Role roleID = privilegeManager.findRoleId(name);
		newPrivilege.setRole(roleID);
		newPrivilege.setEnabled(true);
		privilegeManager.createPrivilege(newPrivilege);
		privileges = privilegeManager.getPrivileges();
		roleName.clear();
		return "/private/mains/superuser.xhtml";
	}

	// redirect to update view
	public String editPrivilege() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String PrivilegeID = getParam(fc);
		updatePrivilege = privilegeManager.findPrivilege(PrivilegeID);
		roles = privilegeManager.getRoles();
		for (Role r : roles) {
			roleName.add(r.getRole());
		}
		name = new String();
		return "/private/management/privilegesUpdate.xhtml";
	}

	// delete privilege method
	public void deletePrivilege() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String PrivilegeID = getParam(fc);
		privilegeToDelete = privilegeManager.findPrivilege(PrivilegeID);
		privilegeManager.deletePrivilege(privilegeToDelete);
		privileges = privilegeManager.getPrivileges();
	}

	// update user method
	public String saveEditPrivileges() {
		Role rol = privilegeManager.getRoleByName(name);
		updatePrivilege.setRole(rol);
		privilegeManager.updatePrivileges(updatePrivilege);
		privileges = privilegeManager.getPrivileges();
		roleName.clear();
		return "/private/mains/superuser.xhtml";
	}
	
	// redirect to create user view
		public String createPrivilege() {
			newPrivilege = new Privilege();
			
			return "/private/management/privilegesCreate.xhtml";
		}

	// getting the id
	public String getParam(FacesContext fc) {
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("privilegeID");
	}

	// getters and setters
	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	public Privilege getNewPrivilege() {
		return newPrivilege;
	}

	public void setNewPrivilege(Privilege newPrivilege) {
		this.newPrivilege = newPrivilege;
	}

	public Privilege getUpdatePrivilege() {
		return updatePrivilege;
	}

	public void setUpdatePrivilege(Privilege updatePrivilege) {
		this.updatePrivilege = updatePrivilege;
	}

	public PrivilegeManager getPrivilegeManager() {
		return privilegeManager;
	}

	public void setPrivilegeManager(PrivilegeManager privilegeManager) {
		this.privilegeManager = privilegeManager;
	}

	public List<String> getRoleName() {
		return roleName;
	}

	public void setRoleName(List<String> roleName) {
		this.roleName = roleName;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
