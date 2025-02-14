package org.hyeonjoon.gcu.domain.community.dto.response;

import org.hyeonjoon.gcu.domain.community.Community;

import java.util.List;

public record CommunityConnection(
        List<Community> communities,
        PageInfo pageInfo
) {
}
