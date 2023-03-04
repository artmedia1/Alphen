package com.github.artmedia1.commands;

import com.github.artmedia1.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Ping extends Command {
    protected String description = "**!ping** - Responds with pong if server is online.";
    public Ping(){
        super("!ping");
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        event.getChannel().sendMessage("pong!").queue();
    }
}
