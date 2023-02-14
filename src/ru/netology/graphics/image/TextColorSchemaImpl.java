package ru.netology.graphics.image;

public class TextColorSchemaImpl implements TextColorSchema {
    private String SCHEMA;

    public TextColorSchemaImpl(String schema) {
        this.SCHEMA = schema;
    }

    @Override
    public char convert(int color) {
        int colorValue = (int) Math.round((SCHEMA.length() - 1) / 255.0 * color);
        if (colorValue > SCHEMA.length()) {
            colorValue = SCHEMA.length() - 1;
        }
        return SCHEMA.charAt(colorValue);
    }
}