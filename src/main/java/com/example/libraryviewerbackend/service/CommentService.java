package com.example.libraryviewerbackend.service;

import com.example.libraryviewerbackend.modelmapper.CommentModelMapper;
import com.example.libraryviewerbackend.repositoryadapter.CommentRepositoryAdapter;
import com.openapi.gen.springboot.dto.CommentDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CommentService implements ICommentService {
    private final CommentRepositoryAdapter commentRepositoryAdapter;

    public CommentService(CommentRepositoryAdapter commentRepositoryAdapter) {
        this.commentRepositoryAdapter = commentRepositoryAdapter;
    }

    @Override
    public List<CommentDTO> getAllComments() {
        return commentRepositoryAdapter.getAllComments().stream()
                .map(CommentModelMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDTO addComment(CommentDTO commentDTO) {
        return CommentModelMapper.INSTANCE.toDTO(commentRepositoryAdapter.addComment(CommentModelMapper.INSTANCE.toEntity(commentDTO)));
    }
}
