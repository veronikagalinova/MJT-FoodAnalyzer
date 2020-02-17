package bg.sofia.uni.fmi.mjt.food.analyzer.dto.report;

import com.google.gson.annotations.SerializedName;

public class Ingredients {
	@SerializedName("desc")
	private String description;

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "Ingredients [" + description + "]";
	}
	
}
