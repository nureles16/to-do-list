package intern.project.to_do.list.facade;
import intern.project.to_do.list.dto.ToDoListFilterDto;
import intern.project.to_do.list.entity.ToDoItem;
import java.util.List;

public interface TodoFacade {
    List<ToDoItem> listTodos(ToDoListFilterDto filter);
    ToDoItem createTodoItem(ToDoItem item);
    ToDoItem updateTodoItem(Long id, ToDoItem updatedItem);
    void deleteTodoItem(Long id);
    void archiveTodoItem(Long id);
}