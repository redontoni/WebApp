package comapany.management.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import company.management.entity.Role;
import company.management.entity.User;
import company.management.manager.UserManager;

@SessionScoped
@ManagedBean(name = "userlist_ui")
public class UserListUI  {

	private User newUser;
	private User userToDelete;
	private User editUser;
	private UserManager userManager = new UserManager();
	private String name = new String();
	private List<User> users;
	private List<String> roleNames = new ArrayList<>();
	private List<Role> roles = new ArrayList<>();

	public UserListUI() {
		users = userManager.getUsers();
	}

	// create the new user and redirect to create view
	public String addNewUser() {
		Role roleID = userManager.findRoleId(name);
		newUser.setRole(roleID);
		newUser.setEnabled(true);
		userManager.createUser(newUser);
		users = userManager.getUsers();
		roleNames.clear();
		return "/private/mains/superuser.xhtml";
	}

	// redirect to update view
	public String editUser() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String userID = getParam(fc);
		editUser = userManager.findUser(userID);
		
		roles = userManager.getRoles();
		for (Role r : roles) {
			roleNames.add(r.getRole());
		}
		return "/private/management/userUpdate.xhtml";
	}

	// delete user method
	public void deleteUser() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String userID = getParam(fc);
		userToDelete = userManager.findUser(userID);
		userManager.deleteUser(userToDelete);
		users = userManager.getUsers();
	}

	// update user method
	public String saveEditUser() {
		Role rol = userManager.findRoleId(name);
		editUser.setRole(rol);
		userManager.updateUser(editUser);
		users = userManager.getUsers();
		roleNames.clear();
		return "/private/mains/superuser.xhtml";
	}

	// redirect to user view
	public String cancel() {
		users = userManager.getUsers();
		roleNames.clear();
		return "/private/mains/superuser.xhtml";
	}

	// redirect to create user view
	public String createUser() {
		newUser = new User();
		roles = userManager.getRoles();
		for (Role r : roles) {
			roleNames.add(r.getRole());
		}
		return "/private/management/userCreate.xhtml";
	}

	public String getParam(FacesContext fc) {
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("userID");
	}

	// getters and setters
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(List<String> roleNames) {
		this.roleNames = roleNames;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public User getEditUser() {
		return editUser;
	}

	public void setEditUser(User editUser) {
		this.editUser = editUser;
	}

}
