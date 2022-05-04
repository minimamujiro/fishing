package com.example.fishing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fishing.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}