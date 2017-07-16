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

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("javadoc")
public class PlenumDesign extends VerticalLayout {
    /**
     * The serial version UID.
     *
     */
    private static final long serialVersionUID = 5630090579378443274L;

    protected TabSheet tabs;
    protected GridLayout tabInformation;

    protected Label labelInfoName;

    /**
     * Constructor of PlenumDesign.
     *
     */
    public PlenumDesign() {
        this.tabs = new TabSheet();

        this.tabs.setSizeFull();
        this.tabs.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
        this.tabs.addStyleName(ValoTheme.TABSHEET_FRAMED);

        this.initInformationTab();

        this.tabs.addTab(this.tabInformation);
        this.addComponent(this.tabs);
    }

    private void initInformationTab() {
        this.tabInformation = new GridLayout();

        this.tabInformation.setCaption("Information");
        this.tabInformation.setSizeFull();

        this.labelInfoName = new Label();

        this.tabInformation.addComponent(this.labelInfoName);
    }
}
