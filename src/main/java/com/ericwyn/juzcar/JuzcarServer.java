package com.ericwyn.juzcar;

import com.ericwyn.ezerver.expection.WebServerException;
import com.ericwyn.juzcar.scan.ScannerUtils;
import com.ericwyn.juzcar.scan.obj.JuzcarApi;
import com.ericwyn.juzcar.scan.obj.JuzcarClass;
import com.ericwyn.juzcar.scan.obj.JuzcarMethod;
import com.ericwyn.juzcar.server.JuzcarDocServer;

import java.util.HashMap;
import java.util.List;

/**
 *
 * Juzcar 服务入口类
 *      扫描接口
 *      生成 config.json
 *      生成配置文件
 *
 * Created by Ericwyn on 18-11-26.
 */
public class JuzcarServer {
    private static Class initClass;

    private static Runnable juzcarRunnable = new Runnable() {
        @Override
        public void run() {
            // scan 模块
            List<JuzcarClass> juzcarClasses = ScannerUtils.scannerAllController(JuzcarServer.initClass);
            // 确定类当中哪些是真的要扫描的（去掉被 Ignore 注解的类）
            ScannerUtils.removeTheIgnoreController(juzcarClasses);
            // 以 Controller 名称分组，扫描其中的方法
            HashMap<String, List<JuzcarMethod>> juzcarMethodMap = ScannerUtils.scannerMethods(juzcarClasses);
            // 针对方法扫描出具体的 API
            HashMap<String, List<JuzcarApi>> apis = ScannerUtils.scannerAPI(juzcarMethodMap);
            // server 模块
            try {
                new JuzcarDocServer(apis).startServer();
            } catch (WebServerException e) {
                e.printStackTrace();
            }
            System.out.println(juzcarClasses.size());

            // TODO server模块，静态页面存储问题
            // TODO Json 模块导入问题
            // TODO 对 Controller 和 RestController 的分别判断问题
            // TODO 对 RequestBody 和 ResponseBody 的支持
            // TODO 如果交流的方式并非 JSON 而是 XML ？（暂时不考虑支持 xml 了
        }
    };

    public static void run(Class initClass){
        JuzcarServer.initClass = initClass;
        new Thread(juzcarRunnable).run();
    }
}
