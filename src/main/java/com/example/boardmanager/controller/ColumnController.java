package com.example.boardmanager.controller;

import com.example.boardmanager.dto.request.ColumnRequestDto;
import com.example.boardmanager.dto.response.ColumnResponseDto;
import com.example.boardmanager.mapper.impl.ColumnMapper;
import com.example.boardmanager.model.Column;
import com.example.boardmanager.service.BoardService;
import com.example.boardmanager.service.ColumnService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/columns")
public class ColumnController {
    private final ColumnService columnService;
    private final ColumnMapper columnMapper;
    private final BoardService boardService;

    public ColumnController(ColumnService columnService,
                            ColumnMapper columnMapper, BoardService boardService) {
        this.columnService = columnService;
        this.columnMapper = columnMapper;
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<ColumnResponseDto> save(
            @RequestBody ColumnRequestDto columnRequestDto) {
        return new ResponseEntity<>(columnMapper.getDto(columnService
                .save(columnMapper.getEntity(columnRequestDto))), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColumnResponseDto> update(
            @PathVariable Long id,
            @RequestBody ColumnRequestDto columnRequestDto,
            @RequestParam(value = "board-id") Long boardId) {
        Column entity = columnMapper.getEntity(columnRequestDto);
        entity.setId(id);
        entity.setBoard(boardService.getById(boardId));
        return new ResponseEntity<>(columnMapper.getDto(columnService.save(entity)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ColumnResponseDto> delete(@PathVariable Long id) {
        return new ResponseEntity<>(columnMapper.getDto(columnService.delete(id)), HttpStatus.OK);
    }
}
