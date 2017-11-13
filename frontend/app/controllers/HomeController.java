package controllers;

import static play.libs.Scala.asScala;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableSet;

import filetObish.Comment;
import filetObish.filetObish;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    public Result index() {
        return ok(views.html.index.render());
    }

    public static Result listResults(filetObish philae) {
        // Extract filetObish
        List<Long> topOffenders = new LinkedList<Long>();
        NavigableSet<Double> keys = philae.negativityMap.descendingKeySet();

        System.out.println("Philae map");
        System.out.println(philae.negativityMap);

        // Fill topOffenders with the top offenders
        Iterator<Double> key = keys.iterator();
        if (key.hasNext()) {
            Iterator<Comment> com = philae.negativityMap.get(key.next()).iterator();
            for (Comment current = com.next(); topOffenders.size() < 10; com.next()) {
                topOffenders.add(current.id);
                if (!com.hasNext()) {
                    if (key.hasNext()) {
                        com = philae.negativityMap.get(key.next()).iterator();
                    } else {
                        break;
                    }
                }
            }
        }

        System.out.println(topOffenders);

        // Create ordered list of comments
        List<models.Comment> displaySet = new LinkedList<models.Comment>();
        for (Long keyId : topOffenders) {
            displaySet.add(new models.Comment(philae.comments.get(keyId).id, philae.comments.get(keyId).content));
        }

        System.out.println(displaySet);

        // Display top of filetObish
        return ok(views.html.results.render(asScala(displaySet)));
    }
}
