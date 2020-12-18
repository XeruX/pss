package utp.pss.patryklewandowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.pss.patryklewandowski.model.Project;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findByName(String name);
    List<Project> findByProjectAdmin(String projectAdmin);
}