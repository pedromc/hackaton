package com.faire.hackaton.dataObjects;

import java.util.Objects;

public class ProductOption {
    public String id;
    public String product_id;
    public boolean active;
    public String name;
    public String sku;
    public int available_quantity;
    public String backordered_until;
    public String created_at;
    public String updated_at;

    public ProductOption(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOption that = (ProductOption) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
