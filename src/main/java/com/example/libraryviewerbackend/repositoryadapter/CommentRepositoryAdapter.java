package com.example.libraryviewerbackend.repositoryadapter;

import com.example.libraryviewerbackend.model.Comment;
import com.example.libraryviewerbackend.repository.CommentRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentRepositoryAdapter {
    CommentRepository commentRepository;

    public CommentRepositoryAdapter(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    public Comment addComment(Comment comment){
        return commentRepository.save(comment);
    }

}
