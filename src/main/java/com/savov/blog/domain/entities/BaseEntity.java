package com.savov.blog.domain.entities;


import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {

    private Long id;

    public BaseEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;

    }

    public void setId(Long id) {
        this.id = id;
    }
}
