package intern.project.to_do.list.repository;
import intern.project.to_do.list.entity.ImportanceLevel;
import intern.project.to_do.list.entity.TaskStatus;
import intern.project.to_do.list.entity.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {
    List<ToDoItem> findByStatus(TaskStatus status);
    List<ToDoItem> findByImportanceLevel(ImportanceLevel importanceLevel);
    List<ToDoItem> findByDueDate(LocalDateTime dueDate);
    List<ToDoItem> findByDueDateBeforeAndStatusNot(LocalDateTime dueDate, TaskStatus status);
    List<ToDoItem> findByDueDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<ToDoItem> findByActiveTrue();
    List<ToDoItem> findAllByStatusAndImportanceLevelAndActive(TaskStatus status, ImportanceLevel importanceLevel, Boolean active);
}
