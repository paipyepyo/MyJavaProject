package util;

import java.lang.classfile.constantpool.DoubleEntry;
import java.util.Scanner;

// Double , Long , Short , Byte , Float , Integer
public class InputControl {
    private static Scanner scanner = new Scanner(System.in);

    // firstTimeText は 初めて入力要求する際に表示する
    // errorMessage は テキストが入った時、再要求する際に表示する
    // type は 安全にキャストして返すため使う
    public static <T extends Number> T getNumNoText(String firstTimeText, String errorMessage, Class<T> type) {
        boolean isALetter = false;
        Printer.print(firstTimeText);
        String input = scanner.nextLine();

        // それぞれに対応するオブジェクトで返す
        do {
            if (isALetter) {
                Printer.print(errorMessage);
                input = scanner.nextLine();

            }
            try {

                if (type == Integer.class) {
                    return type.cast(Integer.parseInt(input));
                }

                if (type == Double.class) {
                    return type.cast(Double.parseDouble(input));
                }

                if (type == Long.class) {
                    return type.cast(Long.parseLong(input));
                }

                if (type == Float.class) {
                    return type.cast(Float.parseFloat(input));
                }

                if (type == Short.class) {
                    return type.cast(Short.parseShort(input));
                }

                if (type == Byte.class) {
                    return type.cast(Byte.parseByte(input));
                }
            } catch (NumberFormatException e) {
                isALetter = true;
            }
        } while (isALetter);

        return null;

    }

    public static void close() {
        scanner.close();
    }

}
