
package com.motorlog.entity;

public enum VehicleType {

    car("car", 0), motorbike("motorbike", 1);

    private int		id;
    private String	name;


    VehicleType(final String name, final int id) {
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
