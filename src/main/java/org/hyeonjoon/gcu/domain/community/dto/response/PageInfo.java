package org.hyeonjoon.gcu.domain.community.dto.response;

public record PageInfo(
        int totalPages,
        long totalElements,
        int currentPage,
        int size,
        boolean hasNext,
        boolean hasPrevious
) {

}