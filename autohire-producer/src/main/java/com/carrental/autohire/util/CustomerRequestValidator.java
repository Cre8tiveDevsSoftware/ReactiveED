package com.carrental.autohire.util;

import com.carrental.autohire.dto.BookVehicleDto;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

public class CustomerRequestValidator {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean isValidCustomerRequestDto(BookVehicleDto dto) throws IllegalAccessException {
        Field[] fields = dto.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            // Check if field is firstName or lastName
            if (field.getName().equals("firstName") || field.getName().equals("lastName")) {
                String value = (String) field.get(dto);
                if (value == null || value.isEmpty()) {
                    return false;
                }
            }

            // Check if field is email
            if (field.getName().equals("email")) {
                String value = (String) field.get(dto);
                if (value == null || value.isEmpty() || !isValidEmail(value)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
