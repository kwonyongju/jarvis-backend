package com.backend.jarvis.repository.query;

import com.backend.jarvis.domain.Order;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.backend.jarvis.domain.Ingredient.QIngredient.ingredient;
import static com.backend.jarvis.domain.QOrder.order;
import static com.backend.jarvis.domain.QOrderIngredient.orderIngredient;
import static com.backend.jarvis.domain.QPerson.person;


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
