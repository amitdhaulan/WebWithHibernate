package java.pack;

import hibernate.util.HibernateUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import database.connection.T_Employee;

@Path("db")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 8241333585141633139L;
	JSONObject outerJsonObject;
	JSONObject innerJsonObject;
	
	@GET
	@Path("table/{table_name}")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject show(@PathParam("table_name") String table_name)
			throws JSONException {

		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("session.getSessionFactory(): "
				+ session.isConnected());

		if (table_name.equals("employee")) {
			
			Runnable myRunnable = new Runnable() {

				@Override
				public void run() {

					try {

						outerJsonObject = employeeRecord();
						System.out.println("... >>> " + outerJsonObject);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			};
			myRunnable.run();
			contextInitialized(myRunnable);

			return outerJsonObject;
		} else if (table_name.equals("customer")) {
			outerJsonObject = customerRecord();
			return outerJsonObject;
		} else if (table_name.equals("product")) {
			outerJsonObject = productRecord();
			return outerJsonObject;
		} else if (table_name.equals("testing")) {
			outerJsonObject = testingRecord();
			return outerJsonObject;
		} else {

			outerJsonObject = new JSONObject();
			innerJsonObject = new JSONObject();
			JSONArray array = new JSONArray();
			array.put(1, new Object());
			return outerJsonObject.put("response:",
					(innerJsonObject.put("record[0]", array)));
		}
	}

	public static void contextInitialized(Runnable myRunnable) {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
		executor.scheduleAtFixedRate(myRunnable, 0, 1, TimeUnit.MINUTES);
	}

	private JSONObject testingRecord() throws JSONException {
		outerJsonObject = new JSONObject();
		innerJsonObject = new JSONObject();
		SQLQuery query = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			query = session.createSQLQuery("SELECT * FROM testing");
		} catch (Exception exception) {
			JSONArray array = new JSONArray();
			array.put("Error: " + "Table does not exist!");
			session.close();
			return outerJsonObject.put("response:",
					(innerJsonObject.put("record[0]", array)));
		}
		List<?> list = query.list();
		Iterator<?> iterator = list.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			JSONArray array = new JSONArray();
			Object rows[] = (Object[]) iterator.next();
			array.put("id: " + rows[0]);
			array.put("name: " + rows[1]);
			array.put("address: " + rows[2]);
			innerJsonObject.put("record[" + i + "]", array);
			i = i + 1;
		}
		outerJsonObject.put("testing_response:", innerJsonObject);
		session.close();
		return outerJsonObject;
	}

	private JSONObject productRecord() throws JSONException {
		outerJsonObject = new JSONObject();
		innerJsonObject = new JSONObject();
		SQLQuery query = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			query = session.createSQLQuery("SELECT * FROM product");
		} catch (Exception exception) {
			JSONArray array = new JSONArray();
			array.put("Error: " + "Table does not exist!");
			session.close();
			return outerJsonObject.put("response:",
					(innerJsonObject.put("record[0]", array)));
		}
		List<?> list = query.list();
		Iterator<?> iterator = list.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			JSONArray array = new JSONArray();
			Object rows[] = (Object[]) iterator.next();
			array.put("product_id: " + rows[0]);
			array.put("company_name: " + rows[1]);
			innerJsonObject.put("record[" + i + "]", array);
			i = i + 1;
		}
		outerJsonObject.put("product_response:", innerJsonObject);
		session.close();
		return outerJsonObject;
	}

	private JSONObject customerRecord() throws JSONException {
		outerJsonObject = new JSONObject();
		innerJsonObject = new JSONObject();
		SQLQuery query = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			query = session.createSQLQuery("SELECT * FROM customer");
		} catch (Exception exception) {
			JSONArray array = new JSONArray();
			array.put("Error: " + "Table does not exist!");
			session.close();
			return outerJsonObject.put("response:",
					(innerJsonObject.put("record[0]", array)));
		}
		List<?> list = query.list();
		Iterator<?> iterator = list.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			JSONArray array = new JSONArray();
			Object rows[] = (Object[]) iterator.next();
			array.put("customer_id: " + rows[0]);
			array.put("customer_name: " + rows[1]);
			innerJsonObject.put("record[" + i + "]", array);
			i = i + 1;
		}
		outerJsonObject.put("customer_response:", innerJsonObject);
		session.close();
		return outerJsonObject;
	}

	private JSONObject employeeRecord() throws JSONException {
		outerJsonObject = new JSONObject();
		innerJsonObject = new JSONObject();
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			SQLQuery query = session.createSQLQuery("SELECT * FROM  employee");
			List<?> list = query.list();
			Iterator<?> it = list.iterator();
			int i = 0;
			while (it.hasNext()) {
				JSONObject array = new JSONObject();
				Object rows[] = (Object[]) it.next();
				array.put("id", rows[0]);
				array.put("name", rows[1]);
				array.put("salary", rows[2]);
				innerJsonObject.put("record[" + i + "]", array);
				i = i + 1;
			}
			outerJsonObject.put("employee_response:", innerJsonObject);
			session.close();
			return outerJsonObject;
		} catch (Exception exception) {
			JSONArray array = new JSONArray();
			array.put("Error: " + "Table does not exist!");
			session.close();
			return outerJsonObject.put("response:",
					(innerJsonObject.put("record[0]", array)));
		}

	}

	@POST
	@Path("/table/employee/insert")
	public JSONObject insert(@FormParam("id") String id,
			@FormParam("name") String name, @FormParam("salary") String salary)
			throws JSONException {
		JSONObject outerjsonObject = new JSONObject();
		JSONObject innerjsonObject = new JSONObject();
		JSONArray array = new JSONArray();
		T_Employee employee = new T_Employee();

		employee.setName(name);
		employee.setSalary(salary);
		employee.setS_id(Integer.parseInt(id));
		Session employee_session = HibernateUtil.getSessionFactory()
				.openSession();
		Transaction ts = employee_session.beginTransaction();

		System.out.println("begin");
		try {
			employee_session.save(employee);
			ts.commit();
			employee_session.close();
			array.put("success: " + "true");
			innerjsonObject
					.put("record", "inserted successfully with id " + id);
			outerjsonObject.put("response: ", innerjsonObject);

		} catch (Exception exception) {

			if (exception.toString().contains("ConstraintViolationException")) {
				System.out.println(exception.getLocalizedMessage());
				ts.rollback();
				employee_session.close();
				array.put("success: " + "false");
				innerjsonObject.put("record", "Duplicate entry " + id
						+ " for key 'PRIMARY'");
				outerjsonObject.put("response: ", innerjsonObject);
			} else {
				System.out.println(exception.getLocalizedMessage());
				ts.rollback();
				employee_session.close();
				array.put("success: " + "false");
				innerjsonObject.put("record", "not inserted");
				outerjsonObject.put("response: ", innerjsonObject);
			}

		}

		System.out.println("End begin");
		return outerjsonObject;
	}

	@POST
	@Path("/table/employee/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject delete(@FormParam("id") String id) throws JSONException {
		JSONObject outerjsonObject = new JSONObject();
		JSONObject innerjsonObject = new JSONObject();
		JSONArray array = new JSONArray();
		Session employee_session = HibernateUtil.getSessionFactory()
				.openSession();
		Transaction ts = employee_session.beginTransaction();

		Query query = employee_session
				.createQuery("delete Employee where id = :id");
		query.setParameter("id", new Integer(Integer.parseInt(id)));

		try {
			int result = query.executeUpdate();
			System.out.println("result: " + result);

			if (result > 0) {
				System.out.println("Deleted successfully");
				array.put("success: " + "true");
				innerjsonObject.put("record", "deleted successfully with id "
						+ id);
				outerjsonObject.put("response: ", innerjsonObject);
			} else {
				array.put("success: " + "false");
				innerjsonObject.put("record", "does not exist");
				outerjsonObject.put("response: ", innerjsonObject);
			}

			return outerjsonObject;

		} catch (Exception exception) {

			array.put("success: " + "false");
			innerjsonObject.put("record", "not deleted");
			outerjsonObject.put("response: ", innerjsonObject);
			ts.rollback();
			employee_session.close();
			return outerjsonObject;
		}

	}

	@GET
	@Path("{db: [a-zA-Z0-9_%/.]+}")
	public JSONObject defaultMethod(@PathParam("db") String db)
			throws JSONException {

		if (db.equals(null)) {
			JSONArray array = new JSONArray();
			JSONObject innerjsonObject = new JSONObject();
			JSONObject outerjsonObject = new JSONObject();

			array.put("success: " + "false");

			innerjsonObject.put("record", "Default called, choose proper URL!");
			outerjsonObject.put("response: ", innerjsonObject);

			return outerjsonObject;
		} else {
			JSONArray array = new JSONArray();
			JSONObject innerjsonObject = new JSONObject();
			JSONObject outerjsonObject = new JSONObject();

			array.put("success: " + "false");

			innerjsonObject.put("record", "Default called, choose proper URL!");
			outerjsonObject.put("response: ", innerjsonObject);

			return outerjsonObject;
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setIntHeader("Refresh", 5);

		// Set response content type
		resp.setContentType("text/html");

		// Get current time
		Calendar calendar = new GregorianCalendar();
		String am_pm;
		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		if (calendar.get(Calendar.AM_PM) == 0)
			am_pm = "AM";
		else
			am_pm = "PM";

		String CT = hour + ":" + minute + ":" + second + " " + am_pm;

		PrintWriter out = resp.getWriter();
		String title = "Auto Page Refresh using Servlet";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 "
				+ "transitional//en\">\n";
		out.println(docType + "<html>\n" + "<head><title>" + title
				+ "</title></head>\n" + "<body bgcolor=\"#f0f0f0\">\n"
				+ "<h1 align=\"center\">" + title + "</h1>\n"
				+ "<p>Current Time is: " + CT + "</p>\n");
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
	}

}
