package com.james.movieservice.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "categories")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseEntity {
  @Column(name = "category_name", nullable = false)
  private String categoryName;

  @OneToMany(mappedBy = "category")
  @Builder.Default
  private List<Movie> movies = new ArrayList<>();

  public void addMovie(Movie movie) {
    this.movies.add(movie);
  }
}
