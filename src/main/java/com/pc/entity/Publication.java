package com.pc.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

import com.pc.db.MDB;
import com.pc.enums.PublicationType;
import com.pc.model.Pricing;
import com.pc.model.ShippingInformation;

@Entity(value = MDB.DB_PUBLICATION, noClassnameStored = true)
public class Publication extends BaseEntity {

	private static final long serialVersionUID = 1L;

	public static String _title = "title";

	private String binding;
	private String description;
	private String edition;
	private LocalDate firstPublishedDate;
	private String genre; // LOV publication_genres
	private String ISBN_10;
	private String ISBN_13;
	private int numberOfPages;
	@Embedded
	private List<Pricing> pricinginformation = new ArrayList<>(1);
	private PublicationType publicationType = PublicationType.NONE;
	private List<String> authorId = new ArrayList<>(1);
	private List<String> imageURL = new ArrayList<>(1);
	private String publisherId;
	private String releaseCycle; // LOV publication_releasecycle
	private ShippingInformation shippingInformation;
	private String title;
	private String language;

	public String getBinding() {
		return binding;
	}

	public String getDescription() {
		return description;
	}

	public String getEdition() {
		return edition;
	}

	public LocalDate getFirstPublishedDate() {
		return firstPublishedDate;
	}

	public String getGenre() {
		return genre;
	}

	public String getISBN_10() {
		return ISBN_10;
	}

	public String getISBN_13() {
		return ISBN_13;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public List<Pricing> getPricinginformation() {
		return pricinginformation;
	}

	public PublicationType getPublicationType() {
		return publicationType;
	}

	public String getReleaseCycle() {
		return releaseCycle;
	}

	public ShippingInformation getShippingInformation() {
		return shippingInformation;
	}

	public String getTitle() {
		return title;
	}

	public void setBinding(String binding) {
		this.binding = binding;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public void setFirstPublishedDate(LocalDate firstPublishedDate) {
		this.firstPublishedDate = firstPublishedDate;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setISBN_10(String iSBN_10) {
		ISBN_10 = iSBN_10;
	}

	public void setISBN_13(String iSBN_13) {
		ISBN_13 = iSBN_13;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public void setPricinginformation(List<Pricing> pricinginformation) {
		this.pricinginformation = pricinginformation;
	}

	public void setPublicationType(PublicationType publicationType) {
		this.publicationType = publicationType;
	}

	public void setReleaseCycle(String releaseCycle) {
		this.releaseCycle = releaseCycle;
	}

	public void setShippingInformation(ShippingInformation shippingInformation) {
		this.shippingInformation = shippingInformation;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getAuthorId() {
		return authorId;
	}

	public void setAuthorId(List<String> authorId) {
		this.authorId = authorId;
	}

	public String getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	@Override
	public String toString() {
		return "Publication [binding=" + binding + ", description=" + description + ", edition=" + edition
				+ ", firstPublishedDate=" + firstPublishedDate + ", genre=" + genre + ", ISBN_10=" + ISBN_10
				+ ", ISBN_13=" + ISBN_13 + ", numberOfPages=" + numberOfPages + ", pricinginformation="
				+ pricinginformation + ", publicationType=" + publicationType + ", authorId=" + authorId + ", imageURL="
				+ imageURL + ", publisherId=" + publisherId + ", releaseCycle=" + releaseCycle
				+ ", shippingInformation=" + shippingInformation + ", title=" + title + ", language=" + language + "]";
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<String> getImageURL() {
		return imageURL;
	}

	public void setImageURL(List<String> imageURL) {
		this.imageURL = imageURL;
	}

}
