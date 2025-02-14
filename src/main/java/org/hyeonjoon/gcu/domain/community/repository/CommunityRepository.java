package org.hyeonjoon.gcu.domain.community.repository;

import org.hyeonjoon.gcu.domain.community.Community;
import org.hyeonjoon.gcu.domain.community.enums.CommunityType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
    @Query("""
    SELECT DISTINCT c FROM Community c 
    LEFT JOIN FETCH c.tags 
    WHERE c.type = :tab 
    ORDER BY c.id DESC
    """)
    Page<Community> findAllByType(@Param("tab") CommunityType tab, Pageable pageable);
}