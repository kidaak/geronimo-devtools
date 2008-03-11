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
package org.apache.geronimo.st.v20.ui.sections;

import java.util.List;

import javax.xml.bind.JAXBElement;

import org.apache.geronimo.st.ui.CommonMessages;
import org.apache.geronimo.st.ui.providers.AdapterFactory;
import org.apache.geronimo.st.ui.sections.AbstractTableSection;
import org.apache.geronimo.st.v20.ui.internal.EMFEditorContext;
import org.apache.geronimo.st.v20.ui.wizards.ServiceRefWizard;
import org.apache.geronimo.xml.ns.naming_1.ServiceRefType;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class ServiceRefSection extends AbstractTableSection {

	List serviceRefERef;

	private static final String[] COLUMN_NAMES = new String[] { CommonMessages.editorServiceRefName };

	public ServiceRefSection(JAXBElement plan, Composite parent, FormToolkit toolkit, int style, List serviceRefERef) {
		super(plan, parent, toolkit, style);
		this.serviceRefERef = serviceRefERef;
		createClient();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.geronimo.ui.sections.AbstractTableSection#getTitle()
	 */
	public String getTitle() {
		return CommonMessages.editorServiceRefTitle;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.geronimo.ui.sections.AbstractTableSection#getDescription()
	 */
	public String getDescription() {
		return CommonMessages.editorServiceRefDescription;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.geronimo.ui.sections.AbstractTableSection#getTableColumnNames()
	 */
	public String[] getTableColumnNames() {
		return COLUMN_NAMES;
	}

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see org.apache.geronimo.ui.sections.AbstractTableSection#getEReference()
//	 */
//	public EReference getEReference() {
//		return serviceRefERef;
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.geronimo.ui.sections.AbstractTableSection#getWizard()
	 */
	public Wizard getWizard() {
		return new ServiceRefWizard(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.geronimo.ui.sections.AbstractTableSection#getTableEntryObjectType()
	 */
	public Class getTableEntryObjectType() {
		return ServiceRefType.class;
	}

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see org.apache.geronimo.st.ui.sections.AbstractTableSection#getAdapterFactory()
//	 */
//	public AdapterFactory getAdapterFactory() {
//		return EMFEditorContext.getFactory();
//	}
}
