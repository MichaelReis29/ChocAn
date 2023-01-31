package cs200.team16.chocan;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class ManagerTerminal extends Terminal
{

    public ManagerTerminal(Scanner scan)
    {
        this.scan = scan;
        displayMessage("Welcome to the Manager Terminal");
        authenticateUser();
    }
    

    public void authenticateUser()
    {
        // validate the new user
        int accountNum;
        int pin;
        ManagerRecord user;

        // while the user is not valid (i.e. the user is null)
        while(true)
        {
            accountNum = readUserInt("Enter your account number: ");
            pin = readUserInt("Enter your PIN number: ");
            user = RecordController.getManager(accountNum);

            if(user == null || !(user.isPin(pin)))
            {
                displayMessage("Error: The Username and PIN combination is incorrect. Please try again.\n");
                continue;
            }
            break;
        }

        // welcome user and setup terminal environment
        displayMessage("\nSucessfully authenticated Manager");
        displayMessage("Welcome, " + user.getName() + '\n');

        // run program until closed
        while(true)
        {
            if (!returnToMainMenu())
            {
                break;
            }
        }
        
    }

    public boolean returnToMainMenu() 
    {
        String[] menuOptions = {"Request Member Report", "Request Provider Report", "Request Summary Report", "Request EFT Report"};
        String[] menuMethods =  {"requestMemberReport", "requestProviderReport","requestSummaryReport","requestEFT"};

        System.out.println("Manager Terminal");
        System.out.println("-----------------");
        return executeOptionWithLogout(menuOptions, menuMethods);
    }

    public boolean executeOptionWithLogout(String[] options, String[] methods) 
    {
        int userOptionInt = selectOptionWithLogout(options, methods);

        if (userOptionInt == options.length)
        {
            readUserInput("Logged out. Press enter to continue.");
            return false;
        }

        // invoke the corresponding method
        try
        {
            ManagerTerminal.class.getMethod(methods[userOptionInt]).invoke(this);
        }
        catch(InvocationTargetException e)
        {
            displayMessage("Error: the running process crashed");
            displayMessage(e.getTargetException().toString());
        }
        catch(Exception e)
        {
            displayMessage("Error: the running process crashed");
        }
        readUserInput("Press enter to continue.");
        return true;
    }

    public void executeOption(String[] options, String[] methods) 
    {
        int userOptionInt = selectOption(options, methods);

        // invoke the corresponding method
        try
        {
            ManagerTerminal.class.getMethod(methods[userOptionInt]).invoke(this);
        }
        catch(InvocationTargetException e)
        {
            displayMessage("Error: the running process crashed");
            displayMessage(e.getTargetException().toString());
        }
        catch(Exception e)
        {
            displayMessage("Error: the running process crashed");
        }
    }

    public void requestMemberReport()
    {
        displayMessage("Requesting Member Report...");
        String userID = readUserInput("Enter the Member ID:");
        int userNumber = Integer.valueOf(userID);

        //check that the member exists first
        if (RecordController.getMember(userNumber) != null)
        {
            ReportController.generateMemberReport(userNumber);
            displayMessage("Generated Member Report");
        }
        else
        {
            displayMessage("Error: this member does not exist.");
        }
    }

    public void requestProviderReport()
    {
        displayMessage("Requesting Provider Report...");
        String userID = readUserInput("Enter the Provider ID:");
        int userNumber = Integer.valueOf(userID);

        //check that the member exists first
        if (RecordController.getProvider(userNumber) != null)
        {
            ReportController.generateProviderReport(userNumber);
            displayMessage("Generated Provider Report");
        }
        else
        {
            displayMessage("Error: this provider does not exist.");
        }
    }

    public void requestSummaryReport()
    {
        displayMessage("Requesting Summary Report...");
        ReportController.generateSummaryReport();
        displayMessage("Generated Summary Report");
    }

    public void requestEFT()
    {
        displayMessage("Requesting EFT...");
        String userID = readUserInput("Enter the provider ID:");
        int userNumber = Integer.valueOf(userID);

        //check that the member exists first
        if (RecordController.getProvider(userNumber) != null)
        {
            ReportController.generateEFT(userNumber);
            displayMessage("Generated EFT Report");
        }
        else
        {
            displayMessage("Error: this provider does not exist.");
        }
    }

}
