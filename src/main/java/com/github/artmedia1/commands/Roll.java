package com.github.artmedia1.commands;

import com.github.artmedia1.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Random;

public class Roll extends Command {
    protected String description = "**!roll [x]** - rolls a dice with x number of sides";

    public Roll(){
        super("!roll");
    }
    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        try{
            Integer numSides = Integer.parseInt(args[1]);
            Random rand = new Random();
            Integer n = rand.nextInt(numSides);
            event.getChannel().sendMessage(n.toString()).queue();
        }
        catch (NumberFormatException e){
            event.getChannel().sendMessage("That is not a valid number.").queue();
        }


    }
}
