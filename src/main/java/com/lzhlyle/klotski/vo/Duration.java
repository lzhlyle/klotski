package com.lzhlyle.klotski.vo;

import java.util.Date;

public class Duration {
    private Date beginDatetime;
    private Date endDatetime;

    private Date lastFromDatetime;
    private Date lastToDatetime;

    private long durationMillis;

    public Duration() {
        Date now = now();
        this.beginDatetime = now;
        this.endDatetime = null;

        this.lastFromDatetime = now;
        this.lastToDatetime = null;

        this.durationMillis = -1;
    }

    public Date getBeginDatetime() {
        return beginDatetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public long getDurationMillis() {
        return durationMillis;
    }

    /**
     * 暂停
     */
    public void suspend() {
        if (!isCounting() || isEnd()) {
            // suspend or stop
            return;
        }

        this.lastToDatetime = now();
        // add up
        this.durationMillis = this.durationMillis + (this.lastToDatetime.getTime() - this.lastFromDatetime.getTime());
    }

    /**
     * 继续
     */
    public void proceed() {
        if (isCounting() || isEnd()) {
            // counting or stop
            return;
        }

        // recount from to
        this.lastFromDatetime = now();
        this.lastToDatetime = null;
    }

    /**
     * 停止
     */
    public void stop() {
        if (isEnd()) {
            return;
        }

        if (isCounting()) {
            // counting
            Date now = now();
            this.endDatetime = now;
            this.lastToDatetime = now;

            // add up
            this.durationMillis = this.durationMillis + (this.lastToDatetime.getTime() - this.lastFromDatetime.getTime());
        } else {
            // suspend
            this.endDatetime = this.lastToDatetime;
            // should not add up again
        }

        // clear from to
        this.lastFromDatetime = null;
        this.lastToDatetime = null;
    }

    private Date now() {
        return new Date();
    }

    private boolean isCounting() {
        return this.lastToDatetime == null;
    }

    private boolean isEnd() {
        return this.endDatetime != null;
    }
}
