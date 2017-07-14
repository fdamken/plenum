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

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Wither;

/**
 * A speaker in the plenum. Used for backtracking who was there from the start
 * and assigning numbers to people to call them up.
 *
 */
@Data
@Builder
@Wither
public class Speaker implements Serializable {
    /**
     * The serial version UID.
     *
     */
    private static final long serialVersionUID = 6327060179094416234L;

    /**
     * ID.
     *
     */
    private Integer id;
    /**
     * Number of the speaker used for calling somebody up.
     *
     */
    private Integer number;
    /**
     * Display name.
     *
     */
    private String displayName;
    /**
     * Since when the speaker was present.
     *
     */
    private LocalDateTime since;
    /**
     * Until when the speaker was present.
     *
     */
    private LocalDateTime until;
}
