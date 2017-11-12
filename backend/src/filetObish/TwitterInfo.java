package filetObish;

import java.util.List;

import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterInfo {

	public static void main(String[] args) throws TwitterException {
		// TODO Auto-generated method stub
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.setDebugEnabled(true).setOAuthConsumerKey("7660bNlMrjdvYjh2Beer1OBms")
				.setOAuthConsumerSecret("4oWEouoyRLTvjX3PF4xtYSCidrB0dwe5Gg9K1e8b3rhVSqtE5y")
				.setOAuthAccessToken("2410848375-fTSubzzXCZjRXOIpXHv9hkjTnZ7O4waHwnjCHiq")
				.setOAuthAccessTokenSecret("HofpKwldXPJd2iaktDHwvB1JEpUcR8RUsgq2mrWBFvsJP");

		TwitterFactory tf = new TwitterFactory(configurationBuilder.build());
		Twitter twitter = tf.getInstance();

		String user = "irishu37";
		Paging paging = new Paging(1, 100);

		List<Status> status = twitter.getUserTimeline(user, paging);
		List<Status> mentions = twitter.getMentionsTimeline(paging);

		for (Status s : status) {
			System.out.println(s.getUser().getName() + ", ID: " + s.getId() + ", Tweet: " + s.getText());
		}

		System.out.println("\nMentions:");
		
		for (Status s : mentions) {
			System.out.println(s.getUser().getName() + ", ID: " + s.getId() + ", Tweet: " + s.getText());
		}
		
		/*try {
			Query query = new Query("@" + user);
			QueryResult result;
			do {
				result = twitter.search(query);
				List<Status> tweets = result.getTweets();
				for (Status tweet : tweets) {
					System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
				}
			} while ((query = result.nextQuery()) != null);
			System.exit(0);
		} catch (TwitterException te) {
			te.printStackTrace();
		}*/
	}

}