package pl.edu.pw.mini.zpoif.project;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GetLastWeekDate {

    public static String getTodayDate(){
        return formatDateToString(getTodayObject());
    }

    private static LocalDate getTodayObject(){
        return LocalDate.now();
    }

    private static String formatDateToString(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDate.format(formatter);
    }
}
