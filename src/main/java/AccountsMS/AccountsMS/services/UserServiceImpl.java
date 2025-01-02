package AccountsMS.AccountsMS.services;

import AccountsMS.AccountsMS.models.UserDTO;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class UserServiceImpl {
    private final RestTemplate restTemplate;

    public UserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean userExists(Long userId) {
        String url = "http://localhost:8082/user/{userId}" ;

        try {
            // Call User microservice to check if user exists
            restTemplate.getForObject(url, UserDTO.class);
            return true; // User exists
        } catch (HttpClientErrorException.NotFound e) {
            return false; // User not found
        }
    }
}
