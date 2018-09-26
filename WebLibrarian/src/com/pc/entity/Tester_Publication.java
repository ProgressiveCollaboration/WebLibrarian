package com.pc.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.pc.db.MDB;
import com.pc.enums.PricingType;
import com.pc.enums.PublicationType;
import com.pc.model.Pricing;
import com.pc.model.ShippingInformation;
import com.pc.model.ShippingInformation.WeightUnit;

public class Tester_Publication {

	public static void main(String[] args) {
		
		MDB.startDB();

		Publication publication = new Publication();
		// db stuff
		publication.setUuid(UUID.randomUUID().toString());
		publication.setCreatedBy("roomtek@gmail.com");
		publication.setCreatedDate(LocalDateTime.now());

		// details
		publication.setTitle("The Bible");
		publication.setDescription(
				"The NKJV Large Print Personal Size Reference Bible features large, easy-to-read 12-point type in a convenient, easy-to-carry personal trim size that is perfect for devotional reading, personal study, or to carry and use at church.");
		publication.setGenre("Religion");
		publication.setISBN_10("1433606577");
		publication.setISBN_13("978-1433606571");
		publication.setPublicationType(PublicationType.BOOK);
		publication.setLanguage("English");
		publication.setBinding("Imitation Leather");
		publication.setFirstPublishedDate(LocalDate.of(2013, 1, 1));
		publication.setEdition("Large Print edition (October 1, 2013)");
		publication.setNumberOfPages(1568);
 
		{ // author
			Author author = new Author();
			author.setAka("Holman Bible Staff");
			author.save();
			publication.getAuthorId().add(author.getId());

		}

		{ // publisher
			Publisher publisher = new Publisher();
			publisher.setPublisherName("Holman Bible Publishers");
			publisher.setPublisherWebsite("bhpublishinggroup.com");
			publisher.save();
			publication.setPublisherId(publisher.getId());
		}

		{ // pricing
			Pricing buyPrice = new Pricing();
			buyPrice.setUnitCost(499.99);
			buyPrice.setUnitCostCurrency("NGN");
			buyPrice.setPriceType(PricingType.BUY_NEW);
			publication.getPricinginformation().add(buyPrice);

			Pricing rentalPrice = new Pricing();
			rentalPrice.setUnitCost(39.99);
			rentalPrice.setUnitCostCurrency("NGN");
			rentalPrice.setPriceType(PricingType.RENT_WEEK);
			publication.getPricinginformation().add(rentalPrice);
		}

		{ // shipping
			publication.setShippingInformation(new ShippingInformation());
			publication.getShippingInformation().setWeight(1.6, WeightUnit.LB);
			publication.getShippingInformation().setDimensions("5.6 x 1.3 x 8.6 inches");
		}

		System.out.println(publication.toString());
		System.out.println(publication.getShippingInformation().getWeight(WeightUnit.KG));

		publication.save();
		
		MDB.stopDB();
	}

}
