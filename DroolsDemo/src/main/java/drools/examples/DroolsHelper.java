package drools.examples;

import org.drools.runtime.rule.RuleContext;

public class DroolsHelper{
       
        public static void ok(RuleContext rc, Account c){
              System.out.println("Rule: " + rc.getRule().getName());
        }
   }
