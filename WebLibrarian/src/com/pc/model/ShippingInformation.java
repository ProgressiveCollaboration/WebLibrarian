package com.pc.model;

import java.io.Serializable;

public class ShippingInformation implements Serializable {
	private static final long serialVersionUID = 1L;

	public enum WeightUnit {
		KG, LB;

		double convertToUnit(WeightUnit target) {
			if (this == KG && target == LB) {
				return 2.204622622;
			} else if (this == LB && target == KG) {
				return 0.45359237;
			} else
				return 1;
		}
	}

	private double weight;
	private WeightUnit weightUnit;
	private String dimensions;

	public String getDimensions() {
		return dimensions;
	}

	public double getWeight() {
		return weight;
	}

	public double getWeight(WeightUnit targetWeightUnit) {
		if (getWeightUnit().equals(targetWeightUnit))
			return getWeight();
		else {
			return getWeight() * getWeightUnit().convertToUnit(targetWeightUnit);
		}
	}

	public WeightUnit getWeightUnit() {
		return weightUnit;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public void setWeight(double weight, WeightUnit weightUnit) {
		setWeight(weight);
		setWeightUnit(weightUnit);
	}

	public void setWeightUnit(WeightUnit weightUnit) {
		this.weightUnit = weightUnit;
	}

	@Override
	public String toString() {
		return "ShippingInformation [weight=" + weight + ", weightUnit=" + weightUnit + ", dimensions=" + dimensions
				+ "]";
	}

}
