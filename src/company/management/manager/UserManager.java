package company.management.manager;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import company.management.dao.DaoManager;
import company.management.entity.Privilege;
import company.management.entity.Role;
import company.management.entity.User;


public class UserManager {

	// gets all info from user table
	public List<User> getUsers() {
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
			List<User> theUsers = session.createQuery("from User u Where u.enabled= true ").list();

			// controll if password and username are found in the database

			session.getTransaction().commit();
			return theUsers;
		} finally {
			session.close();
		}

	}

	// creating new user
	public void createUser(User newUser) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Role.class).addAnnotatedClass(Privilege.class)
				.setProperty("hibernate.current_session_context_class", "thread").buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// use the session object to save the Java object
			System.out.println("Creating new user object ");

			// start e transaction
			session.beginTransaction();

			// save the student object
			System.out.println("Saving the new user");
			session.save(newUser);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!!");
		} finally {
			session.close();
		}

	}

	// gets all info from role table
	public List<Role> getRoles() {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Role.class).addAnnotatedClass(Privilege.class)
				.setProperty("hibernate.current_session_context_class", "thread").buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start e transaction
			session.beginTransaction();

			// query users
			List<Role> roles = session.createQuery("from Role ").list();

			session.getTransaction().commit();
			return roles;
		} finally {
			session.close();
		}

	}

	// finds the id of the role name i selected to the OneMenu
	public Role findRoleId(String roleName) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Role.class).addAnnotatedClass(Privilege.class)
				.setProperty("hibernate.current_session_context_class", "thread").buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start e transaction
			session.beginTransaction();

			// query users
			List<Role> roles = session.createQuery(" FROM Role r WHERE r.role ='" + roleName + "'").list();
			session.getTransaction().commit();
			if (roles.size() > 0) {
				return roles.get(0);
			} else {
				return new Role();
			}

		} finally {
			session.close();
		}

	}

	// find the userIdand sets its enabled value to true
	public User findUser(String userID) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Role.class).addAnnotatedClass(Privilege.class)
				.setProperty("hibernate.current_session_context_class", "thread").buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start e transaction
			session.beginTransaction();
			// query users
			List<User> users = session.createQuery(" FROM User u WHERE u.userID ='" + userID + "' AND u.enabled = true")
					.list();

			session.getTransaction().commit();

			if (users.size() > 0) {
				return users.get(0);
			} else {
				return new User();
			}

		} finally {
			session.close();
		}
	}

	// safety deletes the user
	public void deleteUser(User userToDelete) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Role.class).addAnnotatedClass(Privilege.class)
				.setProperty("hibernate.current_session_context_class", "thread").buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start e transaction
			session.beginTransaction();

			userToDelete.setEnabled(false);

			session.update(userToDelete);

			session.getTransaction().commit();

		} finally {
			session.close();
		}

	}

	// updates the user
	public void updateUser(User editUser) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Role.class).addAnnotatedClass(Privilege.class)
				.setProperty("hibernate.current_session_context_class", "thread").buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start e transaction
			session.beginTransaction();

			session.saveOrUpdate(editUser);

			session.getTransaction().commit();

		} finally {
			session.close();
		}

	}

}
