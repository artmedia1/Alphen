package com.github.artmedia1.commands;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.artmedia1.Command;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class FX extends Command {
    Dotenv env = Dotenv.load();
    private String credentials;
    protected String description = "**!fx amount first second** - Converts an **amount** of **first** currency into **second**.\n" +
            "> **!fx currencies** - Responds with list of available currencies codes";

    public FX() {
        super("!fx");
        this.credentials = env.get("EXCHANGE_RATE_API_KEY");
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
//        if(args.size < 3)
        String message = "";
        ArrayList<String> currencies = getCurrencies(event);
        switch (args[1]) {
            case "currencies":
                message += "The currency codes supported are: " + String.join(", ", currencies);
                break;
            default:
                if (args.length != 4) {
                    message += "That is not a valid fx command!";
                } else {
                    try {
                        Double amount = Double.parseDouble(args[1]);
                        message += getConversion(currencies, amount, args[2], args[3]);
                    } catch (NumberFormatException e) {
                        message += "That is not a valid fx command! - NumberFormatException";
                        System.out.println("NumberFormatException");
                    }
                }
        }

        event.getChannel().sendMessage(message).queue();
    }

    public String getConversion(ArrayList<String> currencies, Double amount, String first, String second) {
        String apiURL = "https://v6.exchangerate-api.com/v6/" + credentials + "/latest/" + first.toUpperCase();
        String message = "";
        if(currencies.contains(first.toUpperCase()) && currencies.contains(second.toUpperCase())) {
            // Making Request
            try {
                URL url = new URL(apiURL);

                // create an ObjectMapper to parse the JSON response
                ObjectMapper objectMapper = new ObjectMapper();

                // make an HTTP request to the URL and retrieve the JSON response
                JsonNode jsonNode = objectMapper.readTree(url);

                // grabs the links in the JSON object and puts them into results

                String s = jsonNode.get("conversion_rates").get(second.toUpperCase()).asText();
                Double exchangeRate = Double.parseDouble(s);
                Double calc = amount * exchangeRate;
                message += Double.toString(amount) + " " + first.toUpperCase() + " is " + Double.toString(calc) + " " + second.toUpperCase();

            } catch (MalformedURLException e) {
                message += "That is not a valid fx command! - MalformedURLException!";
                System.out.println("MalformedURLException");
            } catch (IOException e) {
                message += "That is not a valid fx command! - MalformedURLException!";
                System.out.println("IOException");
            }
        } else{
            message += "That is not a valid fx command!";
        }


        return message;
    }

    public ArrayList<String> getCurrencies(MessageReceivedEvent event) {
        ArrayList<String> currencies = new ArrayList<>();
        String apiURL = "https://v6.exchangerate-api.com/v6/" + credentials + "/latest/AED";
        // Making Request
        try {
            URL url = new URL(apiURL);

            // create an ObjectMapper to parse the JSON response
            ObjectMapper objectMapper = new ObjectMapper();

            // make an HTTP request to the URL and retrieve the JSON response
            JsonNode jsonNode = objectMapper.readTree(url);

            // grabs the links in the JSON object and puts them into results

            JsonNode conversionRates = jsonNode.get("conversion_rates");
            Iterator<Map.Entry<String, JsonNode>> fields = conversionRates.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                String currencyCode = field.getKey();
                currencies.add(currencyCode);
            }
        } catch (MalformedURLException e) {
            event.getChannel().sendMessage("MalformedURLException").queue();
        } catch (IOException e) {
            event.getChannel().sendMessage("IOException").queue();
        }
        return currencies;
    }
}
