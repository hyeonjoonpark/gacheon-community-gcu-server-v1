package org.hyeonjoon.gcu.domain.bamboo;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hyeonjoon.gcu.global.common.AuditingFields;

@Entity @Table(name = "tbl_bamboo") @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bamboo extends AuditingFields {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50) private String title;
    @Column(length = 200) private String content;

    @Builder
    public Bamboo(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
