package org.paysdk.pay.services;

import org.paysdk.pay.models.Project;

import java.util.List;

public interface ProjectService {

    Project findByToken(String token);

    List<Project> findByTelegramId(String telegramId);

    Project save(Project project);
}
