package models;

import java.util.*;
import javax.persistence.*;

import play.data.validation.*;
import play.db.jpa.Model;

/**
 * Course Model
 * @author stas
 */
@Entity
public class Course extends Model {
    /**
     * Course title
     */
    @Required
    public String title;
    
    /**
     * Course description
     */
    @Lob
    @Required
    @MaxSize(10000)
    public String content;

    /**
     * Course creation date
     */
    @Required
    public Date timestamp;
    
    /**
     * Course author
     */
    @Required
    public String author_email;
    
    /**
     * Course constructor
     * @param t String, course title
     * @param c String, course content
     * @param e String, course author email
     */
    public Course( String t, String c, String e ) {
        title = t;
        content = c;
        author_email = e;
        timestamp = new Date();
    }
    
    /**
     * Returns course content
     * @return String
     */
    public String getContent() {
        return content;
    }

    /**
     * Set course content
     * @param content String
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Returns course title
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set course title
     * @param title String
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Returns course creation date
     * @return Date
     */
    public Date getTimestamp() {
        return timestamp;
    }
    
    /**
     * Returns course author email
     * @return String 
     */
    public String getAuthor() {
        return author_email;
    }
    
}
