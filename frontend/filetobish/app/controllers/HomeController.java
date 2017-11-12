package controllers;

import javax.inject.Inject;

import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import twitter4j.TwitterException;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private final Form<TwitterUserData> form;
    private TwitterGate twitter;

    @Inject
    public HomeController(FormFactory formFactory) {
        this.form = formFactory.form(TwitterUserData.class);
        this.twitter = new TwitterGate();
    }

    public Result index() {
        return ok(views.html.index.render());
    }

    public Result findUser() {
        return ok(views.html.twitter.render(form));
    }

    public Result listResults() {
        return ok("UM");
    }

    public Result twitterGate() {
        try {
            return redirect(twitter.login());
        } catch (TwitterException e) {
            return internalServerError(e.getMessage());
        }
    }

    public Result twitterAccess() {
        final Form<TwitterUserData> boundForm = form.bindFromRequest();

        if (boundForm.hasErrors()) {
            System.out.println(boundForm.toString());
            return badRequest("Form errors :(");
        } else {
            TwitterUserData data = boundForm.get();
            try {
                long user = twitter.getAccess(data.getPin());
                TwitterGate.getTimeline(user);
                return redirect(routes.HomeController.listResults());
            } catch (TwitterException e) {
                e.printStackTrace();
                return badRequest("Bad PIN :(");
            }
        }
    }
}
