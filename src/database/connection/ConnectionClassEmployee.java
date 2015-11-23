package database.connection;

import hibernate.util.HibernateUtil;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class ConnectionClassEmployee {
	static Runnable myRunnable;
	public static void main(String... str) {
		
		final Session session = HibernateUtil.getSessionFactory().openSession();
		
		
		// **********************************************************************

		/*T_Employee employee = new T_Employee();
		employee.setName("Amazon");
		employee.setSalary(50000 + "");
		employee.setS_id(89);
		Session employee_session = HibernateUtil.getSessionFactory()
				.openSession();
		Transaction ts = employee_session.beginTransaction();
		System.out.println("begin");
		try {
			employee_session.save(employee);
			ts.commit();
			employee_session.close();
		} catch (Exception exception) {
			System.out.println(exception.getLocalizedMessage());
			ts.rollback();
			employee_session.close();
		}

		System.out.println("End begin");*/

		// **********************************************************************

		myRunnable = new Runnable() {

			@Override
			public void run() {
				SQLQuery employee_query = session
						.createSQLQuery("SELECT * FROM  employee");
				List<?> employee_list = employee_query.list();
				Iterator<?> employee_it = employee_list.iterator();

				while (employee_it.hasNext()) {

					Object rows[] = (Object[]) employee_it.next();
					System.out.println(rows[0] + " -- " + rows[1] + " -- " + rows[2]);

				}
			}
		};
		myRunnable.run();
		contextInitialized(myRunnable);
		

		// **********************************************************************
		session.close();
		// **********************************************************************
	}
	public static void contextInitialized(Runnable myRunnable) {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
		executor.scheduleAtFixedRate(myRunnable, 0, 3, TimeUnit.SECONDS);
	}
}
