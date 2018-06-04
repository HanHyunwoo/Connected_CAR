package com.controller;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class RLoad {
   public static void main(String[] args) {
      RConnection rconn = null;
      try {
         rconn = new RConnection();
      } catch (RserveException e) {
         System.out.println("R Connection Error");
      }
      System.out.println("OK");
      try {
         rconn.setStringEncoding("utf8");
         rconn.eval("source('C://Users//student//Documents//TIL//R//ASRADA//test.R',encoding='UTF-8')");
                             
         REXP z = rconn.eval("r3()");
         int result = z.asInteger();
         System.out.println(result);
         
         REXP x = rconn.eval("1+2");
         System.out.println(x.asInteger());
      } catch (Exception e) {
         e.printStackTrace();
         System.out.println("why");
      }
      System.out.println("END");

   }

}