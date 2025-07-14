package com.james.userservice.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaginationWorkShiftResponse<T, M> {
  private M theater;
  private List<T> data;
  private int currentPage;
  private int totalElements;
  private int totalPages;
}
