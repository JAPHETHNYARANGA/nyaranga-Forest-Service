
import spark.ModelAndView;
import java.util.HashMap;
import java.util.Map;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        //postgres db details and connection


        //index
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<String, Object>();
//            model.put("HEALTHY", Endangered.HEALTHY);
//            model.put("OKAY", Endangered.OKAY);
//            model.put("ILL", Endangered.ILL);
//            model.put("NEWBORN", Endangered.NEWBORN);
//            model.put("YOUNG", Endangered.YOUNG);
//            model.put("ADULT", Endangered.ADULT);
            model.put("template", "template/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/park-animals/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("rangerName", request.session().attribute("rangerName"));
            model.put("template", "templates/animal.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/park-animals/new", (request, response) -> {
            String name = request.queryParams("name");
            boolean endangered = request.queryParamsValues("endangered") != null;
            if (endangered) {
                String health = request.queryParams("health");
                String age = request.queryParams("age");
                Endangered endangeredAnimal = new Endangered(name, age, health);
                endangeredAnimal.save();
            } else {
                NonEndangered notEndangered = new NonEndangered(name);
                notEndangered.save();
            }
            response.redirect("/park-animals");
            return null;
        });


        get("/park-animals", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("nonendangered", NonEndangered.all());
            model.put("endangered", Endangered.all());
            model.put("template", "templates/animals.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/park-sightings/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Endangered> animals = Endangered.all();
            model.put("animals", animals);
            model.put("template", "templates/park-sightings-form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/sightings/new", (request, response) ->{
            Map<String, Object> model = new HashMap<String, Object>();
            Endangered animal = Endangered.find(Integer.parseInt(request.queryParams("animalid")));
            String location = request.queryParams("location");
            String rangername= request.queryParams("rangername");
            Sightings newSighting = new Sightings(location, rangername, animal.getId());
            newSighting.save();
            model.put("template", "templates/sightings.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/sightings", (request, response) ->{
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("sightings", Sightings.all());
            model.put("template", "templates/sightings.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

    }


}




