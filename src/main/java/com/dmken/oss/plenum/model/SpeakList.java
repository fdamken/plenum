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

import java.util.PriorityQueue;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Wither;

/**
 * A speak list for the upcoming speakers supporting prioritization.
 *
 */
@Data
@Builder
@Wither
public class SpeakList implements Model, Comparable<SpeakList> {
    /**
     * The serial version UID.
     *
     */
    private static final long serialVersionUID = 9166133572125979453L;

    /**
     * ID.
     *
     */
    private Integer id;
    /**
     * Name.
     *
     */
    private String name;
    /**
     * Description.
     *
     */
    private String description;
    // Mapped values.
    /**
     * Queue of next speakers.
     *
     */
    private PriorityQueue<SpeakListEntry> entries;
    /**
     * The currently speaking speaker.
     *
     */
    private SpeakListEntry speaking;

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(final SpeakList that) {
        return this.name.compareTo(that.name);
    }

    public SpeakListEntry next() {
        final SpeakListEntry speakListEntry = this.entries.poll();
        this.setSpeaking(speakListEntry);
        return speakListEntry;
    }
}
