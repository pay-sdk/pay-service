package org.paysdk.pay.util.convertors;

import org.paysdk.pay.dto.ProjectRequest;
import org.paysdk.pay.dto.ProjectResponse;
import org.paysdk.pay.dto.UserRequest;
import org.paysdk.pay.dto.UserResponse;
import org.paysdk.pay.models.Project;
import org.paysdk.pay.models.User;

public class ProjectConvertor {

    public static Project convert(ProjectRequest projectRequest) {
        return Project.builder()
                .name(projectRequest.getName())
                .build();
    }

    public static ProjectResponse convert(Project project) {
        return ProjectResponse.builder()
                .name(project.getName())
                .token(project.getToken())
                .build();
    }
}
