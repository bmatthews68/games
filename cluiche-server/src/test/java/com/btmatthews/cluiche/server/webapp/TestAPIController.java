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

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.server.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.List;

import com.btmatthews.cluiche.server.controller.PlayerAPIController;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.server.MockMvc;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

/**
 * @author <a href="mailto:brian@btmatthews.com">Brian Matthews</a>
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ServerConfig.class }, loader = AnnotationConfigContextLoader.class)
public class TestAPIController {

    @Autowired
    private PlayerAPIController apiController;

    private MockMvc mockController;

    @Before
    public void setUp() throws Exception {
        final List<View> viewList = new ArrayList<View>();
        viewList.add(new MappingJacksonJsonView());

        final ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
        viewResolver.setDefaultViews(viewList);
        viewResolver.setDefaultContentType(MediaType.APPLICATION_JSON);

        mockController = standaloneSetup(apiController).setViewResolvers(viewResolver).build();
    }

    @Test
    public void testGetPlayerById() throws Exception {
        mockController
                .perform(get("/players/00000000-0000-0000-0000-00000000").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().type(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testRegisterNewPlayer() throws Exception {
        mockController
                .perform(post("/players").accept(MediaType.APPLICATION_JSON).param("username",
                        "bmatthews68").contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(content().type(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.username").value("bmatthews68"));
    }
}
