package com.management.inventory.yjinventorymanagement.repository.query;

import com.management.inventory.yjinventorymanagement.domain.Purchase;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.management.inventory.yjinventorymanagement.domain.QItem.item;
import static com.management.inventory.yjinventorymanagement.domain.QPerson.person;
import static com.management.inventory.yjinventorymanagement.domain.QPurchase.purchase;
import static com.management.inventory.yjinventorymanagement.domain.QPurchaseItem.purchaseItem;

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
