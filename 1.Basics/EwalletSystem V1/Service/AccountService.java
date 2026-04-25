package Service;

import Model.Account;

public interface AccountService {

    boolean CreateAccount(Account account);
    boolean isAccountExistByUsernameAndPassword(Account account);
}
