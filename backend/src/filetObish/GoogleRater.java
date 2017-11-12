package filetObish;

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;

public class GoogleRater implements CommentRater {
	@Override
	public double rateComment(Comment comment) {
		try (LanguageServiceClient language = LanguageServiceClient.create()) {

			// The text to analyze
			String text = comment.toString();
			Document doc = Document.newBuilder().setContent(text).setType(Type.PLAIN_TEXT).build();

			// Detects the sentiment of the text
			Sentiment sentiment = language.analyzeSentiment(doc).getDocumentSentiment();

			return (sentiment.getScore()*sentiment.getMagnitude());

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			return 0;
		}

	}
}
