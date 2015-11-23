package database.connection;

import hibernate.util.HibernateUtil;

import java.util.Iterator;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class ConnectionClassCustomer {
	public static void main(String... str) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		// **********************************************************************

		/*T_Customer customer= new T_Customer();
		customer.setNAME("Amazon");
		customer.setSN(89);
		Session employee_session = HibernateUtil.getSessionFactory()
				.openSession();
		Transaction ts = employee_session.beginTransaction();
		System.out.println("begin");
		try {
			employee_session.save(customer);
			ts.commit();
			employee_session.close();
		} catch (Exception exception) {
			System.out.println(exception.getLocalizedMessage());
			ts.rollback();
			employee_session.close();
		}

		System.out.println("End begin");*/

		// **********************************************************************

		SQLQuery employee_query = session
				.createSQLQuery("SELECT * FROM  customer");
		List<?> employee_list = employee_query.list();
		Iterator<?> employee_it = employee_list.iterator();

		while (employee_it.hasNext()) {

			Object rows[] = (Object[]) employee_it.next();
			System.out.println(rows[0] + " -- " + rows[1]);

		}

		// **********************************************************************

		session.close();
		// **********************************************************************
	}
}
