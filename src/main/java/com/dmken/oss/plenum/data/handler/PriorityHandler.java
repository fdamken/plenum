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

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import com.dmken.oss.plenum.util.Priority;

/**
 * Type handler for {@link Priority}.
 *
 */
@MappedJdbcTypes(value = JdbcType.INTEGER,
                 includeNullJdbcType = true)
public class PriorityHandler extends BaseTypeHandler<Priority> {
    /**
     * {@inheritDoc}
     *
     * @see org.apache.ibatis.type.BaseTypeHandler#setNonNullParameter(java.sql.PreparedStatement,
     *      int, java.lang.Object, org.apache.ibatis.type.JdbcType)
     */
    @Override
    public void setNonNullParameter(final PreparedStatement ps, final int i, final Priority parameter, final JdbcType jdbcType)
            throws SQLException {
        ps.setInt(i, parameter.getPriority());
    }

    /**
     * {@inheritDoc}
     *
     * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.ResultSet,
     *      java.lang.String)
     */
    @Override
    public Priority getNullableResult(final ResultSet rs, final String columnName) throws SQLException {
        return this.determinePriority(rs.getInt(columnName));
    }

    /**
     * {@inheritDoc}
     *
     * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.ResultSet,
     *      int)
     */
    @Override
    public Priority getNullableResult(final ResultSet rs, final int columnIndex) throws SQLException {
        return this.determinePriority(rs.getInt(columnIndex));
    }

    /**
     * {@inheritDoc}
     *
     * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.CallableStatement,
     *      int)
     */
    @Override
    public Priority getNullableResult(final CallableStatement cs, final int columnIndex) throws SQLException {
        return this.determinePriority(cs.getInt(columnIndex));
    }

    /**
     * Determines the {@link Priority} of the given priority value.
     *
     * @param priority
     *            The priority value.
     * @return <code>null</code> if <code>priority &lt; 0</code>,
     *         {@link Priority} otherwise.
     */
    private Priority determinePriority(final int priority) {
        return priority < 0 ? null : Priority.of(priority);
    }
}
