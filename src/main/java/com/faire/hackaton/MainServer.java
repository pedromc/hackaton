package com.faire.hackaton;

import spark.ModelAndView;
import spark.Request;
import spark.template.velocity.VelocityTemplateEngine;
import spark.utils.IOUtils;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;


public class MainServer {

    public static void main(String [] argv){
        staticFiles.location("/public");

        post("/api/upload", (req, res) -> {
            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/tmp"));
            Part filePart = req.raw().getPart("myfile");

            try (InputStream inputStream = filePart.getInputStream()) {
                OutputStream outputStream = new FileOutputStream("/tmp/" + filePart.getSubmittedFileName());
                IOUtils.copy(inputStream, outputStream);
                outputStream.close();
            }

            //return "File uploaded and saved.";
            return renderFieldsMap(req, "/tmp/" + filePart.getSubmittedFileName());
        });


        get("/api/fieldsMap", (req, res) -> {
            Map<String, String> fieldHeaderMap = new HashMap<>();
            fieldHeaderMap.put("name",req.queryParams("name"));
            fieldHeaderMap.put("short_desc",req.queryParams("short_desc"));
            fieldHeaderMap.put("desc",req.queryParams("desc"));
            fieldHeaderMap.put("price",req.queryParams("price"));
            fieldHeaderMap.put("wholesale_price",req.queryParams("wholesale_price"));
            fieldHeaderMap.put("sku/id",req.queryParams("sku/id"));
            fieldHeaderMap.put("unit_multiplier",req.queryParams("unit_multiplier"));
            fieldHeaderMap.put("option_desc",req.queryParams("option_desc"));
            fieldHeaderMap.put("available_qtd",req.queryParams("available_qtd"));

            String fileName = req.queryParams("fileName");


            return "File uploaded and saved. ";
        });

    }

    private static String renderFieldsMap(Request req, String filename) {
        List<String[]> csvData =  ReadCsv.readAll(filename);
        String[] csvHeaders = csvData.get(0);
        List<String> headers = Arrays.asList(csvHeaders);

        Map<String, Object> model = new HashMap<>();
        model.put("mylist", headers);
        model.put("fileName", filename);

        return new VelocityTemplateEngine().render(new ModelAndView(model, "velocity/mapForm.vm"));
    }

}