package com.pc.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
		publication.setCreatedBy("roomtek@gmail.com");
		publication.setCreatedDate(LocalDateTime.now());

		// details
		publication.setTitle("Things Fall Apart");
		publication.setDescription(
				"One of Chinua Achebe's many achievements in his acclaimed first novel, Things Fall Apart, is his relentlessly unsentimental rendering of Nigerian tribal life before and after the coming of colonialism. First published in 1958, just two years before Nigeria declared independence from Great Britain, the book eschews the obvious temptation of depicting pre-colonial life as a kind of Eden. Instead, Achebe sketches a world in which violence, war, and suffering exist, but are balanced by a strong sense of tradition, ritual, and social coherence. His Ibo protagonist, Okonkwo, is a self-made man. The son of a charming ne'er-do-well, he has worked all his life to overcome his father's weakness and has arrived, finally, at great prosperity and even greater reputation among his fellows in the village of Umuofia. Okonkwo is a champion wrestler, a prosperous farmer, husband to three wives and father to several children. He is also a man who exhibits flaws well-known in Greek tragedy:\r\n" + 
				"\r\n" + 
				"Okonkwo ruled his household with a heavy hand. His wives, especially the youngest, lived in perpetual fear of his fiery temper, and so did his little children. Perhaps down in his heart Okonkwo was not a cruel man. But his whole life was dominated by fear, the fear of failure and of weakness. It was deeper and more intimate than the fear of evil and capricious gods and of magic, the fear of the forest, and of the forces of nature, malevolent, red in tooth and claw. Okonkwo's fear was greater than these. It was not external but lay deep within himself. It was the fear of himself, lest he should be found to resemble his father.");
		publication.setGenre("African Literature");
		publication.setISBN_10("0385474547"); 
		publication.setPublicationType(PublicationType.BOOK);
		publication.setLanguage("English");
		publication.setBinding("Paperback");
		publication.setFirstPublishedDate(LocalDate.of(1994, 9, 1));
		publication.setEdition("Unabridged");
		publication.setNumberOfPages(209);

		{ // author
//			Author author = new Author();
//			author.setAka("Holman Bible Staff");
//			author.save();
			publication.getAuthorId().add("PER-0e48ddf6-7856-4b38-8dda-a98bf7ded9c6");
		}

		{ // publisher
			Publisher publisher = new Publisher();
			publisher.setPublisherName("William Heinemann");
			publisher.setPublisherWebsite( "https://www.penguin.co.uk/company/publishers/cornerstone/william-heinemann.html");
			publisher.save();
			publication.setPublisherId(publisher.getId());
		}

		{ // pricing
			Pricing buyPrice = new Pricing();
			buyPrice.setUnitCost(11.58);
			buyPrice.setUnitCostCurrency("USD");
			buyPrice.setPriceType(PricingType.BUY_NEW);
			publication.getPricinginformation().add(buyPrice);

			Pricing rentalPrice = new Pricing();
			rentalPrice.setUnitCost(0.25);
			rentalPrice.setUnitCostCurrency("USD");
			rentalPrice.setPriceType(PricingType.BUY_USED);
			publication.getPricinginformation().add(rentalPrice);
		}

		{ // shipping
			publication.setShippingInformation(new ShippingInformation());
			publication.getShippingInformation().setWeight(0.64, WeightUnit.LB);
			publication.getShippingInformation().setDimensions("5.2 x 0.6 x 8 inches");
		}

		System.out.println(publication.toString());
		System.out.println(publication.getShippingInformation().getWeight(WeightUnit.KG));

		publication.save();

		MDB.stopDB();
	}

}
