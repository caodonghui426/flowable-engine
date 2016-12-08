/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti5.engine.impl.bpmn.parser.factory;

import org.activiti5.engine.impl.bpmn.parser.BpmnParse;
import org.activiti5.engine.impl.bpmn.parser.BpmnParser;
import org.activiti5.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.flowable.bpmn.model.ActivitiListener;
import org.flowable.bpmn.model.EventListener;
import org.flowable.engine.common.api.delegate.event.FlowableEventListener;
import org.flowable.engine.delegate.ExecutionListener;
import org.flowable.engine.delegate.TaskListener;

/**
 * Factory class used by the {@link BpmnParser} and {@link BpmnParse} to instantiate
 * the behaviour classes for {@link TaskListener} and {@link ExecutionListener} usages.
 *
 * You can provide your own implementation of this class. This way, you can give
 * different execution semantics to the standard construct. 
 * 
 * The easiest and advisable way to implement your own {@link ListenerFactory} 
 * is to extend the {@link DefaultListenerFactory}.
 * 
 * An instance of this interface can be injected in the {@link ProcessEngineConfigurationImpl}
 * and its subclasses. 
 * 
 * @author Joram Barrez
 */
public interface ListenerFactory {

  public abstract TaskListener createClassDelegateTaskListener(ActivitiListener activitiListener);

  public abstract TaskListener createExpressionTaskListener(ActivitiListener activitiListener);

  public abstract TaskListener createDelegateExpressionTaskListener(ActivitiListener activitiListener);

  public abstract ExecutionListener createClassDelegateExecutionListener(ActivitiListener activitiListener);

  public abstract ExecutionListener createExpressionExecutionListener(ActivitiListener activitiListener);

  public abstract ExecutionListener createDelegateExpressionExecutionListener(ActivitiListener activitiListener);
  
  public abstract FlowableEventListener createClassDelegateEventListener(EventListener eventListener);
  
  public abstract FlowableEventListener createDelegateExpressionEventListener(EventListener eventListener);
  
  public abstract FlowableEventListener createEventThrowingEventListener(EventListener eventListener);

}