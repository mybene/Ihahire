
package com.example.ihahire;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("alias")
    @Expose
    private String alias;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("parent_aliases")
    @Expose
    private List<Object> parentAliases = null;
    @SerializedName("country_whitelist")
    @Expose
    private List<String> countryWhitelist = null;
    @SerializedName("country_blacklist")
    @Expose
    private List<Object> countryBlacklist = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Category() {
    }

    /**
     * 
     * @param countryBlacklist
     * @param title
     * @param alias
     * @param countryWhitelist
     * @param parentAliases
     */
    public Category(String alias, String title, List<Object> parentAliases, List<String> countryWhitelist, List<Object> countryBlacklist) {
        super();
        this.alias = alias;
        this.title = title;
        this.parentAliases = parentAliases;
        this.countryWhitelist = countryWhitelist;
        this.countryBlacklist = countryBlacklist;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Object> getParentAliases() {
        return parentAliases;
    }

    public void setParentAliases(List<Object> parentAliases) {
        this.parentAliases = parentAliases;
    }

    public List<String> getCountryWhitelist() {
        return countryWhitelist;
    }

    public void setCountryWhitelist(List<String> countryWhitelist) {
        this.countryWhitelist = countryWhitelist;
    }

    public List<Object> getCountryBlacklist() {
        return countryBlacklist;
    }

    public void setCountryBlacklist(List<Object> countryBlacklist) {
        this.countryBlacklist = countryBlacklist;
    }

}
