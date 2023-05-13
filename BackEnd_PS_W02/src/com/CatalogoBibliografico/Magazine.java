package com.CatalogoBibliografico;

public class Magazine extends Generic {

	private Periodicita periodicita;
	private int c;

	public Magazine(String publishYear, String title, String period, String pages) {
		super(publishYear, title, pages);

		this.periodicita = switchEnum(period);

	}

	@Override
	public String toString() {
		return super.title + "@" + this.c + "@" + super.publishYear + "@" + super.pages + "@" + super.codiceISBN + ";";
	}

	public Periodicita switchEnum(String period) {

		switch (period) {
		case "1":
			this.c = 1;
			return Periodicita.SETTIMANALE;

		case "2":
			this.c = 2;
			return Periodicita.MENSILE;

		case "3":
			this.c = 3;
			return Periodicita.SEMESTRALE;

		default:
			break;
		}
		return periodicita;
	}

	public void setPeriodicita(Periodicita periodicita) {
		this.periodicita = periodicita;
	}
}
