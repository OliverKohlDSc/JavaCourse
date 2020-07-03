import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// apt
// Annotation Processin g Tool
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestForEnhancement {
    int     id();
    String  synpsis();
    String  engineer()  default "[unassigned]";
    String  date()      default "[unimplmented]";
}
