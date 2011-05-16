package controllers;

import play.data.validation.Required;
import play.mvc.*;

import models.*;

public class Application extends Controller {
    /**
     * Render index page
     */
    public static void index() {
        currentUserCan( 0 );
        render();
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
        session.put("userRoleId", user.role );
    }
    
    /**
     * Check if current user has access to roleId level
     * @param roledId, the roleId level to check
     */
    public static Boolean currentUserCan( Integer roledId ) {
        Boolean okey = false;
        String email = session.get("userEmail");
        Integer userRoleId = Integer.decode( session.get("userRoleId") );
        
        if( email != null )
            okey = true;
        
        if( okey && userRoleId >= roledId )
            okey = true;
        else
            okey = false;
        
        if( okey == false )
            Application.login();
        
        return okey;
    }
}