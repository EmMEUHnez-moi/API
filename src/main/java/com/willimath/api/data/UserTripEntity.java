package com.willimath.api.data;

import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name = "user_trip", schema = "public")
public class UserTripEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private TripEntity trip;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    public UserTripEntity() {
    }

    public UserTripEntity(UserEntity user, TripEntity trip, RoleEntity role) {
        this.user = user;
        this.trip = trip;
        this.role = role;
    }

    public UserTripEntity(Integer id, UserEntity user, TripEntity trip, RoleEntity role) {
        this.id = id;
        this.user = user;
        this.trip = trip;
        this.role = role;
    }

    public UserTripEntity(UserEntity userEntity) {
    }

    public Integer getId() {
        return id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public TripEntity getTrip() {
        return trip;
    }

    public void setTrip(TripEntity trip) {
        this.trip = trip;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }
}
