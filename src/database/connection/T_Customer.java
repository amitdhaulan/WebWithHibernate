package database.connection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class T_Customer {

	public int getSN() {
		return id;
	}

	public void setSN(int _id) {
		id = _id;
	}

	public String getNAME() {
		return customer_name;
	}

	public void setNAME(String nAME) {
		customer_name = nAME;
	}

	@Column(name = "CUSTOMERNAME")
	private String customer_name;
	
	@Id
	@Column(name = "CUSTOMERID")
	@GeneratedValue
	private int id;

}
