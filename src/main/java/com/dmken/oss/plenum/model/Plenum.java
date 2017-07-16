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
import java.util.SortedSet;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

/**
 * Main class of the whole model. This class represents one plenum sessions
 * including registered {@link Speaker speakers}, {@link Protocol protocols},
 * etc..
 *
 */
@Data
@Builder
@Wither
@NoArgsConstructor
@AllArgsConstructor
public class Plenum implements Model {
    /**
     * The serial version UID.
     *
     */
    private static final long serialVersionUID = -7434405378535211860L;

    /**
     * ID.
     *
     */
    private Integer id;
    /**
     * Reference.
     *
     */
    @Getter(AccessLevel.PRIVATE)
    private final UUID reference = UUID.randomUUID();
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
    /**
     * Password.
     *
     */
    private String password;
    /**
     * Start time where the plenum has started.
     *
     */
    private LocalDateTime start;
    /**
     * End time where the plenum has started.
     *
     */
    private LocalDateTime end;
    // Mapped values.
    /**
     * All registered speakers.
     *
     */
    private SortedSet<Speaker> speakers;
    // Foreign values.
    /**
     * All available protocols.
     *
     */
    private SortedSet<Protocol> protocols;
    /**
     * All available speak lists.
     *
     */
    private SortedSet<SpeakList> speakLists;
}
