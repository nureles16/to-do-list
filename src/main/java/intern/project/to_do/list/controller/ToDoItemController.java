package intern.project.to_do.list.controller;
import intern.project.to_do.list.dto.ToDoListFilterDto;
import intern.project.to_do.list.entity.ImportanceLevel;
import intern.project.to_do.list.entity.TaskStatus;
import intern.project.to_do.list.entity.ToDoItem;
import intern.project.to_do.list.facade.TodoFacade;
import intern.project.to_do.list.service.ToDoItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class ToDoItemController {
    private final ToDoItemService service;
    private final TodoFacade todoFacade;

    @PostMapping
    public ResponseEntity<ToDoItem> createToDoItem(@Valid @RequestBody ToDoItem item) {
        ToDoItem createdItem = todoFacade.createTodoItem(item);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ToDoItem>> getAllToDoItems(@RequestBody ToDoListFilterDto filter) {
        return new ResponseEntity<>(todoFacade.listTodos(filter), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDoItem> updateToDoItem(@PathVariable Long id, @Valid @RequestBody ToDoItem item) {
        ToDoItem updatedItem = todoFacade.updateTodoItem(id, item);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDoItem(@PathVariable Long id) {
        todoFacade.deleteTodoItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/archive")
    public ResponseEntity<Void> archiveToDoItem(@PathVariable Long id) {
        todoFacade.archiveTodoItem(id);
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
