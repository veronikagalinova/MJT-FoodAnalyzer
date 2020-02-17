package bg.sofia.uni.fmi.mjt.food.analyzer.upc.decoder;

import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class UPCReader {
	
	private UPCReader() {
		// Utility class. Static methods only
	}
	
    public static String decodeQRCode(File qrCodeimage) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(qrCodeimage);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        try {
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (NotFoundException e) {
            System.out.println("There is no QR code in the image");
            return null;
        }
    }
    
}
