package Models;

public class Specializations
{
    /**
     * Enum of the different possible specializations
     * Could be a table in the database but for simplicity, an enum was used
     */
    public enum SpecializationType
    {
        CARDIOLOGY,
        NEUROLOGY,
        PEDIATRICS,
        ONCOLOGY,
        ORTHOPEDICS,
        PSYCHIATRY
    }

    /**
     * Display each option with its assigned id number e.g. 1.CARDIOLOGY
     */
    public static void DisplayOptions()
    {
        System.out.println("Specialization Options");

        for (int i = 0; i < SpecializationType.values().length; i++)
        {
            System.out.println(i+1 + ": " + SpecializationType.values()[i].name());
        }
    }

    /**
     * Shows the amount of specializations
     * @return the total amount of possible specializations
     */
    public static int OptionAmount()
    {
        return  SpecializationType.values().length;
    }

    /**
     * Takes an inputted id and returns specialization as a string
     * @param id the id of the specialization e.g. 1
     * @return the specialization as a string e.g. CARDIOLOGY
     */
    public static String GetSpecializationType(int id)
    {
        return SpecializationType.values()[id - 1].toString();
    }
}
