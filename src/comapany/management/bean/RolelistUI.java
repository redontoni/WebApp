package comapany.management.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import company.management.entity.Role;
import company.management.manager.RoleManager;

@SessionScoped
@ManagedBean(name = "rolelist_ui")
public class RolelistUI {
	private Role newRole;
	private Role roleToDelete;
	private Role updateRole;
	private RoleManager roleManager = new RoleManager();
	private List<Role> roles;
	private List<String> roleName = new ArrayList<>();

	public RolelistUI() {
		roles = roleManager.getRoles();
	}

	// redirect to create roleCreate view
	public String createRole() {
		newRole = new Role();
		return "/private/management/roleCreate.xhtml";
	}

	// redirects to roleList view
	public String cancel() {
		roles = roleManager.getRoles();
		return "/private/mains/superuser.xhtml";
	}

	// creating role method and redirect to roleList view
	public String addNewRole() {
		newRole.setEnabled(true);
		roleManager.createRole(newRole);
		roles = roleManager.getRoles();
		return "/private/mains/superuser.xhtml";
	}

	// getting the id
	public String getParam(FacesContext fc) {
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("roleID");
	}

	// redirect to update view
	public String editRole() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String roleID = getParam(fc);
		updateRole = roleManager.findRole(roleID);
		return "/private/management/roleUpdate.xhtml";
	}

	// update role method and redirect to roleList
	public String saveEditUser() {
		roleManager.updateRole(updateRole);
		roles = roleManager.getRoles();
		return "/private/mains/superuser.xhtml";
	}

	// delete role method
	public void deleteRole() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String roleID = getParam(fc);
		roleToDelete = roleManager.findRole(roleID);
		roleManager.deleteRole(roleToDelete);
		roles = roleManager.getRoles();
	}

	// getters and setters
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<String> getRoleName() {
		return roleName;
	}

	public void setRoleName(List<String> roleName) {
		this.roleName = roleName;
	}

	public Role getNewRole() {
		return newRole;
	}

	public void setNewRole(Role newRole) {
		this.newRole = newRole;
	}

	public Role getUpdateRole() {
		return updateRole;
	}

	public void setUpdateRole(Role updateRole) {
		this.updateRole = updateRole;
	}

}
