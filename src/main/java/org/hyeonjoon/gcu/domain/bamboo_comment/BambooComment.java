package org.hyeonjoon.gcu.domain.bamboo_comment;

import jakarta.persistence.*;
import lombok.*;
import org.hyeonjoon.gcu.domain.bamboo.Bamboo;
import org.hyeonjoon.gcu.global.common.AuditingFields;

@Entity @Table(name = "tbl_bamboo_comment") @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BambooComment extends AuditingFields {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false) private String comment;

    @ManyToOne(fetch = FetchType.LAZY) @Setter
    private Bamboo bamboo;

    @Builder
    public BambooComment(String comment, Bamboo bamboo) {
        this.comment = comment;
        this.bamboo = bamboo;
    }
}
