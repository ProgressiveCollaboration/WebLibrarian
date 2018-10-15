package com.pc.model;

import java.io.Serializable;

import com.pc.enums.PricingType;

public class Pricing implements Serializable {
	private static final long serialVersionUID = 1L;

	public Pricing() {
	}

	public Pricing(double cost, String currency, PricingType type) {
		setUnitCost(cost);
		setPriceType(type);
		setUnitCostCurrency(currency);
	}

	public PricingType getPriceType() {
		return priceType;
	}

	public double getUnitCost() {
		return unitCost;
	}

	public String getUnitCostCurrency() {
		return unitCostCurrency;
	}

	public void setPriceType(PricingType priceType) {
		this.priceType = priceType;
	}

	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}

	public void setUnitCostCurrency(String unitCostCurrency) {
		this.unitCostCurrency = unitCostCurrency;
	}

	private PricingType priceType;
	private double unitCost;
	private String unitCostCurrency;

	@Override
	public String toString() {
		return "Pricing [priceType=" + priceType + ", unitCost=" + unitCost + ", unitCostCurrency=" + unitCostCurrency
				+ "]";
	}

}
