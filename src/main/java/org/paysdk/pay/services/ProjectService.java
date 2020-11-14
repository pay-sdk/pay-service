package org.paysdk.pay.services;

import org.paysdk.pay.dto.ProjectRequest;
import org.paysdk.pay.models.Project;
import org.paysdk.pay.models.User;

public interface ProjectService {

    Project findByToken(String token);

    Project save(Project project);
}
