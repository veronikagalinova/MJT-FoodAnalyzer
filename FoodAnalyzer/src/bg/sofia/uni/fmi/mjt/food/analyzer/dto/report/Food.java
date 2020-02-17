package bg.sofia.uni.fmi.mjt.food.analyzer.dto.report;

import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.annotations.SerializedName;

public class Food {
	
	private FoodDescription desc;
	
	private List<Nutrient> nutrients;
	
	@SerializedName("ing")
	private Ingredients ingredients;
	
	private static final String ENERGY_FIELD = "Energy";
	
	private static final String PROTEIN_FIELD = "Protein";
	
	private static final String FATS_FIELD = "Total lipid (fat)";
	
	private static final String CARBOHYDRATES_FIELD = "Carbohydrate, by difference";
	
	private static final String FYBERS_FIELD = "Fiber, total dietary";
	
	
	public List<Nutrient> getNutrients() {
		return nutrients.stream().filter(nutrient -> nutrient.getName().equals(ENERGY_FIELD)
				|| nutrient.getName().equals(PROTEIN_FIELD)
				|| nutrient.getName().equals(FATS_FIELD)
				|| nutrient.getName().equals(CARBOHYDRATES_FIELD)
				|| nutrient.getName().equals(FYBERS_FIELD))
			.collect(Collectors.toUnmodifiableList());
	}

	public Ingredients getIngredients() {
		return ingredients;
	}
	
	public void setIng(Ingredients ing) {
		this.ingredients = ing;
	}


	public FoodDescription getDesc() {
		return desc;
	}


	@Override
	public String toString() {
		return "Food [" + desc + ", nutrients=" + getNutrients() + ", ingredients=" + ingredients + "]";
	}
	
}
