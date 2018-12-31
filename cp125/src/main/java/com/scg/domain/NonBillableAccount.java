/**
 * 
 */
package com.scg.domain;

/**
 * @author Norman Skinner
 *
 */
public enum NonBillableAccount implements Account {
	SICK_LEAVE("Sick Leave") {
		@Override
		public String toString() {
			return "Sick Leave";
		}
	},
	VACATION("Vacation") {
		@Override
		public String toString() {
			return "Vacation";
		}
	},
	BUSINESS_DEVELOPMENT("Business Development") {
		@Override
		public String toString() {
			return "Business Development";
		}
	};
	
	private String name;
	
	private NonBillableAccount(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isBillable() {
		return false;
	}

}
