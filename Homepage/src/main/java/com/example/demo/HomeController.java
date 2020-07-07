package com.example.demo;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class HomeController {


@RequestMapping("/")
public ModelAndView enter() {
return new ModelAndView("home");
}
@RequestMapping("/login")
public ModelAndView login(HttpServletRequest req , HttpServletResponse res) throws IOException{
return new ModelAndView("login");
}
   @RequestMapping("/neww")
   public ModelAndView neww() {
   return new ModelAndView("neww");
   }

@RequestMapping("/bill")
public ModelAndView enter1() {
return new ModelAndView("bill");
}

   @RequestMapping("/check")
   public ModelAndView check(HttpServletRequest req, HttpServletResponse res) {
   try{
      String username =req.getParameter("id"); 
      System.out.println(username);
      String password = req.getParameter("password");
      String rpassword=req.getParameter("rpassword");
      if(password.equals(rpassword)) {
      System.out.print("Pass match");
      Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
      Connection conn=DriverManager.getConnection(  
       "jdbc:mysql://localhost:3306/logins","root","Kishore@123");
     
      PreparedStatement pst = conn.prepareStatement("Select id,password from logins.login_details");
      ResultSet rs = pst.executeQuery();
      while(rs.next()){
       if (rs.getString("id").equals(username)){
       return new ModelAndView("welcome");
       }
      }
      pst = conn.prepareStatement("INSERT INTO logins.login_details (id,password)VALUES (?,?)");
      pst.setString(1, username);
      pst.setString(2, password);
      int n=pst.executeUpdate();
      }
      else {
    	  System.out.print("pass not match");
     return new ModelAndView("passalert");
      }
     
   }
      catch (Exception e) {
System.out.print(e);
}
   return new ModelAndView("home");
   }
@RequestMapping("/validate")
public ModelAndView test(HttpServletRequest req , HttpServletResponse res) throws IOException{
try{
      String username = req.getParameter("username");  
      String password = req.getParameter("password");
      Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
      Connection conn=DriverManager.getConnection(  
       "jdbc:mysql://localhost:3306/logins","root","Kishore@123");      
      PreparedStatement pst = conn.prepareStatement("Select id,password from logins.login_details where id=? and password=?");
      pst.setString(1, username);
      pst.setString(2, password);
      ResultSet rs = pst.executeQuery();    
      if(rs.next()) {
       return new ModelAndView("welcome");
      }
     
      else {
          return new ModelAndView("home1");
         
      }
 }
 catch(Exception e){      
 System.out.print(e);
 return new ModelAndView("home1");
 }      

}

}