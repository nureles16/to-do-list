package intern.project.to_do.list.controller;

import intern.project.to_do.list.entity.ImportanceLevel;
import intern.project.to_do.list.entity.TaskStatus;
import intern.project.to_do.list.entity.ToDoItem;
import intern.project.to_do.list.service.ToDoItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class ToDoItemController {

    private final ToDoItemService service;

    @PostMapping
    public ResponseEntity<ToDoItem> createToDoItem(@Valid @RequestBody ToDoItem item) {
        ToDoItem createdItem = service.createToDoItem(item);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ToDoItem>> getAllToDoItems(@RequestParam(required = false, defaultValue = "id") String sortBy) {
        Sort sort = Sort.by(sortBy);
        List<ToDoItem> items = service.getActiveToDoItems();
        return ResponseEntity.ok(items);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDoItem> updateToDoItem(@PathVariable Long id, @Valid @RequestBody ToDoItem item) {
        return new ResponseEntity<>(service.updateToDoItem(id, item), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDoItem(@PathVariable Long id) {
        service.deleteToDoItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/archive") // New endpoint for archiving
    public ResponseEntity<Void> archiveToDoItem(@PathVariable Long id) {
        service.archiveToDoItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ToDoItem>> getItemsByStatus(@PathVariable TaskStatus status) {
        return ResponseEntity.ok(service.getItemsByStatus(status));
    }

    @GetMapping("/importance/{importanceLevel}")
    public ResponseEntity<List<ToDoItem>> getItemsByImportanceLevel(@PathVariable ImportanceLevel importanceLevel) {
        return ResponseEntity.ok(service.getItemsByImportanceLevel(importanceLevel));
    }
}
