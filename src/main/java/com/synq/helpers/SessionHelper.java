package com.synq.helpers;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class SessionHelper {
    public  void removeMessage()
    {
try {
    HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    session.removeAttribute("message");
    System.out.println("Message Removed");
}
catch (Exception e){
    e.printStackTrace();

}

    }
}
