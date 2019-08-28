package com.lzhlyle.klotski.vo;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class DurationTest {

    @Test
    public void generatorBeginDatetimeShouldNotNull() {
        Duration duration = new Duration();
        assertNotNull(duration.getBeginDatetime());
    }

    @Test
    public void generatorEndDatetimeShouldNull() {
        Duration duration = new Duration();
        assertNull(duration.getEndDatetime());
    }

    @Test
    public void generatorDurationMillisShouldLessThanZero() {
        Duration duration = new Duration();
        assertTrue(duration.getDurationMillis() < 0);
    }

    @Test
    public void suspendBeginDatetimeShouldUnchanged() throws InterruptedException {
        Duration duration = new Duration();
        long beforeSuspend = duration.getBeginDatetime().getTime();
        Thread.sleep(randomMillis());
        duration.suspend();
        long afterSuspend = duration.getBeginDatetime().getTime();
        assertEquals(beforeSuspend, afterSuspend);
    }

    @Test
    public void suspendEndDatetimeShouldNull() {
        Duration duration = new Duration();
        duration.suspend();
        assertNull(duration.getEndDatetime());
    }

    @Test
    public void suspendDurationMillisShouldGreaterThanZero() throws InterruptedException {
        Duration duration = new Duration();
        Thread.sleep(randomMillis());
        duration.suspend();
        assertTrue(duration.getDurationMillis() > 0);
    }

    @Test
    public void proceedBeginDatetimeShouldUnchanged() throws InterruptedException {
        Duration duration = new Duration();
        long beforeSuspend = duration.getBeginDatetime().getTime();
        Thread.sleep(randomMillis());
        duration.proceed();
        Thread.sleep(randomMillis());
        long afterSuspend = duration.getBeginDatetime().getTime();
        assertEquals(beforeSuspend, afterSuspend);
    }

    @Test
    public void proceedEndDatetimeShouldNull() throws InterruptedException {
        Duration duration = new Duration();
        Thread.sleep(randomMillis());
        duration.proceed();
        assertNull(duration.getEndDatetime());
    }

    @Test
    public void proceedDurationMillisShouldLessThanZero() throws InterruptedException {
        Duration duration = new Duration();
        Thread.sleep(randomMillis());
        duration.proceed();
        assertTrue(duration.getDurationMillis() < 0);
    }

    @Test
    public void suspendProceedEndDatetimeShouldNull() throws InterruptedException {
        Duration duration = new Duration();
        Thread.sleep(randomMillis());
        duration.suspend();
        Thread.sleep(randomMillis());
        duration.proceed();
        assertNull(duration.getEndDatetime());
    }

    @Test
    public void suspendProceedDurationMillisShouldGreaterThanZero() throws InterruptedException {
        Duration duration = new Duration();
        Thread.sleep(randomMillis());
        duration.suspend();
        Thread.sleep(randomMillis());
        duration.proceed();
        assertTrue(duration.getDurationMillis() > 0);
    }

    @Test
    public void suspendProceedSuspendDurationMillisShouldIncrease() throws InterruptedException {
        Duration duration = new Duration();
        Thread.sleep(randomMillis());
        duration.suspend();
        long durationMillis1 = duration.getDurationMillis();
        Thread.sleep(randomMillis());
        duration.proceed();
        Thread.sleep(randomMillis());
        duration.suspend();
        long durationMillis2 = duration.getDurationMillis();
        assertTrue(durationMillis2 > durationMillis1);
    }

    @Test
    public void stopBeginDatetimeShouldUnchanged() throws InterruptedException {
        Duration duration = new Duration();
        long beforeSuspend = duration.getBeginDatetime().getTime();
        Thread.sleep(randomMillis());
        duration.stop();
        long afterSuspend = duration.getBeginDatetime().getTime();
        assertEquals(beforeSuspend, afterSuspend);
    }

    @Test
    public void stopEndDatetimeShouldNotNull() throws InterruptedException {
        Duration duration = new Duration();
        Thread.sleep(randomMillis());
        duration.stop();
        assertNotNull(duration.getEndDatetime());
    }

    private long randomMillis() {
        return new Random().nextInt(50) + 2;
    }

    @Test
    public void stopDurationMillisShouldGreaterThanZero() throws InterruptedException {
        Duration duration = new Duration();
        Thread.sleep(randomMillis());
        duration.stop();
        assertTrue(duration.getDurationMillis() > 0);
    }

    @Test
    public void stopProceedEndDatetimeShouldUnchanged() throws InterruptedException {
        Duration duration = new Duration();
        Thread.sleep(randomMillis());
        duration.stop();
        long beforeProceed = duration.getEndDatetime().getTime();
        Thread.sleep(randomMillis());
        duration.proceed();
        long afterProceed = duration.getEndDatetime().getTime();
        assertEquals(beforeProceed, afterProceed);
    }

    @Test
    public void stopSuspendEndDatetimeShouldUnchanged() throws InterruptedException {
        Duration duration = new Duration();
        Thread.sleep(randomMillis());
        duration.stop();
        long beforeSuspend = duration.getEndDatetime().getTime();
        Thread.sleep(randomMillis());
        duration.suspend();
        long afterSuspend = duration.getEndDatetime().getTime();
        assertEquals(beforeSuspend, afterSuspend);
    }

    @Test
    public void suspendStopDurationMillisShouldUnchanged() throws InterruptedException {
        Duration duration = new Duration();
        Thread.sleep(randomMillis());
        duration.suspend();
        long beforeStop = duration.getDurationMillis();
        Thread.sleep(randomMillis());
        duration.stop();
        long afterStop = duration.getDurationMillis();
        assertEquals(beforeStop, afterStop);
    }
}