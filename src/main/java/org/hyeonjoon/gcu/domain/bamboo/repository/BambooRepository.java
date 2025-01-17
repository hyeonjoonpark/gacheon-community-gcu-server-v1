package org.hyeonjoon.gcu.domain.bamboo.repository;

import org.hyeonjoon.gcu.domain.bamboo.Bamboo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BambooRepository extends JpaRepository<Bamboo, Long> {
    
}
