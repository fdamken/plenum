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

import java.net.URL;
import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmken.oss.plenum.data.mapper.ProtocolMapper;
import com.dmken.oss.plenum.data.service.ProtocolService;
import com.dmken.oss.plenum.data.service.exception.NoSuchProtocolException;
import com.dmken.oss.plenum.model.Protocol;

/**
 * Implementation of {@link ProtocolService}.
 *
 */
@Service
public class ProtocolServiceImpl implements ProtocolService {
    /**
     * The {@link ProtocolMapper}.
     *
     */
    @Autowired
    private ProtocolMapper protocolMapper;

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.ProtocolService#retrieveProtocolsOfPlenum(int)
     */
    @Override
    public SortedSet<Protocol> retrieveProtocolsOfPlenum(final int plenumId) {
        return this.protocolMapper.findByPlenumId(plenumId);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.ProtocolService#retrieveProtocol(int)
     */
    @Override
    public Protocol retrieveProtocol(final int protocolId) throws NoSuchProtocolException {
        final Protocol protocol = this.protocolMapper.findById(protocolId);
        if (protocol == null) {
            throw new NoSuchProtocolException("Unable to find protocol with id=" + protocolId + "!");
        }
        return protocol;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.ProtocolService#createProtocol(java.lang.String,
     *      java.lang.String, java.net.URL, java.net.URL)
     */
    @Override
    public Protocol createProtocol(final String name, final String description, final URL url, final URL readOnlyURL) {
        final Protocol protocol = Protocol.builder() //
                .name(name) //
                .description(description) //
                .url(url) //
                .readOnlyURL(readOnlyURL) //
                .build();
        this.protocolMapper.createProtocol(protocol);
        return protocol;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.ProtocolService#setName(int,
     *      java.lang.String)
     */
    @Override
    public Protocol setName(final int protocolId, final String name) throws NoSuchProtocolException {
        final Protocol protocol = this.retrieveProtocol(protocolId);
        this.protocolMapper.setName(protocolId, name);
        protocol.setName(name);
        return protocol;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.ProtocolService#setDescription(int,
     *      java.lang.String)
     */
    @Override
    public Protocol setDescription(final int protocolId, final String description) throws NoSuchProtocolException {
        final Protocol protocol = this.retrieveProtocol(protocolId);
        this.protocolMapper.setDescription(protocolId, description);
        protocol.setDescription(description);
        return protocol;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.ProtocolService#setURL(int,
     *      java.net.URL)
     */
    @Override
    public Protocol setURL(final int protocolId, final URL url) throws NoSuchProtocolException {
        final Protocol protocol = this.retrieveProtocol(protocolId);
        this.protocolMapper.setUrl(protocolId, url);
        protocol.setUrl(url);
        return protocol;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.dmken.oss.plenum.data.service.ProtocolService#setReadOnlyURL(int,
     *      java.net.URL)
     */
    @Override
    public Protocol setReadOnlyURL(final int protocolId, final URL readOnlyUrl) throws NoSuchProtocolException {
        final Protocol protocol = this.retrieveProtocol(protocolId);
        this.protocolMapper.setReadOnlyUrl(protocolId, readOnlyUrl);
        protocol.setReadOnlyURL(readOnlyUrl);
        return protocol;
    }
}
