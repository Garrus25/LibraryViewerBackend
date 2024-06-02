package com.example.libraryviewerbackend.repository;

import com.example.libraryviewerbackend.model.Comment;

import java.util.List;


public interface CommentRepository extends BaseRepository<Comment, Integer> {
    List<Comment> getCommentsByBookId(String bookId);
}