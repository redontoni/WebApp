package comapany.management.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import company.management.entity.User;
import company.management.manager.LoginManager;

@SessionScoped
@ManagedBean(name = "loginController")
public class LoginControllerUI {

	private LoginManager loginModel;
	private User user=new User();
	private String errorMessage;

	// functionalities
	public LoginControllerUI() {
		loginModel = new LoginManager();
	}

	public String login() {
		user.setEnabled(true);
		if (loginModel.controll(user)) {	
				return "/private/mains/superuser.xhtml";
		} 
		else {
			errorMessage="Wrong username password combo";
			return null;
		}
	}
	public String getErrorMessage() {
		return errorMessage;
	}

	// getter and setters
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

}
