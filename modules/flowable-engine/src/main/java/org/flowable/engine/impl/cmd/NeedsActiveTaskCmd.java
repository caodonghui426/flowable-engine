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
package org.flowable.engine.impl.cmd;

import java.io.Serializable;

import org.flowable.engine.common.api.FlowableException;
import org.flowable.engine.common.api.FlowableIllegalArgumentException;
import org.flowable.engine.common.api.FlowableObjectNotFoundException;
import org.flowable.engine.impl.interceptor.Command;
import org.flowable.engine.impl.interceptor.CommandContext;
import org.flowable.engine.impl.persistence.entity.TaskEntity;
import org.flowable.engine.task.Task;

/**
 * An abstract superclass for {@link Command} implementations that want to verify the provided task is always active (ie. not suspended).
 * 
 * @author Joram Barrez
 */
public abstract class NeedsActiveTaskCmd<T> implements Command<T>, Serializable {

  private static final long serialVersionUID = 1L;

  protected String taskId;

  public NeedsActiveTaskCmd(String taskId) {
    this.taskId = taskId;
  }

  public T execute(CommandContext commandContext) {

    if (taskId == null) {
      throw new FlowableIllegalArgumentException("taskId is null");
    }

    TaskEntity task = commandContext.getTaskEntityManager().findById(taskId);

    if (task == null) {
      throw new FlowableObjectNotFoundException("Cannot find task with id " + taskId, Task.class);
    }

    if (task.isSuspended()) {
      throw new FlowableException(getSuspendedTaskException());
    }

    return execute(commandContext, task);
  }

  /**
   * Subclasses must implement in this method their normal command logic. The provided task is ensured to be active.
   */
  protected abstract T execute(CommandContext commandContext, TaskEntity task);

  /**
   * Subclasses can override this method to provide a customized exception message that will be thrown when the task is suspended.
   */
  protected String getSuspendedTaskException() {
    return "Cannot execute operation: task is suspended";
  }

}
