package controllers;

import javax.inject.Inject;

import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

public class InstagramController extends Controller {
    private final Form<InstagramUserData> form;

    @Inject
    public InstagramController(FormFactory formFactory) {
        this.form = formFactory.form(InstagramUserData.class);
    }

    public Result findUser() {
        return ok(views.html.instagram.render(form));
    }

}
