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

import javax.annotation.PostConstruct;

import com.btmatthews.atlas.core.id.IdentifierGenerator;
import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Brian
 * Date: 17/08/12
 * Time: 22:53
 * To change this template use File | Settings | File Templates.
 */
@Component
public class UUIDIdentifierGenerator implements IdentifierGenerator {

    static final String MAC_ADDRESS_ENV_PROP_NAME = "com.btmatthews.atlas.core.id.MACAddresss";

    private Environment environment;

    private TimeBasedGenerator generator;

    @Autowired
    public void setEnvironment(final Environment env) {
        environment = env;
    }

    @PostConstruct
    public void init()
    {
        final String macAddress = environment.getProperty(MAC_ADDRESS_ENV_PROP_NAME);
        if (macAddress == null)
        {
            generator = Generators.timeBasedGenerator();
        }
        else
        {
            final EthernetAddress ethernetAddress = EthernetAddress.valueOf(macAddress);
            generator = Generators.timeBasedGenerator(ethernetAddress);
        }
    }

    public String generateIdentifier() {
        return generator.generate().toString();
    }
}
