package org.hyeonjoon.gcu.domain.bamboo;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hyeonjoon.gcu.domain.bamboo_comment.BambooComment;
import org.hyeonjoon.gcu.global.common.AuditingFields;

import java.util.List;

@Entity @Table(name = "tbl_bamboo") @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bamboo extends AuditingFields {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false) private String title;
    @Column(length = 200, nullable = false) private String content;
    @Column(nullable = false) private boolean isAnonymous;
    @Column(nullable = false) @ColumnDefault("0") private int likeCount;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "bamboo")
    private List<BambooComment> comments;

    public void addBambooComment(BambooComment comment) {
        comments.add(comment);
        comment.setBamboo(this);
    }

    public void removeBambooComment(BambooComment comment) {
        comments.remove(comment);
        comment.setBamboo(null);
    }

    @Builder
    public Bamboo(String title, String content, boolean isAnonymous, int likeCount) {
        this.title = title;
        this.content = content;
        this.isAnonymous = isAnonymous;
        this.likeCount = likeCount;
    }
}
