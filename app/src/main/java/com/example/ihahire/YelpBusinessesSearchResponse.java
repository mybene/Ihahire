
package com.example.ihahire;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class YelpBusinessesSearchResponse {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("alias")
    @Expose
    private String alias;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("is_claimed")
    @Expose
    private Boolean isClaimed;
    @SerializedName("is_closed")
    @Expose
    private Boolean isClosed;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("display_phone")
    @Expose
    private String displayPhone;
    @SerializedName("review_count")
    @Expose
    private Integer reviewCount;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;
    @SerializedName("rating")
    @Expose
    private Double rating;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("coordinates")
    @Expose
    private Coordinates coordinates;
    @SerializedName("photos")
    @Expose
    private List<String> photos = null;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("hours")
    @Expose
    private List<Hour> hours = null;
    @SerializedName("transactions")
    @Expose
    private List<Object> transactions = null;
    @SerializedName("special_hours")
    @Expose
    private List<SpecialHour> specialHours = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public YelpBusinessesSearchResponse() {
    }

    /**
     * 
     * @param displayPhone
     * @param hours
     * @param rating
     * @param coordinates
     * @param transactions
     * @param specialHours
     * @param photos
     * @param url
     * @param isClosed
     * @param phone
     * @param reviewCount
     * @param price
     * @param imageUrl
     * @param name
     * @param alias
     * @param location
     * @param id
     * @param categories
     * @param isClaimed
     */
    public YelpBusinessesSearchResponse(String id, String alias, String name, String imageUrl, Boolean isClaimed, Boolean isClosed, String url, String phone, String displayPhone, Integer reviewCount, List<Category> categories, Double rating, Location location, Coordinates coordinates, List<String> photos, String price, List<Hour> hours, List<Object> transactions, List<SpecialHour> specialHours) {
        super();
        this.id = id;
        this.alias = alias;
        this.name = name;
        this.imageUrl = imageUrl;
        this.isClaimed = isClaimed;
        this.isClosed = isClosed;
        this.url = url;
        this.phone = phone;
        this.displayPhone = displayPhone;
        this.reviewCount = reviewCount;
        this.categories = categories;
        this.rating = rating;
        this.location = location;
        this.coordinates = coordinates;
        this.photos = photos;
        this.price = price;
        this.hours = hours;
        this.transactions = transactions;
        this.specialHours = specialHours;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getIsClaimed() {
        return isClaimed;
    }

    public void setIsClaimed(Boolean isClaimed) {
        this.isClaimed = isClaimed;
    }

    public Boolean getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(Boolean isClosed) {
        this.isClosed = isClosed;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDisplayPhone() {
        return displayPhone;
    }

    public void setDisplayPhone(String displayPhone) {
        this.displayPhone = displayPhone;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Hour> getHours() {
        return hours;
    }

    public void setHours(List<Hour> hours) {
        this.hours = hours;
    }

    public List<Object> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Object> transactions) {
        this.transactions = transactions;
    }

    public List<SpecialHour> getSpecialHours() {
        return specialHours;
    }

    public void setSpecialHours(List<SpecialHour> specialHours) {
        this.specialHours = specialHours;
    }

}
