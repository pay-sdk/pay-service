package org.paysdk.pay.repositories;

import org.paysdk.pay.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Project findByToken(String token);
}
