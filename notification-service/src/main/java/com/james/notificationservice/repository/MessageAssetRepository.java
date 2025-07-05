package com.james.notificationservice.repository;

import com.james.notificationservice.entity.MessageAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageAssetRepository extends JpaRepository<MessageAsset, Long> {}
