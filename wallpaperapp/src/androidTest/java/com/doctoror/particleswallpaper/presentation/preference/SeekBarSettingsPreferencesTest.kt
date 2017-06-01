/*
 * Copyright (C) 2017 Yaroslav Mytkalyk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.doctoror.particleswallpaper.presentation.preference

import android.content.Context
import android.support.test.InstrumentationRegistry
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by Yaroslav Mytkalyk on 30.05.17.
 */
class SeekBarSettingsPreferencesTest {

    @Test
    fun testDotScalePreference() {
        testMapper(DotScalePreference::class.java)
    }

    @Test
    fun testFrameDelayPreference() {
        testMapper(FrameDelayPreference::class.java)
    }

    @Test
    fun testFrameDelayPreferenceMinValue() {
        val p = newInstance(FrameDelayPreference::class.java)
        assertEquals(p.frameDelaySeekbarMin, p.transformToRealValue(p.max))
    }

    @Test
    fun testLineDistancePreference() {
        testMapper(LineDistancePreference::class.java)
    }

    @Test
    fun testLineScalePreference() {
        testMapper(LineScalePreference::class.java)
    }

    @Test
    fun testNumDotsPreference() {
        testMapper(NumDotsPreference::class.java)
    }

    @Test
    fun testSpeedFactorPreference() {
        testMapper(SpeedFactorPreference::class.java)
    }

    private fun <T, A> testMapper(pClass: Class<T>)
            where T : SeekBarPreference, T : MapperSeekbarPreference<A> {
        testMapperMinValue(pClass)
        testMapperMaxValue(pClass)
    }

    private fun <T, A> testMapperMinValue(pClass: Class<T>)
            where T : SeekBarPreference, T : MapperSeekbarPreference<A> {
        val p = newInstance(pClass)
        val seekBarValue = 0
        val frameDelay = p.transformToRealValue(seekBarValue)
        assertEquals(seekBarValue, p.transformToProgress(frameDelay))
    }

    private fun <T, A> testMapperMaxValue(pClass: Class<T>)
            where T : SeekBarPreference, T : MapperSeekbarPreference<A> {
        val p = newInstance(pClass)
        val seekBarValue = p.max
        val frameDelay = p.transformToRealValue(seekBarValue)
        assertEquals(seekBarValue, p.transformToProgress(frameDelay))
    }

    fun <T, A> newInstance(pClass: Class<T>): T where T : SeekBarPreference, T : MapperSeekbarPreference<A> {
        val constructor = pClass.getConstructor(Context::class.java)
        return constructor.newInstance(InstrumentationRegistry.getTargetContext())
    }

}