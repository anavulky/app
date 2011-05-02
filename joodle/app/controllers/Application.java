package controllers;

import models.User;
import play.mvc.*;

public class Application extends Controller {

    /**
     * Render index page
     */
    public static void index() {
        if( session.get("userEmail") != null )
            render();
        else
            login();
    }

    /**
     * Render login page
     */
    public static void login() {
        render();
    }

    /**
     * Render logout page
     */
    public static void logout() {
        flash.success("Deauthentication was successful!");
        session.clear();
        login();
    }

    /**
     * Authenticate user using email and password
     * @param String email, user email
     * @param String password, user password
     */
    public static void authenticate( String email, String password ) {
        User user = User.findByEmail(email);
        if( user == null || !user.checkPass(password) ) {
            flash.error("Authentication failed, check your credentials!");
            flash.put("email", email);
            login();
        }
        connect(user);
        flash.success("Howdy %s!", user.name);
        index();
    }

    /**
     * Create a session on successful user autentication
     * @param User user, user object
     */
    static void connect(User user) {
        session.put("userEmail", user.email);
    }

}