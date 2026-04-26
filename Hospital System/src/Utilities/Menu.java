package Utilities;

import BusinessLogic.*;
import DataAccess.ConflictDataAccess;
import Models.Visit;

public class Menu
{
    /**
     * Menu to show all options
     */
    public static void MainMenu()
    {
        do
        {
            System.out.println("---Hospital Management System---");

            System.out.println("0. Exit");
            System.out.println("1. Manage Patients");
            System.out.println("2. Manage Doctors");
            System.out.println("3. Manage Prescriptions");
            System.out.println("4. Manage Drugs");
            System.out.println("5. Manage Visits");
            System.out.println("6. Manage Insurance");
            System.out.println("7. Manage Conflicts");

            int option = Input.GetInt("Enter an option (0-6): ");

            switch(option)
            {
                case 0:
                    System.out.println("Goodbye!");
                    System.exit(0);
                case 1:
                    ManagePatients();
                    break;
                case 2:
                    ManageDoctors();
                    break;
                case 3:
                    ManagePrescriptions();
                    break;
                case 4:
                    ManageDrugs();
                    break;
                case 5:
                    ManageVisits();
                    break;
                case 6:
                    ManageInsurance();
                    break;
                case 7:
                    ManageConflicts();
                    break;
            }

        }
        while(true);
    }

    /**
     * Menu to show all patient options
     */
    private static void ManagePatients()
    {
        do
        {
            System.out.println("---Manage Patients---");
            System.out.println();

            System.out.println("0. Exit");
            System.out.println("1. Add Patient");
            System.out.println("2. Update Patient");
            System.out.println("3. Delete Patient");
            System.out.println("4. View All Patients");
            System.out.println("5. View Patient by ID");
            System.out.println("6. View Patient by: Firstname, Surname");

            int option = Input.GetInt("Enter an option (0-6): ");

            switch(option)
            {
                case 0:
                    MainMenu();
                    return;
                case 1:
                    PatientBusinessLogic.AddPatient();
                    break;
                case 2:
                    PatientBusinessLogic.UpdatePatient();
                    break;
                case 3:
                    PatientBusinessLogic.DeletePatient();
                    break;
                case 4:
                    PatientBusinessLogic.LoadAllPatients();
                    break;
                case 5:
                    PatientBusinessLogic.LoadPatientById();
                    break;
                case 6:
                    PatientBusinessLogic.LoadPatientByParameters();
                    break;
            }
        }
        while(true);
    }

    /**
     * Menu to show all doctor options
     */
    private static void ManageDoctors()
    {
        do
        {
            System.out.println("---Manage Doctors---");
            System.out.println();

            System.out.println("0. Exit");
            System.out.println("1. Add Doctor");
            System.out.println("2. Update Doctor");
            System.out.println("3. Delete Doctor");
            System.out.println("4. View All Doctors");
            System.out.println("5. View Doctor by ID");
            System.out.println("6. View Doctor by: Firstname, Surname");
            System.out.println("7. View Doctor by: Speciality.");

            int option = Input.GetInt("Enter an option (0-7): ");

            switch(option)
            {
                case 0:
                    MainMenu();
                    return;
                case 1:
                    DoctorBusinessLogic.AddDoctor();
                    break;
                case 2:
                    DoctorBusinessLogic.UpdateDoctor();
                    break;
                case 3:
                    DoctorBusinessLogic.DeleteDoctor();
                    break;
                case 4:
                    DoctorBusinessLogic.LoadAllDoctors();
                    break;
                case 5:
                    DoctorBusinessLogic.LoadDoctorById();
                    break;
                case 6:
                    DoctorBusinessLogic.LoadDoctorByParameters();
                    break;
                case 7:
                    DoctorBusinessLogic.LoadDoctorBySpeciality();
                    break;
            }
        }
        while(true);
    }

    /**
     * Menu to show all prescription options
     */
    private static void ManagePrescriptions()
    {
        do
        {
            System.out.println("---Manage Prescriptions---");
            System.out.println();

            System.out.println("0. Exit");
            System.out.println("1. Add Prescription");
            System.out.println("2. Update Prescription");
            System.out.println("3. Delete Prescription");
            System.out.println("4. View All Prescriptions");
            System.out.println("5. View Prescription by ID");
            System.out.println("6. View Prescription by: Patients name, Doctors name");

            int option = Input.GetInt("Enter an option (0-6): ");

            switch(option)
            {
                case 0:
                    MainMenu();
                    return;
                case 1:
                    PrescriptionBusinessLogic.AddPrescription();
                    break;
                case 2:
                    PrescriptionBusinessLogic.UpdatePrescription();
                    break;
                case 3:
                    PrescriptionBusinessLogic.DeletePrescription();
                    break;
                case 4:
                    PrescriptionBusinessLogic.LoadAllPrescriptions();
                    break;
                case 5:
                    PrescriptionBusinessLogic.LoadPrescriptionById();
                    break;
                case 6:
                    PrescriptionBusinessLogic.LoadPrescriptionByParameters();
                    break;
            }
        }
        while(true);
    }

    /**
     * Menu to show all drug options
     */
    private static void ManageDrugs()
    {
        do
        {
            System.out.println("---Manage Drugs---");
            System.out.println();

            System.out.println("0. Exit");
            System.out.println("1. Add Drugs");
            System.out.println("2. Update Drugs");
            System.out.println("3. Delete Drugs");
            System.out.println("4. View All Drugs");
            System.out.println("5. View Drug by ID");
            System.out.println("6. View Drug by: Drug name");

            int option = Input.GetInt("Enter an option (0-6): ");

            switch(option)
            {
                case 0:
                    MainMenu();
                    return;
                case 1:
                    DrugBusinessLogic.AddDrug();
                    break;
                case 2:
                    DrugBusinessLogic.UpdateDrug();
                    break;
                case 3:
                    DrugBusinessLogic.DeleteDrug();
                    break;
                case 4:
                    DrugBusinessLogic.LoadAllDrugs();
                    break;
                case 5:
                    DrugBusinessLogic.LoadDrugById();
                    break;
                case 6:
                    DrugBusinessLogic.LoadDrugByParameters();
                    break;
            }
        }
        while(true);
    }

    /**
     * Menu to show all conflicts options
     */
    private static void ManageConflicts()
    {
        do
        {
            System.out.println("\n\n\n---Manage Conflicts---");
            System.out.println();

            System.out.println("0. Exit");
            System.out.println("1. Add Conflict");
            System.out.println("2. Delete Conflict");
            System.out.println("3. View Conflicts by Drug id");
            int option = Input.GetInt("Enter an option (0-3): ");

            switch(option)
            {
                case 0:
                    MainMenu();
                    return;
                case 1:
                    ConflictBusinessLogic.addConflict();
                    break;
                case 2:
                    ConflictBusinessLogic.deleteConflict();
                    break;
                case 3:
                    ConflictBusinessLogic.viewConflictByDrugID();
                    break;
            }
        }
        while(true);
    }

    /**
     * Menu to show all visit options
     */
    private static void ManageVisits()
    {
        do
        {
            System.out.println("---Manage Visits---");
            System.out.println();

            System.out.println("0. Exit");
            System.out.println("1. Add Visit");
            System.out.println("2. Update Visit");
            System.out.println("3. Delete Visit");
            System.out.println("4. View All Visit");
            System.out.println("5. View Visit by ID");
            System.out.println("6. View Visit by: Patient name, Doctor name");

            int option = Input.GetInt("Enter an option (0-6): ");

            switch(option)
            {
                case 0:
                    MainMenu();
                    return;
                case 1:
                    VisitBusinessLogic.AddVisit();
                    break;
                case 2:
                    VisitBusinessLogic.UpdateVisit();
                    break;
                case 3:
                    VisitBusinessLogic.DeleteVisit();
                    break;
                case 4:
                    VisitBusinessLogic.LoadAllVisits();
                    break;
                case 5:
                    VisitBusinessLogic.LoadVisitById();
                    break;
                case 6:
                    VisitBusinessLogic.LoadVisitByParameters();
                    break;
            }
        }
        while(true);
    }

    /**
     * Menu to show all insurance options
     */
    private static void ManageInsurance()
    {
        do
        {
            System.out.println("---Manage Insurance---");
            System.out.println();

            System.out.println("0. Exit");
            System.out.println("1. Add Insurance");
            System.out.println("2. Update Insurance");
            System.out.println("3. Delete Insurance");
            System.out.println("4. View All Insurance");
            System.out.println("5. View Insurance by ID");
            System.out.println("6. View Insurance by: Company name");

            int option = Input.GetInt("Enter an option (0-6): ");

            switch(option)
            {
                case 0:
                    MainMenu();
                    return;
                case 1:
                    InsuranceBusinessLogic.AddInsurance();
                    break;
                case 2:
                    InsuranceBusinessLogic.UpdateInsurance();
                    break;
                case 3:
                    InsuranceBusinessLogic.DeleteInsurance();
                    break;
                case 4:
                    InsuranceBusinessLogic.LoadAllInsurance();
                    break;
                case 5:
                    InsuranceBusinessLogic.LoadInsuranceById();
                    break;
                case 6:
                    InsuranceBusinessLogic.LoadInsuranceByParameters();
                    break;
            }
        }
        while(true);
    }
}
