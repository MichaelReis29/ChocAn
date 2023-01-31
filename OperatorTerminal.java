package cs200.team16.chocan;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class OperatorTerminal extends Terminal
{

    public OperatorTerminal(Scanner scan)
    {
        this.scan = scan;
        displayMessage("Welcome to the Operator Terminal");
        authenticateUser();
    }

    public void authenticateUser()
    {
        // validate the new user
        int accountNum;
        int pin;
        OperatorRecord user;

        // while the user is not valid (i.e. the user is null)
        while(true)
        {
            accountNum = readUserInt("Enter your account number: ");
            pin = readUserInt("Enter your PIN number: ");
            user = RecordController.getOperator(accountNum);

            if(user == null || !(user.isPin(pin)))
            {
                displayMessage("Error: The Username and PIN combination is incorrect. Please try again.\n");
                continue;
            }
            break;
        }

        // welcome user and setup terminal environment
        displayMessage("\nSucessfully authenticated Operator");
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
        String[] menuOptions = {"Manage Members", "Manage Providers", "Add Members", "Add Providers"};
        String[] menuMethods =  {"manageMember", "manageProvider", "addMember", "addProvider"};

        System.out.println("Operator Terminal");
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
            OperatorTerminal.class.getMethod(methods[userOptionInt]).invoke(this);
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
            OperatorTerminal.class.getMethod(methods[userOptionInt]).invoke(this);
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


    /*
    public boolean executeOption(String[] options, String[] methods, PersonRecord user) 
    {
        int userOptionInt = selectOption(options, methods);

        // invoke the corresponding method
        try
        {
            OperatorTerminal.class.getDeclaredMethod(methods[userOptionInt]).invoke(this, user);
        }
        catch(InvocationTargetException e)
        {
            displayMessage("Error: the running process crashed");
            displayMessage(e.getTargetException().toString());
        }
        catch(NoSuchMethodException e)
        {
            displayMessage("Error: the running process crashed: The invoked method does not exist");
            displayMessage(e.toString());
        }
        catch(IllegalAccessException e)
        {
            displayMessage("Error: the running process crashed: IllegalAccessException");
            displayMessage(e.toString());

        }
        catch(Exception e)
        {
            displayMessage("Error: the running process crashed");
        }
        readUserInput("Press enter to continue.");
        return true;
    }
    */
    MemberRecord selectedMember;
    ProviderRecord selectedProvider;
    PersonRecord selectedUser;
    
    public void manageMember()
    {
        int accountNum = readUserInt("Enter the member's ID number: ");
        MemberRecord member = RecordController.getMember(accountNum);

        if (member == null)
        {
            displayMessage("This user does not exist.");
            return;
        }
        displayMessage("Found member: " + member.getName());
        String[] menuOptions = {"Edit Name", "Edit Account Number", "Edit Address", "Delete Member"};
        String[] menuMethods = {"editName", "editAccountNumber", "editAddress", "deleteMember"};

        selectedUser = member;
        selectedMember = member;
        executeOption(menuOptions, menuMethods);
    }

    public void manageProvider()
    {
        int accountNum = readUserInt("Enter the provider's ID number: ");
        ProviderRecord provider = RecordController.getProvider(accountNum);

        if (provider == null)
        {
            displayMessage("This user does not exist.");
            return;
        }
        displayMessage("Found provider: " + provider.getName());
        String[] menuOptions = {"Edit name", "Edit PIN", "Edit Account Number", "Edit Address", "Delete Provider"};
        String[] menuMethods = {"editName", "editPin", "editAccountNumber", "editAddress", "deleteProvider"};

        selectedUser = provider;
        selectedProvider = provider;
        executeOption(menuOptions, menuMethods);
    }

    public void editName()
    {
        if (!readUserBool("The current user's name is " + selectedUser.getName() + ". Would you like to change it? [y/n]")){return;}

        String newName = readUserInput("Enter the new user's name: ");

        selectedUser.setName(newName);
        displayMessage("The user's name has been updated.");
    }

    public void editPin()
    {
        if (!readUserBool("Are you sure you would like to change this provider's PIN number? [y/n]")){return;}

        int newPin = readUserInt("Enter the new user's PIN: ");

        
        selectedProvider.setPin(newPin);
        displayMessage("The provider's PIN has been updated.");
    }

    public void editAccountNumber()
    {
        if (!readUserBool("The current user's account number is " + selectedUser.getAccountNumber() + ". Would you like to change it? [y/n]")){return;}

        int newAccountNumber = readUserInt("Enter the new user's account number: ");

        selectedUser.setAccountNumber(newAccountNumber);
        displayMessage("The user's account number has been updated.");
    }

    public void deleteMember()
    {
        if (!readUserBool("Are you sure you want to delete the member " + selectedMember.getName() + "? [y/n]")){return;}
        RecordController.deleteMember(selectedMember.getAccountNumber());
        displayMessage("Member successfully deleted.");
    }
    public void deleteProvider()
    {
        if (!readUserBool("Are you sure you want to delete the provider " + selectedProvider.getName() + "? [y/n]")){return;}
        RecordController.deleteProvider(selectedProvider.getAccountNumber());
        displayMessage("Provider successfully deleted.");
    }

    public void addMember()
    {
        int newMemberID;
        while(true)
        {
           newMemberID = readUserInt("Enter the new member's ID number: ");
           if(RecordController.getMember(newMemberID) == null){break;}
           displayMessage("This user ID already exists.");
        } 

        String newMemberName = readUserInput("Enter the new member's name: ");

        Address newMemberAddress = getNewAddress();

        MemberRecord newMember = new MemberRecord(newMemberName, newMemberID, newMemberAddress);

        RecordController.addMember(newMember);
        displayMessage("Successfully added new member. ");
    }

    public void addProvider()
    {
        int newProviderID;
        while(true)
        {
           newProviderID = readUserInt("Enter the new provider's ID number: ");
           if(RecordController.getProvider(newProviderID) == null){break;}
           displayMessage("This user ID already exists.");
        } 

        int newProviderPin = readUserInt("Enter the new provider's PIN number: ");

        String newProviderName = readUserInput("Enter the new provider's name: ");
        
        Address newProviderAddress = getNewAddress();

        ProviderRecord newProvider = new ProviderRecord(newProviderName, newProviderID, newProviderAddress, newProviderPin);

        RecordController.addProvider(newProvider);
        displayMessage("Successfully added new provider. ");
    }

    public Address getNewAddress()
    {
        String newUserStreet = readUserInput("\nEnter the new street address: ");
        String newUserCity = readUserInput("\nEnter the new city: ");
        String newUserState = readUserInput("\nEnter the new state: ");
        String newUserZip = readUserInput("\nEnter the new zipcode: ");

        return new Address(newUserStreet, newUserCity, newUserState, newUserZip);
    }

    public void editAddress()
    {
        if (!readUserBool("The current address is: \n" + selectedUser.getAddress().toString() + "\nWould you like to change it? [y/n]"))
        {
            return;
        }

        Address newAddress = getNewAddress();
        selectedUser.setAddress(newAddress);
        displayMessage("The user's address has been updated.");
    }

}
