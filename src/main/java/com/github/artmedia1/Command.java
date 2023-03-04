package com.github.artmedia1;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class Command {
    private final String command;

    public Command(String commandName){
        this.command = commandName;
    }

    public String getCommand(){
        return command;
    }

    public abstract void execute(MessageReceivedEvent event, String[] args);
}
