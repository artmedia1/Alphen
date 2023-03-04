package com.github.artmedia1;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class Main {

    private final Dotenv config;
    private final ShardManager shardManager;

    public Main() throws LoginException {
        config = Dotenv.configure().load();
        String token = config.get("DISCORD_TOKEN");
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.watching("you"));
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        builder.enableCache(CacheFlag.VOICE_STATE);
        shardManager = builder.build();

        //Register Listeners
        //shardManager.addEventListener(new EventListener());
        shardManager.addEventListener(new CommandManager());
    }
    public Dotenv getConfig(){
        return config;
    }
    public ShardManager getShardManager(){
        return shardManager;
    }
    public static void main(String[] args){
        try{
            Main bot = new Main();
        }
        catch (LoginException e){
            System.out.println("Error: Provided bot token is invalid.");
        }
    }
}
