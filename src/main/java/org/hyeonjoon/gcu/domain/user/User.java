package org.hyeonjoon.gcu.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Table(name = "tbl_user") @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    private String id;

    @Column(length = 20) private String username;
    @Column(length = 30) private String email;
    @Column(length = 20) private String department;
    @Column private String password;
    @Column private int enteredDate;

    @Builder
    public User(String id, String username, String email, String department, String password, int enteredDate) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.department = department;
        this.password = password;
        this.enteredDate = enteredDate;
    }
}
