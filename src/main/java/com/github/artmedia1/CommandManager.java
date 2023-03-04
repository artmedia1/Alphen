package com.github.artmedia1;

import com.github.artmedia1.commands.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;

public class CommandManager extends ListenerAdapter {
    private final HashMap<String, Command> CommandMap = new HashMap<String, Command>();
    public CommandManager(){
        Ping ping = new Ping();
        Help help = new Help();
        Play play = new Play();
        Reddit reddit = new Reddit();
        TwitterTrends twitter = new TwitterTrends();
        FlipACoin flipACoin = new FlipACoin();
        Roll roll = new Roll();
        GoogleSearch googleSearch = new GoogleSearch();
        FX fx = new FX();
        fillMap(ping, help, play, reddit, twitter, flipACoin, roll, googleSearch, fx);
    }

    public void fillMap(Command... commands){ //you can use a construct called varargs to pass an arbitrary number of values to a method.
        for (Command cmd : commands){
            this.CommandMap.put(cmd.getCommand(),cmd);
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] message = event.getMessage().getContentRaw().split(" ");

        String commandName = message[0];
        Command command = CommandMap.get(commandName);

        if (command != null && !event.getAuthor().isBot()){ //Ignores bots
            command.execute(event, message);
        }
    }
}
