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
package com.dmken.oss.plenum.data.service.impl;

import java.time.LocalDateTime;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmken.oss.plenum.data.mapper.PlenumMapper;
import com.dmken.oss.plenum.data.service.PlenumService;
import com.dmken.oss.plenum.data.service.ProtocolService;
import com.dmken.oss.plenum.data.service.SpeakListService;
import com.dmken.oss.plenum.data.service.exception.NoSuchPlenumException;
import com.dmken.oss.plenum.data.service.exception.NoSuchProtocolException;
import com.dmken.oss.plenum.data.service.exception.NoSuchSpeakListException;
import com.dmken.oss.plenum.data.service.exception.SpeakerAlreadyExistsException;
import com.dmken.oss.plenum.model.Model;
import com.dmken.oss.plenum.model.Plenum;
import com.dmken.oss.plenum.model.Protocol;
import com.dmken.oss.plenum.model.SpeakList;
import com.dmken.oss.plenum.model.Speaker;

/**
 * Implementation of {@link PlenumService}.
 *
 */
@Service
public class PlenumServiceImpl implements PlenumService {
    /**
     * The {@link ProtocolService}.
     *
     */
    @Autowired
    private ProtocolService protocolService;
    /**
     * The {@link SpeakListService}.
     *
     */
    @Autowired
    private SpeakListService speakListService;
    /**
     * The {@link PlenumMapper}.
     *
     */
    @Autowired
    private PlenumMapper plenumMapper;

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.PlenumService#retrievePlenums()
     */
    @Override
    public SortedSet<Plenum> retrievePlenums() {
        final SortedSet<Plenum> set = new TreeSet<>(Model.ID_COMPARATOR);
        set.addAll(this.plenumMapper.findAll());
        return set;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.PlenumService#retrievePlenum(int)
     */
    @Override
    public Plenum retrievePlenum(final int plenumId) throws NoSuchPlenumException {
        final Plenum plenum = this.plenumMapper.findById(plenumId);
        if (plenum == null) {
            throw new NoSuchPlenumException("Unable to find plenum with id=" + plenumId + "!");
        }
        plenum.setProtocols(this.protocolService.retrieveProtocolsOfPlenum(plenumId));
        plenum.setSpeakLists(this.speakListService.retrieveSpeakListsOfPlenum(plenumId));
        return plenum;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.PlenumService#createPlenum(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public Plenum createPlenum(final String name, final String description) {
        final Plenum plenum = Plenum.builder() //
                .name(name) //
                .description(description) //
                .build();
        this.plenumMapper.createPlenum(plenum);
        return plenum;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.PlenumService#setName(int,
     *      java.lang.String)
     */
    @Override
    public Plenum setName(final int plenumId, final String name) throws NoSuchPlenumException {
        final Plenum plenum = this.retrievePlenum(plenumId);
        this.plenumMapper.setName(plenumId, name);
        plenum.setName(name);
        return plenum;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.PlenumService#setDescription(int,
     *      java.lang.String)
     */
    @Override
    public Plenum setDescription(final int plenumId, final String description) throws NoSuchPlenumException {
        final Plenum plenum = this.retrievePlenum(plenumId);
        this.plenumMapper.setDescription(plenumId, description);
        plenum.setDescription(description);
        return plenum;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.PlenumService#setStart(int,
     *      java.time.LocalDateTime)
     */
    @Override
    public Plenum setStart(final int plenumId, final LocalDateTime start) throws NoSuchPlenumException {
        final Plenum plenum = this.retrievePlenum(plenumId);
        this.plenumMapper.setStart(plenumId, start);
        plenum.setStart(start);
        return plenum;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.PlenumService#setEnd(int,
     *      java.time.LocalDateTime)
     */
    @Override
    public Plenum setEnd(final int plenumId, final LocalDateTime end) throws NoSuchPlenumException {
        final Plenum plenum = this.retrievePlenum(plenumId);
        this.plenumMapper.setEnd(plenumId, end);
        plenum.setEnd(end);
        return plenum;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.PlenumService#startPlenum(int)
     */
    @Override
    public Plenum startPlenum(final int plenumId) throws NoSuchPlenumException {
        return this.setStart(plenumId, LocalDateTime.now());
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.PlenumService#stopPlenum(int)
     */
    @Override
    public Plenum stopPlenum(final int plenumId) throws NoSuchPlenumException {
        return this.setEnd(plenumId, LocalDateTime.now());
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.PlenumService#addSpeaker(int,
     *      com.dmken.oss.plenum.model.Speaker)
     */
    @Override
    public Plenum addSpeaker(final int plenumId, final Speaker speaker)
            throws NoSuchPlenumException, SpeakerAlreadyExistsException {
        if (this.plenumMapper.hasSpeaker(plenumId, speaker.getNumber(), speaker.getDisplayName())) {
            throw new SpeakerAlreadyExistsException("Speaker with number=" + speaker.getNumber() + " or name="
                    + speaker.getDisplayName() + " alreay exists in plenum with id=" + plenumId + "!");
        }

        final Plenum plenum = this.retrievePlenum(plenumId);
        this.plenumMapper.addSpeaker(plenumId, speaker);
        plenum.getSpeakers().add(speaker);
        return plenum;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.PlenumService#removeSpeaker(int,
     *      int)
     */
    @Override
    public Plenum removeSpeaker(final int plenumId, final int speakerId) throws NoSuchPlenumException {
        final Plenum plenum = this.retrievePlenum(plenumId);
        this.plenumMapper.removeSpeaker(plenumId, speakerId);
        plenum.getSpeakers().removeIf(speaker -> speaker.getId() == speakerId);
        return plenum;
    }

    /**
     *
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.PlenumService#addProtocol(int,
     *      int)
     */
    @Override
    public Plenum addProtocol(final int plenumId, final int protocolId) throws NoSuchPlenumException, NoSuchProtocolException {
        final Plenum plenum = this.retrievePlenum(plenumId);
        final Protocol protocol = this.protocolService.retrieveProtocol(protocolId);
        this.plenumMapper.addProtocol(plenumId, protocolId);
        plenum.getProtocols().add(protocol);
        return plenum;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.PlenumService#removeProtocol(int,
     *      int)
     */
    @Override
    public Plenum removeProtocol(final int plenumId, final int protocolId) throws NoSuchPlenumException {
        final Plenum plenum = this.retrievePlenum(plenumId);
        this.plenumMapper.removeProtocol(plenumId, protocolId);
        plenum.getProtocols().removeIf(protocol -> protocol.getId() == protocolId);
        return plenum;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.PlenumService#addSpeakList(int,
     *      int)
     */
    @Override
    public Plenum addSpeakList(final int plenumId, final int speakListId) throws NoSuchPlenumException, NoSuchSpeakListException {
        final Plenum plenum = this.retrievePlenum(plenumId);
        final SpeakList speakList = this.speakListService.retrieveSpeakList(speakListId);
        this.plenumMapper.addSpeakList(plenumId, speakListId);
        plenum.getSpeakLists().add(speakList);
        return plenum;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.PlenumService#removeSpeakList(int,
     *      int)
     */
    @Override
    public Plenum removeSpeakList(final int plenumId, final int speakListId) throws NoSuchPlenumException {
        final Plenum plenum = this.retrievePlenum(plenumId);
        this.plenumMapper.removeSpeakList(plenumId, speakListId);
        plenum.getSpeakLists().removeIf(speakList -> speakList.getId() == speakListId);
        return plenum;
    }
}
