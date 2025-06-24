package org.ecommerce.util;

import java.util.Arrays;

public class Enums {

    public static <E extends Enum<E>> Boolean isInEnum(String value, Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .anyMatch(e -> e.name().equals(value));
    }
}
