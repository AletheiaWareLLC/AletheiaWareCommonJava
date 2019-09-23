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
    public void testBinarySizeToString() throws Exception {
        Assert.assertEquals("0bytes", CommonUtils.binarySizeToString(0L));
        Assert.assertEquals("1byte", CommonUtils.binarySizeToString(1L));
        Assert.assertEquals("64bytes", CommonUtils.binarySizeToString(64L));
        Assert.assertEquals("1.21KiB", CommonUtils.binarySizeToString(1234L));
        Assert.assertEquals("55.46KiB", CommonUtils.binarySizeToString(56789L));
        Assert.assertEquals("1.18MiB", CommonUtils.binarySizeToString(1234567L));
        Assert.assertEquals("8.29GiB", CommonUtils.binarySizeToString(8901234567L));
        Assert.assertEquals("8.10TiB", CommonUtils.binarySizeToString(8901234567890L));
        Assert.assertEquals("10.97PiB", CommonUtils.binarySizeToString(12345678901234567L));
    }

    @Test
    public void testDecimalSizeToString() throws Exception {
        Assert.assertEquals("0bytes", CommonUtils.decimalSizeToString(0L));
        Assert.assertEquals("1byte", CommonUtils.decimalSizeToString(1L));
        Assert.assertEquals("64bytes", CommonUtils.decimalSizeToString(64L));
        Assert.assertEquals("1.23KB", CommonUtils.decimalSizeToString(1234L));
        Assert.assertEquals("56.79KB", CommonUtils.decimalSizeToString(56789L));
        Assert.assertEquals("1.23MB", CommonUtils.decimalSizeToString(1234567L));
        Assert.assertEquals("8.90GB", CommonUtils.decimalSizeToString(8901234567L));
        Assert.assertEquals("8.90TB", CommonUtils.decimalSizeToString(8901234567890L));
        Assert.assertEquals("12.35PB", CommonUtils.decimalSizeToString(12345678901234567L));
    }

    @Test
    public void testTimeToString() throws Exception {
        Assert.assertEquals("2019-08-12 17:36:05", CommonUtils.timeToString(1565656565656565656L));
    }

    @Test
    public void testMoneyToString() throws Exception {
        Assert.assertEquals("Free", CommonUtils.moneyToString("usd", 0L));
        Assert.assertEquals("$0.64", CommonUtils.moneyToString("usd", 64L));
        Assert.assertEquals("$12.34", CommonUtils.moneyToString("usd", 1234L));
        Assert.assertEquals("$567.89", CommonUtils.moneyToString("usd", 56789L));
        Assert.assertEquals("$12345.67", CommonUtils.moneyToString("usd", 1234567L));
        Assert.assertEquals("$89012345.67", CommonUtils.moneyToString("usd", 8901234567L));
        Assert.assertEquals("$89012345678.9", CommonUtils.moneyToString("usd", 8901234567890L));
    }
}