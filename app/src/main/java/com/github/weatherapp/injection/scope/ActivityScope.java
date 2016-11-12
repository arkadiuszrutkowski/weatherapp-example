package com.github.weatherapp.injection.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Retention(RetentionPolicy.SOURCE)
@Scope
public @interface ActivityScope {
}
