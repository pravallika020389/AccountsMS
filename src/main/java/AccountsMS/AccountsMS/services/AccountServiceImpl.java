package AccountsMS.AccountsMS.services;

import AccountsMS.AccountsMS.models.Account;
import AccountsMS.AccountsMS.repositories.AccountRepository;
import AccountsMS.AccountsMS.services.interfaces.AccountService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    private RestTemplate restTemplate;

    //private final UserServiceImpl userService;

    private static final String USER_MICROSERVICE_URL = "http://localhost:8082/user/{userId}";

    public boolean checkIfUserExists(Long userId) {
        try {
            // Make a GET request to the user microservice
            ResponseEntity<String> response = restTemplate.getForEntity(USER_MICROSERVICE_URL, String.class, userId);

            // If the response is OK, the user exists
            return response.getStatusCode() == HttpStatus.OK;
        } catch (HttpClientErrorException e) {
            // If a 404 is returned, the user does not exist
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return false;
            }
            // Handle other exceptions (optional)
            throw e;
        }
    }



    @Override
    @Transactional
    public Account addAccount(Account account) {
        //return accountRepository.save(account);

        // Verify if the user exists
        if (!checkIfUserExists(account.getUserId())) {
            throw new IllegalArgumentException("User does not exist.");
        }

        // Proceed to create the account (this is where you'd save to the database)
        // For simplicity, we're just returning a success message
       // return "Account created successfully for User ID: " + account.getUserId();
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override

    public Account getAccountById(Long accountId) {
        // Fetch account details using the repository
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        return accountOptional.orElse(null); // Return account details or null if not found
    }



    @Override
    public void updateBalance(Long accountId, BigDecimal newBalance) throws AccountNotFoundException {

        // Check if the account exists
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with ID: " + accountId));

        // Update the balance
        account.setBalance(newBalance);

        // Save the updated account
        accountRepository.save(account);
    }


    public boolean checkIfAccountExists(Long accountId) {
        return accountRepository.existsById(accountId);
    }

    public void updateAccount(Long accountId, Account account) throws AccountNotFoundException {
        // Check if the account exists
        Account existingAccount = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with ID: " + accountId));

        // Update fields
        existingAccount.setBalance(account.getBalance());


        // Save the updated account
         accountRepository.save(existingAccount);

    }


}
