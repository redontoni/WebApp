package company.management.manager;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import company.management.entity.Brand;
import company.management.entity.Company;
import company.management.entity.CompanyBrand;
import company.management.entity.CompanyDepartment;
import company.management.entity.Country;
import company.management.entity.Currency;
import company.management.entity.Department;
import company.management.entity.DepartmentSector;
import company.management.entity.Privilege;
import company.management.entity.Product;
import company.management.entity.Role;
import company.management.entity.Sector;
import company.management.entity.SectorBrand;
import company.management.entity.SectorShop;
import company.management.entity.Shop;
import company.management.entity.ShopProduct;
import company.management.entity.User;

public class DepartmentManager {

	// creating new department
	public void createDepartment(Department newDepartment) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Department.class)
				.addAnnotatedClass(DepartmentSector.class).addAnnotatedClass(Sector.class)
				.addAnnotatedClass(CompanyDepartment.class).addAnnotatedClass(Company.class)
				.addAnnotatedClass(SectorBrand.class).addAnnotatedClass(Brand.class).addAnnotatedClass(SectorShop.class)
				.addAnnotatedClass(Shop.class).addAnnotatedClass(ShopProduct.class).addAnnotatedClass(Country.class)
				.addAnnotatedClass(Product.class).addAnnotatedClass(Currency.class)
				.addAnnotatedClass(CompanyBrand.class).setProperty("hibernate.current_session_context_class", "thread")
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// use the session object to save the Java object
			System.out.println("Creating new department");

			// start e transaction
			session.beginTransaction();

			// save the student object
			System.out.println("Saving the new department");
			session.save(newDepartment);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!!");
		} finally {
			session.close();
		}
	}

	// gets all info from department table
	public List<Department> getDepartment() {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Department.class)
				.addAnnotatedClass(DepartmentSector.class).addAnnotatedClass(Sector.class)
				.addAnnotatedClass(CompanyDepartment.class).addAnnotatedClass(Company.class)
				.addAnnotatedClass(SectorBrand.class).addAnnotatedClass(Brand.class).addAnnotatedClass(SectorShop.class)
				.addAnnotatedClass(Shop.class).addAnnotatedClass(ShopProduct.class).addAnnotatedClass(Country.class)
				.addAnnotatedClass(Product.class).addAnnotatedClass(Currency.class)
				.addAnnotatedClass(CompanyBrand.class).setProperty("hibernate.current_session_context_class", "thread")
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start e transaction
			session.beginTransaction();

			List<Department> department = session.createQuery("from Department d where d.enabled = true").list();

			session.getTransaction().commit();
			return department;
		} finally {
			session.close();
		}
	}

	// find the sectorNames in the department with id(x)
	public List<Sector> getAllDepartmentSectorsById(String departmentID) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Department.class)
				.addAnnotatedClass(DepartmentSector.class).addAnnotatedClass(Sector.class)
				.addAnnotatedClass(CompanyDepartment.class).addAnnotatedClass(Company.class)
				.addAnnotatedClass(SectorBrand.class).addAnnotatedClass(Brand.class).addAnnotatedClass(SectorShop.class)
				.addAnnotatedClass(Shop.class).addAnnotatedClass(ShopProduct.class).addAnnotatedClass(Country.class)
				.addAnnotatedClass(Product.class).addAnnotatedClass(Currency.class)
				.addAnnotatedClass(CompanyBrand.class).setProperty("hibernate.current_session_context_class", "thread")
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start e transaction
			session.beginTransaction();

			List<Integer> sectorIds = session.createNativeQuery(
					"Select ds.sectorId from department_sector ds where ds.departmentId = :deptId AND ds.enabled= true")
					.setParameter("deptId", departmentID).list();
			List<Sector> sector = new ArrayList<>();
			sectorIds.forEach(setId -> {
				sector.add(session.createNativeQuery("Select * from sectors s where s.sectorId=:setId", Sector.class)
						.setParameter("setId", setId).getSingleResult());
			});
			session.getTransaction().commit();
			if (sector.size() > 0) {
				return sector;
			} else {
				return sector;
			}

		} finally {
			session.close();
		}
	}

	public Department getDepartmentById(String departmentID) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Department.class)
				.addAnnotatedClass(DepartmentSector.class).addAnnotatedClass(Sector.class)
				.addAnnotatedClass(CompanyDepartment.class).addAnnotatedClass(Company.class)
				.addAnnotatedClass(SectorBrand.class).addAnnotatedClass(Brand.class).addAnnotatedClass(SectorShop.class)
				.addAnnotatedClass(Shop.class).addAnnotatedClass(ShopProduct.class).addAnnotatedClass(Country.class)
				.addAnnotatedClass(Product.class).addAnnotatedClass(Currency.class)
				.addAnnotatedClass(CompanyBrand.class).setProperty("hibernate.current_session_context_class", "thread")
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start e transaction
			session.beginTransaction();

			List<Department> department = session
					.createQuery("from Department d where d.departmentId ='" + departmentID + "' AND d.enabled = true")
					.list();

			session.getTransaction().commit();
			if (department.size() > 0) {
				return department.get(0);
			} else {
				return new Department();
			}

		} finally {
			session.close();
		}
	}

	// soft delete the department
	public void deleteDepartment(Department departmentToDelete) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Department.class)
				.addAnnotatedClass(DepartmentSector.class).addAnnotatedClass(Sector.class)
				.addAnnotatedClass(CompanyDepartment.class).addAnnotatedClass(Company.class)
				.addAnnotatedClass(SectorBrand.class).addAnnotatedClass(Brand.class).addAnnotatedClass(SectorShop.class)
				.addAnnotatedClass(Shop.class).addAnnotatedClass(ShopProduct.class).addAnnotatedClass(Country.class)
				.addAnnotatedClass(Product.class).addAnnotatedClass(Currency.class)
				.addAnnotatedClass(CompanyBrand.class).setProperty("hibernate.current_session_context_class", "thread")
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start e transaction
			session.beginTransaction();

			departmentToDelete.setEnabled(false);
			session.saveOrUpdate(departmentToDelete);
			session.getTransaction().commit();
		} finally {
			session.close();
		}

	}

	// get the the id-s of department and sector in the department_sector table
	public DepartmentSector getDepartmentSectorByID(String sectorID, String departmentId) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Department.class)
				.addAnnotatedClass(DepartmentSector.class).addAnnotatedClass(Sector.class)
				.addAnnotatedClass(CompanyDepartment.class).addAnnotatedClass(Company.class)
				.addAnnotatedClass(SectorBrand.class).addAnnotatedClass(Brand.class).addAnnotatedClass(SectorShop.class)
				.addAnnotatedClass(Shop.class).addAnnotatedClass(ShopProduct.class).addAnnotatedClass(Country.class)
				.addAnnotatedClass(Product.class).addAnnotatedClass(Currency.class)
				.addAnnotatedClass(CompanyBrand.class).setProperty("hibernate.current_session_context_class", "thread")
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start e transaction
			session.beginTransaction();

			List<DepartmentSector> sector = session.createQuery("from DepartmentSector ds where ds.sector = '"
					+ sectorID + "' AND ds.department = '" + departmentId + "' AND ds.enabled = true").list();

			session.getTransaction().commit();
			if (sector.size() > 0) {
				return sector.get(0);
			} else {
				return new DepartmentSector();
			}

		} finally {
			session.close();
		}
	}

	// delete the sector
	public void deleteSector(DepartmentSector sectorToDelete) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Department.class)
				.addAnnotatedClass(DepartmentSector.class).addAnnotatedClass(Sector.class)
				.addAnnotatedClass(CompanyDepartment.class).addAnnotatedClass(Company.class)
				.addAnnotatedClass(SectorBrand.class).addAnnotatedClass(Brand.class).addAnnotatedClass(SectorShop.class)
				.addAnnotatedClass(Shop.class).addAnnotatedClass(ShopProduct.class).addAnnotatedClass(Country.class)
				.addAnnotatedClass(Product.class).addAnnotatedClass(Currency.class)
				.addAnnotatedClass(CompanyBrand.class).setProperty("hibernate.current_session_context_class", "thread")
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start e transaction
			session.beginTransaction();

			sectorToDelete.setEnabled(false);
			session.saveOrUpdate(sectorToDelete);
			session.getTransaction().commit();
		} finally {
			session.close();
		}

	}

	// updates the department
	public void updateDepartment(Department updateDepartment) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Department.class)
				.addAnnotatedClass(DepartmentSector.class).addAnnotatedClass(Sector.class)
				.addAnnotatedClass(CompanyDepartment.class).addAnnotatedClass(Company.class)
				.addAnnotatedClass(SectorBrand.class).addAnnotatedClass(Brand.class).addAnnotatedClass(SectorShop.class)
				.addAnnotatedClass(Shop.class).addAnnotatedClass(ShopProduct.class).addAnnotatedClass(Country.class)
				.addAnnotatedClass(Product.class).addAnnotatedClass(Currency.class)
				.addAnnotatedClass(CompanyBrand.class).setProperty("hibernate.current_session_context_class", "thread")
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			// start e transaction
			session.beginTransaction();

			session.saveOrUpdate(updateDepartment);
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}
}
