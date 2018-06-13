package com.jin.cmp.gg;

/**
 * 产品中要monitor service response time，假设已经收集了大量数据，给定percentile的定义：如果说20ms的响应时间是90 percentile，
 * 代表收集的数据中90%的response time小于20ms，10%的response time大于20ms。同理，如果是说50 percentile，就是50%小于，50%大于。
 * 分析如何处理数据从而得到percentile。（这里假设了已经收集了大量数据，但是我们仍然就大量采集数据的系统环境进行了分析。
 * 最终还是回归到已经收集好的情况下）假设数据量大到memory无法存下，怎么处理？不考察系统设计，只根据实际需求在数据和算法层面进行优化
 *
 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=429208&ctid=483
 *
 * Solution1: bucketing
 *
 * Solution2: https://blog.superfeedr.com/streaming-percentiles/
 */
public class PercentileOfStreamingData {
}
