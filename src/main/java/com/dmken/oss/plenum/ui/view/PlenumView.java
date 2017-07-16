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

import org.springframework.beans.factory.annotation.Autowired;

import com.dmken.oss.plenum.model.Plenum;
import com.dmken.oss.plenum.ui.session.PlenumSession;
import com.dmken.oss.plenum.ui.view.design.PlenumDesign;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;

/**
 * View for the actual plenum.
 *
 */
@SpringView(name = PlenumView.NAME)
public class PlenumView extends PlenumDesign implements View {
    /**
     * The serial version UID.
     *
     */
    private static final long serialVersionUID = 6648641035005875979L;

    /**
     * View name.
     *
     */
    public static final String NAME = "plenum-information";

    /**
     * The {@link PlenumSession}.
     *
     */
    @Autowired
    private PlenumSession plenumSession;
    /**
     * The current plenum.
     *
     */
    private Plenum plenum;

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
        this.plenum = this.plenumSession.getPlenum();
    }

    /**
     * {@inheritDoc}
     *
     * @see com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
     */
    @Override
    public void enter(final ViewChangeEvent event) {
        if (this.plenum == null) {
            this.getUI().getNavigator().navigateTo(DefaultView.NAME);
            return;
        }
        if (!this.plenumSession.isAuthenticated()) {
            this.plenumSession.openLoginWindow(this.getUI());
            return;
        }

        this.refresh();
    }

    /**
     * Refreshes the data in the view.
     *
     */
    private void refresh() {
        this.labelInfoName.setCaption(this.plenum.getName());
    }
}
