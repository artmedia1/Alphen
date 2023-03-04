package com.github.artmedia1.commands;

import com.github.artmedia1.Command;
import io.github.cdimascio.dotenv.Dotenv;

import net.dean.jraw.RedditClient;
import net.dean.jraw.http.NetworkAdapter;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.models.Listing;
import net.dean.jraw.models.SubredditSort;
import net.dean.jraw.models.TimePeriod;
import net.dean.jraw.oauth.Credentials;
import net.dean.jraw.oauth.OAuthHelper;
import net.dean.jraw.models.Submission;
import net.dean.jraw.pagination.DefaultPaginator;
import net.dean.jraw.pagination.Paginator;
import net.dean.jraw.references.SubredditReference;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Reddit extends Command {
    Dotenv env = Dotenv.load();
    private Dotenv config = Dotenv.load();
    private String[] tokens = new String[4];
    private Credentials credentials;
    private RedditClient client;
    private UserAgent userAgent;
    protected String description = "**!reddit [subreddit]** - Gets the first five threads on the HOT section of the subreddit.";
    public Reddit(){
        super("!reddit");
        this.tokens[0] = config.get("REDDIT_USERNAME");
        this.tokens[1] = config.get("REDDIT_PASSWORD");
        this.tokens[2] = config.get("REDDIT_APP_ID");
        this.tokens[3] = config.get("REDDIT_SECRET");
        this.userAgent = new UserAgent("bot", "com.github.artmedia1", "v1.0.0", "artmedia1");
        this.credentials = Credentials.script(tokens[0], tokens[1],tokens[2], tokens[3]);
        NetworkAdapter adapter = new OkHttpNetworkAdapter(userAgent);
        this.client = OAuthHelper.automatic(adapter, credentials);
    }

    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        SubredditReference subreddit = this.client.subreddit(args[1]);

        Paginator<Submission> paginator = subreddit.posts().sorting(SubredditSort.HOT).limit(3).build();
        DefaultPaginator<Submission> defaultPaginator = (DefaultPaginator<Submission>) paginator;

        Listing<Submission> topFiveHotThreads = paginator.next();
        for (Submission post : topFiveHotThreads.getChildren()) {
            String message = post.getTitle() + "\n" + "http://reddit.com" + post.getPermalink() + "\n";
            event.getChannel().sendMessage(message).queue();
        }
    }
}
