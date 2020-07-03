import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class EmployeeList extends ArrayList<Employee> {
    public Optional<Employee> findById(int id) {
        return this.stream().filter(employee -> employee.getID() == id).findFirst();
    }

    public static <T> List<T> toList(Optional<T> optional) {
        /*
        return optional.isPresent()
            ? Collections.singletonList(optional.get())
            : Collections.emptyList();
         */

        return optional
                .map(Collections::singletonList)
                .orElseGet(Collections::emptyList);
    }
}
