package Service;

import Model.Account;

public interface AccountService {

    Account CreateAccount(Account account);
    Account isAccountExistByUsernameAndPassword(Account account);
    Integer deposit(Account account, double amount);
    Integer withdraw(Account account, double amount);
    Integer transfer(Account fromAccount, String toUsername, double amount);
    Integer changePassword(Account account, String oldPassword, String newPassword);
    Integer removeAccount(Account account);
}