package com.scg.domain;

public enum Skill {
	PROJECT_MANGER(250) {
		@Override
		public String toString() {
			return "Project Manager";
		}
	},
	SYSTEM_ARCHITECT(200) {
		@Override
		public String toString() {
			return "System Architect";
		}
	},
	SOFTWARE_ENGINEER(150) {
		@Override
		public String toString() {
			return "Software Engineer";
		}
	},
	SOFTWARE_TESTER(100) {
		@Override
		public String toString() {
			return "Software Tester";
		}
	},
	UNKNOWN_SKILL(0) {
		@Override
		public String toString() {
			return "Unknown Skill";
		}
	};
	
	private int rate;
	
	private Skill(int rate) {
		this.rate = rate;
	}
	
	public int getRate() {
		return rate;
	}

}
