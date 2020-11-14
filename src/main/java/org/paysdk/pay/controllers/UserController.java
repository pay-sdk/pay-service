package org.paysdk.pay.controllers;

import lombok.RequiredArgsConstructor;
import org.paysdk.pay.dto.ProjectRequest;
import org.paysdk.pay.dto.ProjectResponse;
import org.paysdk.pay.models.Project;
import org.paysdk.pay.models.User;
import org.paysdk.pay.dto.UserRequest;
import org.paysdk.pay.services.ProjectService;
import org.paysdk.pay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.paysdk.pay.util.convertors.UserConvertor.*;
import static org.paysdk.pay.util.convertors.ProjectConvertor.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final ProjectService projectService;

    @PostMapping()
    private User register(@RequestBody UserRequest userRequest) {
        return userService.save(convert(userRequest));
    }

    @PostMapping("/project")
    private ProjectResponse createProject(@RequestBody ProjectRequest projectRequest) {
        User user = userService.findByTelegramId(projectRequest.getTelegramId());
        if (user == null) {
            return null;
        }
        Project project = convert(projectRequest);
        project.setUser(user);
        Project savedProject = projectService.save(project);
        return convert(savedProject);
    }
}
