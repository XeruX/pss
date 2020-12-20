package utp.pss.patryklewandowski.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "sprints")
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sprint")
    private Long id;
    private String description;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateStart;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateEnd;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

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

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sprint)) return false;

        Sprint sprint = (Sprint) o;

        if (!id.equals(sprint.id)) return false;
        if (!description.equals(sprint.description)) return false;
        if (!dateStart.equals(sprint.dateStart)) return false;
        if (!dateEnd.equals(sprint.dateEnd)) return false;
        return project.equals(sprint.project);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + dateStart.hashCode();
        result = 31 * result + dateEnd.hashCode();
        result = 31 * result + project.hashCode();
        return result;
    }
}
