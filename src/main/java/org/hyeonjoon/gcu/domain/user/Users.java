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
public class Users {
    @Id
    private String id;

    @Column(length = 20) private String username;
    @Column(length = 50, nullable = false) private String nickname;
    @Column(length = 30, nullable = false) private String email;
    @Column(length = 20) private String department;
    @Column(nullable = false) private String password;
    @Column private LocalDate enteredYear;
    @Column @Enumerated(value = EnumType.STRING) private Role role;

    @Builder
    public Users(String id, String username, String nickname, String email, String department, String password, LocalDate enteredYear, Role role) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.department = department;
        this.password = password;
        this.enteredYear = enteredYear;
        this.role = role;
    }

    public void update(String department, LocalDate enteredYear, String username) {
        this.department = department;
        this.enteredYear = enteredYear;
        this.username = username;
    }
}
