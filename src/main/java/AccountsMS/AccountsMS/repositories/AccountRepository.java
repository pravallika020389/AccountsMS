package AccountsMS.AccountsMS.repositories;

import AccountsMS.AccountsMS.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
