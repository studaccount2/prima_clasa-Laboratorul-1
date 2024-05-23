package compulsory;

import lombok.NonNull;
import lombok.ToString;

@ToString(callSuper = true)
public class Student extends Entity {
    public Student(@NonNull String name) {
        super(name);
    }
}
