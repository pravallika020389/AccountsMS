package AccountsMS.AccountsMS.controllers;

import AccountsMS.AccountsMS.models.Account;
import AccountsMS.AccountsMS.services.AccountServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class AccountController {
    @Autowired
    private final AccountServiceImpl accountService;


    @GetMapping("/account")
    public List<Account> getAllFundTransfers(){
        return accountService.getAllAccounts();
    }

    @PostMapping("/account")
    public Account addAccount(@RequestBody Account account){
        return accountService.addAccount(account);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long accountId) {
        Account account = accountService.getAccountById(accountId);
        if (account == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(account);
    }
    @PutMapping("/account/{accountId}")
    public ResponseEntity<String> updateAccount(@PathVariable Long accountId, @RequestBody Account account) {
        try {
            accountService.updateAccount(accountId, account);
            return ResponseEntity.ok("Account updated successfully");
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update account");
        }
    }

    @PatchMapping("/account/{accountId}/balance")
    public ResponseEntity<String> updateBalance(
            @PathVariable Long accountId,
            @RequestBody Map<String, BigDecimal> balanceUpdate) {
        try {
            if (!balanceUpdate.containsKey("balance")) {
                return ResponseEntity.badRequest().body("Balance value is required");
            }
            BigDecimal newBalance = balanceUpdate.get("balance");
            accountService.updateBalance(accountId, newBalance);
            return ResponseEntity.ok("Balance updated successfully");
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update balance");
        }
    }

    @ExceptionHandler
    public ResponseEntity<?> respondWithError(Exception e){
        return new ResponseEntity<>("Exception Occurred. More Info :"
                + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
