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
package org.flowable.dmn.engine.impl.cmd;

import java.io.Serializable;

import org.flowable.dmn.engine.impl.interceptor.Command;
import org.flowable.dmn.engine.impl.interceptor.CommandContext;
import org.flowable.dmn.engine.impl.util.DecisionTableUtil;
import org.flowable.dmn.model.DmnDefinition;
import org.flowable.engine.common.api.FlowableIllegalArgumentException;

/**
 * @author Joram Barrez
 */
public class GetDmnDefinitionCmd implements Command<DmnDefinition>, Serializable {

  private static final long serialVersionUID = 1L;

  protected String decisionTableId;

  public GetDmnDefinitionCmd(String decisionTableId) {
    this.decisionTableId = decisionTableId;
  }

  public DmnDefinition execute(CommandContext commandContext) {
    if (decisionTableId == null) {
      throw new FlowableIllegalArgumentException("decisionTableId is null");
    }

    return DecisionTableUtil.getDmnDefinition(decisionTableId);
  }
}