package com.test.product;

public class Product {

    private String category;
    private String title;
    private String linkToProduct;

    public Product(String category, String title, String linkToProduct) {
        this.category = category;
        this.title = title;
        this.linkToProduct = linkToProduct;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String title) {
        this.title = title;
    }

    public void setLinkToProduct(String linkToProduct) {
        this.linkToProduct = linkToProduct;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getLinkToProduct() {
        return linkToProduct;
    }

}
