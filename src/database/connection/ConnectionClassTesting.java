package database.connection;

import hibernate.util.HibernateUtil;

import java.util.Iterator;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class ConnectionClassTesting {
	public static void main(String... str) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		// **********************************************************************

		
		/*T_Testing testing = new T_Testing();
		testing.setAddress("USA");
		testing.setNAME("John");
		testing.setSN(95);
		Session testing_session = HibernateUtil.getSessionFactory()
				.openSession();
		Transaction ts = testing_session.beginTransaction();
		System.out.println("begin");
		try {
			testing_session.save(testing);
			ts.commit();
			testing_session.close();
		} catch (Exception exception) {
			System.out.println(exception.getLocalizedMessage());
			ts.rollback();
			testing_session.close();
		}

		System.out.println("End begin");*/

		// **********************************************************************

		SQLQuery testing_query = session
				.createSQLQuery("SELECT * FROM  testing");
		List<?> testing_list = testing_query.list();
		Iterator<?> testing_it = testing_list.iterator();

		while (testing_it.hasNext()) {

			Object rows[] = (Object[]) testing_it.next();
			System.out.println(rows[0] + " -- " + rows[1] + " -- " + rows[2]);

		}
		// **********************************************************************

		session.close();
		// **********************************************************************
	}
}
