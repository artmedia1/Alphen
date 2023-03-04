package com.github.artmedia1.commands;

import com.github.artmedia1.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Help extends Command {
    protected String description = "**!help** - Responds with a list of what I can do.";
    Ping ping = new Ping();
    Play play = new Play();
    Reddit reddit = new Reddit();
    TwitterTrends twitter = new TwitterTrends();
    FlipACoin flip = new FlipACoin();
    Roll roll = new Roll();
    GoogleSearch googleSearch = new GoogleSearch();
    public Help() {
        super("!help");
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        String message =
                "List of things I can do\n" +
                        "**Help**\n" +
                        "> " + this.description + " \n" +
                        "> " + ping.description + " \n" +
                        "\n**Social Media**\n" +
                        "> " + reddit.description + " \n" +
                        "> " + twitter.description + " \n" +
                        "\n**Fun Stuff**\n" +
                        "> " + flip.description + " \n" +
                        "> " + roll.description + " \n" +
                        "\n**Google Search**\n" +
                        "> " + googleSearch.description + " \n" +
                        "\n**Other**\n" +
                        "> " + play.description;

        event.getChannel().sendMessage(message).queue();
    }
}
