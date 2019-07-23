/*
 * Copyright 2019 Aletheia Ware LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aletheiaware.common.utils;

import org.junit.Assert;
import org.junit.Test;

public class CommonUtilsTest {

    @Test
    public void testSizeToString() throws Exception {
        Assert.assertEquals("0bytes", CommonUtils.sizeToString(0L));
        Assert.assertEquals("64bytes", CommonUtils.sizeToString(64L));
        Assert.assertEquals("1.21Kb", CommonUtils.sizeToString(1234L));
        Assert.assertEquals("55.46Kb", CommonUtils.sizeToString(56789L));
        Assert.assertEquals("1.18Mb", CommonUtils.sizeToString(1234567L));
        Assert.assertEquals("8.29Gb", CommonUtils.sizeToString(8901234567L));
        Assert.assertEquals("8.10Tb", CommonUtils.sizeToString(8901234567890L));
        Assert.assertEquals("10.97Pb", CommonUtils.sizeToString(12345678901234567L));
    }

    @Test
    public void testTimeToString() throws Exception {
        Assert.assertEquals("2019-08-12 17:36:05", CommonUtils.timeToString(1565656565656565656L));
    }
}