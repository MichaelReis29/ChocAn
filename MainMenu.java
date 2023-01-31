package cs200.team16.chocan;

import java.time.LocalDateTime;
import java.util.Scanner;

public class MainMenu {

  public static void main(String[] args)
  {
    // Any other methods/objects which need to be created at the start of the program can be placed here

    // FOR TESTING: add a default Manager named Admin with a user ID of 1 and a pin of 1111; for testing user authentication
	Address address1 = new Address("742 Evergreen Ter", "Springfield", "Pennsylvania", "19064");
	ManagerRecord admin = new ManagerRecord("Ad Min", 1111, address1, 1111);
    RecordController.addManager(admin);

    Address address2 = new Address("1273 Rockefeller St", "Waycross", "Georgia", "31501");
    OperatorRecord operator = new OperatorRecord("O. P. Rator", 2222, address2, 2222);
    RecordController.addOperator(operator);

    Address address3 = new Address("727 Magnolia Dr", "Tuscaloosa", "Alabama", "35401");
    ProviderRecord provider1 = new ProviderRecord("Provider One", 3333, address3, 3333);
    RecordController.addProvider(provider1);
    
    Address address5 = new Address("123 Sesame St", "Tuscaloosa", "Alabama", "35401");
    ProviderRecord provider2 = new ProviderRecord("Pro Vider", 5555, address5, 5555);
    RecordController.addProvider(provider2);
    
    Address address4 = new Address("1 Hacker Way", "Menlo Park", "California", "94025");
    MemberRecord member1 = new MemberRecord("Pencil Deer", 4444, address4);
    RecordController.addMember(member1);
    
    Address address6 = new Address("2 Tired Way", "Forthisville", "Florida", "12345");
    MemberRecord member2 = new MemberRecord("Hello World", 6666, address6);
    RecordController.addMember(member2);
    
    ServiceRecord service1 = new ServiceRecord(LocalDateTime.now(), LocalDateTime.now(), provider1, member1, ProviderDirectory.searchByCode("4001"), "Placeholder");
    RecordController.addServiceRecord(service1);
    ServiceRecord service2 = new ServiceRecord(LocalDateTime.now(), LocalDateTime.now(), provider1, member2, ProviderDirectory.searchByCode("4002"), "Placeholder");
    RecordController.addServiceRecord(service2);
    ServiceRecord service3 = new ServiceRecord(LocalDateTime.now(), LocalDateTime.now(), provider2, member1, ProviderDirectory.searchByCode("5001"), "Placeholder");
    RecordController.addServiceRecord(service3);
    ServiceRecord service4 = new ServiceRecord(LocalDateTime.now(), LocalDateTime.now(), provider2, member2, ProviderDirectory.searchByCode("5001"), "Placeholder");
    RecordController.addServiceRecord(service4);

    // Instantiate new objects
    Terminal terminal;

    Scanner scan = new Scanner(System.in);

    while(true)
    {   
        System.out.println("Main Menu");
        System.out.println("-----------------");

        System.out.println("Select an option: ");
        System.out.println("0): Login as Manager");
        System.out.println("1): Login as Operator");
        System.out.println("2): Login as Provider");
        System.out.println("3): Run Main Accounting Procedure");
        System.out.println("4): Exit");

        int userOption;
        try
        {
            userOption = Integer.valueOf(scan.nextLine());
        }
        catch(Exception e)
        {
            System.out.println("Error: Please enter a valid integer");
            continue;
        }

        // Assign the correct terminal object based on the argument for the program
        if (userOption == 0)
        {
            // Setup ManagerTerminal
            terminal = new ManagerTerminal(scan);
        }
        else if (userOption == 1)
        {
            // Setup OperatorTerminal
            terminal = new OperatorTerminal(scan);
        }
        else if (userOption == 2)
        {
            // Setup ProviderTerminal
            terminal = new ProviderTerminal(scan);
        }
        else if (userOption == 3)
        {
            // Run main accounting procedure
        	System.out.println("Running main accounting procedure...");
        	ReportController.runMainAccountingProcedure();
        	System.out.println("Done, all reports can be found in the 'reports' folder.");
        }
        else if (userOption == 4)
        {
            System.out.println("Exiting");
            break;
        }
        else
        {
            System.out.println("Error: Please enter a valid option");
            scan.nextLine();
            continue;
        }
    }
    scan.close();

  }

}
