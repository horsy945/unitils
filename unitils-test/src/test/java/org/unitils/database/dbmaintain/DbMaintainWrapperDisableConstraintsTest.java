/*
 * Copyright 2012,  Unitils.org
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

package org.unitils.database.dbmaintain;

import org.dbmaintain.MainFactory;
import org.dbmaintain.structure.constraint.ConstraintsDisabler;
import org.junit.Before;
import org.junit.Test;
import org.unitils.mock.Mock;
import org.unitilsnew.UnitilsJUnit4;

/**
 * @author Tim Ducheyne
 */
public class DbMaintainWrapperDisableConstraintsTest extends UnitilsJUnit4 {

    /* Tested object */
    private DbMaintainWrapper dbMaintainWrapper;

    protected Mock<MainFactory> mainFactoryMock;
    protected Mock<ConstraintsDisabler> constraintsDisablerMock;


    @Before
    public void initialize() {
        dbMaintainWrapper = new DbMaintainWrapper(mainFactoryMock.getMock(), true);
        mainFactoryMock.returns(constraintsDisablerMock).createConstraintsDisabler();
    }


    @Test
    public void disableConstraints() {
        dbMaintainWrapper.disableConstraints();
        constraintsDisablerMock.assertInvoked().disableConstraints();
    }
}