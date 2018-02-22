package com.edlo.network;

/**
 * Created by edward.lo on 2018/1/5.
 */

public enum HTTPMethod { // same as Volley value
	GET(0), POST(1), PUT(2), DELETE(3),
	HEAD(4), OPTIONS(5), TRACE(6), PATCH(7);

	private int value;

	HTTPMethod(int value) { this.value = value; }
	public int getValue() { return this.value; }
}
