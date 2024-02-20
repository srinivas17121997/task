/*
package com.example.demo.respoitory;

import com.example.demo.model.Task;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class PersonSpecification implements Specification<Task> {

    private Task filter;

    public PersonSpecification(Task filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Task> root,CriteriaQuery<?> cq,
                                 CriteriaBuilder cb) {

        Predicate p = cb.conjunction();

        if (filter.getTitle() != null) {
            p.getExpressions()
                    .add(cb.like(root.get("title"), filter.getTitle()));
        }
        return p;
    }

    public static Specification<Task> endDateBefore(LocalDate date) {
        return (root, query, builder) -> builder.lessThanOrEqualTo(root.get("endDate"), date);
    }

    public static Specification<Task> title(String title) {
        return (root, query, builder) -> builder.like(root.get("title"), title);
    }

    public static Specification<Task> statusType(int  title) {
        return (root, query, builder) -> builder.equal(root.get("taskStatus"), title);
    }

    public static Specification<Task> endDateAfter(LocalDate date) {
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("endDate"), date);
    }
    public static Specification<Task> endDate(LocalDate date) {
        return (root, query, builder) -> builder.equal(root.get("endDate"), date);
    }

    public static Specification<Task> createdDateBefore(LocalDate date) {
        return (root, query, builder) -> builder.lessThanOrEqualTo(root.get("createdOn"), date);
    }

    public static Specification<Task> createdOnAfter(LocalDate date) {
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("createdOn"), date);
    }
    public static Specification<Task> createdOn(LocalDate date) {
        return (root, query, builder) -> builder.equal(root.get("createdOn"), date);
    }
}*/
