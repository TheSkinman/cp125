package com.scg.domain;

public class Tester {

	public static void main(String[] args) {
		Skill me = Skill.PROJECT_MANGER;
		System.out.println("I am a " + me.toString() + ". I make $" + me.getRate() + " an hour.");
		System.out.println("");
	}

}
