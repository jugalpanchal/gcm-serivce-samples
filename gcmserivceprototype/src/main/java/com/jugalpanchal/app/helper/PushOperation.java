package com.jugalpanchal.app.helper;

public enum PushOperation {

	Add(1),
	Edit(2),
	Delete(3),
	Deactivated(4);
	
    private int value;

    PushOperation(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
