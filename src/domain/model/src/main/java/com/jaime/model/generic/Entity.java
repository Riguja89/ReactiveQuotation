package com.jaime.model.generic;

public class Entity<I extends Identity> {

    private final I id;

    public Entity(I id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        return id.equals(o);
    }

    public I identity() {
        return id;
    }


}
