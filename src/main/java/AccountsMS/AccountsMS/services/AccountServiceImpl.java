package AccountsMS.AccountsMS.services;

import AccountsMS.AccountsMS.models.Account;
import AccountsMS.AccountsMS.repositories.AccountRepository;
import AccountsMS.AccountsMS.services.interfaces.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    @Override
    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}
