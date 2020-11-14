package org.paysdk.pay.services.realizations;

import lombok.RequiredArgsConstructor;
import org.paysdk.pay.dto.ProjectRequest;
import org.paysdk.pay.models.Project;
import org.paysdk.pay.repositories.ProjectRepository;
import org.paysdk.pay.services.ProjectService;
import org.paysdk.pay.util.RandomTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceRealization implements ProjectService {

    @Autowired
    private final ProjectRepository projectRepository;

    @Override
    public Project findByToken(String token) {
        return projectRepository.findByToken(token);
    }

    @Override
    public Project save(Project project) {
        if (project.getToken() == null) {
            project.setToken(RandomTokenGenerator.nextString());
        }

        return projectRepository.save(project);
    }
}
