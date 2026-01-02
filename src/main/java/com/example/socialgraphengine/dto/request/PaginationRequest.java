package com.example.socialgraphengine.dto.request;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PaginationRequest {

    private Integer page = 1;
    private Integer size = 10;
    private String sortBy = "id";
    private String sortDirection = "ASC";
    private String searchQuery;


    public Pageable toPageable()
    {
        Sort.Direction direction =
                "DESC".equalsIgnoreCase(sortDirection) ?  Sort.Direction.DESC: Sort.Direction.ASC;
        return PageRequest.of(page-1 , size , Sort.by(direction, sortBy));
    }

    // TODO: impl white list & VERIFY
}
