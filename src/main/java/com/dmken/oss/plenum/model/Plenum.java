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

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;
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
public class Plenum implements Serializable {
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
     * Start time where the plenum has started.
     *
     */
    private LocalDateTime start;
    /**
     * End time where the plenum has started.
     *
     */
    private LocalDateTime end;
    // Foreign values.
    /**
     * All registered speakers.
     *
     */
    private List<Speaker> speakers;
    /**
     * All available protocols.
     *
     */
    private List<Protocol> protocols;
}