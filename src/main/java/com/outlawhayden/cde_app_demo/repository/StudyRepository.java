package com.outlawhayden.cde_app_demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outlawhayden.cde_app_demo.model.Study;


public interface StudyRepository extends JpaRepository<Study, Long> {
    List<Study> findByPublished(boolean published);
    
    List<Study> findByTitleContaining(String title);
}
