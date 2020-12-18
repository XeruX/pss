package utp.pss.patryklewandowski.model;

import javax.persistence.*;

@Entity(name = "story_points")
public class StoryPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_story_point")
    private Long id;
    private String description;
    private boolean isDone;
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public StoryPoint() {
        this.isDone = false;
    }

    public StoryPoint(String description, Task task) {
        this.description = description;
        this.task = task;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "StoryPoint [" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", isDone=" + isDone +
                ", task=" + task +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StoryPoint)) return false;

        StoryPoint that = (StoryPoint) o;

        if (isDone != that.isDone) return false;
        if (!id.equals(that.id)) return false;
        if (!description.equals(that.description)) return false;
        return task.equals(that.task);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + (isDone ? 1 : 0);
        result = 31 * result + task.hashCode();
        return result;
    }
}
