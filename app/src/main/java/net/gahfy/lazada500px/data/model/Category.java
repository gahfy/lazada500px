package net.gahfy.lazada500px.data.model;

import android.content.Context;
import android.support.annotation.NonNull;

import net.gahfy.lazada500px.R;

/**
 * Model of the 500px categories
 * @author Gaëtan HERFRAY
 */
public class Category implements Comparable<Category>{
    /** The unique identifier of the category */
    private int categoryId;
    /** The name of the category */
    private String categoryName;
    /** The code name of the category */
    private String categoryCodeName;

    /**
     * Returns a list of Categories defined by the specified string array.
     * @param context Context in which the application is working
     * @return a list of Categories defined by the specified string array
     */
    public static Category[] getCategories(Context context){
        String[] categoryNames = context.getResources().getStringArray(R.array.category_name);
        String[] categoryCodeNames = context.getResources().getStringArray(R.array.category_code_name);
        Category[] result = new Category[categoryNames.length];
        for(int i=0; i<categoryNames.length; i++){
            result[i] = new Category(i, categoryNames[i], categoryCodeNames[i]);
        }
        return result;
    }

    /**
     * Sets the unique identifier of the category.
     * @param categoryId the unique identifier of the category to set
     */
    private void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Sets the name of the category.
     * @param categoryName the name of the category to set
     */
    private void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Sets the code name of the category.
     * @param categoryCodeName the code name of the category to set
     */
    private void setCategoryCodeName(String categoryCodeName) {
        this.categoryCodeName = categoryCodeName;
    }

    /**
     * Returns the unique identifier of the category.
     * @return the unique identifier of the category
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Returns the name of the category.
     * @return the name of the category
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Returns the code name of the category.
     * @return the code name of the category
     */
    public String getCategoryCodeName() {
        return categoryCodeName;
    }

    @Override
    public int compareTo(@NonNull Category category) {
        // The two first conditions put null values at the end
        if(getCategoryName() == null)
            return category.getCategoryName() == null ? 0 : 1;
        else if(category.getCategoryName() == null)
            return -1;
        return getCategoryName().compareTo(category.getCategoryName());
    }

    /**
     * Instantiates a new Category.
     * @param categoryId the unique identifier of the category to set
     * @param categoryName the name of the category to set
     * @param categoryCodeName the code name of the category to set
     */
    private Category(int categoryId, String categoryName, String categoryCodeName){
        setCategoryId(categoryId);
        setCategoryName(categoryName);
        setCategoryCodeName(categoryCodeName);
    }
}
