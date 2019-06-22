package com.sda.group.family_apps.configs;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("default")
@RestController
@RequestMapping("/profile")
public class DefaultProfileController {

    @RequestMapping
    public String prod() {
        return "DEFAULT";
    }
}
