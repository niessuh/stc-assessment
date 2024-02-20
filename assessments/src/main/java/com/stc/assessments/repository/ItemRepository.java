package com.stc.assessments.repository;

import com.stc.assessments.entites.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
