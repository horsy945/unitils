/*
 * Copyright 2013,  Unitils.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.unitils.mock.core;

import org.junit.Before;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;

import java.util.Properties;

import static org.junit.Assert.assertEquals;

/**
 * @author Tim Ducheyne
 */
public class MockObjectFormatObjectTest extends UnitilsJUnit4 {

    private MockObject<Properties> mockObject;


    @Before
    public void initialize() {
        mockObject = new MockObject<Properties>("name", null, null, null, false, null, null, null, null);
    }


    @Test
    public void formatObject() {
        String result = mockObject.$formatObject();
        assertEquals("Mock<name>", result);
    }
}
