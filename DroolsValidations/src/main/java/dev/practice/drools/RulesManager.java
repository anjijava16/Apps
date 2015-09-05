package dev.practice.drools;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

import dev.practice.db.connection.IOHelper;
import dev.practice.db.connection.Persistor;
import dev.practice.drools.jpa.entity.ValidationControl;
import dev.practice.drools.jpa.entity.ValidationErrors;
import dev.practice.util.DateUtil;

/* This is a sample class to launch a rule. */

public class RulesManager {
  private static final Log log = LogFactory.getLog(RulesManager.class);

  public static final void main(String[] args) {
    Persistor persistor = null;
    try {
      persistor = new Persistor();
      persistor.begin();

      FreedomPK pk = new FreedomPK();
      pk.setContinent("AMERICA");
      pk.setCountry("US");
      Freedom item1 = persistor.find(Freedom.class, pk);

      ValidationControl control = persistor.find(ValidationControl.class, 1);

      ValidationErrors error = new ValidationErrors();
      // error.setErrorSeqNbr(1);
      error.setErrorMsg("state is blank");
      error.setCreatedBy("Reddy");
      error.setCreatedDate(DateUtil.yyyyMMddFormat(DateUtil.now()));
      error.setCreatedTime(DateUtil.hh24miFormat(DateUtil.now()));

      // load up the knowledge base
      KnowledgeBase kbase = readKnowledgeBase();
      StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

      ksession.insert(item1);
      ksession.insert(control);
      ksession.insert(error);

      ksession.fireAllRules();

      persistor.persist(error);
      persistor.commit();

      System.out.println("\n Rules Executed Successfully....");

    }
    catch (Exception e) {
      log.error("Error to validate the Party Role ", e);
    }
    finally {
      IOHelper.close(persistor);
    }
  }

  private static KnowledgeBase readKnowledgeBase() throws Exception {

    KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

    kbuilder.add(ResourceFactory.newClassPathResource("rules/validations-001-099.drl"), ResourceType.DRL);
    kbuilder.add(ResourceFactory.newClassPathResource("rules/validations-100-199.drl"), ResourceType.DRL);

    KnowledgeBuilderErrors errors = kbuilder.getErrors();

    if (errors.size() > 0) {
      for (KnowledgeBuilderError error : errors) {
        System.err.println(error);
      }
      throw new IllegalArgumentException("Could not parse knowledge.");
    }

    KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
    kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

    return kbase;
  }
}
