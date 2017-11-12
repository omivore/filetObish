package controllers;

import java.util.Map;

import play.libs.F.Tuple;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

public class TwitterGate {

    static Map<Long, Tuple<String, String>> userTokens;

    public static String login() throws TwitterException {
        // The factory instance is re-useable and thread safe.
        Twitter twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer("7660bNlMrjdvYjh2Beer1OBms",
                                 "4oWEouoyRLTvjX3PF4xtYSCidrB0dwe5Gg9K1e8b3rhVSqtE5y");
        RequestToken requestToken = twitter.getOAuthRequestToken();
        return requestToken.getAuthenticationURL();
            /*System.out.println(requestToken.getAuthorizationURL());
            System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
            String pin = br.readLine();
            try{
                if(pin.length() > 0){
                    accessToken = twitter.getOAuthAccessToken(requestToken, pin);
                } else{
                    accessToken = twitter.getOAuthAccessToken();
                }
            } catch (TwitterException te) {
                if(401 == te.getStatusCode()){
                    System.out.println("Unable to get the access token.");
                } else{
                    te.printStackTrace();
                }
            }
            //persist to the accessToken for future reference.
            userTokens.put(twitter.verifyCredentials().getId(),
                           new Tuple<String, String>(accessToken.getToken(), accessToken.getTokenSecret()));
        }*/
    }
}
