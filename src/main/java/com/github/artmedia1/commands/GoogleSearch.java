package com.github.artmedia1.commands;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.artmedia1.Command;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;
public class GoogleSearch extends Command {
    public String description = "**!google [query]**- Responds the top 3 links from google for your query";
    private String[] credentials = new String[2];
    Dotenv env = Dotenv.load();
    public GoogleSearch(){
        super("!google");
        this.credentials[0] = env.get("GOOGLE_SEARCH_ENGINE_ID");
        this.credentials[1] = env.get("GOOGLE_API_KEY");
    }
    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        int numOfLinks = 3;
        String[] links = httpGETRequest(event, args, numOfLinks);
        System.out.println(links.length);
        for(String link : links){
            event.getChannel().sendMessage(link).queue();
        }
    }
    public String[] httpGETRequest(MessageReceivedEvent event, String[] args, int numOfLinks) {
        String[] results = new String[numOfLinks];

        String searchTerm = String.join(" ", Arrays.asList(args).subList(1, args.length));
        searchTerm = searchTerm.replace(" ", "%20");

        // create a URL object for the API endpoint
        String apiURL = "https://customsearch.googleapis.com/customsearch/v1?cx=" + credentials[0] + "&q=" + searchTerm + "&key=" + credentials[1];
        try {
            URL url = new URL(apiURL);

            // create an ObjectMapper to parse the JSON response
            ObjectMapper objectMapper = new ObjectMapper();

            // make an HTTP request to the URL and retrieve the JSON response
            JsonNode jsonNode = objectMapper.readTree(url);

            // grabs the links in the JSON object and puts them into results
            for(int i = 0; i < numOfLinks; i++){
                results[i] = jsonNode.get("items").get(i).get("link").asText();
            }

        } catch (MalformedURLException e) {

        } catch (IOException e) {
            event.getChannel().sendMessage("IOException").queue();
        }
        return results;
    }
}
