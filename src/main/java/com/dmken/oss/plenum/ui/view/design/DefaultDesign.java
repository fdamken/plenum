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

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("javadoc")
public class DefaultDesign extends VerticalLayout {
    /**
     * The serial version UID.
     *
     */
    private static final long serialVersionUID = 2753039593455049762L;

    protected Label labelWelcome;
    protected Label labelText;
    protected Button buttonCreatePlenum;

    /**
     * Constructor of DefaultViewDesign.
     *
     */
    public DefaultDesign() {
        this.labelWelcome = new Label("<h2>Welcome</h2>", ContentMode.HTML);
        this.labelText = new Label("Hello and welcome to the Plenum Manager. The software "
                + "for managing large plenums using speak lists, speaker numbering and a lot more.");
        this.buttonCreatePlenum = new Button("Create Plenum");

        this.buttonCreatePlenum.setStyleName("huge primary");

        this.addComponent(this.labelWelcome);
        this.addComponent(this.labelText);
        this.addComponent(this.buttonCreatePlenum);

    }
}
