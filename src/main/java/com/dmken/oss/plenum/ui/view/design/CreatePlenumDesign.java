/*-
 * #%L
 * Plenum Manager
 * %%
 * Copyright (C) 2017 Fabian Damken
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.dmken.oss.plenum.ui.view.design;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("javadoc")
public class CreatePlenumDesign extends VerticalLayout {
    /**
     * The serial version UID.
     *
     */
    private static final long serialVersionUID = 7769600995813480108L;

    protected Panel panel;
    protected FormLayout form;

    protected TextField inputName;
    protected TextField inputDescription;
    protected PasswordField inputPassword;

    protected Button buttonSave;

    /**
     * Constructor of CreatePlenumDesign.
     *
     */
    public CreatePlenumDesign() {
        this.panel = new Panel();
        this.form = new FormLayout();

        this.panel.setCaption("Create Plenum");
        this.panel.setSizeFull();
        this.form.setSizeFull();
        this.form.setMargin(true);

        this.inputName = new TextField();
        this.inputDescription = new TextField();
        this.inputPassword = new PasswordField();
        this.buttonSave = new Button();

        this.inputName.setCaption("Name");
        this.inputName.setRequiredIndicatorVisible(true);
        this.inputDescription.setCaption("Description");
        this.inputPassword.setCaption("Password");
        this.buttonSave.setCaption("Save");
        this.buttonSave.addStyleName(ValoTheme.BUTTON_PRIMARY);

        this.form.addComponent(this.inputName);
        this.form.addComponent(this.inputDescription);
        this.form.addComponent(this.inputPassword);
        this.form.addComponent(this.buttonSave);
        this.panel.setContent(this.form);
        this.addComponent(this.panel);
    }
}
