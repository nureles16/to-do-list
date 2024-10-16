package intern.project.to_do.list.facade.impl;
import intern.project.to_do.list.dto.ToDoListFilterDto;
import intern.project.to_do.list.entity.ToDoItem;
import intern.project.to_do.list.facade.TodoFacade;
import intern.project.to_do.list.service.ToDoItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoFacadeImpl implements TodoFacade {
    private final ToDoItemService service;

    @Override
    public List<ToDoItem> listTodos(ToDoListFilterDto filter) {
        return service.getActiveToDoItems();
    }

    @Override
    public ToDoItem createTodoItem(ToDoItem item) {
        return service.createToDoItem(item);
    }

    @Override
    public ToDoItem updateTodoItem(Long id, ToDoItem updatedItem) {
        return service.updateToDoItem(id, updatedItem);
    }

    @Override
    public void deleteTodoItem(Long id) {
        service.deleteToDoItem(id);
    }

    @Override
    public void archiveTodoItem(Long id) {
        service.archiveToDoItem(id);
    }
}