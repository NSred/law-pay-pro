package rs.wepublishlaws.cryptoservice.domain.service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.wepublishlaws.cryptoservice.domain.configuration.QRCodeConfig;
import rs.wepublishlaws.cryptoservice.domain.exception.QRCodeCreationException;

@Service
public class QRCodeGeneratorService {

    public static final String IMAGE_FORMAT = "PNG";
    private static final Logger LOGGER = LogManager.getLogger(QRCodeGeneratorService.class);
    private final QRCodeWriter qrCodeWriter;
    private final int imageWidth;
    private final int imageHeight;

    @Autowired
    public QRCodeGeneratorService(final QRCodeConfig config) {
        this.qrCodeWriter = new QRCodeWriter();
        this.imageHeight = config.height();
        this.imageWidth = config.width();
    }

    public String generateQRCodeImage(final String address) {
        final BitMatrix bitMatrix = getBitMatrix(address);
        final ByteArrayOutputStream pngOutputStream = getPngOutputStream(bitMatrix);

        return Base64.getEncoder().encodeToString(pngOutputStream.toByteArray());
    }

    private BitMatrix getBitMatrix(final String address) {
        try {
            String bitcoinURI = "bitcoin:" + address;
            return qrCodeWriter.encode(bitcoinURI, BarcodeFormat.QR_CODE, imageWidth, imageHeight);
        } catch (final WriterException exception) {
            throw new QRCodeCreationException(String.format("Unable to encode payload %s to QR Code image matrix", address), exception);
        }
    }

    private ByteArrayOutputStream getPngOutputStream(final BitMatrix bitMatrix) {
        try {
            final ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, IMAGE_FORMAT, pngOutputStream);
            return pngOutputStream;
        } catch (final IOException exception) {
            throw new QRCodeCreationException("Unable to write to stream qr code matrix", exception);
        }
    }
}
