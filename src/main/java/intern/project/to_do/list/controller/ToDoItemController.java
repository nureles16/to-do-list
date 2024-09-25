package intern.project.to_do.list.controller;

import intern.project.to_do.list.entity.TaskStatus;
import intern.project.to_do.list.entity.ToDoItem;
import intern.project.to_do.list.service.ToDoItemService;
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

    @PostMapping
    public ResponseEntity<ToDoItem> createToDoItem(@RequestBody ToDoItem item) {
        ToDoItem createdItem = service.createToDoItem(item);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    @GetMapping
    public List<ToDoItem> getAllToDoItems() {
        return service.getAllToDoItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoItem> getToDoItemById(@PathVariable Long id) {
        ToDoItem item = service.getToDoItemById(id); // Directly get the ToDoItem, no need for map
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDoItem> updateToDoItem(@PathVariable Long id, @RequestBody ToDoItem item) {
        return new ResponseEntity<>(service.updateToDoItem(id, item), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDoItem(@PathVariable Long id) {
        service.deleteToDoItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/status/{status}")
    public List<ToDoItem> getItemsByStatus(@PathVariable TaskStatus status) {
        return service.getItemsByStatus(status);
    }
}