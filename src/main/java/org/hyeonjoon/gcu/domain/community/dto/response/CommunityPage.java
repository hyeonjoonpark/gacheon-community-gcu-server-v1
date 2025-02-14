package org.hyeonjoon.gcu.domain.community.dto.response;

import org.hyeonjoon.gcu.domain.community.Community;
import java.util.List;

public record CommunityPage(
        List<Community> content,
        PageInfo pageInfo
) {

}

