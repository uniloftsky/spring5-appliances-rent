package com.uniloftsky.springframework.spring5appliancesrent.model.pagination;

import com.uniloftsky.springframework.spring5appliancesrent.model.Category;
import com.uniloftsky.springframework.spring5appliancesrent.model.Type;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemSearchCriteria {

    private Type type;
    private Category category;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

}
