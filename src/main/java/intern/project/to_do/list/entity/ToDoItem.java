package intern.project.to_do.list.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ToDoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Title is mandatory")
    @Size(min = 1, max = 50, message = "Title must be between 1 and 50 characters")
    @Column(nullable = false)
    private String title;

    @Size(max = 200, message = "Description must be up to 200 characters")
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Importance level is mandatory")
    @Column(nullable = false)
    private ImportanceLevel importanceLevel = ImportanceLevel.MEDIUM;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status is mandatory")
    @Column(nullable = false)
    private TaskStatus status = TaskStatus.NOT_STARTED;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @FutureOrPresent(message = "Due date cannot be in the past")
    private LocalDateTime dueDate;

    @Column(nullable = false)
    private boolean active = true;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }
}
