package com.sample.process;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;

public class HelloWorld {
	public static void main(String[] args) {
		  ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
			      .setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe")
			      .setJdbcUsername("SYSTEM")
			      .setJdbcPassword("SYSTEM")
			      .setJdbcDriver("oracle.jdbc.driver.OracleDriver")
			      .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
			    ProcessEngine processEngine = cfg.buildProcessEngine();
			    String pName = processEngine.getName();
			    String ver = ProcessEngine.VERSION;
			    System.out.println("ProcessEngine [" + pName + "] Version: [" + ver + "]");
			    RepositoryService repositoryService = processEngine.getRepositoryService();
			    Deployment deployment = repositoryService.createDeployment()
			        .addClasspathResource("myprocess.bpmn20.xml").deploy();
			    ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
			        .deploymentId(deployment.getId()).singleResult();
			    System.out.println(
			        "Found process definition [" 
			            + processDefinition.getName() + "] with id [" 
			            + processDefinition.getId() + "]");
			    RuntimeService runtimeService = processEngine.getRuntimeService();
			    ProcessInstance processInstance = runtimeService
			        .startProcessInstanceById("activitiAdhoc:1:4");
			    System.out.println(" process started with process instance id [" 
			        + processInstance.getProcessInstanceId()
			        + "] key [" + processInstance.getProcessDefinitionKey() + "]");
	}
}
