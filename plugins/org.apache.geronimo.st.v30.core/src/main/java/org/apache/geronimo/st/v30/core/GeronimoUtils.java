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
package org.apache.geronimo.st.v30.core;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.jar.Manifest;

import javax.enterprise.deploy.shared.ModuleType;
import javax.xml.bind.JAXBElement;

import org.apache.geronimo.jee.application.Application;
import org.apache.geronimo.jee.connector.Connector;
import org.apache.geronimo.jee.deployment.Artifact;
import org.apache.geronimo.jee.deployment.Environment;
import org.apache.geronimo.jee.openejb.OpenejbJar;
import org.apache.geronimo.jee.web.WebApp;
import org.apache.geronimo.st.v30.core.internal.Trace;
import org.apache.geronimo.st.v30.core.jaxb.JAXBUtils;
import org.apache.geronimo.st.v30.core.osgi.AriesHelper;
import org.apache.geronimo.st.v30.core.osgi.OsgiConstants;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.deployables.J2EEFlexProjDeployable;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.server.core.IWebModule;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.internal.ModuleFactory;
import org.eclipse.wst.server.core.internal.ServerPlugin;
import org.osgi.framework.Version;

/**
 * @version $Rev$ $Date$
 */
public class GeronimoUtils {

    public static final String WEB_PLAN_NAME = "geronimo-web.xml";

    public static final String OPENEJB_PLAN_NAME = "openejb-jar.xml";

    public static final String APP_PLAN_NAME = "geronimo-application.xml";

    public static final String APP_CLIENT_PLAN_NAME = "geronimo-application-client.xml";

    public static final String CONNECTOR_PLAN_NAME = "geronimo-ra.xml";
    
    public static final String SERVICE_PLAN_NAME = "geronimo-service.xml"; 


    public static boolean isWebModule(IModule module) {
        return IModuleConstants.JST_WEB_MODULE.equals(module.getModuleType().getId());
    }

    public static boolean isEjbJarModule(IModule module) {
        return IModuleConstants.JST_EJB_MODULE.equals(module.getModuleType().getId());
    }

    public static boolean isEarModule(IModule module) {
        return IModuleConstants.JST_EAR_MODULE.equals(module.getModuleType().getId());
    }

    public static boolean isAppClientModule(IModule module) {
        return IModuleConstants.JST_APPCLIENT_MODULE.equals(module.getModuleType().getId());
    }

    public static boolean isRARModule(IModule module) {
        return IModuleConstants.JST_CONNECTOR_MODULE.equals(module.getModuleType().getId());
    }
    
    public static boolean isEBAModule(IModule module) {
        return OsgiConstants.APPLICATION.equals(module.getModuleType().getId());
    }
    
    public static boolean isCBAModule(IModule module) {
        return OsgiConstants.COMPOSITE_BUNDLE.equals(module.getModuleType().getId());
    }
    
    public static boolean isBundleModule(IModule module) {
        return OsgiConstants.BUNDLE.equals(module.getModuleType().getId());
    }
    
    public static boolean isFragmentBundleModule(IModule module) {
        return OsgiConstants.FRAGMENT_BUNDLE.equals(module.getModuleType().getId());
    }
    
    public static ModuleType getJSR88ModuleType(IModule module) {
        if (isWebModule(module)) {
            return ModuleType.WAR;
        } else if (isEjbJarModule(module)) {
            return ModuleType.EJB;
        } else if (isEarModule(module)) {
            return ModuleType.EAR;
        } else if (isRARModule(module)) {
            return ModuleType.RAR;
        } else {
            Trace.trace(Trace.ERROR, "getJSR88ModuleType = null", Activator.logCore);
            return null;
        }
    }
    
    public static JAXBElement getDeploymentPlan(IFile file) throws Exception {
        Trace.tracePoint("ENTRY", Activator.traceCore, "GeronimoUtils.getDeploymentPlan", file);

        if (!file.exists()) {
            return null;
        }

        if (file.getName().equals(GeronimoUtils.APP_PLAN_NAME))
            return getApplicationDeploymentPlan(file);
        else if (file.getName().equals(GeronimoUtils.OPENEJB_PLAN_NAME))
            return getOpenEjbDeploymentPlan(file);
        else if (file.getName().equals(GeronimoUtils.WEB_PLAN_NAME))
            return getWebDeploymentPlan(file);
        else if (file.getName().equals(GeronimoUtils.CONNECTOR_PLAN_NAME))
            return getConnectorDeploymentPlan(file);
        else if (file.getName().equals(GeronimoUtils.APP_CLIENT_PLAN_NAME))
            return getApplicationClientDeploymentPlan(file);

        Trace.tracePoint("EXIT", Activator.traceCore, "GeronimoUtils.getDeploymentPlan", null);
        return null;
    }
    
    public static IFile getDeploymentPlanFile(IModule module) {
        IProject project = module.getProject();
        IVirtualComponent comp = ComponentCore.createComponent(project);
        String type = J2EEProjectUtilities.getJ2EEProjectType(project);
        if (IModuleConstants.JST_WEB_MODULE.equals(type)) {
            return getWebDeploymentPlanFile(comp);
        } else if (IModuleConstants.JST_EJB_MODULE.equals(type)) {
            return getOpenEjbDeploymentPlanFile(comp);
        } else if (IModuleConstants.JST_EAR_MODULE.equals(type)) {
            return getApplicationDeploymentPlanFile(comp);
        } else if (IModuleConstants.JST_APPCLIENT_MODULE.equals(type)) {
            return getApplicationClientDeploymentPlanFile(comp);
        } else if (IModuleConstants.JST_CONNECTOR_MODULE.equals(type)) {
            return getConnectorDeploymentPlanFile(comp);
        }
        return null;
    }
    
    public static String getConfigId(IModule module) throws Exception {
        Trace.tracePoint("ENTRY", Activator.traceCore, "GeronimoUtils.getConfigId", module);

        Environment environment = null;
        if (isWebModule(module)) {
            if (getWebDeploymentPlan(module)!=null) {
                WebApp plan = getWebDeploymentPlan(module).getValue();
                if (plan != null)
                    environment = plan.getEnvironment();
            }
        }
        else if (isEjbJarModule(module)) {
               if (getOpenEjbDeploymentPlan(module)!=null) {
                    OpenejbJar plan = getOpenEjbDeploymentPlan(module).getValue();
                    if (plan != null)
                        environment = plan.getEnvironment();
               }
        }
        else if (isEarModule(module)) {
            if (getApplicationDeploymentPlan(module)!=null) {
                Application plan = getApplicationDeploymentPlan(module).getValue();
                if (plan != null)
                    environment = plan.getEnvironment();
            }
        }
        else if (isRARModule(module)) {
            if (getConnectorDeploymentPlan(module)!=null) {
                Connector plan = getConnectorDeploymentPlan(module).getValue();
                if (plan != null)
                    environment = plan.getEnvironment();
            }
        }

        if (environment != null
            && environment.getModuleId() != null) {
            Trace.tracePoint("EXIT", Activator.traceCore, "GeronimoUtils.getConfigId", getQualifiedConfigID(environment.getModuleId()));
            return getQualifiedConfigID(environment.getModuleId());
        }
        
        if (AriesHelper.isAriesInstalled()) {
            try {
                if (isEBAModule(module)) {
                    Class ariesUtilsClass = Class.forName("com.ibm.etools.aries.internal.core.utils.AriesUtils");
                    Method method = ariesUtilsClass.getMethod("getApplicationManifest", IProject.class);
                    Object object = method.invoke(null, module.getProject());
                    
                    Class appManifestClass = Class.forName("com.ibm.etools.aries.core.models.ApplicationManifest");
                    method = appManifestClass.getMethod("getApplicationSymbolicName");
                    String artifactID = (String) method.invoke(object); 
                    
                    method = appManifestClass.getMethod("getApplicationVersion"); 
                    String versionStr = (String) method.invoke(object);
                    Version version = Version.parseVersion(versionStr);
                    String newVersionStr = getVersion(version);                    
                    
                    if (artifactID != null && version != null) {
                        return getQualifiedConfigID(OsgiConstants.ARTIFACT_GROUP, artifactID, newVersionStr, OsgiConstants.ARTIFACT_TYPE);
                    }                 
                }
            } catch (Exception e) {
            } 
        }

        Trace.tracePoint("EXIT", Activator.traceCore, "GeronimoUtils.getConfigId", getId(module));
        return getId(module);
    }
    
    // copied from org.apache.geronimo.aries.builder.ApplicationInstaller.getVersion(Version)
    private static String getVersion(Version version) {
        String str = version.getMajor() + "." + version.getMinor() + "." + version.getMicro();
        String qualifier = version.getQualifier();
        if (qualifier != null && qualifier.trim().length() > 0) {
            str += "-" + version.getQualifier().trim();
        }
        return str;
    }

    public static String getQualifiedConfigID(String groupId, String artifactId, String version, String type) {
        return groupId + "/" + artifactId + "/" + version + "/" + type;
    }

    public static IFile getWebDeploymentPlanFile(IVirtualComponent comp) {
        IPath deployPlanPath = comp.getRootFolder().getUnderlyingFolder().getProjectRelativePath().append("WEB-INF").append(WEB_PLAN_NAME);
        return comp.getProject().getFile(deployPlanPath);
    }

    public static IFile getOpenEjbDeploymentPlanFile(IVirtualComponent comp) {
        IPath deployPlanPath = comp.getRootFolder().getUnderlyingFolder().getProjectRelativePath().append("META-INF").append(OPENEJB_PLAN_NAME);
        return comp.getProject().getFile(deployPlanPath);
    }

    public static IFile getApplicationDeploymentPlanFile(IVirtualComponent comp) {
        IPath deployPlanPath = comp.getRootFolder().getUnderlyingFolder().getProjectRelativePath().append("META-INF").append(APP_PLAN_NAME);
        return comp.getProject().getFile(deployPlanPath);
    }

    public static IFile getApplicationClientDeploymentPlanFile(IVirtualComponent comp) {
        IPath deployPlanPath = comp.getRootFolder().getUnderlyingFolder().getProjectRelativePath().append("META-INF").append(APP_CLIENT_PLAN_NAME);
        return comp.getProject().getFile(deployPlanPath);
    }

    public static JAXBElement getApplicationClientDeploymentPlan(IFile file) throws Exception {
        Trace.tracePoint("ENTRY", Activator.traceCore, "GeronimoUtils.getApplicationClientDeploymentPlan", file);

        if (file.getName().equals(APP_CLIENT_PLAN_NAME) && file.exists()) {
            return JAXBUtils.unmarshalFilterDeploymentPlan(file);
        }

        Trace.tracePoint("EXIT", Activator.traceCore, "GeronimoUtils.getApplicationClientDeploymentPlan", null);
        return null;
    }
    public static IFile getConnectorDeploymentPlanFile(IVirtualComponent comp) {
        IPath deployPlanPath = comp.getRootFolder().getUnderlyingFolder().getProjectRelativePath().append("META-INF").append(CONNECTOR_PLAN_NAME);
        return comp.getProject().getFile(deployPlanPath);
    }

    public static IVirtualComponent getVirtualComponent(IModule module) {
        return ComponentCore.createComponent(module.getProject());
    }

    public static Manifest getBundleManifest(IModule module) {
        IVirtualComponent component = getVirtualComponent(module);
        IPath manifestPath = component.getRootFolder().getUnderlyingFolder().getProjectRelativePath().append("META-INF").append("MANIFEST.MF");
        IFile manifestFile = component.getProject().getFile(manifestPath);
        Manifest manifest = null;
        InputStream in = null;
        try {
            in = manifestFile.getContents();
            manifest = new Manifest(in);
        } catch (Exception e) {
            Trace.trace(Trace.ERROR, "Could load manifest file", e, Activator.logCore);
        } finally {
            if (in != null) {
                try { in.close(); } catch (Exception ee) {}
            }
        }
        return manifest;
    }
    
    public static String getContextRoot(IModule module, boolean standalone) {
        String contextRoot = null; 
        if (standalone) {
            try {
                JAXBElement<WebApp> plan = getWebDeploymentPlan(module);
                if (plan != null) {
                    contextRoot = plan.getValue().getContextRoot();
                }
            } catch (Exception e) {
                Trace.trace(Trace.ERROR, "Could load geronimo-web.xml", e, Activator.logCore);
            }
        } else {
            // TODO: this does not seem to pick up changes if application.xml is updated with a new context-root
            J2EEFlexProjDeployable j2eeModule = (J2EEFlexProjDeployable) module.loadAdapter(J2EEFlexProjDeployable.class, null);
            contextRoot = ((IWebModule) j2eeModule).getContextRoot();
        }
        
        if (contextRoot == null) {
            contextRoot = getId(module);
        }
        
        return contextRoot;
    }
    
    public static String getId(IModule module) {
        // use the module ID
        String moduleId = module.getId();

        J2EEFlexProjDeployable j2eeModule = (J2EEFlexProjDeployable) module.loadAdapter(J2EEFlexProjDeployable.class, null);
        if (j2eeModule != null) {
            // j2eeFlex
            ArtifactEdit edit = null;
            try {
                edit = ArtifactEdit.getArtifactEditForRead(j2eeModule.getProject());
                //TODO JAXB Refactoring - Can below two lines be removed without harm?
                //XMIResource res = (XMIResource) edit.getContentModelRoot().eResource();
                //moduleId = res.getID(edit.getContentModelRoot());
            } finally {
                if (edit != null)
                    edit.dispose();
            }
        }

        if (moduleId != null && moduleId.length() > 0)
            return moduleId;

        // ...but if there is no defined module ID, pick the best alternative

        IPath moduleLocation = new Path(j2eeModule.getURI(module));
        if (moduleLocation != null) {
            moduleId = moduleLocation.removeFileExtension().lastSegment();
        }

        if (j2eeModule instanceof IWebModule) {
            // A better choice is to use the context root
            // For wars most appservers use the module name
            // as the context root
            String contextRoot = ((IWebModule) j2eeModule).getContextRoot();
            if (contextRoot.charAt(0) == '/')
                moduleId = contextRoot.substring(1);
        }

        return moduleId;
    }


    public static IFile getServiceDeploymentPlanFile(IVirtualComponent comp) {
        IPath deployPlanPath = comp.getRootFolder().getUnderlyingFolder().getProjectRelativePath().append("META-INF").append(SERVICE_PLAN_NAME);
        return comp.getProject().getFile(deployPlanPath);
    }
    public static JAXBElement<WebApp> getWebDeploymentPlan(IModule module) throws Exception {
        return getWebDeploymentPlan(getVirtualComponent(module));
    }
    public static JAXBElement getWebDeploymentPlan(IVirtualComponent comp) throws Exception {
        return getWebDeploymentPlan(getWebDeploymentPlanFile(comp));
    }
    public static JAXBElement getWebDeploymentPlan(IFile file) throws Exception {
        Trace.tracePoint("ENTRY", Activator.traceCore, "GeronimoUtils.getWebDeploymentPlan", file);

        if (file.getName().equals(WEB_PLAN_NAME) && file.exists()) {
            return JAXBUtils.unmarshalFilterDeploymentPlan(file);
        }

        Trace.tracePoint("EXIT", Activator.traceCore, "GeronimoUtils.getWebDeploymentPlan", null);
        return null;
    }


    public static JAXBElement<OpenejbJar> getOpenEjbDeploymentPlan(IModule module) throws Exception {
        return getOpenEjbDeploymentPlan(getVirtualComponent(module));
    }
    public static JAXBElement getOpenEjbDeploymentPlan(IVirtualComponent comp) throws Exception {
        return getOpenEjbDeploymentPlan(getOpenEjbDeploymentPlanFile(comp));
    }
    public static JAXBElement getOpenEjbDeploymentPlan(IFile file) throws Exception {
        Trace.tracePoint("ENTRY", Activator.traceCore, "GeronimoUtils.getOpenEjbDeploymentPlan", file);

        if (file.getName().equals(OPENEJB_PLAN_NAME) && file.exists()) {
            return JAXBUtils.unmarshalFilterDeploymentPlan(file);
        }

        Trace.tracePoint("EXIT", Activator.traceCore, "GeronimoUtils.getOpenEjbDeploymentPlan", null);
        return null;
    }



    public static JAXBElement<Application> getApplicationDeploymentPlan(IModule module) throws Exception {
        return getApplicationDeploymentPlan(getVirtualComponent(module));
    }
    public static JAXBElement getApplicationDeploymentPlan(IVirtualComponent comp) throws Exception {
        return getApplicationDeploymentPlan(getApplicationDeploymentPlanFile(comp));
    }
    public static JAXBElement getApplicationDeploymentPlan(IFile file) throws Exception {
        Trace.tracePoint("ENTRY", Activator.traceCore, "GeronimoUtils.getApplicationDeploymentPlan", file);

        if (file.getName().equals(APP_PLAN_NAME) && file.exists()) {
            return JAXBUtils.unmarshalFilterDeploymentPlan(file);
        }

        Trace.tracePoint("EXIT", Activator.traceCore, "GeronimoUtils.getApplicationDeploymentPlan", null);
        return null;
    }



    public static JAXBElement<Connector> getConnectorDeploymentPlan(IModule module) throws Exception {
        return getConnectorDeploymentPlan(getVirtualComponent(module));
    }
    public static String getQualifiedConfigID(Artifact artifact) {
        return getQualifiedConfigID(artifact.getGroupId(), artifact.getArtifactId(), artifact.getVersion(), artifact.getType());
    }
    public static JAXBElement getConnectorDeploymentPlan(IVirtualComponent comp) throws Exception {
        return getConnectorDeploymentPlan(getConnectorDeploymentPlanFile(comp));
    }
    public static JAXBElement getConnectorDeploymentPlan(IFile file) throws Exception {
        Trace.tracePoint("ENTRY", Activator.traceCore, "GeronimoUtils.getConnectorDeploymentPlan", file);

        if (file.getName().equals(CONNECTOR_PLAN_NAME) && file.exists()) {
            return JAXBUtils.unmarshalFilterDeploymentPlan(file);
        }

        Trace.tracePoint("EXIT", Activator.traceCore, "GeronimoUtils.getConnectorDeploymentPlan", null);
        return null;
    }
    
    public static IModule[] getModules(IProject project) {
        ModuleFactory[] factories = ServerPlugin.getModuleFactories();
        if (factories != null) {
            for (ModuleFactory factory : factories) {
                IModule[] modules = factory.getModules(project, new NullProgressMonitor());
                if (modules != null && modules.length != 0) {
                    return modules;
                }
            }
        }
        return new IModule [0];
    }
}
