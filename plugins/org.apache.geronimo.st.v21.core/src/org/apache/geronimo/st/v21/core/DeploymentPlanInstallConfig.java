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
package org.apache.geronimo.st.v21.core;

import org.apache.geronimo.st.v21.core.internal.Trace;
import org.eclipse.wst.common.project.facet.core.IActionConfigFactory;


/**
 * @version $Rev: 588513 $ $Date: 2007-10-26 00:14:23 -0400 (Fri, 26 Oct 2007) $
 */
public class DeploymentPlanInstallConfig {
	
	private String groupId;
	
	private String artifactId;
	
	private String version;
	
	private String type;
	
	private boolean sharedLib;
	
    public static final class Factory implements IActionConfigFactory
    {
        public Object create()
        {
        	Trace.trace("Entry/Exit", "DeploymentPlanInstallConfig.create");
            return new DeploymentPlanInstallConfig();
        }
    }

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public boolean isSharedLib() {
		return sharedLib;
	}

	public void setSharedLib(boolean sharedLib) {
		this.sharedLib = sharedLib;
	}
}