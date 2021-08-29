package com.foodplaza.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Restaurant extends AbstractPersistable<Long> {
    private String name;
    private String address;
    @OneToOne
    private User vendor;
}
