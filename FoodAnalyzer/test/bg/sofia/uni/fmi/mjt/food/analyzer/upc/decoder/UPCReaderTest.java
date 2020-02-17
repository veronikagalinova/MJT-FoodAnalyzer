package bg.sofia.uni.fmi.mjt.food.analyzer.upc.decoder;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public final class UPCReaderTest {
	
	private static final String PATH_TO_UPC = "resources/nutella.png";

	private static final String EXPECTED_UPC = "009800895007";
	
	@Test
	public void testDecoder() throws IOException {
	            File file = new File(PATH_TO_UPC);
	            assertEquals(EXPECTED_UPC,UPCReader.decodeQRCode(file)); 
	}

}
