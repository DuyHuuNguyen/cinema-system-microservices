package com.james.userservice.controller;

import com.james.userservice.enums.ResourceType;
import com.james.userservice.facade.StorageFacade;
import com.james.userservice.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
public class StorageFileController {
  private final StorageFacade storageFacade;

  @PostMapping(value = "/upload", consumes = "multipart/form-data", produces = "application/json")
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      summary = "Upload image",
      tags = {"Storage APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("isAuthenticated()")
  @SneakyThrows
  public BaseResponse<String> uploadImage(
      @RequestPart("image") MultipartFile image,
      @RequestParam("resourceType") ResourceType resourceType) {
    return BaseResponse.build(this.storageFacade.uploadImage(image.getBytes(), resourceType), true);
  }
}
