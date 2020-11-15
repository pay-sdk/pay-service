package org.paysdk.pay.repositories;

import org.paysdk.pay.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findByToken(String token);

    List<Project> findAllByUserId(Long id);
}
