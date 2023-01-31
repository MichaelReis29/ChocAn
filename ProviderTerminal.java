package cs200.team16.chocan;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class ProviderTerminal extends Terminal
{
    public ProviderTerminal(Scanner scan)
    {
        this.scan = scan;
        displayMessage("Welcome to the Provider Terminal");
        authenticateUser();
    }

    ProviderRecord currentUser;

    /**
     * display prompt to get user input and build a ServiceRecord object
     *
     * @return the resulting ServiceRecord object
     */
    private ServiceRecord promptCreateServiceRecord(){
    	ProviderDirectoryEntry service;
        while(true)
        {
            String serviceCode = readUserInput("Enter the service code: ");
            service = ProviderController.getProviderDirectoryEntry(serviceCode);
            if (service == null)
            {
                displayMessage("This service code was invalid.");
                return null;
            }
            displayMessage("Service: " + service.getServiceName());
            if (!readUserBool("Is this the correct service? [y/n]"))
            {
                continue;
            }
            break;
        }

        displayMessage("Enter the date and time of the service: ");
        LocalDateTime timeOfService = readDateAndTime();

        int userID = readUserInt("Enter the member ID: ");
        String comments = readUserInput("Enter any additional comments: ");

        LocalDateTime currentTime = LocalDateTime.now();
        return new ServiceRecord(currentTime, timeOfService, currentUser, RecordController.getMember(userID), service, comments);
    }

    public void authenticateUser() 
    {
        // validate the new user
        int accountNum;
        int pin;
        ProviderRecord user;

        // while the user is not valid (i.e. the user is null)
        while(true)
        {
            accountNum = readUserInt("Enter your account number: ");
            pin = readUserInt("Enter your PIN number: ");
            user = RecordController.getProvider(accountNum);

            if(user == null || !(user.isPin(pin)))
            {
                displayMessage("Error: The Username and PIN combination is incorrect. Please try again.\n");
                continue;
            }
            break;
        }
        currentUser = user;

        // welcome user and setup terminal environment
        displayMessage("\nSucessfully authenticated Provider");
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
        String[] menuOptions = {"Authenticate Member", "Bill ChocAn", "Request Provider Directory"};
        String[] menuMethods =  {"authenticateMember", "billChocAn","requestProviderDirectory"};

        System.out.println("Provider Terminal");
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
            ProviderTerminal.class.getMethod(methods[userOptionInt]).invoke(this);
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
            ProviderTerminal.class.getMethod(methods[userOptionInt]).invoke(this);
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

    public void authenticateMember()
    {
        int memberId = readUserInt("Enter the member ID: ");

        MemberRecord member = RecordController.getMember(memberId);
        if (member == null)
        {
            displayMessage("This user does not exist.");
            return;
        }
        displayMessage("This user is valid.");
        displayMessage("Member name: " + member.getName());
        displayMessage("Status: " + member.getStatus());
    }
    public void billChocAn()
    {
        // get user input to build ServiceRecord object
        ServiceRecord newServiceRecord = promptCreateServiceRecord();
        if (newServiceRecord != null) {
        	RecordController.addServiceRecord(newServiceRecord);
        }
        returnToMainMenu();
    }

    public LocalDateTime readDateAndTime()
    {
        LocalDate dateRead;
        LocalTime timeRead;
        while(true)
        {
            String dateString = readUserInput("Format as MM/DD/YYYY");
            String[] date = dateString.split("/",0);
            if (date.length != 3)
            {
                displayMessage("The date format is invalid. Please try again.");
                continue;
            }
            try
            {  
                dateRead = LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[0]), Integer.parseInt(date[1]));
            }
            catch(Exception e)
            {
                displayMessage("The date format is invalid. Please try again.");
                continue;
            }
            break;
        }
        while(true)
        {
            String timeString = readUserInput("Format in military time as HH:MM");
            String[] time = timeString.split(":",0);
            if (time.length != 2)
            {
                displayMessage("The time format is invalid. Please try again.");
                continue;
            }
            try
            {
                timeRead = LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1]), 0);
            }
            catch(Exception e)
            {
                displayMessage("The time format is invalid. Please try again.");
                continue;
            }
            break;
        }
        return LocalDateTime.of(dateRead, timeRead);
    }

    public void requestProviderDirectory()
    {
        displayMessage("Requesting Provider Directory...");
        displayMessage(ProviderController.requestProviderDirectory());
    }

}
