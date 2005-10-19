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
package org.apache.geronimo.ui.pages;

import org.apache.geronimo.ui.editors.OpenEjbPlanEditor;
import org.apache.geronimo.ui.sections.DependencySection;
import org.apache.geronimo.ui.sections.ImportSection;
import org.apache.geronimo.ui.sections.OpenEjbJarGeneralSection;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.openejb.xml.ns.openejb.jar.JarPackage;
import org.openejb.xml.ns.openejb.jar.OpenejbJarType;

public class EjbOverviewPage extends FormPage {

    /**
     * @param editor
     * @param id
     * @param title
     */
    public EjbOverviewPage(FormEditor editor, String id, String title) {
        super(editor, id, title);
    }

    /**
     * @param id
     * @param title
     */
    public EjbOverviewPage(String id, String title) {
        super(id, title);
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.forms.editor.FormPage#createFormContent(org.eclipse.ui.forms.IManagedForm)
     */
    protected void createFormContent(IManagedForm managedForm) {
        ScrolledForm form = managedForm.getForm();
        form.setText(getTitle());
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        layout.horizontalSpacing = 20;
        layout.makeColumnsEqualWidth = true;
        form.getBody().setLayout(layout);
        fillBody(managedForm);
        form.reflow(true);
    }

    protected void fillBody(IManagedForm managedForm) {

        OpenejbJarType plan = (OpenejbJarType) ((OpenEjbPlanEditor) getEditor())
                .getDeploymentPlan();

        Composite body = managedForm.getForm().getBody();

        int style = ExpandableComposite.TWISTIE | ExpandableComposite.EXPANDED
                | ExpandableComposite.TITLE_BAR | Section.DESCRIPTION
                | ExpandableComposite.FOCUS_TITLE;

        managedForm.addPart(new OpenEjbJarGeneralSection(body,
                managedForm.getToolkit(), style, plan));
        
        managedForm.addPart(new DependencySection(plan,
                JarPackage.eINSTANCE.getOpenejbJarType_Dependency(), body,
                managedForm.getToolkit(), style));

        managedForm.addPart(new ImportSection(plan, JarPackage.eINSTANCE
                .getOpenejbJarType_Import(), body, managedForm.getToolkit(),
                style));

    }

}
