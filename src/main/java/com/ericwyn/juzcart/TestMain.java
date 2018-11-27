package com.ericwyn.juzcart;

import com.ericwyn.juzcar.JuzcarServer;
import com.ericwyn.juzcar.scan.cb.JuzcarScannerCb;
import com.ericwyn.juzcar.scan.obj.JuzcarApi;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Ericwyn on 18-11-26.
 */
public class TestMain {
    public static void main(String[] args) {
        JuzcarServer.run(JuzcartApplication.class, new JuzcarScannerCb() {
            @Override
            public void callback(HashMap<String, List<JuzcarApi>> apis) {
                System.out.println(apis.size());
            }
        });
    }

}
