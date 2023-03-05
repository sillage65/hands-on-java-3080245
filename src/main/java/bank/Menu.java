package bank;

import java.util.Scanner;

public class Menu {
  
  private Scanner scanner;
  
  public static void main(String[] args){
    System.out.println(."Welcome to Globe Bank Int.");

    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);
    
    Customer customer = menu.authUser();

    if (customer != null){
      Account account = DataSource.getAccount(customer.getAccountId());
      menu.showMenu(customer, account);
    }




    menu.scanner.close();
  }

  private Customer authUser(){
    System.out.println("Please enter username: ");
    String username = scanner.next();
    System.out.println("Please enter password: ");
    String password = scanner.next();

    Customer customer = null;
    try{
    customer = Authenticator.login(username, password);
    } catch(LoginException e){
      System.out.println("there was an error:" + e.getMessge());
    }

    return customer;
  }

  private void showMenu(Customer customer, Account account){
    int selection = 0;

    while(selection != 4 && customer.isAuthenticated()){
      System.out.println("===================================");
      System.out.println("please select one of the following: ");
      System.out.println("1: Deposit");
      System.out.println("2: Withdraw");
      System.out.println("3: Check Balance");
      System.out.println("4: Exit");
      System.out.println("===================================");

      selection = scanner.nextInt();
      double amount = 0;

      switch(selection){
        case 1:
          System.out.println("How much?");
          amount = scanner.nextDouble();
          account.deposit(amount);
        break;

        case 2:
          System.out.println("How much?");
          amount = scanner.nextDouble();
          account.withdraw(amount);
        break;

        case 3:
          System.out.println("current Balance: " + account.getBalance());
        break;

        case 4:
          Authenticator.logout(customer);
          System.out.println("Thanks for banking with us...see you again...");
        break;

        default:
          System.out.println("invalid option, try again");
          break;
      }


    }
  }


}
