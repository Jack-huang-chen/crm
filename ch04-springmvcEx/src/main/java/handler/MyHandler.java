package handler;

import ex.MyException;
import ex.MyNullException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
@ControllerAdvice
public class MyHandler {
    @ExceptionHandler(value = MyException.class)
    public ModelAndView handler(Exception e){
        ModelAndView ma = new ModelAndView();
        ma.addObject("error","名字不正确");
        ma.addObject("exception",e);
        ma.setViewName("doSome");
        return ma;
    }
    @ExceptionHandler
    public ModelAndView handler1(Exception e){
        ModelAndView ma = new ModelAndView();
        ma.addObject("error","其他异常");
        ma.addObject("exception",e);
        ma.setViewName("doSome");
        return ma;
    }
}
