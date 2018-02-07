package company.management.manager;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

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
import company.management.entity.Product;
import company.management.entity.Sector;
import company.management.entity.SectorBrand;
import company.management.entity.SectorShop;
import company.management.entity.Shop;
import company.management.entity.ShopProduct;

public class SectorManager {

	// get sector list
	public List<Sector> getSector() {
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

			List<Sector> sector = session.createQuery("from Sector s where s.enabled = true").list();

			session.getTransaction().commit();
			return sector;
		} finally {
			session.close();
		}
	}

	// creating new sector
	public void createSector(Sector newSector, Department departmentid) {
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

			String sectorname = newSector.getSectorName();
			session.beginTransaction();
			session.save(newSector);

			Long lastId = ((BigInteger) session.createNativeQuery("SELECT LAST_INSERT_ID()").uniqueResult())
					.longValue();
			Sector sc = getSectorById(lastId);

			sc.setSectorId(lastId.intValue());
			sc.setSectorName(sectorname);
			DepartmentSector ds = new DepartmentSector();
			ds.setDepartment(departmentid);
			ds.setSector(sc);
			ds.setEnabled(true);
			session.save(ds);

			session.getTransaction().commit();

			
			
		} finally {
			// session.close();
		}
	}

	// finds the id of the role name i selected to the OneMenu
	public Department findDepartmentId(String departmentName) {
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
			// start e transaction
			session.beginTransaction();

			// query users
			List<Department> department = session
					.createQuery(" FROM Department d WHERE d.departmentName ='" + departmentName + "'").list();
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

	// get the sector by the id
	public Sector getSectorById(String sectorID) {
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

			List<Sector> sector = session
					.createQuery("from Sector s where s.sectorId ='" + sectorID + "' AND s.enabled = true").list();

			session.getTransaction().commit();
			if (sector.size() > 0) {
				return sector.get(0);
			} else {
				return new Sector();
			}

		} finally {
			session.close();
		}
	}

	// update the sector
	public void updateSector(Sector updateSector) {
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

			session.saveOrUpdate(updateSector);
			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}

	// soft delete the sector
	public void deleteSectorById(Sector sectorToDelete) {
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

//	public void createDepartmenSector(DepartmentSector testing) {
//		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Department.class)
//				.addAnnotatedClass(DepartmentSector.class).addAnnotatedClass(Sector.class)
//				.addAnnotatedClass(CompanyDepartment.class).addAnnotatedClass(Company.class)
//				.addAnnotatedClass(SectorBrand.class).addAnnotatedClass(Brand.class).addAnnotatedClass(SectorShop.class)
//				.addAnnotatedClass(Shop.class).addAnnotatedClass(ShopProduct.class).addAnnotatedClass(Country.class)
//				.addAnnotatedClass(Product.class).addAnnotatedClass(Currency.class)
//				.addAnnotatedClass(CompanyBrand.class).setProperty("hibernate.current_session_context_class", "thread")
//				.buildSessionFactory();
//
//		// create session
//		Session session = factory.getCurrentSession();
//
//		try {
//			// use the session object to save the Java object
//			System.out.println("Creating new sector");
//
//			// start e transaction
//			session.beginTransaction();
//
//			// save the student object
//			System.out.println("Saving the new sector");
//			session.save(testing);
//
//			// commit transaction
//			session.getTransaction().commit();
//
//			Long lastId = ((BigInteger) session.createNativeQuery("SELECT LAST_INSERT_ID()").uniqueResult())
//					.longValue();
//			System.out.println(lastId);
//
//			System.out.println("Done!!");
//		} finally {
//			session.close();
//		}
//
//	}

	public Sector getSectorById(long id) {
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

			List<Sector> sector = session.createQuery("from Sector s where s.sectorId =" + id + " AND s.enabled = true")
					.list();

			session.getTransaction().commit();
			if (sector.size() > 0) {
				return sector.get(0);
			} else {
				return new Sector();
			}

		} finally {
			session.close();
		}
	}

	@PostConstruct
	public void postconstruct() {
		System.out.println("Post Construct");
	}

	@PreDestroy
	public void preDestroy() {
		System.out.println("PreDestroy");
	}
}
