package company.management.manager;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import company.management.entity.Privilege;
import company.management.entity.Role;
import company.management.entity.User;

public class RoleManager {

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
			List<Role> roles = session.createQuery("from Role r where r.enabled = true").list();

			session.getTransaction().commit();
			return roles;
		} finally {
			session.close();
		}
	}

	// creating new role
	public void createRole(Role newRole) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Role.class).addAnnotatedClass(Privilege.class)
				.setProperty("hibernate.current_session_context_class", "thread").buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// use the session object to save the Java object
			System.out.println("Creating new role");

			// start e transaction
			session.beginTransaction();

			// save the student object
			System.out.println("Saving the new user");
			session.save(newRole);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!!");
		} finally {
			session.close();
		}
	}

	// updates the user
	public void updateRole(Role updateRole) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Role.class).addAnnotatedClass(Privilege.class)
				.setProperty("hibernate.current_session_context_class", "thread").buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start e transaction
			session.beginTransaction();

			session.saveOrUpdate(updateRole);

			session.getTransaction().commit();

		} finally {
			session.close();
		}

	}

	// find the userIdand sets its enabled value to true
	public Role findRole(String roleID) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Role.class).addAnnotatedClass(Privilege.class)
				.setProperty("hibernate.current_session_context_class", "thread").buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start e transaction
			session.beginTransaction();
			// query users
			List<Role> roles = session.createQuery(" FROM Role r WHERE r.roleID ='" + roleID + "' AND r.enabled = true")
					.list();

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

	// safety deletes the user
	public void deleteRole(Role roleToDelete) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Role.class).addAnnotatedClass(Privilege.class)
				.setProperty("hibernate.current_session_context_class", "thread").buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start e transaction
			session.beginTransaction();

			roleToDelete.setEnabled(false);

			session.update(roleToDelete);

			session.getTransaction().commit();

		} finally {
			session.close();
		}

	}

	public Role findRoleById(long parseLong) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Role.class).addAnnotatedClass(Privilege.class)
				.setProperty("hibernate.current_session_context_class", "thread").buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start e transaction
			session.beginTransaction();
			// query users
			List<Role> roles = session.createQuery(" FROM Role r WHERE r.roleID ='" + parseLong + "' AND r.enabled = true")
					.list();

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

}
