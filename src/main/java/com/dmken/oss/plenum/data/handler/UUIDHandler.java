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
package com.dmken.oss.plenum.data.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * TODO: Add type description!
 *
 */
public class UUIDHandler extends BaseTypeHandler<UUID> {
    /**
     * {@inheritDoc}
     *
     * @see org.apache.ibatis.type.BaseTypeHandler#setNonNullParameter(java.sql.PreparedStatement,
     *      int, java.lang.Object, org.apache.ibatis.type.JdbcType)
     */
    @Override
    public void setNonNullParameter(final PreparedStatement ps, final int i, final UUID parameter, final JdbcType jdbcType)
            throws SQLException {
        ps.setString(i, parameter.toString());
    }

    /**
     * {@inheritDoc}
     *
     * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.ResultSet,
     *      java.lang.String)
     */
    @Override
    public UUID getNullableResult(final ResultSet rs, final String columnName) throws SQLException {
        return this.determineUUID(rs.getString(columnName));
    }

    /**
     * {@inheritDoc}
     *
     * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.ResultSet,
     *      int)
     */
    @Override
    public UUID getNullableResult(final ResultSet rs, final int columnIndex) throws SQLException {
        return this.determineUUID(rs.getString(columnIndex));
    }

    /**
     * {@inheritDoc}
     *
     * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.CallableStatement,
     *      int)
     */
    @Override
    public UUID getNullableResult(final CallableStatement cs, final int columnIndex) throws SQLException {
        return this.determineUUID(cs.getString(columnIndex));
    }

    /**
     * Determines the UUID of the given value.
     *
     * @param value
     *            The value.
     * @return The newly created UUID or <code>null</code> if <code>value</code>
     *         was <code>null</code>.
     * @throws SQLException
     *             If the UUID is malformed.
     */
    private UUID determineUUID(final String value) throws SQLException {
        try {
            return value == null ? null : UUID.fromString(value);
        } catch (final IllegalArgumentException cause) {
            throw new SQLException(cause);
        }
    }
}
