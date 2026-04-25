package Service.impl;

import Model.Account;
import Model.EwalletSystem;
import Service.AccountService;

public class AccountServiceImpl implements AccountService {

    // Create an instance of the system that holds all accounts
    private EwalletSystem ewalletSystem = new EwalletSystem();

    @Override
    public boolean CreateAccount(Account account) {
        // Check if an account with the same username already exists
        boolean isAccountExist = ewalletSystem.getAccounts()
                .stream()
                .anyMatch(acc -> acc.getUserName().equals(account.getUserName()));

        // Add the new account to the system
        ewalletSystem.getAccounts().add(account);

        // If account already exists, return false
        if(isAccountExist){
            return false;
        }

        // If account is new, return true
        return true;
    }

    @Override
    public boolean isAccountExistByUsernameAndPassword(Account account) {
        // Check if there is an account with matching username and password
        boolean isAccountExist = ewalletSystem.getAccounts()
                .stream()
                .anyMatch(acc -> acc.getUserName().equals(account.getUserName())
                        && acc.getPassword().equals(account.getPassword()));

        // (Likely a mistake) Adds the account even when just checking
        ewalletSystem.getAccounts().add(account);

        // Return true if account exists
        if(isAccountExist){
            return true;
        }

        // Return false if account does not exist
        return false;
    }
}