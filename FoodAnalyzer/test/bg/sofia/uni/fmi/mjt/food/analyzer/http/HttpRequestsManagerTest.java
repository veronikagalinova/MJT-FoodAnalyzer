package bg.sofia.uni.fmi.mjt.food.analyzer.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import bg.sofia.uni.fmi.mjt.food.analyzer.dto.details.ProductDetails;
import bg.sofia.uni.fmi.mjt.food.analyzer.dto.report.Report;

public final class HttpRequestsManagerTest {
	
	private static final String FOOD_TO_SEARCH = "raffaello";
	
	private static final int RESULT_LIST_SIZE = 1;
	
	private static final String RAFFAELLO_NDBNO = "45142036";
	
	private static final String RAFFAELLO_DESCRIPTION = "RAFFAELLO, ALMOND COCONUT TREAT, UPC: 009800146130";
	
	@Test
	public void testGetFoodDetails() {
		ProductDetails raffaelloDetails = HttpRequestsManager.getFoodDetails(FOOD_TO_SEARCH);
		assertNotNull(raffaelloDetails);
		assertEquals(RESULT_LIST_SIZE,raffaelloDetails.getProductList().getItem().size());
	}
	
	@Test
	public void testGetFoodReport() {
		Report report = HttpRequestsManager.getFoodReport(RAFFAELLO_NDBNO);
		assertNotNull(report);
		assertTrue(report.getFoods().get(0).getFood().getDesc().getName().equals(RAFFAELLO_DESCRIPTION));
	}

}
