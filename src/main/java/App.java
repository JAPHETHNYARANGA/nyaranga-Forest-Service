
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
            model.put("template", "template/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        }



    }




