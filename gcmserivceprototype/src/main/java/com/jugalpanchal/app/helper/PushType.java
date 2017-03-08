package com.jugalpanchal.app.helper;

public enum PushType {

	Registration(1),
	OilChange(2),
	TireRotation(3);
	
    private int value;

    PushType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
