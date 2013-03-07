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
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Created with IntelliJ IDEA.
 * User: Brian
 * Date: 17/08/12
 * Time: 09:10
 * To change this template use File | Settings | File Templates.
 */
public class ServerInitializer implements WebApplicationInitializer {

    public void onStartup(final ServletContext servletContext) throws ServletException
    {
        final AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(ServerConfig.class);

        final ServletRegistration.Dynamic servlet = servletContext.addServlet("api", new DispatcherServlet(applicationContext));
        servlet.addMapping("/api/*");
        servlet.setLoadOnStartup(1);
    }
}
