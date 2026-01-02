package com.example.socialgraphengine.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResponse<T> {
    private List<T> content;
    private PageMetadata metadata;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PageMetadata {

        private Integer currentPage;
        private Integer pageSize;
        private Long totalElements;
        private Integer totalPages;
        private boolean hasNext;
        private boolean hasPrevise;
        private boolean isFirst;
        private boolean isLast;


        @JsonProperty("rangeStart")
        public int getRangeStart()
        {
            return (currentPage-1) * pageSize+1;
        }

        @JsonProperty("rangeEnd")
        public long getRangeEnd()
        {
            long end = (long) currentPage * pageSize;
            return Math.min(end, totalElements);
        }

        public <T> PaginationResponse<T> of (Page<T> page)
        {
            PageMetadata metadata1 = PageMetadata.builder()
                    .currentPage(page.getNumber() + 1)
                    .totalElements(page.getTotalElements())
                    .totalPages(page.getTotalPages())
                    .hasNext(page.hasNext())
                    .hasPrevise(page.hasPrevious())
                    .pageSize(page.getSize())
                    .isFirst(page.isFirst())
                    .isLast(page.isLast())
                    .build();

            return new PaginationResponse<>(page.getContent() , metadata1);
        }

    }
}
