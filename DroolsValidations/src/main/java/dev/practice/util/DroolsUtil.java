package dev.practice.util;

import org.drools.spi.KnowledgeHelper;

public class DroolsUtil {
   public static void help(final KnowledgeHelper drools, final String message){
      System.out.println(message);
      System.out.println("\n Rule Triggered: " + drools.getRule().getName());
   }
   
   public static void helper(final KnowledgeHelper drools){
      System.out.println("\n Rule Triggered: " + drools.getRule().getName());
   }
}
