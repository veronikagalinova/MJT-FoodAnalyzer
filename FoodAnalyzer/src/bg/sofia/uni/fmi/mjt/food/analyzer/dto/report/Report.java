package bg.sofia.uni.fmi.mjt.food.analyzer.dto.report;

import java.util.Collections;
import java.util.List;


public class Report {
	private List<FoodWrapper> foods;
	
	public Report() {
		super();
	}

	public Report(List<FoodWrapper> foods) {
		this.foods = foods;
	}

	public List<FoodWrapper> getFoods() {
		return Collections.unmodifiableList(foods);
	}
	
}
