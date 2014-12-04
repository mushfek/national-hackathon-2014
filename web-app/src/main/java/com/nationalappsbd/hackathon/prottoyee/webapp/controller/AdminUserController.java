package com.nationalappsbd.hackathon.prottoyee.webapp.controller;

import com.nationalappsbd.hackathon.prottoyee.webapp.service.AdminUserService;
import com.nationalappsbd.hackathon.prottoyee.webapp.persistance.entity.AdminUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @author Abdullah Al Mamun Oronno (www.oneous.com)
 */

@Controller
public class AdminUserController {
    private static final Logger log = LoggerFactory.getLogger(AdminUserController.class);

    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping("/admin/users")
    public String getUserList(Model model) {
        log.debug("getUserList");
        model.addAttribute(adminUserService.findAllUsers());

        return "users";
    }

    @RequestMapping(value = "/admin/users/create", method = RequestMethod.GET)
    public String createNewUserForm(Model model) {
        model.addAttribute("adminUser", new AdminUser());
        return "users-create";
    }

    @RequestMapping(value = "/admin/users/create", method = RequestMethod.POST)
    public String createNewUser(@Valid AdminUser adminUser, BindingResult bindingResult) {
        log.debug("createNewUser, username={}, email={}, errorCount={}", adminUser.getUsername(), adminUser.getEmail(), bindingResult.getErrorCount());

        if (adminUserService.findUserByUsername(adminUser.getUsername()) != null) {
            bindingResult.rejectValue("username", "error.username.exist");
        }

        if (adminUserService.findUserByEmail(adminUser.getEmail()) != null) {
            bindingResult.rejectValue("email", "error.email.exist");
        }

        if (bindingResult.hasErrors()) {
            return "users-create";
        }

        adminUser.setEnabled(true);
        adminUserService.saveUser(adminUser);

        return "redirect:/admin/users";
    }

}
