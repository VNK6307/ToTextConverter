package ru.netology.graphics;

import ru.netology.graphics.image.TextColorSchema;
import ru.netology.graphics.image.TextColorSchemaImpl;
import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.image.TextGraphicsConverterImpl;
import ru.netology.graphics.server.GServer;

public class Main {
    public static void main(String[] args) throws Exception {
        TextGraphicsConverter converter = new TextGraphicsConverterImpl(); // Создайте тут объект вашего класса конвертера

        GServer server = new GServer(converter); // Создаём объект сервера
        server.start(); // Запускаем

//         Или то же, но с выводом на экран:
//        String url = "https://raw.githubusercontent.com/netology-code/java-diplom/main/pics/simple-test.png";
//        String url = "https://i.ibb.co/6DYM05G/edu0.jpg";
//        converter.setMaxHeight(100);
//        converter.setMaxWidth(100);
//        converter.setMaxRatio(4);
//
//        TextColorSchema reverseSchema = new TextColorSchemaImpl(".,:-=+*#%@");
//        String imgTxt = converter.convert(url);
//        System.out.println(imgTxt);
//
//        converter.setTextColorSchema(reverseSchema);
//        String imgTxt1 = converter.convert(url);
//        System.out.println(imgTxt1);
    }
}
