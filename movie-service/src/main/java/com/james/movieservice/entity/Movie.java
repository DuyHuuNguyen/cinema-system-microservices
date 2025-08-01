package com.james.movieservice.entity;

import com.james.movieservice.dto.UpdateMovieDTO;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import lombok.experimental.SuperBuilder;

@ToString
@Entity
@Table(name = "movies")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Movie extends BaseEntity {

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "duration", nullable = false)
  private String duration;

  @Column(name = "language", nullable = false)
  private String language;

  @Column(name = "released_at", nullable = false)
  private Long releasedAt;

  @Column(name = "poster_url", nullable = false)
  private String poster;

  @Column(name = "trailer_url", nullable = false)
  private String trailer;

  @Column(name = "movie_url", nullable = false)
  private String movie;

  @Column(name = "theater_id", nullable = false)
  private Long theaterId;

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  @OneToMany(
      mappedBy = "movie",
      orphanRemoval = true,
      cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
  @Builder.Default
  private List<MovieRate> movieRates = new ArrayList<>();

  public void addCategory(Category category) {
    this.category = category;
    category.addMovie(this);
  }

  public String getCategoryName() {
    return this.category.getCategoryName();
  }

  public Long getCategoryId() {
    return this.category.getId();
  }

  public void updateInfo(UpdateMovieDTO updateMovieDTO) {
    this.title = updateMovieDTO.getTitle();
    this.description = updateMovieDTO.getDescription();
    this.duration = updateMovieDTO.getDuration();
    this.language = updateMovieDTO.getLanguage();
    this.releasedAt = updateMovieDTO.getReleasedAt();
    this.poster = updateMovieDTO.getPoster();
    this.trailer = updateMovieDTO.getTrailer();
    this.movie = updateMovieDTO.getMovie();
  }

  public void addRate(MovieRate rate) {
    this.movieRates.add(rate);
  }
}
