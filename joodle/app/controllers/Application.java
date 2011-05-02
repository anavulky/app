package controllers;

import models.User;
import play.data.validation.Required;
import play.mvc.*;

public class Application extends Controller {
    User user = null;

    /**
     * Render index page
     */
    public static void index() {
        User visitor = null;
        String email = session.get("userEmail");
        String pass = session.get("userPass");
        
        if( email != null )
            visitor = new User( email, pass );
        
        if( visitor == null )
            login();
        else {
            Integer visitorRole = visitor.role;
            render( email, visitorRole );
        }
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
    public static void authenticate(@Required String email, @Required String password ) {
        if( !models.Helpers.checkEmail(email) ) {
            flash.error("Please check your email!");
            login();
        }
        
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
        session.put("userPass", user.password);
    }

}