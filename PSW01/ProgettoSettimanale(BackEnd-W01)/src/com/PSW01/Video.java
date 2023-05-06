package com.PSW01;

public class Video extends Audio implements Brightness {

	private int brightness;

	public Video(String title, int durataMin, String type) {
		super(title, durataMin, type);
		this.brightness = 5;

	}

	@SuppressWarnings("unused")

	public void brightUp() {
		if (this.brightness < 10) {
			this.brightness++;
		} else {
			System.out.println("lum: out of range");

		}

	}

	public void brightDown() {
		if (this.brightness >= 0) {
			this.brightness--;
		} else {
			System.out.println("lum: out of range");

		}
	}

	public String showBrightness() {

		String lumStr = "";
		for (int i = 1; i < 11; i++) {
			if (i <= this.brightness) {
				lumStr += "*";
			} else {
				lumStr += "-";
			}

		}
		return lumStr;

	}

	public void play() {
		for (int i = 0; i < this.durataMin; i++) {
			System.out.println(this.title + " (vol: " + showVolume() + ")" + "(lum: " + showBrightness() + ")");
		}
	}
}
