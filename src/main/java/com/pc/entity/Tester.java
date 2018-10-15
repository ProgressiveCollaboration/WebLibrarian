package com.pc.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.pc.db.MDB;
import com.pc.enums.PricingType;
import com.pc.enums.PublicationType;
import com.pc.model.Pricing;
import com.pc.model.ShippingInformation;
import com.pc.model.ShippingInformation.WeightUnit;

public class Tester {

	public static void main(String[] args) {

		MDB.startDB();

//		{ // publisher
//			Publisher publisher = new Publisher();
//			publisher.setPublisherName("Kokila");
//			publisher.setPublisherWebsite("penguin.com/publishers/kokila");
//			publisher.setCopyright("");
//			publisher.setImageUrl("http://www.penguin.com/wp-content/uploads/2018/08/Imprint-page-logo-color.png");
//			publisher.setCreatedBy("roomtek@gmail.com");
//			publisher.save();
//		}

		{
			Author author = new Author();
			author.setFirstName("Jodi");
			author.setLastName("Picoult");
			author.setGender("Female");
			author.setBioGraphy(
					"Jodi Picoult is the #1 New York Times bestselling author of twenty-five novels, including Small Great Things, Leaving Time, The Storyteller, Lone Wolf, Sing You Home, House Rules, Handle with Care, Change of Heart, Nineteen Minutes, and My Sister’s Keeper. She is also the author, with daughter Samantha van Leer, of two young adult novels, Between the Lines and Off the Page. Picoult lives in New Hampshire with her husband and three children.");
			author.setDateOfBirth(LocalDate.parse("May 19, 1966", DateTimeFormatter.ofPattern("MMM dd, yyyy")));
			author.save();

			Publication pub = new Publication();
			pub.setPublisherId("PUBc48f9998-6791-41cb-b1fb-4548378c308e");
			pub.setBinding("Paperback");
			pub.setEdition("Mass Market Paperback");
			pub.setTitle("The Hanging Tree");
			pub.setNumberOfPages(304);
			pub.setPublicationType(PublicationType.BOOK);
			pub.getImageURL().add("https://images.penguinrandomhouse.com/cover/9780756409678");

			ShippingInformation shipi = new ShippingInformation();
			shipi.setDimensions("4.2 x 0.8 x 6.8 inches");
			shipi.setWeight(0.5, WeightUnit.LB);

			pub.setShippingInformation(shipi);
			pub.getPricinginformation().add(new Pricing(7.19, "USD", PricingType.BUY_NEW));

			pub.setFirstPublishedDate(LocalDate.parse("Jan 31, 2017", DateTimeFormatter.ofPattern("MMM dd, yyyy")));
			pub.setGenre("Urban Fantasy");
			pub.setLanguage("English");
			pub.setDescription(
					"Ben Aaronovitch’s bestselling Rivers of London urban fantasy series • “The perfect blend of CSI and Harry Potter.” —io9\r\n"
							+ "\r\n"
							+ "Suspicious deaths are not usually the concern of Police Constable Peter Grant or the Folly—London’s police department for supernatural cases—even when they happen at an exclusive party in one of the flats of the most expensive apartment blocks in London. But the daughter of Lady Ty, influential goddess of the Tyburn river, was there, and Peter owes Lady Ty a favor.\r\n"
							+ "\r\n"
							+ "Plunged into the alien world of the super-rich, where the basements are bigger than the houses, where the law is something bought and sold on the open market, a sensible young copper would keep his head down and his nose clean. \r\n"
							+ "\r\n" + "But this is Peter Grant we’re talking about.\r\n" + "\r\n"
							+ "He’s been given an unparalleled opportunity to alienate old friends and create new enemies at the point where the world of magic and that of privilege intersect. Assuming he survives the week…\r\n"
							+ "\r\n" + "");

			pub.setISBN_10("0756409675");
			pub.setISBN_13("978-0756409678");
			pub.getAuthorId().add(author.getId());
			System.out.println(pub);
			pub.save();
		}

		MDB.stopDB();
	}

}
