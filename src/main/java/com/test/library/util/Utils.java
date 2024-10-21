package com.test.library.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class Utils {
    public static LocalDate parseDateFromString(String date){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            return LocalDate.parse(date, formatter);
        }catch (Exception ex){
            return null;
        }
    }
}
