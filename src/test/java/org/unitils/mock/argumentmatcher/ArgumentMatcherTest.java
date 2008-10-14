/*
 * Copyright 2006-2007,  Unitils.org
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
package org.unitils.mock.argumentmatcher;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.unitils.mock.ArgumentMatchers.*;
import org.unitils.mock.core.MockObject;
import org.unitils.mock.core.Scenario;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests the usage of argment matchers.
 *
 * @author Tim Ducheyne
 * @author Filip Neven
 */
public class ArgumentMatcherTest {

    /* Test mock object */
    private MockObject<TestClass> mockObject;


    @Before
    public void setUp() {
        mockObject = new MockObject<TestClass>("testMock", TestClass.class, false, new Scenario(null));
    }


    /**
     * Tests the equals argument matcher, for an matching argument.
     */
    @Test
    public void testEqualsArgumentMatcher() {
        mockObject.returns(true).testMethodString(eq("test"));

        boolean result = mockObject.getInstance().testMethodString("test");
        assertTrue(result);
        mockObject.assertInvoked().testMethodString(eq("test"));
    }


    /**
     * Tests the equals argument matcher, for a non-matching argument.
     */
    @Test
    public void testEqualsArgumentMatcher_noMatch() {
        mockObject.returns(true).testMethodString(eq("test"));

        boolean result = mockObject.getInstance().testMethodString("xxxx");
        assertFalse(result);
        mockObject.assertNotInvoked().testMethodString(eq("test"));
    }


    /**
     * Tests the equals argument matcher, for a matching null argument.
     */
    @Test
    public void testEqualsArgumentMatcher_bothNull() {
        mockObject.returns(true).testMethodString(eq((String) null));

        boolean result = mockObject.getInstance().testMethodString(null);
        assertTrue(result);
        mockObject.assertInvoked().testMethodString(eq((String) null));
    }


    /**
     * Tests the equals argument matcher, for a non-matching null argument.
     */
    @Test
    public void testEqualsArgumentMatcher_null() {
        mockObject.returns(true).testMethodString(eq("test"));

        boolean result = mockObject.getInstance().testMethodString(null);
        assertFalse(result);
        mockObject.assertNotInvoked().testMethodString(eq("test"));
    }
    
    
    /**
     * Tests the equals argument matcher in case the object changes between the behavior definition,
     * the actual method call and the assert statement. Since the eq() argument matcher uses the original
     * object reference and not a copy of the object, the values should keep on matching. 
     */
    @Test
    public void testEqualsArgumentMatcher_objectChangesBetweenCalls() {
        List<String> list = new ArrayList<String>();
        
        mockObject.returns(true).testMethodObject(eq(list));
        
        list.add("test");
        assertTrue(mockObject.getInstance().testMethodObject(list));
        
        List<String> nonEqualList = new ArrayList<String>();
        assertFalse(mockObject.getInstance().testMethodObject(nonEqualList));
        
        List<String> equalList = new ArrayList<String>();
        equalList.add("test");
        assertTrue(mockObject.getInstance().testMethodObject(equalList));
        
        list.add("test");
        mockObject.assertInvoked().testMethodObject(eq(list));
    }


    /**
     * Tests the not null argument matcher, for an matching (not null) argument.
     */
    @Test
    public void testNotNullArgumentMatcher() {
        mockObject.returns(true).testMethodString(notNull(String.class));

        boolean result = mockObject.getInstance().testMethodString("test");
        assertTrue(result);
    }


    /**
     * Tests the not null argument matcher, for a non-matching (null) argument.
     */
    @Test
    public void testNotNullArgumentMatcher_noMatch() {
        mockObject.returns(true).testMethodString(notNull(String.class));

        boolean result = mockObject.getInstance().testMethodString(null);
        assertFalse(result);
    }


    /**
     * Tests the not null argument matcher, for an matching (null) argument.
     */
    @Test
    public void testNullArgumentMatcher() {
        mockObject.returns(true).testMethodString(isNull(String.class));

        boolean result = mockObject.getInstance().testMethodString(null);
        assertTrue(result);
    }


    /**
     * Tests the not null argument matcher, for a non-matching (not null) argument.
     */
    @Test
    public void testNullArgumentMatcher_noMatch() {
        mockObject.returns(true).testMethodString(isNull(String.class));

        boolean result = mockObject.getInstance().testMethodString("test");
        assertFalse(result);
    }


    /**
     * Tests the lenient equals argument matcher, for an matching argument.
     */
    @Test
    public void testLenEqArgumentMatcher() {
        mockObject.returns(true).testMethodString(lenEq("test"));

        boolean result = mockObject.getInstance().testMethodString("test");
        assertTrue(result);
    }


    /**
     * Tests the lenient equals argument matcher, for a non-matching argument.
     */
    @Test
    public void testLenEqArgumentMatcher_noMatch() {
        mockObject.returns(true).testMethodString(lenEq("test"));

        boolean result = mockObject.getInstance().testMethodString("xxxx");
        assertFalse(result);
    }


    /**
     * Tests the lenient equals argument matcher, for a matching null argument.
     */
    @Test
    public void testLenEqArgumentMatcher_bothNull() {
        mockObject.returns(true).testMethodString(lenEq((String) null));

        boolean result = mockObject.getInstance().testMethodString(null);
        assertTrue(result);
    }


    /**
     * Tests the lenient equals argument matcher, for a non-matching null argument.
     */
    @Test
    public void testLenEqArgumentMatcher_null() {
        mockObject.returns(true).testMethodString(lenEq("test"));

        boolean result = mockObject.getInstance().testMethodString(null);
        assertFalse(result);
    }
    
    
    /**
     * Tests the lenient equals argument matcher in case the object changes between the behavior definition,
     * the actual method call and the assert statement. Since the lenEq() argument matcher uses the a copy of 
     * the object, the values should not match anymore. 
     */
    @Test
    public void testLenEqArgumentMatcher_objectChangesBetweenCalls() {
        List<String> list = new ArrayList<String>();
        
        mockObject.returns(true).testMethodObject(lenEq(list));
        
        list.add("test");
        assertFalse(mockObject.getInstance().testMethodObject(list));
        
        List<String> emptyList = new ArrayList<String>();
        assertTrue(mockObject.getInstance().testMethodObject(emptyList));
        
        List<String> oneElementList = new ArrayList<String>();
        oneElementList.add("test");
        assertFalse(mockObject.getInstance().testMethodObject(oneElementList));
        
        list.add("test");
        mockObject.assertNotInvoked().testMethodObject(lenEq(list));
        mockObject.assertInvoked().testMethodObject(lenEq(emptyList));
    }
    
    
    /**
     * Tests the reflection equals argument matcher, for an matching argument.
     */
    @Test
    public void testRefEqArgumentMatcher() {
        mockObject.returns(true).testMethodString(refEq("test"));

        boolean result = mockObject.getInstance().testMethodString("test");
        assertTrue(result);
    }


    /**
     * Tests the reflection equals argument matcher, for a non-matching argument.
     */
    @Test
    public void testRefEqArgumentMatcher_noMatch() {
        mockObject.returns(true).testMethodString(refEq("test"));

        boolean result = mockObject.getInstance().testMethodString("xxxx");
        assertFalse(result);
    }


    /**
     * Tests the reflection equals argument matcher, for a matching null argument.
     */
    @Test
    public void testRefEqArgumentMatcher_bothNull() {
        mockObject.returns(true).testMethodString(refEq((String) null));

        boolean result = mockObject.getInstance().testMethodString(null);
        assertTrue(result);
    }


    /**
     * Tests the reflection equals argument matcher, for a non-matching null argument.
     */
    @Test
    public void testRefEqArgumentMatcher_null() {
        mockObject.returns(true).testMethodString(refEq("test"));

        boolean result = mockObject.getInstance().testMethodString(null);
        assertFalse(result);
    }
    
    
    /**
     * Tests the lenient equals argument matcher in case the object changes between the behavior definition,
     * the actual method call and the assert statement. Since the lenEq() argument matcher uses the a copy of 
     * the object, the values should not match anymore. 
     */
    @Test
    public void testRefEqArgumentMatcher_objectChangesBetweenCalls() {
        List<String> list = new ArrayList<String>();
        
        mockObject.returns(true).testMethodObject(refEq(list));
        
        list.add("test");
        assertFalse(mockObject.getInstance().testMethodObject(list));
        
        List<String> emptyList = new ArrayList<String>();
        assertTrue(mockObject.getInstance().testMethodObject(emptyList));
        
        List<String> oneElementList = new ArrayList<String>();
        oneElementList.add("test");
        assertFalse(mockObject.getInstance().testMethodObject(oneElementList));
        
        list.add("test");
        mockObject.assertNotInvoked().testMethodObject(refEq(list));
        mockObject.assertInvoked().testMethodObject(refEq(emptyList));
    }


    /**
     */
    @Test
    public void testSameArgumentMatcher() {
        List object = new ArrayList();
        mockObject.returns(true).testMethodObject(same(object));

        boolean result = mockObject.getInstance().testMethodObject(object);
        assertTrue(result);
    }


    /**
     * Tests the same argument matcher, for a non-matching argument.
     */
    @Test
    public void testSameArgumentMatcher_noMatch() {
        mockObject.returns(true).testMethodObject(same(new ArrayList()));

        boolean result = mockObject.getInstance().testMethodObject(new ArrayList());
        assertFalse(result);
    }


    /**
     * Tests the same argument matcher, for a matching null argument.
     */
    @Test
    public void testSameArgumentMatcher_bothNull() {
        mockObject.returns(true).testMethodObject(same(null));

        boolean result = mockObject.getInstance().testMethodObject(null);
        assertTrue(result);
    }


    /**
     * Tests the same argument matcher, for a non-matching null argument.
     */
    @Test
    public void testSameArgumentMatcher_null() {
        mockObject.returns(true).testMethodObject(same(new ArrayList()));

        boolean result = mockObject.getInstance().testMethodObject(null);
        assertFalse(result);
    }


    /**
     * Tests the same argument matcher in case the object changes between the behavior definition,
     * the actual method call and the assert statement. Since the same() argument matcher uses the original
     * object reference and not a copy of the object, the values should keep on matching. 
     */
    @Test
    public void testSameArgumentMatcher_objectChangesBetweenCalls() {
        List<String> list = new ArrayList<String>();

        mockObject.returns(true).testMethodObject(same(list));
        
        list.add("test");
        assertTrue(mockObject.getInstance().testMethodObject(list));
        
        List<String> equalList = new ArrayList<String>();
        equalList.add("test");
        assertFalse(mockObject.getInstance().testMethodObject(equalList));
        
        List<String> otherEqualList = new ArrayList<String>();
        otherEqualList.add("test");
        mockObject.assertNotInvoked().testMethodObject(same(otherEqualList));
        
        list.add("test");
        mockObject.assertInvoked().testMethodObject(same(list));
    }


    /**
     * Interface that is mocked during the tests
     */
    private static interface TestClass {

        boolean testMethodString(String arg1);

        boolean testMethodObject(Object arg1);

    }

}