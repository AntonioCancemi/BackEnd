package com.PSW01;

public class Image extends Media implements Brightness {
	private int brightness;

	public Image(String title, String type) {
		super(title, type);
		this.brightness = 5;

	}

	public int getBrightness() {
		return brightness;
	}

	@Override
	public String toString() {
		return this.title + "\n lum: " + showBrightness();
	}

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

	public void show() {
		System.out.println(this.title + "\n lum: " + showBrightness());

	}

}
