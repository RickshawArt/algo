package Stack.java.search_16;

import Stack.java.service.SortAlgo;
import Stack.java.sorts_11.practice.MyInsertionSort;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 快速定位出一个 ip 地址的归属地
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/6/12 15:04
 */
public class LocateIpAttribution {

    /**
     * 排序算法
     */
    private final SortAlgo sortAlgo;

    public LocateIpAttribution(SortAlgo sortAlgo) {
        this.sortAlgo = sortAlgo;
    }

    /**
     * ip 区间与归属地的对应关系
     */
    private static final Map<String, String> IP_ADDRESS_LIBRARY = new HashMap<>();

    static {
        IP_ADDRESS_LIBRARY.putIfAbsent("[202.102.133.0, 202.102.133.255]", "山东东营");
        IP_ADDRESS_LIBRARY.putIfAbsent("[202.102.135.0, 202.102.136.255]", "山东烟台");
        IP_ADDRESS_LIBRARY.putIfAbsent("[202.102.156.34, 202.102.157.255]", "山东青岛");
        IP_ADDRESS_LIBRARY.putIfAbsent("[202.102.48.0, 202.102.48.255]", "江苏宿迁");
        IP_ADDRESS_LIBRARY.putIfAbsent("[202.102.49.15, 202.102.51.251]", "江苏泰州");
        IP_ADDRESS_LIBRARY.putIfAbsent("[202.102.56.0, 202.102.56.255]", "江苏连云港");
    }

    /**
     * 根据 ip地址获取对应的归属地
     * @param ipAddress ip地址
     * @return java.lang.String 归属地
     * @author Rickshaw
     * @since 2023/6/12 17:22
     */
    public String getAttribution(String ipAddress) {
        long ipToLong = this.ipToLong(ipAddress);
        long[] ipHeaderArr = this.getIpHeaderArr();
        this.sortAlgo.sort(ipHeaderArr);
        //通过二分法找到最后一个 < ipToLong的元素
        int index = searchIp(ipHeaderArr, ipToLong);
        if (index == -1) {
            throw new RuntimeException("没有找到对应的归属地");
        }
        long targetIp = ipHeaderArr[index];
        //通过地址库找到对应的归属地并返回
        Map.Entry<String, String> mapEntry = IP_ADDRESS_LIBRARY.entrySet().stream().filter((entry) -> {
            String key = entry.getKey();
            long headerIp = ipToLong(key.substring(1, key.lastIndexOf(",")));
            long tailIp = ipToLong(key.substring(key.lastIndexOf(",") + 2, key.length() - 1));
            //要查找的 ip 在区间内，才算找到
            return targetIp == headerIp && ipToLong <= tailIp;
        }).findFirst().orElseThrow(() -> new RuntimeException("没有找到对应的归属地"));
        return mapEntry.getValue();
    }

    /**
     * 把十进制的长整形转化为  ip地址
     * @param ip 十进制的长整形
     * @return java.lang.String
     * @author Rickshaw
     * @since 2023/6/13 9:11
     */
    private String longToIp(long ip) {
        long part1 = ip >> 24 & 0xFF;
        long part2 = ip >> 16 & 0xFF;
        long part3 = ip >> 8 & 0xFF;
        long part4 = ip & 0xFF;
        return part1 + "." + part2 + "." + part3 + "." + part4;
    }

    /**
     * 通过二分法查找最后一个 < val 的元素
     * @param arr   有序数组
     * @param val   要查找的元素
     * @return int  数组索引
     * @author Rickshaw
     * @since 2023/6/13 8:48
     */
    private int searchIp(long[] arr, long val) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low >> 1);
            if (arr[mid] <= val) {
                if (mid == arr.length - 1 || arr[mid + 1] > val) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 根据地址库取出头区间的 ip转换成长整型放进数组
     * @return long[]
     * @author Rickshaw
     * @since 2023/6/12 23:18
     */
    private long[] getIpHeaderArr() {
        long[] ipHeaderArr = new long[IP_ADDRESS_LIBRARY.size()];
        AtomicInteger i = new AtomicInteger();
        IP_ADDRESS_LIBRARY.keySet().forEach((key) -> {
            String ipHeader = key.substring(1, key.lastIndexOf(","));
            ipHeaderArr[i.getAndIncrement()] = this.ipToLong(ipHeader);
        });
        return ipHeaderArr;
    }

    /**
     * 把 ip地址转化为十进制的长整形
     * @param ipAddress  ip地址
     * @return long 十进制长整形
     * @author Rickshaw
     * @since 2023/6/12 15:46
     */
    private long ipToLong(String ipAddress) {
        String[] parts = ipAddress.split("\\.");
        long ret = 0L;
        for (String part : parts) {
            long cal = Long.parseLong(part);
            //将每个部分的二进制值左移 8 位并进行位或操作(二进制位或运算，有1的都算1：可以把下一个低八位的二进制全部复制过来)
            ret = ret << 8 | cal;
        }
        return ret;
    }

    public static void main(String[] args) {
        LocateIpAttribution locateIpAttribution = new LocateIpAttribution(new MyInsertionSort());
        String attribution = locateIpAttribution.getAttribution("202.102.111.20");
        System.out.println("attribution = " + attribution);
    }

}
