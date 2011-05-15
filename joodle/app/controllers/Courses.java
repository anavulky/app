package controllers;

import java.util.List;
import models.Course;
import play.mvc.*;
import play.mvc.results.NotFound;

/**
 * Courses controller
 * @author stas
 */
public class Courses extends Controller {
    /**
     * Render all courses
     */
    public static void index() {
        List<Course> courses = Course.findAll();
        render( courses );
    }
    
    /**
     * Render a course
     * @param id, course ID
     */
    public static void course( Long id ) {
        Course course = Course.findById( id );
        
        if( course == null )
            throw new NotFound(null);
        
        render( course );
    }
    
    /**
     * Render course editor for course ID
     * @param id, the course ID
     */
    public static void edit( Integer id ) {
        render();
    }

    /**
     * Not found handler
     */
    private static void not_found() {
        render();
    }
}
