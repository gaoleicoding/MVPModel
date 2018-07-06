package com.gaolei.mvpmodel.thirdframe.dagger2.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author quchao
 * @date 2018/2/13
 */

@Scope
@Retention(RUNTIME)
public @interface ActivityScope {
}
