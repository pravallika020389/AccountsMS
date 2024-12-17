package AccountsMS.AccountsMS.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private String accountId;
    @Column(name = "user_id")
    private int userId; //FOREIGN KEY (users.user_id)
    @Column(name = "account_number", columnDefinition = "varchar(20)", nullable = false, unique = true)
    private String accountNumber;
    @Column(name = "account_type", columnDefinition = "varchar(20)", nullable = false)
    private String accountType;
    @Column(precision = 15, scale = 2)
    /// data type DECIMAL(15, 2)
    private BigDecimal balance = BigDecimal.valueOf(0.00);
    @Column(columnDefinition = "varchar(10)")
    private String currency = "USD";

    @CreationTimestamp // Automatically sets the current timestamp
    @Column(name = "created_at", updatable = false) // Prevent updates to this column
    // @Column(name = "initiated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP") //handled by SQL

    private LocalDate createdAt;
}
