package AccountsMS.AccountsMS.services.interfaces;

import AccountsMS.AccountsMS.models.Account;

import java.util.List;

public interface AccountService {
    Account addAccount (Account account);
    List<Account> getAllAccounts();
}
