package contorller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import vo.Student;

@Controller
@RequestMapping()
public class DoSome {
    @RequestMapping(value = "/some.do")//指定来访的路径
    public ModelAndView doSome(String name,Integer age){

        ModelAndView ma = new ModelAndView();
        ma.addObject("msg",name);//相当于request.setAttribute()
        ma.addObject("fun",age);
        //指定视图
        ma.setViewName("doSome");//指定视图指定完整路径，框架对视图执行请求转发操作
        return ma;
    }
    @RequestMapping(value = "/other.do",method = RequestMethod.POST)//指定来访的路径
    public ModelAndView doOther(String name,Integer age) {

        ModelAndView ma = new ModelAndView();
        ma.addObject("msg", name);//相当于request.setAttribute()
        ma.addObject("fun", age);
        //指定视图
        ma.setViewName("doSome");//指定视图指定完整路径，框架对视图执行请求转发操作
        return ma;
    }
    @RequestMapping(value = "/param.do",method = RequestMethod.POST)
    public ModelAndView param(Student student){
        ModelAndView ma = new ModelAndView();
        ma.addObject("student",student);
        ma.setViewName("doSome");
        return ma;
    }
    @ResponseBody
    @RequestMapping(value = "/ajax.do",method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
    public Student ajax(Student student){
       Student s = new Student();
       s.setAge(student.getAge());
       s.setName(student.getName());

       return s;
    }
    @ResponseBody
    @RequestMapping(value = "/string.do",method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
    public String stringDo(Student student){
        String msg = "huangchenhui加油，奥";

        return msg;
    }
}
