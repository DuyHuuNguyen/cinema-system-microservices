package com.james.userservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class User extends  BaseEntity{
    @Column(name ="first_name",nullable = false)
    private String firstname;

    @Column(name = "last_name",nullable = false)
    private String lastname;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name="email",nullable = false)
    private String email;

    @Column(name="avatar_key")
    private String avatarKey;

    @Column(name = "date_of_birth")
    private Long dateOfBirth;

    @Column(name="is_loyal_customer")
    @Builder.Default
    private Boolean isLoyalCustomer = false;

    @OneToMany
    @JoinColumn(name = "location_id")
    private List<Location> locations;


    @ManyToMany
    @JoinTable(name = "role_users",
            joinColumns=
            @JoinColumn(name="user_id"),
            inverseJoinColumns=
            @JoinColumn(name="role_id")
    )
    private List<Role> roles = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_hobbies",
            joinColumns=
            @JoinColumn(name="user_id"),
            inverseJoinColumns=
            @JoinColumn(name="hobby_id")
    )
    private List<Hobby> hobbies = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    @Builder.Default
    private List<WorkPlace> workPlaces = new ArrayList<>();
}
