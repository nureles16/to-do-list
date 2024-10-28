package intern.project.to_do.list.dto;
import intern.project.to_do.list.entity.ImportanceLevel;
import intern.project.to_do.list.entity.TaskStatus;
import lombok.Data;

@Data
public class ToDoListFilterDto {
    private TaskStatus status;
    private ImportanceLevel importanceLevel;
    private boolean active;
    private String sortBy;

    public ToDoListFilterDto() {
        this.status = TaskStatus.NOT_STARTED;
        this.importanceLevel = ImportanceLevel.MEDIUM;
        this.active = true;
        this.sortBy = "createdDate";
    }
}
