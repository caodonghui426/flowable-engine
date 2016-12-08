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
package org.flowable.idm.engine.impl.cmd;

import java.io.Serializable;

import org.flowable.engine.common.api.FlowableIllegalArgumentException;
import org.flowable.engine.common.impl.persistence.entity.Entity;
import org.flowable.idm.api.User;
import org.flowable.idm.engine.impl.interceptor.Command;
import org.flowable.idm.engine.impl.interceptor.CommandContext;
import org.flowable.idm.engine.impl.persistence.entity.UserEntity;

/**
 * @author Joram Barrez
 */
public class SaveUserCmd implements Command<Void>, Serializable {

  private static final long serialVersionUID = 1L;
  protected User user;

  public SaveUserCmd(User user) {
    this.user = user;
  }

  public Void execute(CommandContext commandContext) {
    if (user == null) {
      throw new FlowableIllegalArgumentException("user is null");
    }
    if (commandContext.getUserEntityManager().isNewUser(user)) {
      if (user instanceof UserEntity) {
        commandContext.getUserEntityManager().insert((UserEntity) user,true);
      } else {
        commandContext.getDbSqlSession().insert((Entity) user);
      }
    } else {
      commandContext.getUserEntityManager().updateUser(user);
    }

    return null;
  }
}
