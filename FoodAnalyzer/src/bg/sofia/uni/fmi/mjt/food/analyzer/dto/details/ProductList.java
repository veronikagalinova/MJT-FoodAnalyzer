package bg.sofia.uni.fmi.mjt.food.analyzer.dto.details;

import java.util.Collections;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ProductList {
	@SerializedName("q")
	private String foodName;
	private List<Product> item;
	
	public ProductList(List<Product> item) {
		super();
		this.item = item;
	}
	
	public ProductList(String foodName,List<Product> item) {
		this.foodName = foodName;
		this.item = item;
	}

	public String getFoodName() {
		return foodName;
	}

	public List<Product> getItem() {
		return Collections.unmodifiableList(item);
	}

}
