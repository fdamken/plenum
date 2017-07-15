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

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

/**
 * Type handler for {@link URL}.
 *
 */
@MappedJdbcTypes(value = JdbcType.VARCHAR,
                 includeNullJdbcType = true)
public class URLHandler extends BaseTypeHandler<URL> {
    /**
     * {@inheritDoc}
     *
     * @see org.apache.ibatis.type.BaseTypeHandler#setNonNullParameter(java.sql.PreparedStatement,
     *      int, java.lang.Object, org.apache.ibatis.type.JdbcType)
     */
    @Override
    public void setNonNullParameter(final PreparedStatement ps, final int i, final URL parameter, final JdbcType jdbcType)
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
    public URL getNullableResult(final ResultSet rs, final String columnName) throws SQLException {
        return this.determineURL(rs.getString(columnName));
    }

    /**
     * {@inheritDoc}
     *
     * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.ResultSet,
     *      int)
     */
    @Override
    public URL getNullableResult(final ResultSet rs, final int columnIndex) throws SQLException {
        return this.determineURL(rs.getString(columnIndex));
    }

    /**
     * {@inheritDoc}
     *
     * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.CallableStatement,
     *      int)
     */
    @Override
    public URL getNullableResult(final CallableStatement cs, final int columnIndex) throws SQLException {
        return cs.getURL(columnIndex);
    }

    /**
     * Determines the URL of the given value.
     *
     * @param value
     *            The value.
     * @return The newly created URL or <code>null</code> if <code>value</code>
     *         was <code>null</code>.
     * @throws SQLException
     *             If the URL is malformed.
     */
    private URL determineURL(final String value) throws SQLException {
        try {
            return value == null ? null : new URL(value);
        } catch (final MalformedURLException cause) {
            throw new SQLException(cause);
        }
    }
}
