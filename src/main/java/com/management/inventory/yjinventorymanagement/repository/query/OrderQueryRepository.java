package com.management.inventory.yjinventorymanagement.repository.query;

import com.management.inventory.yjinventorymanagement.domain.Order;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.management.inventory.yjinventorymanagement.domain.Ingredient.QIngredient.ingredient;
import static com.management.inventory.yjinventorymanagement.domain.QOrder.order;
import static com.management.inventory.yjinventorymanagement.domain.QOrderIngredient.orderIngredient;
import static com.management.inventory.yjinventorymanagement.domain.QPerson.person;


@Repository
public class OrderQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public OrderQueryRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<Order> findAll() {
        return queryFactory
                .selectFrom(order)
                .join(order.person, person).fetchJoin()
                .join(order.orderIngredients, orderIngredient).fetchJoin()
                .join(orderIngredient.ingredient, ingredient).fetchJoin()
                .fetch();
    }

    public List<Order> findAllByPersonId(Long id) {
        return queryFactory
                .selectFrom(order)
                .join(order.person, person).fetchJoin()
                .join(order.orderIngredients, orderIngredient).fetchJoin()
                .join(orderIngredient.ingredient, ingredient).fetchJoin()
                .where(person.id.eq(id))
                .limit(1000)
                .fetch();
    }
}
