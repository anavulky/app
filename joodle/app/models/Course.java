package models;

import java.util.Date;
import javax.persistence.*;
import play.db.jpa.*;

/**
 * Course Model
 * @author stas
 */
@Entity
public class Course extends Model {
    /**
     * Course title
     */
    @Lob
    public String title = null;
    
    /**
     * Course description
     */
    public String content = null;

    /**
     * Course creation date
     */
    public Date timestamp = null;
    
    /**
     * Course author
     */
    public String author_email = null;
    
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
