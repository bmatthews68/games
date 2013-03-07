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

package com.btmatthews.hamcrest;

import java.util.regex.Pattern;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;

/**
 * @author <a href="http://piotrga.wordpress.com/2009/03/27/hamcrest-regex-matcher/"></a>
 * @since 1.0.0
 */
public class RegularExpressionMatcher extends TypeSafeMatcher<String> {

    private final Pattern pattern;

    public RegularExpressionMatcher(final String pattern) {
        this(Pattern.compile(pattern));
    }

    public RegularExpressionMatcher(final Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("matches regular expression ").appendValue(pattern);
    }

    @Override
    public boolean matchesSafely(final String item) {
        return pattern.matcher(item).matches();
    }

    @Factory
    public static Matcher matchesPattern(final Pattern pattern) {
        return new RegularExpressionMatcher(pattern);
    }

    @Factory
    public static Matcher matchesPattern(final String pattern) {
        return new RegularExpressionMatcher(pattern);
    }}
