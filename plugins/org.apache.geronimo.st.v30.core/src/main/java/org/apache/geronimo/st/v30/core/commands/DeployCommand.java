/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.geronimo.st.v30.core.commands;

import java.io.File;

import org.apache.geronimo.st.v30.core.DeploymentUtils;
import org.apache.geronimo.st.v30.core.IGeronimoServer;
import org.apache.geronimo.st.v30.core.internal.Trace;
import org.eclipse.core.runtime.IPath;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.IServer;

/**
 * @version $Rev$ $Date$
 */
abstract public class DeployCommand extends AbstractDeploymentCommand {

    /**
     * @param server
     * @param module
     */
    public DeployCommand(IServer server, IModule module) {
        super(server, module);
    }

    public File getTargetFile() {
        File file = null;
        IGeronimoServer gs = getGeronimoServer();
        if (gs.isRunFromWorkspace()) {
            //TODO Re-enable after DeployableModule supported in G
            //file = generateRunFromWorkspaceConfig(getModule());
        }
        else {
            IPath outputDir = DeploymentUtils.STATE_LOC.append("server_" + getServer().getId());
            outputDir.toFile().mkdirs();
            file = DeploymentUtils.createJarFile(getModule(), outputDir);
        }
        Trace.trace(Trace.INFO, "File: " + file.getAbsolutePath());
        return file;
    }

}
