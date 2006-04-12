/**
 * Copyright 2004, 2005 The Apache Software Foundation or its licensors, as applicable
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.geronimo.st.v1.ui.editors;

import org.apache.geronimo.st.ui.editors.AbstractGeronimoDeploymentPlanEditor;
import org.apache.geronimo.st.v1.core.GeronimoV1Utils;
import org.apache.geronimo.st.v1.ui.internal.Messages;
import org.apache.geronimo.st.v1.ui.pages.ConnectorOverviewPage;
import org.apache.geronimo.st.v1.ui.pages.DeploymentPage;
import org.apache.geronimo.xml.ns.j2ee.connector.ConnectorFactory;
import org.apache.geronimo.xml.ns.j2ee.connector.ConnectorPackage;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormPage;

public class ConnectorPlanEditor extends AbstractGeronimoDeploymentPlanEditor {

	public ConnectorPlanEditor() {
		super();
	}

	public void doAddPages() throws PartInitException {
		if (getDeploymentPlan() != null) {
			addPage(new ConnectorOverviewPage(this, "connectoroverview", Messages.editorTabGeneral));
			addPage(getDeploymentPage());
		}
		addSourcePage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.geronimo.ui.editors.AbstractGeronimoDeploymentPlanEditor#loadDeploymentPlan(org.eclipse.core.resources.IFile)
	 */
	public EObject loadDeploymentPlan(IFile file) {
		return GeronimoV1Utils.getConnectorDeploymentPlan(file);
	}

	private FormPage getDeploymentPage() {
		DeploymentPage formPage = new DeploymentPage(this, "deploymentpage", Messages.editorTabDeployment);
		ConnectorPackage pkg = ConnectorFactory.eINSTANCE.getConnectorPackage();
		formPage.dependencies = pkg.getConnectorType_Dependency();
		formPage.imports = pkg.getConnectorType_Import();
		formPage.gbeans = pkg.getConnectorType_Gbean();
		return formPage;
	}

}
