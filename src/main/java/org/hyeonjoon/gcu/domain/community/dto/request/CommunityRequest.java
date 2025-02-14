package org.hyeonjoon.gcu.domain.community.dto.request;

import lombok.Builder;
import org.hyeonjoon.gcu.domain.community.enums.CommunityType;

import java.util.List;

@Builder
public record CommunityRequest(
        String title,
        String content,
        List<String> tags,
        CommunityType type
) {

}
