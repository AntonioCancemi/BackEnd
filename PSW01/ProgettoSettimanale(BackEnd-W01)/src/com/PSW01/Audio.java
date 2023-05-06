package com.PSW01;

public class Audio extends Media implements VolumeInterface {
	protected int volume;
	public int durataMin;

	public Audio(String title, int durataMin, String type) {
		super(title, type);
		this.durataMin = durataMin;
		this.volume = 5;

	}

	public String toString() {
		return this.title + "\n vol: " + showVolume() + "\n duration: " + this.durataMin;
	}

	public void volumeUp() {
		if (this.volume <= 10) {
			this.volume++;
		}

	}

	public void volumeDown() {
		if (this.volume >= 0) {
			this.volume--;
		}
	}

	@SuppressWarnings("unused")

	public String showVolume() {

		String volStr = "";
		for (int i = 1; i < 11; i++) {
			if (i <= this.volume) {
				volStr += "!";
			} else {
				volStr += "-";
			}

		}
		return volStr;

	}

	public void play() {
		for (int i = 0; i < this.durataMin; i++) {
			System.out.println(this.title + " (vol: " + showVolume() + ")");
		}

	}

}
