package org.hyeonjoon.gcu.domain.community.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommunityType {
    FREE("자유게시판"),
    DEPARTMENT("학과게시판"),
    STUDENT("학생게시판");

    private final String description;
}