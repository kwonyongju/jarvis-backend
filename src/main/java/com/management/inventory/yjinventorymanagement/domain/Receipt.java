package com.management.inventory.yjinventorymanagement.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Receipt {

    @Id
    @GeneratedValue
    private Long Id;
}
