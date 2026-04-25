package Service.impl;

import Model.Account;
import Service.AccountService;
import Service.AplicationService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EwalletAplicationServiceImpl implements AplicationService {

    // service responsible for account operations
    private AccountService accountService = new AccountServiceImpl();

    // shared scanner for user input
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void start() {
        System.out.println("hello welcome :)");

        int counter = 0; // count invalid attempts
        boolean exit = false; // control exit from loop

        while (true) {
            try {  // exception handling
                // main menu
                System.out.println("pls enter what you need to do ?");
                System.out.println("1.login   2.sign up   3.exit");

                int choose = scanner.nextInt();

                switch (choose) {
                    case 1:
                        login(); // go to login
                        break;

                    case 2:
                        signup(); // go to signup
                        break;

                    case 3:
                        System.out.println("have a nice day");
                        exit = true;
                        break;

                    default:
                        System.out.println("invalid choose :(");
                        counter++;
                }

                // exit condition
                if (exit) {
                    break;
                }

                // too many invalid tries
                if (counter == 4) {
                    System.out.println("many invalid times !! pls contact with admin");
                    break;
                }

            } catch (InputMismatchException e) {
                // handle wrong input type (e.g. string instead of number)
                System.out.println("invalid input! please enter number only.");
                scanner.nextLine(); // clear invalid input
                counter++;

                if (counter == 4) {
                    System.out.println("many invalid times !! pls contact with admin");
                    break;
                }

            } catch (Exception e) {
                // catch any unexpected error
                System.out.println("something went wrong: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private void signup() {
        try {
            // collect user data
            System.out.println("pls enter username.");
            String userName = scanner.next();

            System.out.println("pls enter password.");
            String password = scanner.next();

            System.out.println("pls enter phone number.");
            String phoneNumber = scanner.next();

            System.out.println("pls enter age.");
            float age = scanner.nextFloat();

            // create account object
            Account account = new Account(userName, phoneNumber, password, age);

            // call service to create account
            boolean isAccountCreated = accountService.CreateAccount(account);

            if (isAccountCreated) {
                mainProfile(); // go to profile
            } else {
                System.out.println("account failed because there exists account with same username :(");
            }

        } catch (InputMismatchException e) {
            // handle invalid age input
            System.out.println("invalid age! please enter a valid number.");
            scanner.nextLine();

        } catch (Exception e) {
            // general error handling
            System.out.println("something went wrong while signing up: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void login() {
        try {
            // get login credentials
            System.out.println("pls enter username.");
            String userName = scanner.next();

            System.out.println("pls enter password.");
            String password = scanner.next();

            // create account object for validation
            Account account = new Account(userName, password);

            // check if account exists
            boolean isAccountExist = accountService.isAccountExistByUsernameAndPassword(account);

            if (isAccountExist) {
                mainProfile(); // login success
            } else {
                System.out.println("login failed! username or password is incorrect :(");
            }

        } catch (Exception e) {
            // general error handling
            System.out.println("something went wrong while logging in: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void mainProfile() {
        // simple profile menu (still needs implementation)
        System.out.println("1.deposit  2.withdraw  3.logout");
    }
}