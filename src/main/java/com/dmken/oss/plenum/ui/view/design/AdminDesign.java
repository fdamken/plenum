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

import com.dmken.oss.plenum.model.Plenum;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.TextRenderer;

@SuppressWarnings("javadoc")
public class AdminDesign extends VerticalLayout {
    /**
     * The serial version UID.
     *
     */
    private static final long serialVersionUID = -1942888792338455483L;

    protected Accordion accordion;

    protected VerticalLayout layoutPlenums;
    protected Grid<Plenum> gridPlenums;

    /**
     * Constructor of PlenumDesign.
     *
     */
    public AdminDesign() {
        this.accordion = new Accordion();

        this.initPlenumList();

        this.accordion.addTab(this.layoutPlenums, "Plenum List");

        this.addComponent(this.accordion);
    }

    private void initPlenumList() {
        this.layoutPlenums = new VerticalLayout();
        this.gridPlenums = new Grid<>();

        this.gridPlenums.setSizeFull();
        this.gridPlenums.addColumn(Plenum::getId);
        this.gridPlenums.addColumn(Plenum::getName).setCaption("Name");
        this.gridPlenums.addColumn(Plenum::getStart, new TextRenderer("Not Started")).setCaption("Start Time");

        this.layoutPlenums.addComponent(this.gridPlenums);
    }
}
