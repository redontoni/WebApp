package company.management.manager;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import company.management.entity.Privilege;
import company.management.entity.Role;
import company.management.entity.User;

public class PrivilegeManager {

	// getting all info from privileges table
	public List<Privilege> getPrivileges() {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Role.class).addAnnotatedClass(Privilege.class)
				.setProperty("hibernate.current_session_context_class", "thread").buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start e transaction
			session.beginTransaction();
			// query users
			List<Privilege> privilege = session.createQuery("from Privilege AS p  WHERE p.enabled=true").list();

			session.getTransaction().commit();
			return privilege;
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

	// creating new user
	public void createPrivilege(Privilege newPrivilege) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Role.class).addAnnotatedClass(Privilege.class)
				.setProperty("hibernate.current_session_context_class", "thread").buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start e transaction
			session.beginTransaction();

			// save the student object
			System.out.println("Saving the new privilege");
			session.save(newPrivilege);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!!");
		} finally {
			session.close();
		}

	}

	// safety deletes the user
	public void deletePrivilege(Privilege privilegeToDelete) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Role.class).addAnnotatedClass(Privilege.class)
				.setProperty("hibernate.current_session_context_class", "thread").buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start e transaction
			session.beginTransaction();

			privilegeToDelete.setEnabled(false);

			session.update(privilegeToDelete);

			session.getTransaction().commit();

		} finally {
			session.close();
		}

	}

	// find the userIdand sets its enabled value to true
	public Privilege findPrivilege(String privilegeID) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Role.class).addAnnotatedClass(Privilege.class)
				.setProperty("hibernate.current_session_context_class", "thread").buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start e transaction
			session.beginTransaction();
			// query users
			int privilegeId = Integer.parseInt(privilegeID);
			List<Privilege> privileges = session
					.createQuery(" FROM Privilege p WHERE p.privilegesID ='" + privilegeID + "' AND p.enabled = true")
					.list();

			session.getTransaction().commit();

			if (privileges.size() > 0) {
				return privileges.get(0);
			} else {
				return new Privilege();
			}

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

	// updates the privileges
	public void updatePrivileges(Privilege updatePrivilege) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Role.class).addAnnotatedClass(Privilege.class)
				.setProperty("hibernate.current_session_context_class", "thread").buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start e transaction
			session.beginTransaction();

			session.saveOrUpdate(updatePrivilege);

			session.getTransaction().commit();

		} finally {
			session.close();
		}

	}

	public Role getRoleByName(String name) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Role.class).addAnnotatedClass(Privilege.class)
				.setProperty("hibernate.current_session_context_class", "thread").buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start e transaction
			session.beginTransaction();

			// query users
			List<Role> roles = session.createQuery(" FROM Role r WHERE r.role ='" + name + "'").list();
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
