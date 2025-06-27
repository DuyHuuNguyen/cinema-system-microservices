package com.james.userservice.entity;

import com.james.userservice.enums.HobbyEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "hobbies")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Hobby extends BaseEntity {
    @Column(name = "hobby_name",nullable = false)
    @Enumerated(value = EnumType.STRING)
    private HobbyEnum hobbyName;
}
