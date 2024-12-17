package AccountsMS.AccountsMS.controllers;

import AccountsMS.AccountsMS.models.Account;
import AccountsMS.AccountsMS.services.AccountServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class AccountController {

    private final AccountServiceImpl accountService;


    @GetMapping("/account")
    public List<Account> getAllFundTransfers(){
        return accountService.getAllAccounts();
    }

    @PostMapping("/account")
    public Account addAccount(@RequestBody Account account){
        return accountService.addAccount(account);
    }

//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Value("${microservice1.url}")  // URL for Microservice 1 (User Service)
//    private String userServiceUrl;
//
//    @PostMapping
//    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
//        // Make API call to check if user exists in Microservice 1
//        ResponseEntity<User> response = restTemplate.exchange(
//                userServiceUrl + "/users/" + order.getUserId(),
//                HttpMethod.GET,
//                null,
//                User.class
//        );
//
//        if (!response.getStatusCode().is2xxSuccessful()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
//        }
//
//        // Create the order
//        order = orderService.save(order);
//        return ResponseEntity.status(HttpStatus.CREATED).body(order);
//    }

    @ExceptionHandler
    public ResponseEntity<?> respondWithError(Exception e){
        return new ResponseEntity<>("Exception Occurred. More Info :"
                + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
