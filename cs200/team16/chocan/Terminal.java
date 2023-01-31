package cs200.team16.chocan;

import java.util.Scanner;

public abstract class Terminal
{
    Scanner scan;

    public abstract void authenticateUser();

    public abstract boolean returnToMainMenu();

    // Displays a message on the CLI
    public void displayMessage(String message)
    {
        System.out.println(message);
    }

    // Gets user input from CLI, and returns the input as a string
    public String readUserInput(String message)
    {
        displayMessage(message);
        String input = scan.nextLine();
        return input;
    }

    // Gets an integer as user input from CLI  - if the user doesn't enter a valid integer, the method will continually ask the user for an integer
    public int readUserInt(String message)
    {
        while(true)
        {
            try
            {
                int inputNum = Integer.valueOf(readUserInput(message));
                return inputNum;
            }
            catch(Exception e)
            {
                displayMessage("Error: Please enter a valid integer");
            }
        }
        
    }

    public boolean readUserBool(String message)
    {
        while(true)
        {
            try
            {
                String input = readUserInput(message);
                if (input.equals("y")){return true;}
                else if (input.equals("n")){return false;}
                else
                {
                    displayMessage("Error: Please enter either 'y' or 'n'");
                }
            }
            catch(Exception e)
            {
                displayMessage("Error: Please enter either 'y' or 'n'");
            }
        }
    }

    public int selectOption(String[] options, String[] methods)
    {
        // both parameter array lengths should be identical
        assert options.length == methods.length : "selectOptions parameter size mismatch";

        displayMessage("Select an option:");

        for(int i=0; i<options.length; i++)
        {
            displayMessage("" + i + "): " + options[i]);
        }

        int userOptionInt = readUserInt(">>> ");
        if (userOptionInt < 0 || userOptionInt >= options.length)
        {
            displayMessage("Error: option out of range");
            return -1;
        }
        return userOptionInt;
    }

    // let the user select an option. An option to logout is appended to these options
    public int selectOptionWithLogout(String[] options, String[] methods) 
    {
        // both parameter array lengths should be identical
        assert options.length == methods.length : "selectOptions parameter size mismatch";
        
        displayMessage("Select an option:");

        for(int i=0; i<options.length; i++)
        {
            displayMessage("" + i + "): " + options[i]);
        }
        displayMessage("" + options.length + "): Logout");

        int userOptionInt = readUserInt(">>> ");
        if (userOptionInt < 0 || userOptionInt > options.length)
        {
            displayMessage("Error: option out of range");
            return -1;
        }
        return userOptionInt;
    }

}
