package database.connection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "testing")
public class T_Testing {

	@Id
	@Column(name = "SN")
	@GeneratedValue
	private int SN;

	@Column(name = "NAME")
	private String NAME;

	@Column(name = "Address")
	private String Address;

	public int getSN() {
		return SN;
	}

	public void setSN(int sN) {
		SN = sN;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}
}
