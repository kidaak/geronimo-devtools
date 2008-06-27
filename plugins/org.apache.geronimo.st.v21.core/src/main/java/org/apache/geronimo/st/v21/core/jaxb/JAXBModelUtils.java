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
package org.apache.geronimo.st.v21.core.jaxb;

import java.util.List;

import javax.xml.bind.JAXBElement;

import org.apache.geronimo.jee.application.Application;
import org.apache.geronimo.jee.applicationclient.ApplicationClient;
import org.apache.geronimo.jee.connector.Connector;
import org.apache.geronimo.jee.deployment.Environment;
import org.apache.geronimo.jee.openejb.OpenejbJar;
import org.apache.geronimo.jee.security.Security;
import org.apache.geronimo.jee.web.WebApp;

/**
 * @version $Rev$ $Date$
 */
public class JAXBModelUtils {
	
    
    public static Security getSecurity (JAXBElement element) {
        Object plan = element.getValue();
        if (WebApp.class.isInstance (plan)) {
            if (((WebApp)plan).getSecurity() == null) {
                setSecurity (element, new org.apache.geronimo.jee.security.ObjectFactory().createSecurity());
            }
            Security security = (Security)((WebApp)plan).getSecurity().getValue();
            if (security.getRoleMappings() == null) {
                security.setRoleMappings (new org.apache.geronimo.jee.security.ObjectFactory().createRoleMappings());
            }
            return security;
        } else if (Application.class.isInstance (plan)) {
            if (((Application)plan).getSecurity() == null) {
                setSecurity (element, new org.apache.geronimo.jee.security.ObjectFactory().createSecurity());
            }
            Security security = (Security)((Application)plan).getSecurity().getValue();
            if (security.getRoleMappings() == null) {
                security.setRoleMappings (new org.apache.geronimo.jee.security.ObjectFactory().createRoleMappings());
            }
            return security;
        } else if (OpenejbJar.class.isInstance (plan)) {
            if (((OpenejbJar)plan).getSecurity() == null) {
                setSecurity (element, new org.apache.geronimo.jee.security.ObjectFactory().createSecurity());
            }
            Security security = (Security)((OpenejbJar)plan).getSecurity().getValue();
            if (security.getRoleMappings() == null) {
                security.setRoleMappings (new org.apache.geronimo.jee.security.ObjectFactory().createRoleMappings());
            }
            return security;
        }
        return null;
    }
    
    public static void setSecurity (JAXBElement element, Security security) {
        Object plan = element.getValue();
        if ( WebApp.class.isInstance( plan ) ) {
            ((WebApp)plan).setSecurity((new org.apache.geronimo.jee.security.ObjectFactory()).createSecurity( security ) );
        } else if (Application.class.isInstance(plan)) {
            ((Application)plan).setSecurity((new org.apache.geronimo.jee.security.ObjectFactory()).createSecurity( security ) );
        } else if (OpenejbJar.class.isInstance(plan)) {
            ((OpenejbJar)plan).setSecurity((new org.apache.geronimo.jee.security.ObjectFactory()).createSecurity( security ) );
        }
    }

    public static Environment getEnvironment(JAXBElement element) {
        return getEnvironment (element, true);
    }

    public static Environment getEnvironment(JAXBElement element, boolean serverEnvironment) {
        Object plan = element.getValue();
        if (serverEnvironment == true) {
            if (WebApp.class.isInstance (plan)) {
                return ((WebApp)plan).getEnvironment() == null ? null : ((WebApp)plan).getEnvironment();
            }
            else if (Application.class.isInstance (plan)) {
                return ((Application)plan).getEnvironment() == null ? null : ((Application)plan).getEnvironment();
            }
            else if (OpenejbJar.class.isInstance (plan)) {
                return ((OpenejbJar)plan).getEnvironment() == null ? null : ((OpenejbJar)plan).getEnvironment();
            }
            else if (Connector.class.isInstance (plan)) {
                return ((Connector)plan).getEnvironment() == null ? null : ((Connector)plan).getEnvironment();
            }
            else if (ApplicationClient.class.isInstance (plan)) {
                return ((ApplicationClient)plan).getServerEnvironment() == null ? null : ((ApplicationClient)plan).getServerEnvironment();
            }
        }
        else {
            if (ApplicationClient.class.isInstance (plan)) {
                return ((ApplicationClient)plan).getClientEnvironment() == null ? null : ((ApplicationClient)plan).getClientEnvironment();
            }
        }
        return null;
    }

    public static void setEnvironment (JAXBElement element, Environment environment) {
        setEnvironment (element, environment, true);
    }

    public static void setEnvironment (JAXBElement element, Environment environment, boolean serverEnvironment) {
        Object plan = element.getValue();
        if (serverEnvironment == true) {
            if (WebApp.class.isInstance (plan)) {
                ((WebApp)plan).setEnvironment (environment);
            }
            else if (Application.class.isInstance (plan)) {
                ((Application)plan).setEnvironment (environment);
            }
            else if (OpenejbJar.class.isInstance (plan)) {
                ((OpenejbJar)plan).setEnvironment (environment);
            }
            else if (Connector.class.isInstance (plan)) {
                ((Connector)plan).setEnvironment (environment);
            }
            else if (ApplicationClient.class.isInstance (plan)) {
                ((ApplicationClient)plan).setServerEnvironment (environment);
            }
        }
        else {
            if (ApplicationClient.class.isInstance (plan)) {
                ((ApplicationClient)plan).setClientEnvironment (environment);
            }
        }
    }

    public static List getGbeans (JAXBElement element) {
        Object plan = element.getValue();
        if (WebApp.class.isInstance (plan)) {
            return ((WebApp)plan).getServiceOrPersistence() == null ? null : ((WebApp)plan).getServiceOrPersistence();
        }
        else if (Application.class.isInstance (plan)) {
            return ((Application)plan).getService() == null ? null : ((Application)plan).getService();
        }
        else if (OpenejbJar.class.isInstance (plan)) {
            return ((OpenejbJar)plan).getService() == null ? null : ((OpenejbJar)plan).getService();
        }
        else if (Connector.class.isInstance (plan)) {
            return ((Connector)plan).getService() == null ? null : ((Connector)plan).getService();
        }
        else if (ApplicationClient.class.isInstance (plan)) {
            return ((ApplicationClient)plan).getService() == null ? null : ((ApplicationClient)plan).getService();
        }
        return null;
    }

    public static List getGbeanRefs (JAXBElement element) {
        Object plan = element.getValue();
        if (ApplicationClient.class.isInstance (plan)) {
            return ((ApplicationClient)plan).getGbeanRef() == null ? null : ((ApplicationClient)plan).getGbeanRef();
        }
        return null;
    }

    public static List getMessageDestinations (JAXBElement element) {
        Object plan = element.getValue();
        if (WebApp.class.isInstance (plan)) {
            return ((WebApp)plan).getMessageDestination() == null ? null : ((WebApp)plan).getMessageDestination();
        }
        else if (OpenejbJar.class.isInstance (plan)) {
            return ((OpenejbJar)plan).getMessageDestination() == null ? null : ((OpenejbJar)plan).getMessageDestination();
        }
        else if (ApplicationClient.class.isInstance (plan)) {
            return ((ApplicationClient)plan).getMessageDestination() == null ? null : ((ApplicationClient)plan).getMessageDestination();
        }
        return null;
    }
}
