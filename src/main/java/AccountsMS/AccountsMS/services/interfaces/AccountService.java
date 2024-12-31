package AccountsMS.AccountsMS.services.interfaces;

import AccountsMS.AccountsMS.models.Account;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    Account addAccount (Account account);
    List<Account> getAllAccounts();
    Account getAccountById(Long accountId);

    public void updateAccount(Long accountId, Account account) throws AccountNotFoundException;
    void updateBalance(Long accountId, BigDecimal newBalance) throws AccountNotFoundException;
}
