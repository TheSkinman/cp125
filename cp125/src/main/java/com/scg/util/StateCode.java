package com.scg.util;

import java.io.Serializable;

/**
 * U. S. Postal state codes.
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public enum StateCode implements Comparable<StateCode> {
    /** ALABAMA */
    AL("Alabama"),
    /** ALASKA */
    AK("Alaska"),
    /** AMERICAN SAMOA */
    AS("American Samoa"),
    /** ARIZONA */
    AZ("Arizona"),
    /** ARKANSAS */
    AR("Arkansas"),
    /** CALIFORNIA */
    CA("California"),
    /** COLORADO */
    CO("Colorado"),
    /** CONNECTICUT */
    CT("Connecticut"),
    /** DELAWARE */
    DE("Delaware"),
    /** DISTRICT OF COLUMBIA */
    DC("District of Columbia"),
    /** FEDERATED STATES OF MICRONESIA */
    FM("Federated States of Micronesia"),
    /** FLORIDA */
    FL("Florida"),
    /** GEORGIA */
    GA("Georgia"),
    /** GUAM */
    GU("Guam"),
    /** HAWAII */
    HI("Hawaii"),
    /** IDAHO */
    ID("Idaho"),
    /** ILLINOIS */
    IL("Illinois"),
    /** INDIANA */
    IN("Indiana"),
    /** IOWA */
    IA("Iowa"),
    /** KANSAS */
    KS("Kansas"),
    /** KENTUCKY */
    KY("Kentucky"),
    /** LOUISIANA */
    LA("Louisiana"),
    /** MAINE */
    ME("Maine"),
    /** MARSHALL ISLANDS */
    MH("Marshall Islands"),
    /** MARYLAND */
    MD("Maryland"),
    /** MASSACHUSETTS */
    MA("Massachusetts"),
    /** MICHIGAN */
    MI("Michigan"),
    /** MINNESOTA */
    MN("Minnesota"),
    /** MISSISSIPPI */
    MS("Mississippi"),
    /** MISSOURI */
    MO("Missouri"),
    /** MONTANA */
    MT("Montana"),
    /** NEBRASKA */
    NE("Nebraska"),
    /** NEVADA */
    NV("Nevada"),
    /** NEW HAMPSHIRE */
    NH("New Hampshire"),
    /** NEW JERSEY */
    NJ("New Jersey"),
    /** NEW MEXICO */
    NM("New Mexico"),
    /** NEW YORK */
    NY("New York"),
    /** NORTH CAROLINA */
    NC("North Carolina"),
    /** NORTH DAKOTA */
    ND("North Dakota"),
    /** NORTHERN MARIANA ISLANDS */
    MP("Northern Mariana Islands"),
    /** OHIO */
    OH("Ohio"),
    /** OKLAHOMA */
    OK("Oklahoma"),
    /** OREGON */
    OR("Oregon"),
    /** PALAU */
    PW("Palau"),
    /** PENNSYLVANIA */
    PA("Pennsylvania"),
    /** PUERTO RICO */
    PR("Puerto Rico"),
    /** RHODE ISLAND */
    RI("Rhode Island"),
    /** SOUTH CAROLINA */
    SC("South Carolina"),
    /** SOUTH DAKOTA */
    SD("South Dakota"),
    /** TENNESSEE */
    TN("Tennessee"),
    /** TEXAS */
    TX("Texas"),
    /** UTAH */
    UT("Utah"),
    /** VERMONT */
    VT("Vermont"),
    /** VIRGIN ISLANDS */
    VI("Virgin Islands"),
    /** VIRGINIA */
    VA("Virginia"),
    /** WASHINGTON */
    WA("Washington"),
    /** WEST VIRGINIA */
    WV("West Virginia"),
    /** WISCONSIN */
    WI("Wisconsin"),
    /** WYOMING */
    WY("Wyoming");

    private String fullName;

    private StateCode(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
