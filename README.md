# Alphen
 Personal Discord Bot

## About
Alphen is a [Discord](https://discord.com) bot with currently 8 features that acts to centralize different features and websites onto a discord server.

<p align="center">
  <img src="https://github.com/artmedia1/Alphen/blob/main/media/Alphen-Demo.gif?raw=true" alt="Alphen Demo">
</p>


## Commands

### HELP
+ **!help** - responds with all features and documentation on how to use each command.
+ **!ping** - Responds with pong if server is online."


### Social Media
+ **!reddit subreddit** - Gets the first five threads on the hot section of the **subreddit**.


### Dice and Coins
+ **!flip** - Flips a coin.
+ **!roll x** - Rolls a dice with **x** number of sides.

### Google Search
+ **!google query**  - Responds with the top 3 links from google for your **query**.

### Currency Conversion
+ **!fx amount first second** - Converts an **amount** of **first** currency into **second**.
+ **!fx currencies** - Responds with list of available currencies codes.



## Getting Started
1. Go to `.env.example`, rename file to `.env` and provide the following credentials:

   [Get your Discord token](https://discord.com/developers/applications)  
   + `DISCORD_TOKEN=REPLACE_ME`

   [Get your Reddit Credentials](https://www.reddit.com/prefs/apps)  
   + `REDDIT_USERNAME=REPLACE_ME`
   + `REDDIT_PASSWORD=REPLACE_ME`
   + `REDDIT_APP_ID=REPLACE_ME`
   + `REDDIT_SECRET=REPLACE_ME`

   [Get your Google Google API Key](https://console.cloud.google.com/apis/credentials)
   + `GOOGLE_API_KEY=REPLACE_ME`

   [Get your Google Custom Search Engine ID Key](https://programmablesearchengine.google.com)  
   + `GOOGLE_SEARCH_ENGINE_ID=REPLACE_ME `

   [Get your Exchange Rate API Key](https://www.exchangerate-api.com/docs/java-currency-api)
   + `EXCHANGE_RATE_API_KEY=REPLACE_ME`


2. Place the .env file in the same directory as the `Alphen.jar` located in `out/artifacts/Alphen_jar/`

3. Run Alphen.jar

## TODO
+ **!twitter**
  + I am waiting on api access from twitter to work on this further.
+ **!play**
  + This isn't working right now, am working on a fix. 
