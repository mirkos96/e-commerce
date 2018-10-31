package com.maks.web.controller;

import com.maks.service.IAccountServiceService;
import com.maks.service.IPasswordServiceService;
import com.maks.service.IRoleServiceService;
import com.maks.service.IUserServiceService;
import com.maks.service.modelDto.RoleDto;
import com.maks.service.modelDto.UserDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminUserController {

    private IUserServiceService userServiceService;
    private final IRoleServiceService roleServiceService;
    public static final Logger log = Logger.getLogger(AdminUserController.class);
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final IPasswordServiceService passwordServiceService;
    private final IAccountServiceService accountServiceService;

    @Autowired
    public AdminUserController(IUserServiceService userServiceService,
                               IRoleServiceService roleServiceService,
                               BCryptPasswordEncoder bCryptPasswordEncoder,
                               IPasswordServiceService passwordServiceService,
                               IAccountServiceService accountServiceService) {
        this.userServiceService = userServiceService;
        this.roleServiceService = roleServiceService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.passwordServiceService = passwordServiceService;
        this.accountServiceService = accountServiceService;
    }

    @Secured("ROLE_SUPERADMIN")
    @RequestMapping(value = "/admin/user-control", method = RequestMethod.GET)
    public ModelAndView showControlOverUser() {
        ModelAndView view = new ModelAndView("userControl");
        List<UserDto> userDtoList = userServiceService.getAllUsers();
        List<RoleDto> userRoleList = roleServiceService.getRoles();
        System.out.println(userDtoList.size() + "size of list");
        view.addObject("userDtoList", userDtoList);
        view.addObject("userRole", userRoleList);
        return view;
    }

    @Secured("ROLE_SUPERADMIN")
    @RequestMapping(value = "/admin/user-control/delete-user", method = RequestMethod.POST)
    public String deleteUser(HttpServletRequest request) {
        Long userId = Long.valueOf(request.getParameter("userId"));
        userServiceService.deleteUserById(userId);
        return "redirect:/admin/user-control";
    }

    @Secured("ROLE_SUPERADMIN")
    @RequestMapping(value = "/admin/user-control/set-new-role", method = RequestMethod.GET)
    public String setNewRole(HttpServletRequest request) {
        String role = request.getParameter("userRole");
        Long userId = Long.parseLong(request.getParameter("userId"));
        role = role.trim();
        log.info(role + " " + userId);
        roleServiceService.setNewRole(role, userId);
        return "redirect:/admin/user-control?action=roleSuccess";
    }

    @Secured("ROLE_SUPERADMIN")
    @RequestMapping(value = "/admin/user-control/set-new-password", method = RequestMethod.POST)
    public String setNewPassword(HttpServletRequest request) {
        Long userId = Long.parseLong(request.getParameter("userId"));
        String password = request.getParameter("password");
        password = bCryptPasswordEncoder.encode(password);
        passwordServiceService.setNewPassword(userId, password);
        return "redirect:/admin/user-control?action=passSuccess";
    }

    @Secured("ROLE_SUPERADMIN")
    @RequestMapping(value = "/admin/user-control/block-user", method = RequestMethod.POST)
    public String blockUser(HttpServletRequest request) {
        Long userId = Long.valueOf(request.getParameter("userId"));
        accountServiceService.blockUser(userId);
        return "redirect:/admin/user-control";
    }

    @Secured("ROLE_SUPERADMIN")
    @RequestMapping(value = "/admin/user-control/unblock-user", method = RequestMethod.POST)
    public String unblockUser(HttpServletRequest request) {
        Long userId = Long.valueOf(request.getParameter("userId"));
        accountServiceService.unblockUser(userId);
        return "redirect:/admin/user-control";
    }
}
