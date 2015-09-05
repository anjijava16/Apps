package dev.practice.drools;

import java.io.Reader;
import java.io.StringReader;
import java.util.Collection;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.KnowledgePackage;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

public class RunRules {

  private static KnowledgeBuilder             kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
  private static Collection<KnowledgePackage> pkgs;
  private static KnowledgeBase                kbase    = KnowledgeBaseFactory.newKnowledgeBase();
  private static StatefulKnowledgeSession     ksession;

  public static void main(final String[] args) {

    initDrools();
    initMessageObject();
    fireRules();

  }

  private static void initDrools() {

    // this will parse and compile in one step
    // read from file
    kbuilder.add(ResourceFactory.newClassPathResource("validations-001-099.drl", RunRules.class), ResourceType.DRL);

    // read second rule from String
    String myRule = "import com.practice.drools.Freedom; rule \"Hello World 2\" when eval(true) then System.out.println(\"Test, Drools!\"); end";
    Resource myResource = ResourceFactory.newReaderResource((Reader) new StringReader(myRule));
    kbuilder.add(myResource, ResourceType.DRL);

    // Check the builder for errors
    if (kbuilder.hasErrors()) {
      System.out.println(kbuilder.getErrors().toString());
      throw new RuntimeException("Unable to compile drl\".");
    }

    // get the compiled packages (which are serializable)
    pkgs = kbuilder.getKnowledgePackages();

    // add the packages to a knowledgebase (deploy the knowledge packages).
    kbase.addKnowledgePackages(pkgs);

    ksession = kbase.newStatefulKnowledgeSession();
  }

  private static void fireRules() {
    ksession.fireAllRules();
  }

  private static void initMessageObject() {
    Freedom free = new Freedom();
    free.setState("HP");
    ksession.insert(free);
  }
}
