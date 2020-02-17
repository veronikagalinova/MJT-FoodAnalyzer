package bg.sofia.uni.fmi.mjt.food.analyzer.dto.details;

import com.google.gson.annotations.SerializedName;

public final class Product {
	
	private String group;
	
	private String name;
	
	private String ndbno;
	
	@SerializedName("manu")
	
	private String manufacturer;
	
	private transient String type;
	
	private static final String UPC_FIELD_NAME = "UPC";
	
	private static final int START_OF_UPC_CODE = 2;
	
	private static final String DELIMETER = "/";
	
	public Product(String group, String name, String ndbno, String manufacturer) {
		super();
		this.group = group;
		this.name = name;
		this.ndbno = ndbno;
		this.manufacturer = manufacturer;
	}
	
	public Product(String type, String group, String name, String ndbno, String manufacturer) {
		super();
		this.type = type;
		this.group = group;
		this.name = name;
		this.ndbno = ndbno;
		this.manufacturer = manufacturer;
	}
	
	public String getType() {
		return type;
	}
	
	public String getUPC() {
		if (name.indexOf(UPC_FIELD_NAME) == -1) {
			return "";
		}
		String[] tokens = name.split(UPC_FIELD_NAME);
		return tokens[1].substring(START_OF_UPC_CODE);
	}
 
	public String getGroup() {
		return group;
	}
	
	public void setGroup(String group) {
		this.group = group;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNdbno() {
		return ndbno;
	}
	
	public void setNdbno(String ndbno) {
		this.ndbno = ndbno;
	}

	@Override
	public String toString() {
		StringBuffer productFormatter = new StringBuffer();
		return productFormatter
			.append("Name: ").append(name).append(DELIMETER)
			.append("Group: ").append(group).append(DELIMETER)
			.append("Ndbno: ").append(ndbno).append(DELIMETER)
			.append("Manufacturer: ").append(manufacturer)
			.append(System.lineSeparator()).toString();
	}

}
