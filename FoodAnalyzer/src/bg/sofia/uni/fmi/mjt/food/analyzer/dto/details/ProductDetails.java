package bg.sofia.uni.fmi.mjt.food.analyzer.dto.details;

public class ProductDetails {
	private ProductList list;
	
	private static final String DELIMETER = "#";
	
	public static final String NO_RESULT = "No results available!";
	
	
	public ProductDetails(ProductList list) {
		super();
		this.list = list;
	}

	public ProductList getProductList() {
		return list;
	}
	
	public String printDetails() {
		if (list == null) {
			return NO_RESULT;
		}
		
		String foodName = list.getFoodName();
		StringBuffer listDetails = new StringBuffer();
		list.getItem().forEach(product -> listDetails
				.append(foodName + DELIMETER + product));
		return listDetails.toString();
	}

}
