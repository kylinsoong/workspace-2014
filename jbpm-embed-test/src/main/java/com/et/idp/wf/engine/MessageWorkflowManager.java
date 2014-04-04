package com.et.idp.wf.engine;

import com.et.idp.wf.model.WorkflowInitiate;
import com.et.idp.wf.model.service.ServiceFinish;
import com.et.idp.wf.model.service.ServiceProgress;
import com.et.idp.wf.model.service.ServiceStart;

/**
 * Workflow manager interface for incoming message handlers.
 * 
 * @author mimartin
 */
public interface MessageWorkflowManager {
	/**
	 * Called due to JMS message to initiate a new workflow.
	 * 
	 * @param options
	 * @return process instance id
	 * @throws JbpmException
	 *             if no workflow exists for the given key and/or version
	 */
	public String initiateWorkflow(WorkflowInitiate options);

	/**
	 * Called due to JMS message as a result of a service starting processing.
	 * 
	 * @param executionId
	 *            execution id
	 * @param options
	 */
	public void startServiceStep(String executionId, ServiceStart options);

	/**
	 * Called due to JMS message as a result of a service reporting progress.
	 * 
	 * @param executionId
	 *            execution id
	 * @param options
	 */
	public void progressServiceStep(String executionId, ServiceProgress options);

	/**
	 * Called due to JMS message as a result of a service finishing processing.
	 * 
	 * @param executionId
	 * @param options
	 */
	public void finishServiceStep(String executionId, ServiceFinish options);
}
