package Utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Input
{
    /**
     * A function to make sure that values of any type are not null
     * @param value the value to be checked
     * @return The value that was checked if it's not null or empty
     */
    public static <T> T RequireNonNull(T value)
    {
        if(value == null)
        {
            throw new IllegalArgumentException("Value is null.");
        }
        if(value == "")
        {
            throw new IllegalArgumentException("Value is empty.");
        }

        return value;
    }

    /**
     * A function to get a string value from the user
     * @param message A custom prompt to ask user
     * @return The value the user provided
     */
    public static String GetString(String message)
    {
        Scanner input = new Scanner(System.in);
        String value = "";

        //Keep asking for value if its null or empty
        do
        {
            System.out.print(message);
            value = input.nextLine();
        }
        while (value == null || value.trim().isEmpty());

        return value;
    }

    /**
     * A function to get an int value from the user
     * @param message A custom prompt to ask user
     * @return The value the user provided
     */
    public static int GetInt(String message)
    {
        Scanner input = new Scanner(System.in);
        String value = "";
        int number = 0;
        boolean isValid = false;

        //Check if the value is valid
        while (!isValid)
        {
            System.out.print(message);
            value = input.nextLine();

            try
            {
                //Attempt to convert the string to an integer
                number = Integer.parseInt(value);
                isValid = true; //If parsing succeeds, we can exit the loop
            }

            catch (NumberFormatException e)
            {
                //If parsing fails, this code runs
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }

        return number;
    }

    /**
     * A function to get a date value from the user
     * @param message A custom prompt to ask user
     * @return The value the user provided
     */
    public static LocalDate GetDate(String message)
    {
        Scanner input = new Scanner(System.in);

        //Define the format to Year-Month-Day
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true)
        {
            System.out.print(message);
            String value = input.nextLine();

            if(value.equals("0"))
            {
                return null;
            }

            if (value.isEmpty())
            {
                continue; //Keep asking if user just hits Enter
            }

            try
            {
                //Attempt to turn the string into a LocalDate
                return LocalDate.parse(value, formatter);
            }
            catch (DateTimeParseException e)
            {
                //This catches typos and wrong formats
                System.out.println("Invalid date format. Please try again using YYYY-MM-DD.");
            }
        }
    }

    /**
     * A function to get a yes or no value from the user
     * @param message A custom prompt to ask user
     * @return The value the user provided
     */
    public static boolean YesNo(String message)
    {
        //Keep asking for a yes or no value
        do
        {
            String option = GetString(message + "[yes] [no]: ").toLowerCase();

            if(option.equals("yes")){return true;}
            else if(option.equals("no")){return false;}

            System.out.println("Invalid option. Please try again.");
        }
        while(true);
    }

    /**
     * A function to combine 2 values
     * @param firstString the first string to combine
     * @param secondString the second string to combine
     * @return The combined values
     */
    public static String combineStrings(String firstString,  String secondString)
    {
        return (firstString +  " " + secondString);
    }
}
