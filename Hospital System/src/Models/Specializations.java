package Models;

public class Specializations
{
    public enum SpecializationType
    {
        CARDIOLOGY,
        NEUROLOGY,
        PEDIATRICS,
        ONCOLOGY,
        ORTHOPEDICS,
        PSYCHIATRY
    }

    public static void DisplayOptions()
    {
        System.out.println("Specialization Options");

        for (int i = 0; i < SpecializationType.values().length; i++)
        {
            System.out.println(i+1 + ": " + SpecializationType.values()[i].name());
        }
    }

    public static int OptionAmount()
    {
        return  SpecializationType.values().length;
    }

    public static String GetSpecializationType(int choice)
    {
        return SpecializationType.values()[choice - 1].toString();
    }
}
