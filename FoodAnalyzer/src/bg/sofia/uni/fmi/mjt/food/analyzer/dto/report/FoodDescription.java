package bg.sofia.uni.fmi.mjt.food.analyzer.dto.report;

public class FoodDescription {
	
	private String ndbno;
	
	private String name;
	
	
	public FoodDescription(String ndbno, String name) {
		super();
		this.ndbno = ndbno;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getNdbno() {
		return ndbno;
	}

	@Override
	public String toString() {
		return "name=" + name + ", ndbno: " + ndbno;
	}
	
}
