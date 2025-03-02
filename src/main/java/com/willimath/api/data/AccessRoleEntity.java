package com.willimath.api.data;

import jakarta.persistence.*;

@Entity
@Table(name = "access_role")
public class AccessRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    public AccessRoleEntity() {
    }

    public AccessRoleEntity(String name) {
        this.name = name;
    }

    public AccessRoleEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
