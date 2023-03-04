package com.github.artmedia1.commands;

import com.github.artmedia1.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.v1.Trend;
import twitter4j.v1.Trends;

import java.util.Arrays;

public class TwitterTrends extends Command {
    protected String description = "**!twitter** - Gets the top ten worldwide trending handles!";
    public TwitterTrends()  {
        super("!twitter");
    }
    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        Trend[] trends = getTrends();
        for(Trend trend: trends){
            String message = trend.getName();
            event.getChannel().sendMessage(message).queue();
        }
    }

    public Trend[] getTrends(){
        Twitter twitter = Twitter.getInstance();
        Trend[] topTrends = new Trend[10];
        try{
            Trend[] trends  = twitter.v1().trends().getPlaceTrends(1).getTrends();
            topTrends = Arrays.copyOfRange(trends, 0, 10);
        }
        catch (TwitterException e){
            System.out.println("TwitterException");
        }
        System.out.println(topTrends.length);
        return topTrends;
    }

}
