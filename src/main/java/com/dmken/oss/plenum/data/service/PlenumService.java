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
package com.dmken.oss.plenum.data.service;

import java.time.LocalDateTime;
import java.util.SortedSet;
import java.util.UUID;

import com.dmken.oss.plenum.data.service.exception.NoSuchPlenumException;
import com.dmken.oss.plenum.data.service.exception.NoSuchProtocolException;
import com.dmken.oss.plenum.data.service.exception.NoSuchSpeakListException;
import com.dmken.oss.plenum.data.service.exception.SpeakerAlreadyExistsException;
import com.dmken.oss.plenum.model.Plenum;
import com.dmken.oss.plenum.model.Speaker;

/**
 * Service for the {@link Plenum plenum}.
 *
 */
public interface PlenumService {
    SortedSet<Plenum> retrievePlenums();

    Plenum retrievePlenum(final int plenumId) throws NoSuchPlenumException;

    Plenum retrievePlenum(final UUID reference) throws NoSuchPlenumException;

    Plenum createPlenum(final String name, final String description, final String password);

    Plenum setName(final int plenumId, final String name) throws NoSuchPlenumException;

    Plenum setDescription(final int plenumId, final String description) throws NoSuchPlenumException;

    Plenum setPassword(final int plenumId, final String password) throws NoSuchPlenumException;

    Plenum setStart(final int plenumId, final LocalDateTime start) throws NoSuchPlenumException;

    Plenum setEnd(final int plenumId, final LocalDateTime end) throws NoSuchPlenumException;

    Plenum startPlenum(final int plenumId) throws NoSuchPlenumException;

    Plenum stopPlenum(final int plenumId) throws NoSuchPlenumException;

    Plenum addSpeaker(final int plenumId, final Speaker speaker) throws NoSuchPlenumException, SpeakerAlreadyExistsException;

    Plenum removeSpeaker(final int plenumId, final int speakerId) throws NoSuchPlenumException;

    Plenum addProtocol(final int plenumId, final int protocolId) throws NoSuchPlenumException, NoSuchProtocolException;

    Plenum removeProtocol(final int plenumId, final int protocolId) throws NoSuchPlenumException;

    Plenum addSpeakList(final int plenumId, final int speakListId) throws NoSuchPlenumException, NoSuchSpeakListException;

    Plenum removeSpeakList(final int plenumId, final int speakListId) throws NoSuchPlenumException;
}
