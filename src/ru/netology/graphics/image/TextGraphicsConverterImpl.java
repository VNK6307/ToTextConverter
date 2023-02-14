package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;

public class TextGraphicsConverterImpl implements TextGraphicsConverter {
    private double maxRatio;
    private int maxWidth;
    private int maxHeight;
    private TextColorSchema schema;

    public TextGraphicsConverterImpl() {
        this.schema = new TextColorSchemaImpl("@%#*+=-:,.");
    }

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage originalImage = ImageIO.read(new URL(url));

        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        double ratio = (double) width / height;
        if (ratio > maxRatio || (1 / ratio) > maxRatio) {
            throw new BadImageSizeException(ratio, maxRatio);
        }

        double kWidth = (double) width / maxWidth;
        double kHeight = (double) height / maxHeight;
        double k1 = Math.max(kWidth, kHeight);
        double k = Math.max(1, k1);
        int newWidth = (int) (width / k);
        int newHeight = (int) (height / k);

        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics2D = bwImage.createGraphics();
        graphics2D.drawImage(scaledImage, 0, 0, null);
        WritableRaster bwRaster = bwImage.getRaster();

        char[][] symbolsPicture = new char[newHeight][newWidth];
        for (int h = 0; h < newHeight; h++) {
            for (int w = 0; w < newWidth; w++) {
                int dencity = bwRaster.getPixel(w, h, new int[3])[0];
                symbolsPicture[h][w] = schema.convert(dencity);
            }
        }
        StringBuilder stringPicture = new StringBuilder();
        for (int h = 0; h < newHeight; h++) {
            for (int w = 0; w < newWidth; w++) {
                stringPicture
                        .append(symbolsPicture[h][w])
                        .append(symbolsPicture[h][w]);
            }
            stringPicture.append("\n");
        }
        return stringPicture.toString();
    }

    @Override
    public void setMaxWidth(int width) {
        this.maxWidth = width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.maxHeight = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;
    }
}
