package Entity;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-04T21:29:19")
@StaticMetamodel(Todo.class)
public class Todo_ { 

    public static volatile SingularAttribute<Todo, Date> date;
    public static volatile SingularAttribute<Todo, String> task;
    public static volatile SingularAttribute<Todo, String> bio;
    public static volatile SingularAttribute<Todo, Long> id;

}