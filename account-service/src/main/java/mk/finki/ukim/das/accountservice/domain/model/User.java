package mk.finki.ukim.das.accountservice.domain.model;

import lombok.*;
import mk.ukim.finki.emt.advertisement.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User extends AbstractEntity<UserId> {

    @Version
    private Long version;

    @Column(unique = true)
    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    public User(UserId userId, String username, String email, String password) {
        super(userId);
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
