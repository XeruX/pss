package utp.pss.patryklewandowski.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_project")
    private Long id;
    private String name;
    private String description;
    private String projectAdmin;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinTable(name = "participations", // USUNĄĆ s na końcu !!!
            joinColumns = {@JoinColumn(name = "project_id", referencedColumnName = "id_project")},
            inverseJoinColumns = {@JoinColumn(name = "user_login", referencedColumnName = "login")})
    private List<User> participants;

    public Project() {
        participants = new ArrayList<>();
    }

    public Project(String name, String description, String projectAdmin) {
        this.name = name;
        this.description = description;
        this.projectAdmin = projectAdmin;
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

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> users) {
        this.participants = users;
    }

    public String getProjectAdmin() {
        return projectAdmin;
    }

    public void setProjectAdmin(String projectAdmin) {
        this.projectAdmin = projectAdmin;
    }

    @Override
    public String toString() {
        return "Project [" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", users=" + participants +
                ", projectAdmin='" + projectAdmin + '\'' +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;

        Project project = (Project) o;

        if (!id.equals(project.id)) return false;
        if (!name.equals(project.name)) return false;
        if (!description.equals(project.description)) return false;
        if (!participants.equals(project.participants)) return false;
        return projectAdmin.equals(project.projectAdmin);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + participants.hashCode();
        result = 31 * result + projectAdmin.hashCode();
        return result;
    }
}
