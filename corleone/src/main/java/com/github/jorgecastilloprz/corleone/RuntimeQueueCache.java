/*
 * Copyright (C) 2015 Jorge Castillo Pérez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jorgecastilloprz.corleone;

import java.util.HashMap;

/**
 * Queues need to be accessed and instantiated during runtime. Reflection is very slow and we
 * dont want to search for the classes everytime that {@link JobDispatcher} needs to use them.
 * That is the main purpose of this cache.
 *
 * @author Jorge Castillo Pérez
 */
class RuntimeQueueCache {

  private HashMap<String, RuntimeQueue> runtimeQueueInstances;

  RuntimeQueueCache() {
    runtimeQueueInstances = new HashMap<>();
  }

  RuntimeQueue getQueueForContext(String context) {
    if (runtimeQueueInstances.containsKey(context)) {
      return runtimeQueueInstances.get(context);
    } else {
      return buildRuntimeQueueForContext(context);
    }
  }

  private RuntimeQueue buildRuntimeQueueForContext(String context) {
    String runtimeQueueClassName = NameUtils.getRuntimeQueueQualifiedName(context);

    Class<?> runtimeQueueClass = ClassUtils.findInClassPath(runtimeQueueClassName);
    RuntimeQueue runtimeQueue = (RuntimeQueue) ClassUtils.buildClassInstance(runtimeQueueClass);
    runtimeQueueInstances.put(context, runtimeQueue);
    return runtimeQueue;
  }
}
