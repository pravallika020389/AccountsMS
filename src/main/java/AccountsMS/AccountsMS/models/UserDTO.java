package AccountsMS.AccountsMS.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long userId;

    private String username;

    private String email;

    private String password;

    private String phoneNumber;

    private boolean twoFactorEnabled ;

    private String kycStatus ;


    private LocalDate createdAt;


    private LocalDate updatedAt;
}
