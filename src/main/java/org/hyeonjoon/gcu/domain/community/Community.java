package org.hyeonjoon.gcu.domain.community;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hyeonjoon.gcu.domain.community.enums.CommunityType;
import org.hyeonjoon.gcu.global.common.AuditingFields;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "tbl_community"
//        indexes = {
//                @Index(name = "idx_title", columnList = "title"),
//                @Index(name = "idx_type", columnList = "type")
//        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Community extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;
    @Column(nullable = false, length = 500)
    private String content;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CommunityType type;
    @Column
    @ElementCollection
    private List<String> tags = new ArrayList<>();
    @Column(nullable = false)
    @ColumnDefault("0")
    private int likeCount;

    @Builder
    public Community(String title, String content, CommunityType type, List<String> tags, int likeCount) {
        this.title = title;
        this.content = content;
        this.type = type;
        this.tags = tags;
        this.likeCount = likeCount;
    }
}
