package com.gestion.gestionrh.model;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class QrCodeGenerator {

    public void generateQrCode(String number) throws Exception {

        String basePath = "src/main/resources/qrcode/";

        File directory = new File(basePath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filePath = basePath + number + ".png";

        BitMatrix matrix = new MultiFormatWriter()
                .encode(number, BarcodeFormat.QR_CODE, 300, 300);

        Path path = Paths.get(filePath);

        MatrixToImageWriter.writeToPath(matrix, "PNG", path);

        System.out.println("QR Code généré : " + filePath);
    }
}
