package com.dream.interview.study.jvm.oom;

/**
 * Created by huzejun
 * on 2020/6/19 15:41
 */
public class JavaHeapSpaceDemo {

    public static void main(String[] args) {
        /*String str = "dream";

        while (true){
            str += str + new Random().nextInt(111111)+ new Random(2222222);
            str.intern();
        }
*/
        byte[] bytes = new byte[80 * 1024 * 1024];  //80MB


        // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
    }
}
