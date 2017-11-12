package filetObish;

import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TwitterComment extends Comment{
	Twitter twitter;
	public TwitterComment(long id, String content,Twitter twitter) {
		this.id = id;
		this.content = content;
		this.twitter = twitter;
	}

	@Override
	public void report(long id) {
		try {
			twitter.createBlock(id);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void reportAsSpam(long id) {
		try {
			twitter.reportSpam(id);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}