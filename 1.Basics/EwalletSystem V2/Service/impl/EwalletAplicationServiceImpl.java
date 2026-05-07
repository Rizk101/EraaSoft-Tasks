package Service.impl;

import Model.Account;
import Service.AccountService;
import Service.AplicationService;
import Service.ValidationService;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class EwalletAplicationServiceImpl implements AplicationService {

    private AccountService accountService = new AccountServiceImpl();
    private ValidationService validationService = new ValidationServiceImpl();

    // shared scanner for user input
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void start() {
        System.out.println("=========================================");
        System.out.println(":      Welcome to E-Wallet App      ");
        System.out.println("=========================================\n");

        int counter = 0;
        boolean exit = false;

        while (true) {
            try {
                System.out.println("pls enter what you need to do ?");
                System.out.println("1.login   2.sign up   3.exit");

                int choose = scanner.nextInt();

                switch (choose) {
                    case 1:
                        login();
                        break;

                    case 2:
                        signup();
                        break;

                    case 3:
                        System.out.println("have a nice day");
                        exit = true;
                        break;

                    default:
                        System.out.println("invalid choose :(");
                        counter++;
                }

                if (exit) {
                    break;
                }

                if (counter == 4) {
                    System.out.println("many invalid times !! pls contact with admin");
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println("invalid input! please enter number only.");
                scanner.nextLine();
                counter++;

                if (counter == 4) {
                    System.out.println("many invalid times !! pls contact with admin");
                    break;
                }

            } catch (Exception e) {
                System.out.println("something went wrong: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private void signup() {
        try {
            System.out.println("pls enter username.");
            String userName = scanner.next();

            while (!validationService.isUserNameValid(userName) || userName.equals("")) {
                System.out.println("invalid username, please enter again.");
                userName = scanner.next();
            }

            System.out.println("pls enter password.");
            String password = scanner.next();

            System.out.println("pls enter phone number.");
            String phoneNumber = scanner.next();

            System.out.println("pls enter age.");
            float age = scanner.nextFloat();

            Account account = new Account(userName, password, phoneNumber, age);
            account = accountService.CreateAccount(account);

            if (Objects.nonNull(account)) {
                System.out.println("Account Created Successfully");
                mainProfile(account);
            } else {
                System.out.println("account failed because there exists account with same username :(");
            }

        } catch (InputMismatchException e) {
            System.out.println("invalid age! please enter a valid number.");
            scanner.nextLine();

        } catch (Exception e) {
            System.out.println("something went wrong while signing up: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void login() {
        try {
            System.out.println("pls enter username.");
            String userName = scanner.next();

            while (!validationService.isUserNameValid(userName) || userName.equals("")) {
                System.out.println("invalid username, please enter again.");
                userName = scanner.next();
            }

            System.out.println("pls enter password.");
            String password = scanner.next();

            while (!validationService.isPasswordValid(password) || password.equals("")) {
                System.out.println("invalid password, please enter again.");
                password = scanner.next();
            }

            Account account = new Account(userName, password);
            account = accountService.isAccountExistByUsernameAndPassword(account);

            if (Objects.nonNull(account)) {
                mainProfile(account);
            } else {
                System.out.println("login failed! username or password is incorrect :(");
            }

        } catch (Exception e) {
            System.out.println("something went wrong while logging in: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void mainProfile(Account account) {
        // Loop keeps running until the user logs out or removes their account
        while (true) {
            try {
                System.out.println("\n========= Main Menu =========");
                System.out.println("[1] Deposit");
                System.out.println("[2] Withdraw");
                System.out.println("[3] Transfer");
                System.out.println("[4] Show Profile Details");
                System.out.println("[5] Change Password");
                System.out.println("[6] Remove Account");
                System.out.println("[7] Logout");
                System.out.println("=============================");

                int feature = scanner.nextInt();

                switch (feature) {
                    case 1:
                        deposit(account);
                        break;
                    case 2:
                        withdraw(account);
                        break;
                    case 3:
                        transfer(account);
                        break;
                    case 4:
                        showProfilDetails(account);
                        break;
                    case 5:
                        changePassword(account);
                        break;
                    case 6:
                        boolean removed = removeAccount(account);
                        if (removed) {
                            // Account deleted → go back to login main menu
                            return;
                        }
                        break;
                    case 7:
                        System.out.println("You have been logged out. See you soon!");
                        // Return to the start() login/signup menu
                        return;
                    default:
                        System.out.println("invalid choice, please try again.");
                }

            } catch (InputMismatchException e) {
                System.out.println("invalid input! please enter number only.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("something went wrong: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private void deposit(Account account) {
        try {
            System.out.println("pls enter amount you need to deposit.");
            double amount = scanner.nextDouble();

            Integer depositResult = accountService.deposit(account, amount);

            if (depositResult == 1) {
                System.out.println("Deposit Success :)");
            } else if (depositResult == -1) {
                System.out.println("Deposit failed because [amount must be >= 100]");
            } else if (depositResult == -2) {
                System.out.println("Deposit failed because [amount must be a multiple of 100]");
            } else if (depositResult == -3) {
                System.out.println("Deposit failed because [account not found]");
            }
        } catch (InputMismatchException e) {
            System.out.println("invalid amount! please enter a valid number.");
            scanner.nextLine();
        }
    }

    private void withdraw(Account account) {
        try {
            System.out.println("pls enter amount you need to withdraw.");
            double amount = scanner.nextDouble();

            Integer result = accountService.withdraw(account, amount);

            if (result == 1) {
                System.out.println("Withdraw Success :) | New Balance: " + account.getBalance());
            } else if (result == -1) {
                System.out.println("Withdraw failed because [amount must be > 0 and a multiple of 100]");
            } else if (result == -2) {
                System.out.println("Withdraw failed because [account not found]");
            } else if (result == -3) {
                System.out.println("Withdraw failed because [insufficient balance]");
            }
        } catch (InputMismatchException e) {
            System.out.println("invalid amount! please enter a valid number.");
            scanner.nextLine();
        }
    }

    private void transfer(Account account) {
        try {
            System.out.println("pls enter the username of the recipient.");
            String toUsername = scanner.next();

            System.out.println("pls enter amount you need to transfer.");
            double amount = scanner.nextDouble();

            Integer result = accountService.transfer(account, toUsername, amount);

            if (result == 1) {
                System.out.println("Transfer Success :) | New Balance: " + account.getBalance());
            } else if (result == -1) {
                System.out.println("Transfer failed because [amount must be > 0 and a multiple of 100]");
            } else if (result == -2) {
                System.out.println("Transfer failed because [your account was not found]");
            } else if (result == -3) {
                System.out.println("Transfer failed because [insufficient balance]");
            } else if (result == -4) {
                System.out.println("Transfer failed because [you cannot transfer to yourself]");
            } else if (result == -5) {
                System.out.println("Transfer failed because [recipient account not found]");
            }
        } catch (InputMismatchException e) {
            System.out.println("invalid amount! please enter a valid number.");
            scanner.nextLine();
        }
    }

    private void changePassword(Account account) {
        try {
            System.out.println("pls enter your current password.");
            String oldPassword = scanner.next();

            System.out.println("pls enter your new password.");
            String newPassword = scanner.next();

            // Validate new password format before sending to service
            while (!validationService.isPasswordValid(newPassword)) {
                System.out.println("New password is invalid. It must be >= 8 chars, start with a letter, and contain uppercase, lowercase, digit, and special character.");
                System.out.println("pls enter your new password again.");
                newPassword = scanner.next();
            }

            Integer result = accountService.changePassword(account, oldPassword, newPassword);

            if (result == 1) {
                System.out.println("Password changed successfully :)");
            } else if (result == -1) {
                System.out.println("Change password failed because [old password is incorrect]");
            } else if (result == -2) {
                System.out.println("Change password failed because [new password must be different from old password]");
            } else if (result == -3) {
                System.out.println("Change password failed because [account not found]");
            }
        } catch (Exception e) {
            System.out.println("something went wrong while changing password: " + e.getMessage());
            scanner.nextLine();
        }
    }

    /**
     * Returns true if the account was successfully removed (so mainProfile can exit).
     */
    private boolean removeAccount(Account account) {
        try {
            System.out.println("Are you sure you want to remove your account? (yes/no)");
            String confirm = scanner.next();

            if (!confirm.equalsIgnoreCase("yes")) {
                System.out.println("Account removal cancelled.");
                return false;
            }

            Integer result = accountService.removeAccount(account);

            if (result == 1) {
                System.out.println("Account removed successfully. Goodbye!");
                return true;
            } else if (result == -1) {
                System.out.println("Remove failed because [account not found]");
                return false;
            }
        } catch (Exception e) {
            System.out.println("something went wrong while removing account: " + e.getMessage());
            scanner.nextLine();
        }
        return false;
    }

    private void showProfilDetails(Account account) {
        System.out.println("=========================================");
        System.out.println(":              Account Data              ");
        System.out.println("=========================================\n");

        System.out.println("Username: " + account.getUserName());
        System.out.println("Password: " + account.getPassword());
        System.out.println("Balance: " + account.getBalance());
        System.out.println("Phone Number: " + account.getPhoneNumber());
        System.out.println("Age: " + account.getAge());
    }
}