package com.motorlog.entity;

public enum Status {

    onHold("onHold", 0), working("working", 1), resolved("resolved", 2), irresolvable("irresolvable", 3), suspicious("suspicious", 4);

    private int		id;
    private String	name;


    Status(final String name, final int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;

    }
}
