package com.backend.jarvis.repository.query;

import com.backend.jarvis.domain.Purchase;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.backend.jarvis.domain.QItem.item;
import static com.backend.jarvis.domain.QPerson.person;
import static com.backend.jarvis.domain.QPurchase.purchase;
import static com.backend.jarvis.domain.QPurchaseItem.purchaseItem;

@Repository
public class PurchaseQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public PurchaseQueryRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<Purchase> findAll() {
        return queryFactory
                .selectFrom(purchase)
                .join(purchase.person, person).fetchJoin()
                .join(purchase.purchaseItems, purchaseItem).fetchJoin()
                .join(purchaseItem.item, item).fetchJoin()
                .distinct()
                .fetch();
    }

    public List<Purchase> findAllByPersonId(Long id) {
        return queryFactory
                .selectFrom(purchase)
                .join(purchase.person, person).fetchJoin()
                .join(purchase.purchaseItems, purchaseItem).fetchJoin()
                .join(purchaseItem.item, item).fetchJoin()
                .where(person.id.eq(id))
                .distinct()
                .fetch();
    }
}
