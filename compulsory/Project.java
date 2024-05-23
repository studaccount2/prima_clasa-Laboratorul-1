package compulsory;

import lombok.NonNull;
import lombok.ToString;

@ToString(callSuper = true)
public class Project extends Entity {
    public Project(@NonNull String name) {
        super(name);
    }
}
