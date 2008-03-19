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
package org.apache.geronimo.st.v20.ui.editors;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.apache.geronimo.st.ui.CommonMessages;
import org.apache.geronimo.st.ui.editors.AbstractGeronimoFormContentLoader;
import org.apache.geronimo.st.v20.core.GeronimoV20Utils;
import org.apache.geronimo.st.v20.ui.Activator;
import org.apache.geronimo.st.v20.ui.pages.AppGeneralPage;
import org.apache.geronimo.st.v20.ui.pages.ConnectorOverviewPage;
import org.apache.geronimo.st.v20.ui.pages.DeploymentPage;
import org.apache.geronimo.st.v20.ui.pages.EjbOverviewPage;
import org.apache.geronimo.st.v20.ui.pages.NamingFormPage;
import org.apache.geronimo.st.v20.ui.pages.SecurityPage;
import org.apache.geronimo.st.v20.ui.pages.WebGeneralPage;
import org.eclipse.core.resources.IFile;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;

public class GeronimoFormContentLoader extends AbstractGeronimoFormContentLoader {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.geronimo.st.ui.editors.AbstractGeronimoFormContentLoader#addApplicationPlanPages(org.eclipse.ui.forms.editor.FormEditor)
	 */
	public void addApplicationPlanPages(FormEditor editor) throws PartInitException {
		editor.addPage(new AppGeneralPage(editor, "appgeneralpage", CommonMessages.editorTabGeneral));
		editor.addPage(new SecurityPage(editor, "securitypage", CommonMessages.editorTabSecurity, null));
		editor.addPage(getApplicationDeploymentPage(editor));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.geronimo.st.ui.editors.AbstractGeronimoFormContentLoader#addConnectorPlanPages(org.eclipse.ui.forms.editor.FormEditor)
	 */
	public void addConnectorPlanPages(FormEditor editor) throws PartInitException {
		editor.addPage(new ConnectorOverviewPage(editor, "connectoroverview", CommonMessages.editorTabGeneral));
		editor.addPage(getConnectorDeploymentPage(editor));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.geronimo.st.ui.editors.AbstractGeronimoFormContentLoader#addOpenEjbPlanPages()
	 */
	public void addOpenEjbPlanPages(FormEditor editor) throws PartInitException {
		editor.addPage(new EjbOverviewPage(editor, "ejboverview", CommonMessages.editorTabGeneral));
		// TODO Add naming page but broken down for each bean type
		editor.addPage(new SecurityPage(editor, "securitypage", CommonMessages.editorTabSecurity, null));
		editor.addPage(getEjbJarDeploymentPage(editor));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.geronimo.st.ui.editors.AbstractGeronimoFormContentLoader#addWebPlanPages()
	 */
	public void addWebPlanPages(FormEditor editor) throws PartInitException {
		editor.addPage(new WebGeneralPage(editor, "generalpage", CommonMessages.editorTabGeneral));
		editor.addPage(getWebNamingPage(editor));
		editor.addPage(new SecurityPage(editor, "securitypage", CommonMessages.editorTabSecurity, null));
		editor.addPage(getWebDeploymentPage(editor));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.geronimo.st.ui.editors.IGeronimoFormContentLoader#loadDeploymentPlan(org.eclipse.core.resources.IFile)
	 */
	public JAXBElement loadDeploymentPlan(IFile file) {
		return GeronimoV20Utils.getDeploymentPlan(file);
	}
	
	public void saveDeploymentPlan(JAXBElement deploymentPlan, IFile file) throws IOException, JAXBException {
		JAXBContext jb = JAXBContext.newInstance( "org.apache.geronimo.xml.ns.j2ee.web_2_0:org.apache.geronimo.xml.ns.j2ee.application_2:org.apache.geronimo.xml.ns.deployment_1:org.apache.geronimo.xml.ns.naming_1:org.apache.geronimo.xml.ns.security_2", Activator.class.getClassLoader() );
//		jb.createMarshaller().marshal( deploymentPlan, new File( file.getLocationURI().toURL().getFile()) );
		jb.createMarshaller().marshal( deploymentPlan, new FileOutputStream( new File( file.getLocationURI().toURL().getFile()) ) );
	}
	
	protected FormPage getWebNamingPage(FormEditor editor) {
		NamingFormPage formPage = createNamingFormPage(editor);
//		WebPackage pkg = WebFactory.eINSTANCE.getWebPackage();
//		formPage.ejbLocalRef = pkg.getWebAppType_EjbLocalRef();
//		formPage.ejbRef = pkg.getWebAppType_EjbRef();
//		formPage.resEnvRef = pkg.getWebAppType_ResourceEnvRef();
//		formPage.resRef = pkg.getWebAppType_ResourceRef();
//		formPage.gbeanRef = pkg.getWebAppType_GbeanRef();
//		formPage.serviceRef = pkg.getWebAppType_ServiceRef();
		return formPage;
	}

	protected FormPage getWebDeploymentPage(FormEditor editor) {
		DeploymentPage formPage = createDeploymentFormPage(editor);
//		formPage.environment = WebFactory.eINSTANCE.getWebPackage().getWebAppType_Environment();
//		formPage.gbeanERef = WebFactory.eINSTANCE.getWebPackage().getWebAppType_Gbean();
		return formPage;
	}

	private FormPage getEjbJarDeploymentPage(FormEditor editor) {
		DeploymentPage formPage = createDeploymentFormPage(editor);
//		formPage.environment = JarFactory.eINSTANCE.getJarPackage().getOpenejbJarType_Environment();
//		formPage.gbeanERef = JarFactory.eINSTANCE.getJarPackage().getOpenejbJarType_Gbean();
		return formPage;
	}

	protected FormPage getApplicationDeploymentPage(FormEditor editor) {
		DeploymentPage formPage = createDeploymentFormPage(editor);
//		formPage.environment = ApplicationFactory.eINSTANCE.getApplicationPackage().getApplicationType_Environment();
//		formPage.gbeanERef = ApplicationFactory.eINSTANCE.getApplicationPackage().getApplicationType_Gbean();
		return formPage;
	}

	private FormPage getConnectorDeploymentPage(FormEditor editor) {
		DeploymentPage formPage = createDeploymentFormPage(editor);
//		formPage.environment = ConnectorFactory.eINSTANCE.getConnectorPackage().getConnectorType_Environment();
//		formPage.gbeanERef = ConnectorFactory.eINSTANCE.getConnectorPackage().getConnectorType_Gbean();
		return formPage;
	}

	private NamingFormPage createNamingFormPage(FormEditor editor) {
		return new NamingFormPage(editor, "namingpage", CommonMessages.editorTabNaming);
	}

	private DeploymentPage createDeploymentFormPage(FormEditor editor) {
		return new DeploymentPage(editor, "deploymentpage", CommonMessages.editorTabDeployment);
	}
}