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

package com.btmatthews.cluiche.server.webapp;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockServletContext;

/**
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.0
 */
@Configuration
@ComponentScan({
        "com.btmatthews.cluiche.server.controller",
        "com.btmatthews.cluiche.server.service",
        "com.btmatthews.atlas" })
public class ServerTestConfig {

    @Bean
    public ServletContext getServletContext() {
        return new MockServletContext();
    }
}
