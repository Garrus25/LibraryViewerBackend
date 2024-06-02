package com.example.libraryviewerbackend.service;

import com.openapi.gen.springboot.dto.CommentDTO;

import java.util.List;

public interface ICommentService {
    List<CommentDTO> getAllComments();

    CommentDTO addComment(CommentDTO commentDTO);
}
