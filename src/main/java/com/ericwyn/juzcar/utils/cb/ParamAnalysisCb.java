package com.ericwyn.juzcar.utils.cb;

import com.ericwyn.juzcar.utils.obj.JuzcarParam;

import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;

/**
 * Created by Ericwyn on 18-11-26.
 */
public interface ParamAnalysisCb {
    public JuzcarParam analysis(Parameter parameter, Annotation annotation);
}