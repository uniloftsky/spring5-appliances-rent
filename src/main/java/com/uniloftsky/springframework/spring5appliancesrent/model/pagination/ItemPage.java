package com.uniloftsky.springframework.spring5appliancesrent.model.pagination;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class ItemPage {

    private int pageNumber = 0;
    private int pageSize = 8;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortBy = "id";

}
