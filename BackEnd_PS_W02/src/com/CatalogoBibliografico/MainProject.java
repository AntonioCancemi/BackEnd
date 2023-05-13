package com.CatalogoBibliografico;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

public class MainProject {
	// per lo start del programma
	private static List<String> comandsAvailable = new ArrayList<String>();
	private static Map<String, Generic> catalogGeneric = new HashMap<String, Generic>();
	private static List<String> result = new ArrayList<String>();
	private static String path = "dati/catalogo.txt";
	private static File file = new File(path);
	private static Scanner sc = new Scanner(System.in);
	private static String[] catalogArr;

	public static void main(String[] args) {
		try {
			start();
			availableComands();

			boolean condition = true;
			while (condition) {
				String inputComand = sc.nextLine().toUpperCase();
			}

			searchParam("author");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String univerlasWhileForInput() {
		boolean condition = false;
		String input = null;
		while (condition) {
			input = sc.nextLine();
			input.toUpperCase();
			if (input == "BACK") {
				condition = false;
			}
		}
		return input;
	}

	// search yearAndAuthor
	public static void searchParam(String val) {
		val = val.toUpperCase();
		System.out.println("\n****SRART searchParam*****");
		System.err.println("per interromper digita 'back'");
		System.out.println(
				val.equals("AUTHOR") ? "inserisci l'autore che ti interessa: " : "inserisci l'anno che ti interessa: ");
		boolean condition = true;
		String input = null;
		result.clear();
		while (condition) {
			input = sc.nextLine().toUpperCase();
			if (input.equals("back")) {
				condition = false;
			} else if (input.isEmpty()) {
				System.err.println("Input is Wrong or Empty try again or go 'back':");
			} else {
				switchSearch(input, val);

			}
		}
		System.out.println("****END searchParam*****");

	}

	public static void switchSearch(String input, String val) {
		boolean found = false;
		switch (val) {
		case "YEAR":
			for (String string : catalogGeneric.keySet()) {
				String year = catalogGeneric.get(string).getPublishYear();
				if (year.equals(input)) {
					found = true;
					result.add(catalogGeneric.get(string).toPrint());
				}
			}
			break;
		case "AUTHOR":
			for (String string : catalogGeneric.keySet()) {
				String[] i = catalogGeneric.get(string).toString().split("@");
				String author = i[1].toUpperCase();
				if (author.equals(input) || author.contains(input)) {
					found = true;
					result.add(catalogGeneric.get(string).toPrint());
				}
			}
			break;
		default:
			break;
		}
		if (!found) {
			System.err.println(input + " Not Found");
		} else {
			printResults(input);
		}

	}

	public static void printResults(String input) {
		System.out.println("risultati per : " + input);
		result.forEach(e -> System.out.println(e));
	}

	// ACTION: GrtByKey|REMOVE|ADD
	public static void getByKey() {
		System.out.println("inserisci id");

		String input = sc.nextLine();

		if (!input.isEmpty()) {
			System.out.println(catalogGeneric.get(input));
		} else {
			System.err.println("inserisci un id");
		}

	}

	public static void removeItem() throws IOException {
		System.out.println(
				"----------------remove----------------\nInserisci il codice ISBN del libro o magazine che vuoi cancellare: ");
		String codiceISBN = sc.nextLine();

		readCatalog();
		if (catalogGeneric.containsKey(codiceISBN)) {
			System.out.println("Stringa eliminata dal file/oggetto eliminato:\n" + catalogGeneric.get(codiceISBN));
			catalogGeneric.remove(codiceISBN);
			updateCatalogoFile("");
			readCatalog();
		} else {
			System.err.println("il codiceISBN inserito non e`presente");
		}
	}

	private static void addItem() {
		System.out.println("----------------ADD----------------"
				+ "\nPer aggiungere un libro o una rivista utillazza i seguenti formati:\n----------------Rivista----------------\n"
				+ "[title]@[1:Settimanale|2:Mensile|3:Semestrale]@[anno]@[pagine]\n----------------libro----------------\n"
				+ "[title]@[autore]@[genere]@[anno]@[pagine];");
		String[] data = sc.nextLine().split("@");

		try {
			switch (data.length) {
			case 5:

				makeBookAddIt(data);
				System.out.println("libro aggiunto nella VM e in local");
				break;
			case 4:

				makeMagazineAddIt(data);
				System.out.println("magazine aggiunto nella VM e in local");

				break;
			default:
				System.err.println("ERROR: riprova inserendo un formato validi");
				break;
			}

		} catch (IOException e) {
			System.err.println("Errore nella scrittura del file");
			e.printStackTrace();
		}
	}

	// SCRITTURA||LETTURA FILE
	// File AND VM ACTION
	public static void salvaCatalogoInFile() throws IOException {
		// SALVA TUTTI GLI ELEMENTI CODIFICATI NEL CATALOGO
		String catalog = "";

		for (String key : catalogGeneric.keySet()) {
//
			catalog += catalogGeneric.get(key).toString();
		}

		FileUtils.writeStringToFile(file, catalog, "UTF-8");
	}

	public static void readCatalog() throws IOException {
		catalogGeneric.clear();
		catalogArr = FileUtils.readFileToString(file, "UTF-8").split(";");
		for (String str : catalogArr) {
			if (str != "") {

				String[] data = str.split("@");
				List<String> dat = new ArrayList<String>();
				switch (data.length) {
				case 6:
					makeBookAddIt(data);
					break;
				case 5:
					makeMagazineAddIt(data);
					break;
				default:
					break;
				}

			}
		}

	}

	public static void updateCatalogoFile(String item) throws IOException {
		String updatedFile = "";
		for (String string : catalogArr) {
			updatedFile += string + ";";
		}
		if (!item.isEmpty()) {
			updatedFile += item.toString();
		}
		FileUtils.writeStringToFile(file, updatedFile, "UTF-8");
	}

	// **************** CREA LIBRO | ADD TO GENERIC | UPDATE FILE
	public static void makeBookAddIt(String[] data) throws IOException {
		// Instance OF
		Book book = new Book(data[3], data[0], data[4], data[1], data[2]);
		catalogGeneric.put(book.getCodiceISBN(), book);
		// update local
		updateCatalogoFile(book.toString());
	}

	public static void makeMagazineAddIt(String[] data) throws IOException {
		// Instance OF
		Magazine magazine = new Magazine(data[2], data[0], data[1], data[3]);
		// update VM
		catalogGeneric.put(magazine.getCodiceISBN(), magazine);
		// update local
		updateCatalogoFile(magazine.toString());
	}

	// **************** START
	// START
	public static void start() throws IOException {
		catalogGeneric = catalogGeneretion(catalogGeneric);
		salvaCatalogoInFile();
		readCatalog();
		printCatalog();

	}

	public static Map<String, Generic> catalogGeneretion(Map<String, Generic> map) {

		Book b1 = new Book("2005", "indiana Jhonse", "270", "Filippo blus", "adventure");
		Book b2 = new Book("2005", "Peppa pig", "30", "Ciro Esposito", "bambini");
		Book b3 = new Book("2015", "dora l'esploratrice", "70", "Carla Osti", "adventure~bambini");
		Book b4 = new Book("2007", "L'inferno di Dante", "330", "Dante Alighieri", "Commedia");
		Book b5 = new Book("2011", "L'amore", "987", "Fabio Volo", "romanzo");
		Book b6 = new Book("2020", "L'apocalisse", "450", "Nostradamus", "romanzo");
		Book b7 = new Book("2001", "bianco come la neve", "320", "Jake Trim", "thriller");
		Book b8 = new Book("1890", "Il nuovo Secolo", "170", "Sant'Agostino", "bibliografia");

		Magazine m1 = new Magazine("2012", "Alla scoperte dell'Italia", "1", "30");
		Magazine m2 = new Magazine("2000", "Playboy", "2", "30");
		Magazine m3 = new Magazine("2000", "Gazzetta dello Sport", "2", "30");
		Magazine m4 = new Magazine("1970", "La republica", "1", "30");
		Magazine m5 = new Magazine("1919", "Famosi", "3", "30");
		Magazine m6 = new Magazine("2020", "Vanity Fair", "3", "30");
		Magazine m7 = new Magazine("2016", "EsseMagazine", "1", "30");
		Magazine m8 = new Magazine("2008", "TVpiu", "1", "30");

		map.put(b1.getCodiceISBN(), b1);
		map.put(b2.getCodiceISBN(), b2);
		map.put(b3.getCodiceISBN(), b3);
		map.put(b4.getCodiceISBN(), b4);
		map.put(b5.getCodiceISBN(), b5);
		map.put(b6.getCodiceISBN(), b6);
		map.put(b7.getCodiceISBN(), b7);
		map.put(b8.getCodiceISBN(), b8);

		map.put(m1.getCodiceISBN(), m1);
		map.put(m2.getCodiceISBN(), m2);
		map.put(m3.getCodiceISBN(), m3);
		map.put(m4.getCodiceISBN(), m4);
		map.put(m5.getCodiceISBN(), m5);
		map.put(m6.getCodiceISBN(), m6);
		map.put(m7.getCodiceISBN(), m7);
		map.put(m8.getCodiceISBN(), m8);
		return map;
	}
	// OTHER

//OTHER
	public static void availableComands() {
		String[] comand = { "CLOSE PROGRAM", "ADD", "REMOVE", "SEARCH ISBN", "SEARCH AUTHOR", "SEARCH YEAR",
				"SHOW_COMAND" };
		int i = 0;
		for (String string : comand) {
			i++;
			comandsAvailable.add(string);
		}
		System.out.println("\nAvailable Comands-->");
		comandsAvailable.forEach(e -> {

			System.out.println(comandsAvailable.indexOf(e) + ")" + e);

		});

	}

	public static void printCatalog() {
		System.out.println("************* Catalogo Completo (size: " + catalogGeneric.size() + ") *************");
		catalogGeneric.forEach((K, V) -> System.out
				.println((V.toString().split("@").length == 5 ? "magazine) " : "libro   ) ") + V.toString()));
	}
}
