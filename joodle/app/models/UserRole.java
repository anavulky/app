package models;

/**
 * Main class to handle user roles
 * @author stas
 */
public class UserRole {
    public Integer roleId;
    public String roleName;

    /**
     * UserRole constructor
     * @param String role, user parsed role from email
     */
    public UserRole( String role ) {
        if( role != null ) {
            this.roleName = role;
            this.checkRole();
        }
    }

    /**
     * This will check and generate the role ID for current user
     */
    private void checkRole() {
        String role = this.getRoleName();
        if( role.equals("student") )
            this.roleId = 0; // student.ubbcluj.ro -> students
        else if(role.equals("student"))
            this.roleId = 1; // staff.ubbcluj.ro -> managers/secretary
        else if(role.equals("student"))
            this.roleId = 2; // cs.ubbcluj.ro -> CS professors
        else
            this.roleId = 3; // whatever.ubbcluj.ro
    }

    /**
     * Getter for current user role ID
     * @return Integer, the role ID
     */
    public Integer getRole() {
        return this.roleId;
    }

    /**
     * Getter for current user role name
     * @return String, the role name
     */
    public String getRoleName() {
        return this.roleName;
    }
}