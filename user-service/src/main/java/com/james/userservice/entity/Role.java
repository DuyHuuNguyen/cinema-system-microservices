package com.james.userservice.entity;

import com.james.userservice.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "roles")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity{
    @Column(name = "role_name",nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum roleName;
}
