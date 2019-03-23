package com.faire.hackaton;

import com.faire.hackaton.dataObjects.Product;

import java.util.Map;

public class ProcessCsv {

    public static void process(Map<String, String> fieldsMap, String fileName) {
        for (String it : fieldsMap.keySet()) {

            Product prod = new Product();
            prod.active = true;

            fieldsMap.get("name");
            fieldsMap.get("short_desc");
            fieldsMap.get("desc");
            fieldsMap.get("price");
            fieldsMap.get("wholesale_price");
            fieldsMap.get("sku/id");
            fieldsMap.get("unit_multiplier");
            fieldsMap.get("option_desc");
            fieldsMap.get("available_qtd");

            System.out.println("key = " + it + ", value = " + fieldsMap.get(it));
        }
        System.out.println(fileName);
    }

}
