package contorller;

import ex.MyException;
import ex.MyNullException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import vo.Student;

@Controller
@RequestMapping("/user")
public class DoSome {
    @RequestMapping(value = "/some.do",method = RequestMethod.POST)//指定来访的路径
    public ModelAndView doSome(String name,Integer age) throws MyException {

        ModelAndView ma = new ModelAndView();
        ma.addObject("name",name);//相当于request.setAttribute()
        ma.addObject("age",age);
       if ("zs".equals(name)){
        throw new MyException("名字不对");}
        //指定视图
        ma.setViewName("doSome");//指定视图指定完整路径，框架对视图执行请求转发操作
        return ma;
    }

}
