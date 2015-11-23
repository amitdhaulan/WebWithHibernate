package database.connection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author amitk
 *
 */
@Entity
@Table(name = "employee")
public class T_Employee {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = true)
	private int _id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "SALARY")
	private String salary;

	public T_Employee() {
	}

	public int getS_id() {
		return _id;
	}

	public void setS_id(int s_id) {
		this._id = s_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String _name) {
		this.name = _name;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String _salary) {
		this.salary = _salary;
	}


}
