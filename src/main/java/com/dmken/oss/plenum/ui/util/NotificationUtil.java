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
package com.dmken.oss.plenum.ui.util;

import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class NotificationUtil {
    public static NotificationBuilder chain() {
        return new NotificationBuilder();
    }

    @Setter
    @Accessors(chain = true,
               fluent = true)
    public static class NotificationBuilder {
        private String caption = null;
        private String description = null;
        private Type type = Type.HUMANIZED_MESSAGE;
        private Resource icon = null;
        private Position position = Position.TOP_CENTER;
        private Integer delayMsec = null;
        private String styleName = null;
        private Boolean htmlContentAllowed = null;

        private NotificationBuilder() {
            // Nothing to do.
        }

        public Notification build() {
            final Notification notification = new Notification(this.caption, this.type);
            if (this.description != null) {
                notification.setDescription(this.description);
            }
            if (this.icon != null) {
                notification.setIcon(this.icon);
            }
            if (this.position != null) {
                notification.setPosition(this.position);
            }
            if (this.delayMsec != null) {
                notification.setDelayMsec(this.delayMsec);
            }
            if (this.styleName != null) {
                notification.setStyleName(this.styleName);
            }
            if (this.htmlContentAllowed != null) {
                notification.setHtmlContentAllowed(this.htmlContentAllowed);
            }
            return notification;
        }

        public void show() {
            this.build().show(Page.getCurrent());
        }
    }
}
