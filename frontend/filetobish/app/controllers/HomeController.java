package controllers;

import javax.inject.Inject;

import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private final Form<TwitterUserData> form;

    @Inject
    public HomeController(FormFactory formFactory) {
        this.form = formFactory.form(TwitterUserData.class);
    }

    public Result index() {
        return ok(views.html.index.render());
    }

    public Result findUser() {
        //try {
            return ok(views.html.twitter.render(form));
            //return redirect(TwitterGate.login());
        /*} catch (TwitterException e) {
            return internalServerError(e.getMessage());
        }*/
    }

}
