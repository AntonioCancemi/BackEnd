package com.PSW01;

import java.util.Scanner;

public class MainProject {

	public static void main(String[] args) {

		Scanner sc1 = new Scanner(System.in);
		Media[] mediaCollection = new Media[5];

		initialization(sc1, mediaCollection);

		selectANDPlay(sc1, mediaCollection);
		arrivederci();
	}

	// main 1)

	public static void initialization(Scanner sc1, Media[] mediaCollection) {
		int Mcounter = 0;

		while (Mcounter < 5) {

			try {
				System.out.println("inserisci un file PNG (immagini) MP3 (audio) MP4 (video): ");

				// return title extension
				String[] splits = inputCheck(sc1);
				if (splits[0] != "") {
					// select the correct class for instance
					boolean response = switchOnType(splits, mediaCollection, Mcounter, sc1);
					if (response) {
						Mcounter++;
					}
				} else {
					System.out.println("ERROR: title not found");

				}

			} catch (Exception e) {
				System.out.println("Something went wrong.");
			}

		}
		printDorpDownMedia(mediaCollection);
	}

	// main 2)

	public static void selectANDPlay(Scanner sc1, Media[] mediaCollection) {
		boolean playng = true;
		do {
			try {
				int id = sc1.nextInt();

				if (mediaCollection[id].type != "Image") {
					mediaCollection[id].play();
				} else {
					mediaCollection[id].show();
				}
			} catch (Exception e) {
				playng = false;
			}

		} while (playng);

	}

	// implemented to main 1

	public static String[] inputCheck(Scanner sc1) {

		String input = sc1.nextLine();
		String[] splits = input.split("\\.");
		return splits;

	}

	public static boolean switchOnType(String[] splits, Media[] mediaCollection, int Mcounter, Scanner sc1) {
		int i = Mcounter;
		boolean state = false;
		switch (splits[1].toUpperCase()) {

		case "PNG":

			mediaCollection[i] = new Image(splits[0], "Image");
			state = true;
			break;
		case "MP3":
			System.out.println("inserisci la durata in minuti: ");
			try {
				String durationAudio = sc1.nextLine();
				mediaCollection[i] = new Audio(splits[0], Integer.parseInt(durationAudio), "Audio");
				state = true;
			} catch (Exception e) {
				System.out.println("ERROR: il valore della durata non e` corretto riprova\n");
			}

			break;
		case "MP4":
			try {
				System.out.println("inserisci la durata in minuti: ");
				String durationVideo = sc1.nextLine();
				mediaCollection[i] = new Video(splits[0], Integer.parseInt(durationVideo), "Video");
				state = true;
			} catch (Exception e) {
				System.out.println("ERROR: il valore della durata non e` corretto riprova\n");
			}

			break;

		default:
			System.out.println("ERROR: il file non e` supportato");
		}
		return state;

	}

	public static void printDorpDownMedia(Media[] mediaCollection) {
		System.out.println("_____________________________________________________________________________");

		for (int i = 0; i < mediaCollection.length; i++) {
			Media media = mediaCollection[i];

			System.out.println("\n" + i + ") " + media.title + " [" + media.type + "]");
		}
		System.out.println("_____________________________________________________________________________");
		System.out.println("Scegli il file da riprodurre inserendo il numero corrispondente");
		System.out.println("digita [quit] per chiudere il lettore");
	}

	// implements to main 2 // implemented to main 2

	// Bye
	public static void arrivederci() {
		System.out.println("_____________________________________________________________________________");

		System.out.println("\nprogramma arrestato. Grazie di averlo provato!! Alla prossima!!!!");
		System.out.println("\n_____________________________________________________________________________");

	}
}
