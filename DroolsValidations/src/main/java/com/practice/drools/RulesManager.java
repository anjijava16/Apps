package com.practice.drools;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

import com.practice.drools.jpa.entity.ValidationControl;
import com.practice.drools.jpa.entity.ValidationErrors;

/* This is a sample class to launch a rule. */

public class RulesManager {
	public static final void main(String[] args) {
		try {
			EntityManagerFactory emfactory = Persistence
					.createEntityManagerFactory("validations");
			EntityManager entitymanager = emfactory.createEntityManager();
			entitymanager.getTransaction().begin();

			FreedomPK pk = new FreedomPK();
			pk.setContinent("AMERICA");
			pk.setCountry("US");
			Freedom item1 = entitymanager.find(Freedom.class, pk);

			ValidationControl control = entitymanager.find(
					ValidationControl.class, 1);

			ValidationErrors error = new ValidationErrors();
			// error.setErrorSeqNbr(1);
			error.setErrorMsg("state is empty");
			error.setCreatedBy("Reddy");
			error.setCreatedDate(16082015);
			error.setCreatedTime(1646);

			// load up the knowledge base
			KnowledgeBase kbase = readKnowledgeBase();
			StatefulKnowledgeSession ksession = kbase
					.newStatefulKnowledgeSession();

			ksession.insert(item1);
			ksession.insert(control);
			ksession.insert(error);

			ksession.fireAllRules();

			entitymanager.persist(error);
			entitymanager.getTransaction().commit();

			entitymanager.close();
			emfactory.close();

			System.out.println(item1.getState() + " " + item1.getDate());

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private static KnowledgeBase readKnowledgeBase() throws Exception {

		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();

		kbuilder.add(
				ResourceFactory.newClassPathResource("validations-001-099.drl"),
				ResourceType.DRL);
		kbuilder.add(
				ResourceFactory.newClassPathResource("validations-100-199.drl"),
				ResourceType.DRL);

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
