package ru.olejik.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.olejik.enums.TaskStatus;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "todotable")
public class Task {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    public int getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public TaskStatus getTaskStatus() {
        return this.taskStatus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Task)) return false;
        final Task other = (Task) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$taskStatus = this.getTaskStatus();
        final Object other$taskStatus = other.getTaskStatus();
        if (this$taskStatus == null ? other$taskStatus != null : !this$taskStatus.equals(other$taskStatus))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Task;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $taskStatus = this.getTaskStatus();
        result = result * PRIME + ($taskStatus == null ? 43 : $taskStatus.hashCode());
        return result;
    }

    public String toString() {
        return "Task(id=" + this.getId() + ", description=" + this.getDescription() + ", taskStatus=" + this.getTaskStatus() + ")";
    }
}
