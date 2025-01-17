package org.hyeonjoon.gcu.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hyeonjoon.gcu.domain.user.enums.Role;

import java.time.LocalDate;

@Entity @Table(name = "tbl_user") @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    private String id;

    @Column(length = 20, nullable = false) private String username;
    @Column(length = 30, nullable = false) private String email;
    @Column(length = 20, nullable = false) private String department;
    @Column(nullable = false) private String password;
    @Column(nullable = false) private LocalDate enteredYear;
    @Column private Role role;

    @Builder
    public User(String id, String username, String email, String department, String password, LocalDate enteredYear, Role role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.department = department;
        this.password = password;
        this.enteredYear = enteredYear;
        this.role = role;
    }
}
