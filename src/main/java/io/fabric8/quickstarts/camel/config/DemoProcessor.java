package io.fabric8.quickstarts.camel.config;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONArray;
import org.json.JSONObject;

public class DemoProcessor implements Processor {
    @Override
    public void process(Exchange exchange) {

        String originalFileContent = exchange.getIn().getBody(String.class);
        JSONArray jsonArray = new JSONArray(originalFileContent);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            JSONObject address = jsonObject.getJSONObject("address");
            jsonObject.put("address", address.get("street") + ", " + address.get("city") + " " + address.get("zipCode"));
        }

        exchange.getIn().setBody(jsonArray.toString());
    }
}
