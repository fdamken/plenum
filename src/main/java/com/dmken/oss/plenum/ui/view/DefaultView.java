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
package com.dmken.oss.plenum.ui.view;

import javax.annotation.PostConstruct;

import com.dmken.oss.plenum.ui.view.design.DefaultDesign;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;

/**
 * Default view explaining what the software is.
 *
 */
@SpringView(name = DefaultView.NAME)
public class DefaultView extends DefaultDesign implements View {
    /**
     * The serial version UID.
     *
     */
    private static final long serialVersionUID = 6605050449510526880L;

    /**
     * View name.
     *
     */
    public static final String NAME = "";

    /**
     * Invoked after bean was initialized.
     *
     * <p>
     * <b> NOTE: To be invoked by Spring! Do not invoke manually! </b>
     * </p>
     *
     */
    @PostConstruct
    public void onPostConstruct() {
        this.buttonCreatePlenum.addClickListener(event -> this.getUI().getNavigator().navigateTo(CreatePlenumView.NAME));
    }

    /**
     * {@inheritDoc}
     *
     * @see com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
     */
    @Override
    public void enter(final ViewChangeEvent event) {
        // Nothing to do.
    }
}
