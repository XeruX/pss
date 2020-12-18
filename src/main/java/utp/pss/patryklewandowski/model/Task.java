package utp.pss.patryklewandowski.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_task")
    private Long id;
    private String name;
    private String description;
    private Byte priority;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "task",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<StoryPoint> storyPoints;
    @ManyToOne
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User assignedUser;

    public Task() {
        storyPoints = new ArrayList<>();
        this.status = Status.BACKLOG;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte getPriority() {
        return priority;
    }

    public void setPriority(Byte priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status progress) {
        this.status = progress;
    }

    public List<StoryPoint> getStoryPoints() {
        return storyPoints;
    }

    public void setStoryPoints(List<StoryPoint> storyPoints) {
        this.storyPoints = storyPoints;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    @Override
    public String toString() {
        return "Task [" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", progress=" + status +
                ", storyPoints=" + storyPoints +
                ", sprint=" + sprint +
                ", assignedUser=" + assignedUser +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;

        Task task = (Task) o;

        if (!id.equals(task.id)) return false;
        if (!name.equals(task.name)) return false;
        if (!description.equals(task.description)) return false;
        if (!priority.equals(task.priority)) return false;
        if (status != task.status) return false;
        if (storyPoints != null ? !storyPoints.equals(task.storyPoints) : task.storyPoints != null) return false;
        if (!sprint.equals(task.sprint)) return false;
        return assignedUser.equals(task.assignedUser);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + priority.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + (storyPoints != null ? storyPoints.hashCode() : 0);
        result = 31 * result + sprint.hashCode();
        result = 31 * result + assignedUser.hashCode();
        return result;
    }

    private enum Status {
        BACKLOG,
        TO_DO,
        IN_PROGRESS,
        QA,
        DONE
    }
}
