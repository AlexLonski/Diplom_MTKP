package application;

/* �������:
 * Getters - ����� ������;
 * Setters - ���� ������;
 * getSQL - ����� ���� ���������� ���
 * SQL-������;
 * ���������� ���������:
 * id, postcode, city, 
 * streetAddress - ��������� �������
 * 'location'.
 */

public class Location{

	private Integer id;
	private String postcode;
	private String city;
	private String streetAddress;

	// ����������
	public Location(Integer id, String postcode, String city, String streetAddress) {
		this.id = id;
		this.postcode = postcode;
		this.city = city;
		this.streetAddress = streetAddress;
	}

	// ����������
	public Location(String postcode, String city, String streetAddress) {
		this.postcode = postcode;
		this.city = city;
		this.streetAddress = streetAddress;
	}


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreetAddress() {
		return this.streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	String getSQL()
	{
		return "'" + getPostcode() +"', '"+ getCity()+"', '" + getStreetAddress() +"'";
	}
}
