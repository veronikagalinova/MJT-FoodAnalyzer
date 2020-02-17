package bg.sofia.uni.fmi.mjt.food.analyzer.server;

import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.BeforeClass;
import org.junit.Test;

public final class ClientRequestHandlerTest {
	
	private static ClientRequestHandler handler;
	
	private static final String PRODUCT_NAME = "raffaello";
	
	@BeforeClass
	public static void setup() {
		Socket clientSocket = mock(Socket.class);
		handler = new ClientRequestHandler(clientSocket);
	}
	
	@Test
	public void test() {
		String actual = handler.retrieveProductDetails(PRODUCT_NAME);
		String expected = "Name: RAFFAELLO, ALMOND COCONUT TREAT, UPC: 009800146130/"
				+ "Group: Branded Food Products Database/Ndbno: 45142036/"
				+ "Manufacturer: Ferrero U.S.A., Incorporated"; 
		assertEquals(PRODUCT_NAME + "#" + expected,actual.trim());
		
		String requestUPC = "get-food-by-barcode --upc=009800146130";
		assertEquals(expected,handler.getProductWithUPC(requestUPC).trim());
		
	}

}
