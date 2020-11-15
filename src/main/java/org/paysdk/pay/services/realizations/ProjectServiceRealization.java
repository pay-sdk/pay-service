package org.paysdk.pay.services.realizations;

import lombok.RequiredArgsConstructor;
import org.paysdk.pay.dto.ProjectRequest;
import org.paysdk.pay.models.Project;
import org.paysdk.pay.models.User;
import org.paysdk.pay.repositories.ProjectRepository;
import org.paysdk.pay.repositories.UserRepository;
import org.paysdk.pay.services.ProjectService;
import org.paysdk.pay.util.RandomTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceRealization implements ProjectService {

    @Autowired
    private final ProjectRepository projectRepository;

    private final UserRepository userRepository;

    @Override
    public Project findByToken(String token) {
        return projectRepository.findByToken(token);
    }

    @Override
    public List<Project> findByTelegramId(String telegramId) {
        if (telegramId == null) {
            return null;
        }

        User storedUser = userRepository.findByTelegramId(telegramId);

        List<Project> projects = null;

        if (storedUser != null) {
            projects = projectRepository.findAllByUserId(storedUser.getId());
        }

        return projects;
    }

    @Override
    public Project save(Project project) {
        if (project.getToken() == null) {
            project.setToken(RandomTokenGenerator.nextString());
        }

        return projectRepository.save(project);
    }
}
