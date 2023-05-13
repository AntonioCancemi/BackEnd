package com.CatalogoBibliografico;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generic {

	private static List<String> allItemsId = new ArrayList<String>();

	protected String codiceISBN;
	protected String publishYear;
	protected String title;
	protected String pages;

	public Generic(String publishYear, String title, String pages) {
		this.codiceISBN = idGeneration();
		this.publishYear = publishYear;
		this.title = title;
		this.pages = pages;
	}

	public String getCodiceISBN() {
		return String.valueOf(codiceISBN);
	}

	public void setCodiceISBN(String codiceISBN) {
		this.codiceISBN = codiceISBN;
	}

	public String getPublishYear() {
		return this.publishYear;
	}

	public void setPublishYear(String publishYear) {
		this.publishYear = publishYear;
	}

	public String idGeneration() {
		Random rand = new Random();
		long codiceISBN = rand.nextLong(9999999999l);
		while (allItemsId.contains(String.valueOf(codiceISBN))) {
			codiceISBN = rand.nextLong(9999999999l);
		}
		allItemsId.add(String.valueOf(codiceISBN));
		return String.valueOf(codiceISBN);

	}

	public String toPrint() {
		return "->[titolo= " + this.title + ", Year= " + this.publishYear + ", pages= " + this.pages + ", IBSN= "
				+ this.codiceISBN + " ]";

	}
}
