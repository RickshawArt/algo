package Stack.java.search_16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 快速定位出一个 IP 地址的归属地
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/6/12 15:04
 */
public class LocateIpAttribution {

    /**
     * IP区间与归属地的对应关系
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
     * 根据IP地址获取对应的归属地
     * @param ipAddress IP地址
     * @return java.lang.String 归属地
     * @author Rickshaw
     * @since 2023/6/12 17:22
     */
    public String getAttribution(String ipAddress) {
        //IP区间头部的集合
        List<String> ipHeaderList = new ArrayList<>(IP_ADDRESS_LIBRARY.size());
        IP_ADDRESS_LIBRARY.keySet().forEach((key) -> {
            // TODO: 2023/6/12
        });
        return null;
    }

    /**
     * 把IP地址转化为二进制的长整形
     * @param ipAddress  IP地址
     * @return long 十进制长整形
     * @author Rickshaw
     * @since 2023/6/12 15:46
     */
    public long ipToLong(String ipAddress) {
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

    }

}
