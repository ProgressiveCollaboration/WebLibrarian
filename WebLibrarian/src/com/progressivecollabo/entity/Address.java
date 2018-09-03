package com.progressivecollabo.entity;

import java.io.Serializable;

import com.progressivecollabo.enums.AddressType;

/**
 * Represents a single address.
 */
public class Address implements Serializable
{

    private static final long serialVersionUID = 1L;

    public int id; // for sorting
    boolean primaryAddress;
    String streetAddressLine1;
    String city;
    String state;
    String zipCode;
    String country;
    AddressType addressType = AddressType.NONE;
}
