package com.progressivecollabo.entity;

import java.util.ArrayList;
import java.util.UUID;

import com.progressivecollabo.entity.AudioVideo.AudioBook;
import com.progressivecollabo.entity.BaseEntity.Pricing;

public class Tester
{

    public static void main(String[] args)
    {

        AudioVideo musicVideoA = new AudioVideo();
        musicVideoA.uuid = "XYZ-ABC-" + UUID.randomUUID().toString();
        musicVideoA.pricinginformation = new ArrayList<>();

        Pricing pricing = new Pricing();
        pricing.priceType = "RENTAL";
        pricing.unitCost = 0.2;
        pricing.unitCostCurrency = "$";

        musicVideoA.pricinginformation.add(pricing);

        AudioBook abook;

    }

}
