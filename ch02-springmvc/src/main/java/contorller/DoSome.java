package contorller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import vo.Student;
@RequestMapping("/user")
@Controller
public class DoSome {
    @RequestMapping(value = "/login.do")//指定来访的路径
    public ModelAndView doSome(String name){

        ModelAndView ma = new ModelAndView();
        ma.addObject("msg",name);//相当于request.setAttribute()

        //指定视图
        ma.setViewName("doSome");//指定视图指定完整路径，框架对视图执行请求转发操作
        return ma;
    }


}
