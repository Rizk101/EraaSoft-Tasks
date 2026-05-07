package Service.impl;

import Model.Account;
import Model.EwalletSystem;
import Service.AccountService;

import java.util.Objects;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {

    private EwalletSystem ewalletSystem = new EwalletSystem();

    @Override
    public Account CreateAccount(Account account) {
        Optional<Account> accountOptional = ewalletSystem.getAccounts()
                .stream()
                .filter(acc -> acc.getUserName().equals(account.getUserName()))
                .findFirst();

        if (accountOptional.isPresent()) {
            return null;
        }

        ewalletSystem.getAccounts().add(account);
        return account;
    }

    @Override
    public Account isAccountExistByUsernameAndPassword(Account account) {
        Optional<Account> accountOptional = ewalletSystem.getAccounts().stream()
                .filter(acc -> acc.getUserName().equals(account.getUserName()) &&
                        acc.getPassword().equals(account.getPassword()))
                .findFirst();

        if (accountOptional.isPresent()) {
            return accountOptional.get();
        }

        return null;
    }

    @Override
    public Integer deposit(Account account, double amount) {
        if (amount < 100) {
            return -1;
        }
        if (amount < 100 || amount % 100 != 0) {
            return -2;
        }

        Account accountExist = ewalletSystem.getAccounts().stream()
                .filter(acc -> acc.getUserName().equals(account.getUserName()) &&
                        acc.getPassword().equals(account.getPassword()))
                .findFirst()
                .orElse(null);

        if (Objects.isNull(accountExist)) {
            return -3;
        }

        double totalBalance = accountExist.getBalance() + amount;
        accountExist.setBalance(totalBalance);

        return 1;
    }

    @Override
    public Integer withdraw(Account account, double amount) {
        // amount must be > 0 and multiple of 100
        if (amount <= 0 || amount % 100 != 0) {
            return -1; // invalid amount
        }

        Account accountExist = ewalletSystem.getAccounts().stream()
                .filter(acc -> acc.getUserName().equals(account.getUserName()) &&
                        acc.getPassword().equals(account.getPassword()))
                .findFirst()
                .orElse(null);

        if (Objects.isNull(accountExist)) {
            return -2; // account not found
        }

        if (accountExist.getBalance() < amount) {
            return -3; // insufficient balance
        }

        accountExist.setBalance(accountExist.getBalance() - amount);
        // sync balance back to the passed-in account object
        account.setBalance(accountExist.getBalance());
        return 1;
    }

    @Override
    public Integer transfer(Account fromAccount, String toUsername, double amount) {
        // amount must be > 0 and multiple of 100
        if (amount <= 0 || amount % 100 != 0) {
            return -1; // invalid amount
        }

        // cannot transfer to yourself
        if (fromAccount.getUserName().equals(toUsername)) {
            return -4; // same account
        }

        Account sender = ewalletSystem.getAccounts().stream()
                .filter(acc -> acc.getUserName().equals(fromAccount.getUserName()) &&
                        acc.getPassword().equals(fromAccount.getPassword()))
                .findFirst()
                .orElse(null);

        if (Objects.isNull(sender)) {
            return -2; // sender not found
        }

        if (sender.getBalance() < amount) {
            return -3; // insufficient balance
        }

        Account receiver = ewalletSystem.getAccounts().stream()
                .filter(acc -> acc.getUserName().equals(toUsername))
                .findFirst()
                .orElse(null);

        if (Objects.isNull(receiver)) {
            return -5; // receiver not found
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);
        // sync balance back to the passed-in account object
        fromAccount.setBalance(sender.getBalance());
        return 1;
    }

    @Override
    public Integer changePassword(Account account, String oldPassword, String newPassword) {
        // old password must match account's current password
        if (!account.getPassword().equals(oldPassword)) {
            return -1; // old password incorrect
        }

        // new password must be different
        if (oldPassword.equals(newPassword)) {
            return -2; // same password
        }

        Account accountExist = ewalletSystem.getAccounts().stream()
                .filter(acc -> acc.getUserName().equals(account.getUserName()) &&
                        acc.getPassword().equals(oldPassword))
                .findFirst()
                .orElse(null);

        if (Objects.isNull(accountExist)) {
            return -3; // account not found
        }

        accountExist.setPassword(newPassword);
        // sync new password back to the passed-in account object so the session stays valid
        account.setPassword(newPassword);
        return 1;
    }

    @Override
    public Integer removeAccount(Account account) {
        Account accountExist = ewalletSystem.getAccounts().stream()
                .filter(acc -> acc.getUserName().equals(account.getUserName()) &&
                        acc.getPassword().equals(account.getPassword()))
                .findFirst()
                .orElse(null);

        if (Objects.isNull(accountExist)) {
            return -1; // account not found
        }

        ewalletSystem.getAccounts().remove(accountExist);
        return 1;
    }
}