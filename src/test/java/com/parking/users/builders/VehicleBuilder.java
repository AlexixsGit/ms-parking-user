package com.parking.users.builders;

import com.parking.users.model.Vehicle;

public class VehicleBuilder {

	private String plaque;
	private String type;

	public VehicleBuilder() {
		this.plaque = "MIG455";
		this.type = "car";
	}

	public VehicleBuilder withPlaque(String plaque) {
		this.plaque = plaque;
		return this;
	}

	public VehicleBuilder withType(String type) {
		this.type = type;
		return this;
	}

	public static VehicleBuilder aVehicle() {
		return new VehicleBuilder();
	}

	public Vehicle build() {
		return new Vehicle(this.plaque, this.type);
	}
}
