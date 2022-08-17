package com.cj.freelanceapp.security;

import com.cj.freelanceapp.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * THIS CLASS DEFINES WHAT HAPPENS AFTER SUCCESSFUL AUTHENTICATION
 * IS MADE
 * author MILLION SHARBE
 * date jun 6 , 2022
 */
@Component
public class SuccessfullLoginHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    /**
     * REGIRECT USER TO THIER HOME PAGE
     * BASED ON THIER ROLE
     *
     * @param request
     * @param response
     * @param authentication
     * @throws ServletException
     * @throws IOException
     */
    /**
     * AUTHENTICATED USER
     */
    private User user;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        System.out.println(authentication.getPrincipal());
        if (user != null) {
            System.out.println(user);
            user = null;
        }
        /**
         * REDIRECTING STRING
         */
        String redirect = request.getContextPath();
        /**
         * GETTING THE AUTHENTICATED USER
         */
        user = (User) authentication.getPrincipal();
        /**
         * CHECK IT'S ROLE
         */
        if (user.getRole().equalsIgnoreCase("CUSTOMER")) {
            /**
             * IF CUSTOMER -> CUSTOMER HOME
             */
            redirect += "/customerhome";
        } else if (user.getRole().equalsIgnoreCase("FREELANCER")) {
            /**
             * IF FREELANCER -> FREELANCER HOME
             */
            redirect += "/freelancerhome";

        }
        /**
         * SEND THE RESPONSE USING THE REDIRECT
         * STRING ASSIGNED IN THE IF CLAUSE
         */
        response.sendRedirect(redirect);
    }

    public User getUser() {

        return user;
    }
}
