package threadtasks;

import java.util.LinkedHashMap;

public class TaskLocks {
    public static void main(String[] args) {
        ReadWriteMap<Integer, String> linkedSafeMap = new ReadWriteMap<>(new LinkedHashMap<>());
    }

}
