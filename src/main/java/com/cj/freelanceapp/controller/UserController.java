package com.cj.freelanceapp.controller;

import com.cj.freelanceapp.ServiceImp.CustomerServiceImp;
import com.cj.freelanceapp.ServiceImp.FreelancerServiceImp;
import com.cj.freelanceapp.ServiceImp.SkillServiceImp;
import com.cj.freelanceapp.dto.SearchDTO;
import com.cj.freelanceapp.dto.SignUpDTO;
import com.cj.freelanceapp.exception.EthioFreelancingApplicationException;
import com.cj.freelanceapp.helpers.ROLE;
import com.cj.freelanceapp.model.Customer;
import com.cj.freelanceapp.model.Freelancer;
import com.cj.freelanceapp.model.User;
import com.cj.freelanceapp.security.SuccessfullLoginHandler;
import com.cj.freelanceapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RestController
public class UserController
        /**
         * USER REST END POINT DEFINITION CLASS
         * MAIN USER RELATED REQUESTS ARE SENT AND
         * ACCEPTED IN THIS CLASS
         * AUTHOR THOMAS FASIL
         * DATE JUN 21 , 2022
         */
{
    /**
     * USER SERVICE OBJECT INJECTION
     */
    @Autowired
    private UserService userService;
    /**
     * SUCCESSFULL LOGIN HANDLER INJECTION
     */
    @Autowired
    SuccessfullLoginHandler successfullLoginHandler;
    /**
     * CUSTOMER SERVICE INJECTION
     */
    @Autowired
    CustomerServiceImp customerServiceImp;
    /**
     * FREELANCER SERVICE INJECTION
     */
    @Autowired
    FreelancerServiceImp freelancerServiceImp;
    /**
     * SKILL SERVICE INJECTION
     */
    @Autowired
    private SkillServiceImp skillServiceImp;

    /**
     * MAX PAGE RESULT
     */
    @Value("${max.result.per.page}")
    private int maxResults;
    /**
     * MAX PAGINATION SIZE
     */
    @Value("${max.card.display.on.pagination.tray}")
    private int maxPaginationTraySize;

    /**
     * @return INDEX PAGE
     */
    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    /**
     * @param page
     * @param size
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/home")
    public ModelAndView home(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                             @RequestParam(value = "size", defaultValue = "4", required = false) Integer size,
                             HttpServletRequest request, HttpServletResponse response) {

        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        List<String> list = new ArrayList<>();
        authorities.forEach(e -> {
            list.add(e.getAuthority());
        });

        ModelAndView modelAndView = new ModelAndView();
        if (list.contains(ROLE.ADMIN.name())) {
            modelAndView.setViewName("home");
            Page<User> allUsers = userService.listUsers(PageRequest.of(page, size, Sort.by("fullName")));
            modelAndView.addObject("allUsers", allUsers);
            modelAndView.addObject("maxTraySize", size);
            modelAndView.addObject("currentPage", page);
        } else {
            modelAndView.setViewName("user-home");
            User user = userService.findUserByEmail(request.getUserPrincipal().getName());
            modelAndView.addObject("currentUser", user);
        }

        return modelAndView;
    }

    /**
     * @param page
     * @param size
     * @param searchTerm
     * @return
     */
    @GetMapping("/searchBox")
    public ModelAndView searchByTerm(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                     @RequestParam(value = "size", defaultValue = "4", required = false) Integer size,
                                     @RequestParam(value = "searchTerm", required = false) String searchTerm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        Page<User> allUsers = userService.searchByTerm(searchTerm.trim(), PageRequest.of(page, size, Sort.by("fullName")));
        modelAndView.addObject("allUsers", allUsers);
        modelAndView.addObject("maxTraySize", size);
        modelAndView.addObject("currentPage", page);
        return modelAndView;
    }

    /**
     * @return
     */
    @GetMapping("/search")
    public ModelAndView search() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");
        return modelAndView;
    }

    /**
     * @param searchDto
     * @return
     */
    @PostMapping("/searchSubmit")
    public ModelAndView searchSubmit(@ModelAttribute SearchDTO searchDto) {
        List<User> result = userService.searchBy(searchDto.getSearchKeyword(), searchDto.getCriteria());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");
        modelAndView.addObject("result", result);
        return modelAndView;
    }

    /**
     * @return
     */
    @GetMapping("/addNewUser")
    public ModelAndView addNewUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("create-user");
        return modelAndView;
    }


    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ModelAndView register(@RequestParam("fullName") String fullName,
                                 @RequestParam("phoneNumber") String phoneNumber,
                                 @RequestParam("email") String email,
                                 @RequestParam("password") String password,
                                 @RequestParam("confirmPassword") String confirmPassword,
                                 @RequestParam("role") String role) {
        /**
         * model and view object
         */
        ModelAndView modelAndView = new ModelAndView();
        /**
         * redirecting string
         */

        SignUpDTO signUpDTO = SignUpDTO
                .builder()
                .fullName(fullName)
                .phoneNumber(phoneNumber)
                .email(email)
                .password(password)
                .confirmPassword(confirmPassword)
                .role(role)
                .build();

        String result = "redirect:/";
        User dbUser = userService.findUserByEmail(signUpDTO.getEmail());
        if (signUpDTO.getFullName() == null || signUpDTO.getFullName().trim().isEmpty()) {
            result = "redirect:/addNewUser?error=Enter valid fist name";
        } else if (signUpDTO.getPhoneNumber() == null || signUpDTO.getPhoneNumber().trim().isEmpty()) {
            result = "redirect:/addNewUser?error=Enter valid last name";
        } else if (signUpDTO.getEmail() == null || signUpDTO.getEmail().trim().isEmpty()) {
            result = "redirect:/addNewUser?error=Enter valid email";
        } else if (signUpDTO.getPassword() == null || signUpDTO.getPassword().trim().isEmpty()) {
            result = "redirect:/addNewUser?error=Enter valid password";
        } else if (signUpDTO.getPassword().equals(signUpDTO.getConfirmPassword())) {
            result = "redirect:/addNewUser?error=Password mis-match";
        } else if (StringUtils.isEmpty(signUpDTO.getRole())) {
            result = "redirect:/addNewUser?error=Select a valid Role";
        }

        if (dbUser == null) {
            User user = User.builder()
                    .fullName(signUpDTO.getFullName())
                    .email(signUpDTO.getEmail())
//                    .isActive(signUpDTO.isActive())
                    .password(signUpDTO.getPassword())
                    .role(signUpDTO.getRole())
                    .phoneNumber(signUpDTO.getPhoneNumber())
                    .build();
            /**
             * CHECK USER ROLE IF IT'S CUSTOMER JUST
             * SAVE THE USER AND CUSTOMER TO DATABASE
             * ELSE IF USER ROLE IS FREELANCER REDIRECT IT
             * TO CREATE UR PROFILE PAGE TO SET UP HIS
             * PROFILE
             */
            if (user.getRole().equalsIgnoreCase("CUSTOMER")) {
                /**
                 * CUSTOMER ROLE
                 */
                Customer customer =
                        Customer.
                                builder()
                                .user(user)
                                .build();
                userService.saveUser(user);
                customerServiceImp.add_customer(customer);
                modelAndView.setViewName("login");
            } else if (user.getRole().equalsIgnoreCase("FREELANCER")) {
                /**
                 * FREELANCER ROLE
                 */
                /**
                 * CREATE FREELANCER OBJECT AND SAVE IT
                 * THE DATABASE WITH ITS USER ACCOUNT
                 */
                Freelancer freelancer = Freelancer
                        .builder()
                        .user(user)
                        .build();
                userService.saveUser(user);
                freelancerServiceImp.add_freelancer(freelancer);
                modelAndView.setViewName("login");
            }
        } else {
            throw new EthioFreelancingApplicationException();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/uploadImage")
    public ModelAndView uploadImage(@RequestParam("image") MultipartFile image) {
//        System.out.println(image.getOriginalFilename());
        String fileName = image.getOriginalFilename();
        String newFileName = "C:\\Users\\Thinkpad\\Desktop\\FYP\\freelanceapp\\UserImages\\" + fileName;
        try {
            if (!image.isEmpty()) {
                image.transferTo(new File(newFileName));
                String imagePath = "\\UserImages\\"+fileName;
                imagePath.replace('\\','/');
                User user = successfullLoginHandler.getUser();
                user.setImage(imagePath);
                userService.saveUser(user);
            } else {
                throw new EthioFreelancingApplicationException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView("profile");
    }
    /**
     * @param userId
     * @return
     */
    @RequestMapping("/delete/{userId}")
    public String delete(@PathVariable Long userId) {
        userService.removeById(userId);
        return "redirect:/";
    }

    /**
     * @return
     */
    @RequestMapping("/removeAll")
    public Boolean removeAll() {
        return userService.removeAll();
    }

    /**
     * @return
     */
    @RequestMapping("/403")
    public ModelAndView accessDenied() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("403");
        return modelAndView;
    }

    @RequestMapping("/profile")
    public ModelAndView profile() {
        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("user", successfullLoginHandler.getUser());
        return modelAndView;
    }
    /**
     * @return
     */
    @RequestMapping("/about")
    public ModelAndView about() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("about");
        return modelAndView;
    }

}
