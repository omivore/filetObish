package filetObish;

import java.util.ArrayList;
import java.util.TreeMap;

public class filetObish {
	public static TreeMap<Double,ArrayList<Comment>> getRatingMap(TreeMap<Long,Comment> comments){
		TreeMap<Double,ArrayList<Comment>> negativityMap = new TreeMap<Double,ArrayList<Comment>>();
		GoogleRater rater = new GoogleRater();
		for(Long id:comments.keySet()) {
			double rate = rater.rateComment(comments.get(id));
			if(!negativityMap.containsKey(rate)) {
				ArrayList<Comment> commentList = new ArrayList<Comment>();
				commentList.add(comments.get(id));
				negativityMap.put(rate, commentList);
			} else {
				negativityMap.get(rate).add(comments.get(id));
			}
		}
		return negativityMap;
	}
}