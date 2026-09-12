package ro.comodinoh.mirror;

import java.util.Arrays;
import java.util.Objects;

public class MethodID {
    private String name;
    private Class<?>[] params;

    MethodID(String name, Class<?>... params) {
        this.name = name;
        this.params = Arrays.copyOf(params, params.length);
    }

    public String getName() {
        return name;
    }

    public Class<?>[] getParams() {
        return params;
    }
}
