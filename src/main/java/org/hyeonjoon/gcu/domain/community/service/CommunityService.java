package org.hyeonjoon.gcu.domain.community.service;

import lombok.RequiredArgsConstructor;
import org.hyeonjoon.gcu.domain.community.Community;
import org.hyeonjoon.gcu.domain.community.dto.request.CommunityRequest;
import org.hyeonjoon.gcu.domain.community.enums.CommunityType;
import org.hyeonjoon.gcu.domain.community.repository.CommunityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommunityService {
    private final CommunityRepository communityRepository;

    @Transactional(rollbackFor = Exception.class)
    public boolean save(CommunityRequest request) {
        Community community = Community.builder()
                .title(request.title())
                .content(request.content())
                .type(request.type())
                .build();
        communityRepository.save(community);

        return true;
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Page<Community> findAll(CommunityType tab, int page, int size) {
        return communityRepository.findAllByType(
                tab,
                PageRequest.of(page - 1, size, Sort.by("id").descending())
        );
    }
}