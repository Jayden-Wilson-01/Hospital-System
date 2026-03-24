package Utilities;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Input
{
    //A class to validate input data based on values

    public static <T> T RequireNonNull(T value)
    {
        if(value == null)
        {
            throw new IllegalArgumentException("Value is null or empty.");
        }
    }

    //Take a string value and make it a date
    public static LocalDate StringToDate(String value)
    {
        if(value == null || value.isBlank())
        {
            throw new IllegalArgumentException("Date string must not be null or empty.");
        }

        try
        {
            return LocalDate.parse(value);
        }
        catch(DateTimeParseException e)
        {
            throw new IllegalArgumentException("Invalid date format. " + e);
        }
    }
}
