package testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TestHelper {

    public static String currentTimeStamp(){
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Define the desired format pattern with "hh" for 12-hour format and "a" for AM/PM marker
        // Using Locale.ENGLISH ensures the "AM/PM" string is consistently English
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a", Locale.ENGLISH);

        // Format the current date and time
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime;

    }
}
