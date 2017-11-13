package filetObish;

import java.util.ArrayList;
import java.util.TreeMap;

public class filetObish {

	public TreeMap<Long, Comment> comments;
	public TreeMap<Double, ArrayList<Comment>> negativityMap;

	public filetObish(TreeMap<Long, Comment> comments) {
		this.comments = comments;
		negativityMap = new TreeMap<Double, ArrayList<Comment>>();
		CommentRater rater = new GoogleRater();
		for (Long id : comments.keySet()) {
			double rate = rater.rateComment(comments.get(id));
			if (!negativityMap.containsKey(rate)) {
				ArrayList<Comment> commentList = new ArrayList<Comment>();
				commentList.add(comments.get(id));
				negativityMap.put(rate, commentList);
			} else {
				negativityMap.get(rate).add(comments.get(id));
			}
		}
	}
}