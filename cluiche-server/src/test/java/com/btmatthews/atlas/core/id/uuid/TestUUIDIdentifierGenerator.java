/*
    Copyright (c) 2012 Brian Matthews

    This file is part of the Cluiche Server.

    Cluiche Server is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Cluiche Server is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Cluiche Server.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.btmatthews.atlas.core.id.uuid;

import static com.btmatthews.hamcrest.RegularExpressionMatcher.matchesPattern;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.btmatthews.hamcrest.RegularExpressionMatcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.core.env.Environment;

/**
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.0
 */
public class TestUUIDIdentifierGenerator {

    private static final String MAC_ADDRESS = "98-8E-E1-CC-76-D3";

    private static final String UUID_REGEX = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}";

    private UUIDIdentifierGenerator identifierGenerator;

    @Mock
    private Environment environment;

    @Before
    public void setUp() {
        initMocks(this);
        identifierGenerator = new UUIDIdentifierGenerator();
        identifierGenerator.setEnvironment(environment);
        identifierGenerator.init();
    }

    @Test
    public void testGenerateWithoutSpecifyingMACAddress() {
        when(environment.getProperty(UUIDIdentifierGenerator.MAC_ADDRESS_ENV_PROP_NAME)).thenReturn(null);
        final String id = identifierGenerator.generateIdentifier();
        assertNotNull(id);
        assertThat(id, matchesPattern(UUID_REGEX));
        verify(environment).getProperty(UUIDIdentifierGenerator.MAC_ADDRESS_ENV_PROP_NAME);
    }

    @Test
    public void testGenerateSpecifyingMACAddress() {
        when(environment.getProperty(UUIDIdentifierGenerator.MAC_ADDRESS_ENV_PROP_NAME)).thenReturn(MAC_ADDRESS);
        final String id = identifierGenerator.generateIdentifier();
        assertNotNull(id);
        assertThat(id, matchesPattern(UUID_REGEX));
        verify(environment).getProperty(UUIDIdentifierGenerator.MAC_ADDRESS_ENV_PROP_NAME);
    }
}
