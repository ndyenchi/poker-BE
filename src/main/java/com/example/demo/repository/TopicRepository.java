package com.example.demo.repository;

import com.example.demo.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: dao.nduytan on 04/06/2022
 */
public interface TopicRepository extends JpaRepository<Topic, Long> {
}
