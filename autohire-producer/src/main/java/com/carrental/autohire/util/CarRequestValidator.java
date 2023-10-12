package com.carrental.autohire.util;

import com.carrental.autohire.dto.CarRequestDto;

import java.lang.reflect.Field;

public class CarRequestValidator {

        public static boolean isValidCarRequestDto(CarRequestDto dto) throws IllegalAccessException {
            Field[] fields = dto.getClass().getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);

                // Check if field is manufacturer or model
                if (field.getName().equals("manufacturer") || field.getName().equals("model")) {
                    String value = (String) field.get(dto);
                    if (value == null || value.isEmpty()) {
                        return false;
                    }
                }

                // Check if field is year
                if (field.getName().equals("year")) {
                    int value = field.getInt(dto);
                    if (value == 0) {
                        return false;
                    }
                }
            }

            return true;
        }

}
