package bg.sofia.uni.fmi.mjt.food.analyzer.dto.report;

import com.google.gson.annotations.SerializedName;

public class Nutrient {
	@SerializedName("nutrient_id")
	private String nutrientId;
	private String name;
	private String unit;
	private String value;
	
	public String getUnit() {
		return unit;
	}
	
	public String getNutrient() {
		return nutrientId;
	}

	public void setNutrient(String nutrientId) {
		this.nutrientId = nutrientId;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name + ": "   + value + unit;
	}
	
}

