package com.faire.hackaton.dataObjects;

import java.util.List;

public class Product {
    public String id;
    public String brand_id;
    public String short_description;
    public String description;
    public int wholesale_price_cents;
    public int retail_price_cents;
    public boolean active;
    public String name;
    public int unit_multiplier;
    public String created_at;
    public String updated_at;
    public List<ProductOption> options;


}
