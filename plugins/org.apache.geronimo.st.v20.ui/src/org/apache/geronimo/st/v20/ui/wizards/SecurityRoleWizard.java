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
package org.apache.geronimo.st.v20.ui.wizards;

import javax.xml.bind.JAXBElement;

import org.apache.geronimo.st.core.jaxb.JAXBObjectFactory;
import org.apache.geronimo.st.ui.CommonMessages;
import org.apache.geronimo.st.ui.sections.AbstractTableSection;
import org.apache.geronimo.st.ui.wizards.AbstractTableWizard;
import org.apache.geronimo.st.v20.core.jaxb.JAXBModelUtils;
import org.apache.geronimo.st.v20.core.jaxb.JAXBObjectFactoryImpl;
import org.apache.geronimo.st.v20.ui.sections.SecuritySection;
import org.apache.geronimo.xml.ns.security_2.DescriptionType;
import org.apache.geronimo.xml.ns.security_2.RoleMappingsType;
import org.apache.geronimo.xml.ns.security_2.RoleType;
import org.apache.geronimo.xml.ns.security_2.SecurityType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class SecurityRoleWizard extends AbstractTableWizard {

	public SecurityRoleWizard(AbstractTableSection section) {
		super(section);
	}

	public JAXBObjectFactory getEFactory() {
		return JAXBObjectFactoryImpl.getInstance();
	}

	public String[] getTableColumnEAttributes() {
		return new String[] { "RoleName" };
	}

	public String getAddWizardWindowTitle() {
		return CommonMessages.wizardNewTitle_SecurityRole;
	}

	public String getEditWizardWindowTitle() {
		return CommonMessages.wizardEditTitle_SecurityRole;
	}

	public String getWizardFirstPageTitle() {
		return CommonMessages.wizardPageTitle_SecurityRole;
	}

	public String getWizardFirstPageDescription() {
		return CommonMessages.wizardPageDescription_SecurityRole;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#addPages()
	 */
	public void addPages() {
		SecurityRoleWizardPage page = new SecurityRoleWizardPage("Page0");
		page.setImageDescriptor(descriptor);
		addPage(page);
	}

	public class SecurityRoleWizardPage extends DynamicWizardPage {

		Text descriptionText;

		public SecurityRoleWizardPage(String pageName) {
			super(pageName);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.apache.geronimo.ui.wizards.DynamicAddEditWizard.DynamicWizardPage#doCustom()
		 */
		public void doCustom(Composite parent) {
			Label label = new Label(parent, SWT.LEFT);
			String columnName = CommonMessages.description;
			if (!columnName.endsWith(":"))
				columnName = columnName.concat(":");
			label.setText(columnName);
			GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			label.setLayoutData(data);

			descriptionText = new Text(parent, SWT.SINGLE | SWT.BORDER);
			data = new GridData(GridData.HORIZONTAL_ALIGN_FILL
					| GridData.VERTICAL_ALIGN_FILL);
			data.grabExcessHorizontalSpace = true;
			data.widthHint = 100;
			descriptionText.setLayoutData(data);

			if (eObject != null && eObject instanceof RoleType) {
				RoleType roleType = (RoleType) eObject;
				if (!roleType.getDescription().isEmpty()) {
					DescriptionType desc = (DescriptionType) roleType.getDescription().get(0);
					if (desc.getValue() != null) {
						descriptionText.setText(desc.getValue());
					}
				}
			}
		}
	}

	public boolean performFinish() {
		SecurityRoleWizardPage page = (SecurityRoleWizardPage) getPages()[0];

		if (eObject == null) {
			eObject = getEFactory().create(RoleType.class);
			JAXBElement plan = section.getPlan();

			SecurityType securityType = JAXBModelUtils.getSecurityType(plan);
			if (securityType == null) {
				securityType = (SecurityType)getEFactory().create(SecurityType.class);
				JAXBModelUtils.setSecurityType(plan, securityType);
			}

			RoleMappingsType roleMappingsType = securityType.getRoleMappings();
			if (roleMappingsType == null) {
				roleMappingsType = (RoleMappingsType)getEFactory().create(RoleMappingsType.class);
				securityType.setRoleMappings(roleMappingsType);
			}

			roleMappingsType.getRole().add((RoleType)eObject);
		}

		processEAttributes(page);

		DescriptionType type = null;
		RoleType roleType = ((RoleType) eObject);
		if (roleType.getDescription().isEmpty()) {
			type = (DescriptionType)getEFactory().create(DescriptionType.class);
			roleType.getDescription().add(type);
		} else {
			type = (DescriptionType) roleType.getDescription().get(0);
		}
		type.setValue(page.descriptionText.getText());

		if (section.getTableViewer().getInput() == section.getPlan()) {
			section.getTableViewer().setInput(section.getInput());
		}

		return true;
	}
}
