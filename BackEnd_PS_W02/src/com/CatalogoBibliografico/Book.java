package com.CatalogoBibliografico;

public class Book extends Generic {

	private String author;
	private String genre;

	public Book(String publishYear, String title, String pages, String author, String genre) {
		super(publishYear, title, pages);
		this.author = author;
		this.genre = genre;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return super.title + "@" + this.author + "@" + this.genre + "@" + super.publishYear + "@" + super.pages + "@"
				+ super.codiceISBN + ";";
	}

}
