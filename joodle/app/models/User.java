package models;

import javax.persistence.*;
import play.db.jpa.*;
import play.libs.Codec;

/**
 * Main user class
 * @author stas
 */
@Entity
public class User extends Model {
    public String email;
    public String password;
    public String name;
    public Integer role;

    public User( String email, String password ) {
        this.email = email;
        this.password = Codec.hexMD5(password);
        this.name = this.parseName(email);
        this.role = new UserRole( this.parseRole(email) ).getRole();
    }

    /**
     * Parses user email and generates a short name we can use for fast identification
     * @param String email, user email
     * @return String, parsed user name
     */
    private String parseName( String email ) {
        String[] parsedEmail = email.split("@");
        if( parsedEmail.length > 0 )
            return parsedEmail[0];
        else
            return null;
    }

    /**
     * Parses user email and returns it's role name
     * @param String email, user email
     * @return String, the role name
     */
    private String parseRole( String email ) {
        String[] parsedRole = null;
        String[] parsedEmail = email.split("@");
        if( parsedEmail.length >= 2 )
            if ( parsedEmail[1].length() > 0 )
                parsedRole = parsedEmail[1].split(".");
        
        if( parsedRole != null && parsedRole.length > 1 )
            return parsedRole[0]; //<role>.ubbcluj.ro
        else
            return null;
    }

    /**
     * Find a user by his email
     * @param String email, to be found
     * @return User object
     */
    public static User findByEmail(String email) {
        return new User(email, "");
        // TODO: find a way to check email against the UBB users database
        //return find("email", email).first();
    }

    /**
     * Check if authentication is OK
     * @param String password, to be checked
     * @return Boolean, true if OK, false on error
     */
    public static Boolean checkPass( String password ) {
        // TODO: integrate with scs.ubbcluj.ro, send a dummy POST and check the response code
        return true;
    }
}
