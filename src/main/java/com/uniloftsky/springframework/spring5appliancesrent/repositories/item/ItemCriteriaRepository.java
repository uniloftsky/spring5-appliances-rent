package com.uniloftsky.springframework.spring5appliancesrent.repositories.item;

import com.uniloftsky.springframework.spring5appliancesrent.model.Item;
import com.uniloftsky.springframework.spring5appliancesrent.model.pagination.ItemPage;
import com.uniloftsky.springframework.spring5appliancesrent.model.pagination.ItemSearchCriteria;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ItemCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public ItemCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Item> findAllWithFilters(ItemPage itemPage,
                                         ItemSearchCriteria itemSearchCriteria){
        CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
        Root<Item> itemRoot = criteriaQuery.from(Item.class);
        Predicate predicate = getPredicate(itemSearchCriteria, itemRoot);
        criteriaQuery.where(predicate);
        setOrder(itemPage, criteriaQuery, itemRoot);

        TypedQuery<Item> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(itemPage.getPageNumber() * itemPage.getPageSize());
        typedQuery.setMaxResults(itemPage.getPageSize());

        Pageable pageable = getPageable(itemPage);

        long itemsCount = getEmployeesCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, itemsCount);
    }

    private Predicate getPredicate(ItemSearchCriteria itemSearchCriteria,
                                   Root<Item> itemRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(itemSearchCriteria.getType().getTypeName())){
            predicates.add(
                    criteriaBuilder.like(itemRoot.get("category").get("type").get("typeName"),
                            "%" + itemSearchCriteria.getType().getTypeName() + "%")
            );
        }
        if(Objects.nonNull(itemSearchCriteria.getCategory())){
            predicates.add(
                    criteriaBuilder.like(itemRoot.get("category").get("categoryName"),
                            "%" + itemSearchCriteria.getCategory().getCategoryName() + "%")
            );
        }
        if(Objects.nonNull(itemSearchCriteria.getMaxPrice())) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(itemRoot.get("price"),
                    itemSearchCriteria.getMaxPrice())
            );
        }
        if(Objects.nonNull(itemSearchCriteria.getMinPrice())) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(itemRoot.get("price"),
                    itemSearchCriteria.getMinPrice())
            );
        }
        predicates.add(criteriaBuilder.equal(itemRoot.get("active"), true));
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(ItemPage itemPage,
                          CriteriaQuery<Item> criteriaQuery,
                          Root<Item> itemRoot) {
        if(itemPage.getSortDirection().equals(Sort.Direction.ASC)){
            criteriaQuery.orderBy(criteriaBuilder.asc(itemRoot.get(itemPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(itemRoot.get(itemPage.getSortBy())));
        }
    }

    private Pageable getPageable(ItemPage itemPage) {
        Sort sort = Sort.by(itemPage.getSortDirection(), itemPage.getSortBy());
        return PageRequest.of(itemPage.getPageNumber(),itemPage.getPageSize(), sort);
    }

    private long getEmployeesCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Item> countRoot = countQuery.from(Item.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

}
