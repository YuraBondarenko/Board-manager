package com.example.boardmanager.controller;

import com.example.boardmanager.dto.request.BoardRequestDto;
import com.example.boardmanager.dto.response.BoardColumnTaskResponseDto;
import com.example.boardmanager.dto.response.BoardResponseDto;
import com.example.boardmanager.mapper.impl.BoardColumnTaskMapper;
import com.example.boardmanager.mapper.impl.BoardMapper;
import com.example.boardmanager.model.Board;
import com.example.boardmanager.service.BoardService;
import com.example.boardmanager.service.TaskService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;
    private final TaskService taskService;
    private final BoardMapper boardMapper;
    private final BoardColumnTaskMapper boardColumnTaskMapper;

    public BoardController(BoardService boardService, TaskService taskService,
                           BoardMapper boardMapper,
                           BoardColumnTaskMapper boardColumnTaskMapper) {
        this.boardService = boardService;
        this.taskService = taskService;
        this.boardMapper = boardMapper;
        this.boardColumnTaskMapper = boardColumnTaskMapper;
    }

    @PostMapping
    public ResponseEntity<Board> save(@RequestBody BoardRequestDto boardRequestDto) {
        return new ResponseEntity<>(boardService.save(boardMapper.getEntity(boardRequestDto)),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> getAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int limit,
            @RequestParam(required = false, defaultValue = "id") String sortBy) {
        List<BoardResponseDto> boards = boardService.getAll(page, limit, sortBy)
                .stream()
                .map(boardMapper::getDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(boardMapper.getDto(boardService.getById(id)), HttpStatus.OK);
    }

    @GetMapping("/information/{id}")
    public ResponseEntity<BoardColumnTaskResponseDto> getWithRelations(@PathVariable Long id) {
        return new ResponseEntity<>(boardColumnTaskMapper
                .getDto(taskService.getWithRelations(id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BoardResponseDto> delete(@PathVariable Long id) {
        return new ResponseEntity<>(boardMapper.getDto(boardService.delete(id)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardResponseDto> update(@PathVariable Long id,
                                                   @RequestBody BoardRequestDto dto) {
        Board board = boardMapper.getEntity(dto);
        board.setId(id);
        return new ResponseEntity<>(boardMapper.getDto(boardService.save(board)), HttpStatus.OK);
    }

    @GetMapping(path = "/image/download/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable Long id) throws IOException {
        File file = new File(boardService.getById(id).getFilePath());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @PutMapping("/image/{id}")
    public ResponseEntity<BoardResponseDto> addImage(
            @PathVariable Long id,
            @RequestParam(value = "file-path") String filePath) {
        Board board = boardService.getById(id);
        board.setFilePath(filePath);
        return new ResponseEntity<>(boardMapper.getDto(boardService.save(board)), HttpStatus.OK);
    }
}
