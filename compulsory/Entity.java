package compulsory;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@ToString
public class Entity implements Comparable<Entity> {

    private static int currentIndex = 0;
    @Getter
    private final String name;
    @Getter
    private final int index;

    public Entity(@NonNull String name) {
        index = currentIndex;
        currentIndex++;
        this.name = name;
    }

    @Override
    public int compareTo(@NonNull Entity o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return this.name.equals(entity.name);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
