package com.github.artmedia1.commands;

import com.github.artmedia1.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Random;

public class FlipACoin extends Command {
    protected String description = "**!flip** - Flips a coin.";

    public FlipACoin(){
        super("!flip");
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        Random rand = new Random();
        int n = rand.nextInt(2);
        String message = "";

        if(n == 1){
            message += "Heads!";
        }
        else{
            message += "Tails!";
        }

        event.getChannel().sendMessage(message).queue();
    }
}
