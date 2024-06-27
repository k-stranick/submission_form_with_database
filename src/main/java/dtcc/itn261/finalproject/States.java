/**==================================================
 Author: Kyle Stranick
 Class: ITN261
 Class Section: 201
 Assignment: Final Project
 Notes: there are some cool features need to go over and update this summer
 use as project piece for resume
 4/23/24
 =====================================================

 Code adapted from: check resources

 ===================================================== */
package dtcc.itn261.finalproject;

// record?
public enum States {
    SELECTION,
    ALABAMA,
    ALASKA,
    ARIZONA,
    ARKANSAS,
    CALIFORNIA,
    COLORADO,
    CONNECTICUT,
    DELAWARE,
    FLORIDA,
    GEORGIA,
    HAWAII,
    IDAHO,
    ILLINOIS,
    INDIANA,
    IOWA,
    KANSAS,
    KENTUCKY,
    LOUISIANA,
    MAINE,
    MARYLAND,
    MASSACHUSETTS,
    MICHIGAN,
    MINNESOTA,
    MISSISSIPPI,
    MISSOURI,
    MONTANA,
    NEBRASKA,
    NEVADA,
    NEW_HAMPSHIRE,
    NEW_JERSEY,
    NEW_MEXICO,
    NEW_YORK,
    NORTH_CAROLINA,
    NORTH_DAKOTA,
    OHIO,
    OKLAHOMA,
    OREGON,
    PENNSYLVANIA,
    RHODE_ISLAND,
    SOUTH_CAROLINA,
    SOUTH_DAKOTA,
    TENNESSEE,
    TEXAS,
    UTAH,
    VERMONT,
    VIRGINIA,
    WASHINGTON,
    WEST_VIRGINIA,
    WISCONSIN,
    WYOMING;

    @Override
    public String toString() {
        return switch (this) {
            case SELECTION -> "Select A State";
            case ALABAMA -> "AL";
            case ALASKA -> "AK";
            case ARIZONA -> "AZ";
            case ARKANSAS -> "AR";
            case CALIFORNIA -> "CA";
            case COLORADO -> "CO";
            case CONNECTICUT -> "CT";
            case DELAWARE -> "DE";
            case FLORIDA -> "FL";
            case GEORGIA -> "GA";
            case HAWAII -> "HI";
            case IDAHO -> "ID";
            case ILLINOIS -> "IL";
            case INDIANA -> "IN";
            case IOWA -> "IA";
            case KANSAS -> "KS";
            case KENTUCKY -> "KY";
            case LOUISIANA -> "LA";
            case MAINE -> "ME";
            case MARYLAND -> "MD";
            case MASSACHUSETTS -> "MA";
            case MICHIGAN -> "MI";
            case MINNESOTA -> "MN";
            case MISSISSIPPI -> "MS";
            case MISSOURI -> "MO";
            case MONTANA -> "MT";
            case NEBRASKA -> "NE";
            case NEVADA -> "NV";
            case NEW_HAMPSHIRE -> "NH";
            case NEW_JERSEY -> "NJ";
            case NEW_MEXICO -> "NM";
            case NEW_YORK -> "NY";
            case NORTH_CAROLINA -> "NC";
            case NORTH_DAKOTA -> "ND";
            case OHIO -> "OH";
            case OKLAHOMA -> "OK";
            case OREGON -> "OR";
            case PENNSYLVANIA -> "PA";
            case RHODE_ISLAND -> "RI";
            case SOUTH_CAROLINA -> "SC";
            case SOUTH_DAKOTA -> "SD";
            case TENNESSEE -> "TN";
            case TEXAS -> "TX";
            case UTAH -> "UT";
            case VERMONT -> "VT";
            case VIRGINIA -> "VA";
            case WASHINGTON -> "WA";
            case WEST_VIRGINIA -> "WV";
            case WISCONSIN -> "WI";
            case WYOMING -> "WY";
        };
    }
}