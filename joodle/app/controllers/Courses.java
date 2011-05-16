package controllers;

import java.util.List;
import play.mvc.*;
import play.mvc.results.NotFound;

import models.*;

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
    public static void edit( Long id ) {
        Application.currentUserCan( 1 );
        
        Course course = Course.findById( id );
        if( course == null )
            throw new NotFound(null);
        
        render( course );
    }
    
    /**
     * Render course editor for adding new course
     */
    public static void add() {
        Application.currentUserCan( 1 );
        render();
    }
    
    /**
     * Handle POST form to update a course
     * @param id, course to be updated
     */
    public static void update( Long id ) {
        Application.currentUserCan( 1 );
        
        String title = params.get( "course[title]", String.class );
        String content = params.get( "course[content]", String.class );
        Long old_id = params.get( "course[title]", Long.class );
        Course course = Course.findById( id );
        if( !id.equals(old_id) && course != null ) {
            course.setTitle(title);
            course.setContent(content);
            course.save();
        }
        edit(id);
    }
    
    /**
     * Handle POST form to create a course
     */
    public static void create() {
        Application.currentUserCan( 1 );
        
        String author = session.get("userEmail");
        String title = params.get( "course[title]", String.class );
        String content = params.get( "course[content]", String.class );
        
        if( title.length() > 0 && content.length() > 0 ) {
            Course course = new Course(title, content, author);
            course.save();
        }
        
        index();
    }
}
