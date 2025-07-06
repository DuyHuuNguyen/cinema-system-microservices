package com.james.userservice.entity;

import com.james.userservice.dto.LocationDTO;
import com.james.userservice.dto.ProfileDTO;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import lombok.experimental.SuperBuilder;

@ToString
@Entity
@Table(name = "users")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
  @Column(name = "first_name", nullable = false)
  private String firstname;

  @Column(name = "last_name", nullable = false)
  private String lastname;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "avatar_key")
  private String avatarKey;

  @Column(name = "date_of_birth")
  private Long dateOfBirth;

  @Column(name = "is_loyal_customer")
  @Builder.Default
  private Boolean isLoyalCustomer = false;

  @OneToOne(
      cascade = {
        CascadeType.DETACH,
        CascadeType.MERGE,
        CascadeType.PERSIST,
        CascadeType.PERSIST,
        CascadeType.REFRESH
      })
  @JoinColumn(name = "location_id", referencedColumnName = "id")
  private Location location;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Builder.Default
  @JoinTable(
      name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private List<Role> roles = new ArrayList<>();

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @Builder.Default
  @JoinTable(
      name = "user_hobbies",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "hobby_id"))
  private List<Hobby> hobbies = new ArrayList<>();

  @OneToMany(
      fetch = FetchType.LAZY,
      mappedBy = "employee",
      cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.PERSIST, CascadeType.REFRESH})
  @Builder.Default
  private List<WorkPlace> workPlaces = new ArrayList<>();

  public void addLocation(Location location) {
    location.addUser(this);
    this.location = location;
  }

  public void addHobby(Hobby hobby) {
    this.hobbies.add(hobby);
  }

  public void addRole(Role role) {
    this.roles.add(role);
  }

  public void changeHobbies(List<Hobby> newHobbies) {
    this.hobbies.clear();
    for (var hobby : newHobbies) {
      this.hobbies.add(hobby);
    }
  }

  public void changeProfile(ProfileDTO profileDTO) {
    this.lastname = profileDTO.getLastName();
    this.firstname = profileDTO.getFirstName();
    this.dateOfBirth = profileDTO.getDateOfBirth();
    this.avatarKey = profileDTO.getAvatarUrl();
  }

  public LocationDTO getLocationDTO() {
    return this.location.getLocationDTO();
  }
}
