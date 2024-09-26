package intern.project.to_do.list.service;

import intern.project.to_do.list.entity.TaskStatus;
import intern.project.to_do.list.entity.ToDoItem;
import intern.project.to_do.list.exceptions.ResourceNotFoundException;
import intern.project.to_do.list.repository.ToDoItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoItemService {

    private final ToDoItemRepository repository;

    public ToDoItem createToDoItem(ToDoItem item) {
        item.setCreatedDate(LocalDateTime.now());
        return repository.save(item);
    }

    public List<ToDoItem> getAllToDoItems() {
        return repository.findAll();
    }

    public ToDoItem getToDoItemById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("To-Do Item with id " + id + " not found"));
    }

    public ToDoItem updateToDoItem(Long id, ToDoItem updatedItem) {
        return repository.findById(id)
                .map(existingItem -> {
                    existingItem.setTitle(updatedItem.getTitle());
                    existingItem.setDescription(updatedItem.getDescription());
                    existingItem.setStatus(updatedItem.getStatus());
                    existingItem.setDueDate(updatedItem.getDueDate());
                    return repository.save(existingItem);
                }).orElseThrow(() -> new EntityNotFoundException("To-Do Item with id " + id + " not found"));
    }

    public void deleteToDoItem(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("To-Do Item with id " + id + " not found");
        }
        repository.deleteById(id);
    }

    public List<ToDoItem> getItemsByStatus(TaskStatus status) {
        return repository.findByStatus(status);
    }
}
