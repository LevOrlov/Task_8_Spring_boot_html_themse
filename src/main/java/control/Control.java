package control;

import dao.UserDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

//ну здесь собственно объясняется спринг, что этот класс явялется сервлетом(контроллером)
@Controller
public class Control {
    //Аннотация @Autowired неявно внедряет объектную зависимость.
    @Autowired
    private UserDao UserServiceImpl;

    //он создает базовый URI, для которого будет использоваться контроллер
    @RequestMapping(value = "/")
    public ModelAndView listContact(ModelAndView model) {
        List<User> listContact = UserServiceImpl.getAllUsers();
        model.addObject("listContact", listContact);
        //TODO при ревью перейди на страницы home , там херня про инкапусляцию
        model.setViewName("home");
        return model;
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.GET)
    public ModelAndView addUser() {
        return new ModelAndView("add");
    }

    @RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user) {
        UserServiceImpl.addUser(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
    public ModelAndView editUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = UserServiceImpl.getUserById(userId);
        ModelAndView model = new ModelAndView("edit");
        model.addObject("user", user);
        return model;
    }

    @RequestMapping(value = "/admin/edit", method = RequestMethod.POST)
    public String editUser(@ModelAttribute User user, HttpServletRequest request) {
        user.setId(Integer.parseInt(request.getParameter("id")));
        UserServiceImpl.updateUser(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
    public String deleteContact(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("id"));
        UserServiceImpl.deleteUser(userId);
        return "redirect:/";
    }

}