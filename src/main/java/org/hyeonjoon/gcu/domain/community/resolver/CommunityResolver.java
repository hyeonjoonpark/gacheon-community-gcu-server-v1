package org.hyeonjoon.gcu.domain.community.resolver;

import lombok.RequiredArgsConstructor;
import org.hyeonjoon.gcu.domain.community.Community;
import org.hyeonjoon.gcu.domain.community.dto.request.CommunityRequest;
import org.hyeonjoon.gcu.domain.community.dto.response.CommunityConnection;
import org.hyeonjoon.gcu.domain.community.dto.response.PageInfo;
import org.hyeonjoon.gcu.domain.community.enums.CommunityType;
import org.hyeonjoon.gcu.domain.community.service.CommunityService;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityResolver {

    private final CommunityService communityService;

    @MutationMapping(value = "saveCommunity")
    public boolean saveCommunity(@Argument CommunityRequest communityRequest) {
        return communityService.save(communityRequest);
    }

    @QueryMapping(value = "findAllByTab")
    public CommunityConnection findAllByTab(
            @Argument CommunityType tab,
            @Argument(name = "page") Integer page,
            @Argument(name = "size") Integer size
    ) {
        int pageNum = page != null ? page : 1;
        int sizeNum = size != null ? size : 10;

        Page<Community> communityPage = communityService.findAll(tab, pageNum, sizeNum);
        communityPage.getContent();
        return new CommunityConnection(
                communityPage.getContent(),
                new PageInfo(
                        communityPage.getTotalPages(),
                        communityPage.getTotalElements(),
                        communityPage.getNumber() + 1,
                        communityPage.getSize(),
                        communityPage.hasNext(),
                        communityPage.hasPrevious()
                )
        );
    }
}