package com.example.libraryviewerbackend.controller;

import com.example.libraryviewerbackend.service.CommentService;
import com.openapi.gen.springboot.api.CommentApiController;
import com.openapi.gen.springboot.dto.CommentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController extends CommentApiController {

    CommentService commentService;

    public CommentController(CommentService commentService) {
        super(null);
        this.commentService = commentService;
    }

    @Override
    public ResponseEntity<List<CommentDTO>> getAllComments() {
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @Override
    public ResponseEntity<CommentDTO> addComment(CommentDTO commentDTO) {
        return ResponseEntity.ok(commentService.addComment(commentDTO));
    }

    @Override
    public ResponseEntity<List<CommentDTO>> getAllCommentsForSpecificBook(String id) {
        return ResponseEntity.ok(commentService.getCommentsByBookId(id));
    }
}
