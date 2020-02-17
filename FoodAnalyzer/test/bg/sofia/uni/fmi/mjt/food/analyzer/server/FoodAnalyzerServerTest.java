package bg.sofia.uni.fmi.mjt.food.analyzer.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import bg.sofia.uni.fmi.mjt.food.analyzer.dto.details.Product;
import bg.sofia.uni.fmi.mjt.food.analyzer.dto.details.ProductDetails;
import bg.sofia.uni.fmi.mjt.food.analyzer.dto.details.ProductList;

public final class FoodAnalyzerServerTest {
	
	private static final String PRODUCT_NAME = "DOMINO COOKIE DOUGH, UPC: 033700900035";
	
	private static final String PRODUCT_GROUP = "Fast Foods";
	
	private static final String PRODUCT_NDBNO = "21279";
	
	private static final String PRODUCT_MANUFACTURER = "Domino's Pizza, LLC";
	
	private static final String PRODUCT_UPC = "033700900035";
	
	
	@Test
	public void testServer() {
		
		ProductDetails expected = new ProductDetails(
				new ProductList(List.of(
				new Product(PRODUCT_GROUP,PRODUCT_NAME,PRODUCT_NDBNO,PRODUCT_MANUFACTURER))));
		
		FoodAnalyzerServer.addNewProduct(PRODUCT_NAME,expected);
		
		ProductDetails actual = FoodAnalyzerServer.getProductWithName(PRODUCT_NAME);
		
		assertTrue(actual.equals(expected));
		
		assertEquals(PRODUCT_NAME,FoodAnalyzerServer.getFoodWithBarcode(PRODUCT_UPC).getName());
	}

}
