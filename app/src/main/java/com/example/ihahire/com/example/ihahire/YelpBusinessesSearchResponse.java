
package com.example.ihahire;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class YelpBusinessesSearchResponse {

    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public YelpBusinessesSearchResponse() {
    }

    /**
     * 
     * @param categories
     */
    public YelpBusinessesSearchResponse(List<Category> categories) {
        super();
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}
