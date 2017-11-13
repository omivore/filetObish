package controllers;

import javax.inject.Inject;

import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import twitter4j.TwitterException;

public class TwitterController extends Controller {
    private final Form<TwitterUserData> form;
    private TwitterGate twitter;

    @Inject
    public TwitterController(FormFactory formFactory) {
        this.form = formFactory.form(TwitterUserData.class);
        this.twitter = new TwitterGate();
    }

    public Result findUser() {
        return ok(views.html.twitter.render(form));
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
                System.out.println("user: " + user);
                return HomeController.listResults(TwitterGate.getPoisons(user));
            } catch (TwitterException e) {
                e.printStackTrace();
                return badRequest("Bad PIN :(");
            }
        }
    }
}
