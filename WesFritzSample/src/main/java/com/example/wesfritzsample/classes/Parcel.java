package com.example.wesfritzsample.classes;



/** This creates a class to hold the Parcel object. */
public class Parcel {
    private long pin;
    private String address;
    private String owner;
    private double marketValue;
    private String saleDate;
    private double salePrice;
    private String link;

    /** This method is invoked when structuring parcel objects. Holds key data for parcels
     * @param pin - pin of the parcel.
     * @param address - address of the parcel.
     * @param owner - last and first name of the people attached to the parcel.
     * @param marketValue - what the parcel is seen as, worth wise.
     * @param saleDate - date of sale for the parcel.
     * @param salePrice - the amount of money the parcel sold for.
     * @param link - parcel link to be viewed online.
     */
    public Parcel(long pin, String address, String owner, double marketValue,
                  String saleDate, double salePrice, String link) {
        this.pin = pin;
        this.address = address;
        this.owner = owner;
        this.marketValue = marketValue;
        this.saleDate = saleDate;
        this.salePrice = salePrice;
        this.link = link;
    }

    /** This method returns the pin of the parcel object.
     * @return the parcel pin.
     */
    public long getPin() {
        return pin;
    }

    /** This method sets the parcel's pin.
     * @param pin is the pin given to the parcel.
     */
    public void setPin(int pin) {
        this.pin = pin;
    }

    /** This method returns the address of the parcel object.
     * @return the parcel address.
     */
    public String getAddress() {
        return address;
    }

    /** This method sets the parcel's address.
     * @param address is the address given to the parcel.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /** This method returns the owner names of the parcel object.
     * @return the owner's names.
     */
    public String getOwner() {
        return owner;
    }

    /** This method sets the name(s) of the parcel's owner(s).
     * @param owner sets the owner's names.
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /** This method returns the market value of the parcel.
     * @return the market value of the parcel.
     */
    public double getMarketValue() {
        return marketValue;
    }

    /** This method sets the market value of the parcel.
     * @param marketValue sets the market value of the parcel.
     */
    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
    }

    /** This method returns the date the parcel was sold.
     * @return the date of sale for the parcel.
     */
    public String getSaleDate() {
        return saleDate;
    }

    /** This method sets the date the parcel was sold.
     * @param saleDate sets the date of sale for the parcel.
     */
    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    /** This method returns the sale price of the parcel.
     * @return how much the parcel sold for.
     */
    public double getSalePrice() {
        return salePrice;
    }

    /** This method sets the sale price of the parcel.
     * @param salePrice sets how much the parcel sold for.
     */
    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    /** This method returns a link showing the parcel online.
     * @return a link for the parcel online.
     */
    public String getLink() {
        return link;
    }

    /** This method sets a link for the parcel online.
     * @param link sets the link for the parcel.
     */
    public void setLink(String link) {
        this.link = link;
    }


}
