package net.gahfy.lazada500px.injection.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Activity scope for Dagger
 * @author Gaëtan HERFRAY
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {

}
