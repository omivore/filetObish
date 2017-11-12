package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import play.libs.F.Tuple;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterGate {

    static public Map<Long, Tuple<String, String>> userTokens = new HashMap<>();

    Twitter twitter = TwitterFactory.getSingleton();
    RequestToken requestToken;

    public String login() throws TwitterException {
        if (requestToken == null) {
            twitter.setOAuthConsumer("7660bNlMrjdvYjh2Beer1OBms",
                                     "4oWEouoyRLTvjX3PF4xtYSCidrB0dwe5Gg9K1e8b3rhVSqtE5y");
            requestToken = twitter.getOAuthRequestToken();
        }
        return requestToken.getAuthenticationURL();
    }

    public Long getAccess(String pin) throws TwitterException {
        AccessToken access = twitter.getOAuthAccessToken(requestToken, pin);
        userTokens.put(twitter.verifyCredentials().getId(),
                       new Tuple<String, String>(access.getToken(), access.getTokenSecret()));
        return twitter.verifyCredentials().getId();
    }

    public static Twitter getUserTwitter(Long user) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey("7660bNlMrjdvYjh2Beer1OBms")
          .setOAuthConsumerSecret("4oWEouoyRLTvjX3PF4xtYSCidrB0dwe5Gg9K1e8b3rhVSqtE5y")
          .setOAuthAccessToken(userTokens.get(user)._1)
          .setOAuthAccessTokenSecret(userTokens.get(user)._2);
        return new TwitterFactory(cb.build()).getInstance();
    }

    public static String getTimeline(Long user) {
        Twitter twitter = getUserTwitter(user);
        try {
            List<Status> statuses = twitter.getHomeTimeline();
            System.out.println("Showing home timeline.");
            for (Status status : statuses) {
                System.out.println(status.getUser().getName() + ":" +
                                   status.getText());
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return "Wheee";
    }

    public static String getPoisons(Long user) {
        Twitter twitter = getUserTwitter(user);
        /*TwitterInfo info = TwinterInfo(twitter);
        new filetObish(info.getIdCommentsTree()).getRatingMap();*/
        return null;
    }
}
