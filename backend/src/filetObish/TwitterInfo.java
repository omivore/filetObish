package filetObish;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TwitterInfo implements InfoGatherer{
	
	Twitter twitter;
	Paging paging;
	
	public TwitterInfo(Twitter twitter) {
		this.twitter = twitter;
		paging = new Paging(1, 100);
		
	}
	
	public TreeMap<Long,Comment> getIdCommentsTree(){
		TreeMap<Long,Comment> map = new TreeMap<Long,Comment>();

		List<Status> mentions = new ArrayList<Status>();
		try {
			mentions = twitter.getMentionsTimeline(paging);
		} catch (TwitterException e) {
			e.printStackTrace();
		}

		for (Status s : mentions) {
			Comment com = new TwitterComment(s.getId(), s.getText());
			map.put(s.getId(), com);
		}
		
		return map;
	}

}