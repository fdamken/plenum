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
package com.dmken.oss.plenum.model;

import java.time.LocalDateTime;

import com.dmken.oss.plenum.util.Priority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

/**
 * An entry on a {@link SpeakList speak list} holder the actual speaker and the
 * priority.
 *
 */
@Data
@Builder
@Wither
@NoArgsConstructor
@AllArgsConstructor
public class SpeakListEntry implements Model, Comparable<SpeakListEntry> {
    /**
     * The serial version UID.
     *
     */
    private static final long serialVersionUID = 8554675620014331703L;

    /**
     * ID.
     *
     */
    private Integer id;
    /**
     * Priority.
     *
     */
    private Priority priority;
    /**
     * Whether the speak entry was processed or not.
     *
     */
    private Boolean spoke;
    /**
     * When the speaker started to speak. Useful for speaking time limitations.
     *
     */
    private LocalDateTime from;
    /**
     * When the speaker stopped to speak.
     *
     */
    private LocalDateTime to;
    // Mapped values.
    /**
     * The actual person.
     *
     */
    private Speaker speaker;

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(final SpeakListEntry that) {
        return Integer.compare(this.priority.getPriority(), that.priority.getPriority());
    }
}
