package servlets;

import models.Students;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class creates and returns a json representation of students data
 * It is an API Endpoint
 */
@WebServlet(urlPatterns = {"/api","/api/*"})
public class JsonHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String name = request.getParameter("name");

        response.setContentType("application/json");

        //should be optimized to switch statement
        if(pathInfo == null){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }else if (pathInfo.equals("/")) {
            System.out.println("root");
        }else if (pathInfo.equals("/students")) {
            System.out.println("students");
            try(Jsonb jsonb = JsonbBuilder.create()) {
                Students students = new Students(100);
                String json = jsonb.toJson(students);
                response.getWriter().write(json);
            //not needed cose try with resource used.
                //jsonb.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (pathInfo.equals("/teachers")) {
            System.out.println("teachers");
        } else {
            System.out.println("path ej null men fel");
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
    }

