package com.github.artmedia1.commands;

import com.github.artmedia1.Command;
import com.github.artmedia1.lavalplayer.PlayerManager;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;


public class Play extends Command{
    protected String description = "**!Play 'Title'** - Joins chat and plays the first video of 'Title' found on Youtube.";
    public Play() {
        super("!play");
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        String[] subArr = Arrays.copyOfRange(args, 1, args.length);
        String title = String.join(" ", subArr);

        if(!isUrl(title)){
            title = "ytsearch:" + title + " official audio";
        }

        GuildVoiceState memberVoiceState = event.getMember().getVoiceState();
        if (memberVoiceState.inAudioChannel()) { //Check to see if user is in audio channel
            AudioChannel channel = event.getMember().getVoiceState().getChannel();
            GuildVoiceState botVoiceState = event.getGuild().getSelfMember().getVoiceState();
            if (!botVoiceState.inAudioChannel()) { //If bot is not in a voice channel
                AudioManager audioManager = event.getGuild().getAudioManager();
                PlayerManager playerManager = new PlayerManager();
                playerManager.getINSTANCE().loadAndPlay((TextChannel) event.getChannel(), title);
                audioManager.openAudioConnection(channel); // connect bot to the user's voice channel
            }
            else {
                event.getChannel().sendMessage("I am already in another voice channel!").queue();
            }
        }
        else {
            event.getChannel().sendMessage("User is not in a voice channel!").queue();
        }
    }

    public boolean isUrl(String url){
        try{
            new URI(url);
            return true;
        }
        catch (URISyntaxException e){
            return false;
        }
    }
}
