/**
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
package org.apache.geronimo.st.v21.ui.sections;

import org.apache.geronimo.st.v21.core.IGeronimoServer;
import org.apache.geronimo.st.ui.commands.SetServerInstancePropertyCommand;
import org.apache.geronimo.st.ui.internal.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

/**
 * @version $Rev: 732518 $ $Date: 2009-01-08 05:50:54 +0800 (Thu, 08 Jan 2009) $
 */
public class ServerEditorStartupSection extends AbstractServerEditorSection {
	
	Text pingDelay;

	Text pingInterval;
	
	Text maxPings;
	
	public ServerEditorStartupSection() {
		super();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.server.ui.editor.ServerEditorSection#createSection(org.eclipse.swt.widgets.Composite)
	 */
	public void createSection(Composite parent) {
		super.createSection(parent);

		FormToolkit toolkit = getFormToolkit(parent.getDisplay());

		Section section = toolkit.createSection(parent,
				ExpandableComposite.TWISTIE | ExpandableComposite.EXPANDED
						| ExpandableComposite.TITLE_BAR | Section.DESCRIPTION
						| ExpandableComposite.FOCUS_TITLE);

		section.setText(Messages.editorSectionStartupTitle);
		section.setDescription(Messages.editorSectionStartupDescription);
		section.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

		Composite composite = toolkit.createComposite(section);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginHeight = 5;
		layout.marginWidth = 10;
		layout.verticalSpacing = 5;
		layout.horizontalSpacing = 15;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		section.setClient(composite);

		// ------- Label and text field for the ping delay -------
		createLabel(composite, Messages.pingDelay, toolkit);
		pingDelay = toolkit.createText(composite, getPingDelay(), SWT.BORDER);
		pingDelay.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		pingDelay.setToolTipText(Messages.pingDelayTooltip);
		pingDelay.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Integer value = Integer.valueOf(pingDelay.getText());
				execute(new SetServerInstancePropertyCommand(server, value, "setPingDelay", Integer.class, IGeronimoServer.class));
			}
		});

		// ------- Label and text field for the ping interval -------
		createLabel(composite, Messages.pingInterval, toolkit);
		pingInterval = toolkit.createText(composite, getPingInterval(), SWT.BORDER);
		pingInterval.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		pingInterval.setToolTipText(Messages.pingIntervalTooltip);
		pingInterval.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Integer value = Integer.valueOf(pingInterval.getText());
				execute(new SetServerInstancePropertyCommand(server, value, "setPingInterval", Integer.class, IGeronimoServer.class));
			}
		});
		
		// ------- Label and text field for the max pings -------
		createLabel(composite, Messages.maxPings, toolkit);
		maxPings = toolkit.createText(composite, getMaxPings(), SWT.BORDER);
		maxPings.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		maxPings.setToolTipText(Messages.maxPingsTooltip);
		maxPings.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Integer value = Integer.valueOf(maxPings.getText());
				execute(new SetServerInstancePropertyCommand(server, value, "setMaxPings", Integer.class, IGeronimoServer.class));
			}
		});
	}

	public String getMaxPings() {
		if (gs != null) {
			return Integer.toString(gs.getMaxPings());
		}
		return "";
	}

	public String getPingDelay() {
		if (gs != null) {
			return Integer.toString(gs.getPingDelay());
		}
		return "";
	}

	public String getPingInterval() {
		if (gs != null) {
			return Integer.toString(gs.getPingInterval());
		}
		return "";
	}
}