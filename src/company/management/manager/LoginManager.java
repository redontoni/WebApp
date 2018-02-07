package company.management.manager;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import comapany.management.bean.LoginControllerUI;
import company.management.entity.*;

public class LoginManager {

	LoginControllerUI loginController;
	private int found;

	public boolean controll(User userInfo) {
		found = 0;

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Role.class).addAnnotatedClass(Privilege.class)
				.setProperty("hibernate.current_session_context_class", "thread").buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start e transaction
			session.beginTransaction();
			// query users
			List<User> theUsers = session.createQuery("from User u where u.username = '" + userInfo.getUsername() + "' and u.password = '" + userInfo.getPassword() + "' ")
					.list();
			
			// controll if password and username are found in the database
			for (User u : theUsers) {
				if (u.getUsername().equals(userInfo.getUsername()) && u.getPassword().equals(userInfo.getPassword())) {
					found = 1;
				}
			}

			session.getTransaction().commit();
		} finally {
			session.close();
		}
		if (found == 0) {
			return false;
		} else
			return true;

	}
}
