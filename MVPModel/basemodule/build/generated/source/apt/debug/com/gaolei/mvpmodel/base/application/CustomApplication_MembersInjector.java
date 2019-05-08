package com.gaolei.mvpmodel.base.application;

import android.app.Activity;
import android.support.v4.app.Fragment;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class CustomApplication_MembersInjector implements MembersInjector<CustomApplication> {
  private final Provider<DispatchingAndroidInjector<Activity>> mDispatchingAndroidInjectorProvider;

  private final Provider<DispatchingAndroidInjector<Fragment>>
      mFragmentDispatchingAndroidInjectorProvider;

  public CustomApplication_MembersInjector(
      Provider<DispatchingAndroidInjector<Activity>> mDispatchingAndroidInjectorProvider,
      Provider<DispatchingAndroidInjector<Fragment>> mFragmentDispatchingAndroidInjectorProvider) {
    assert mDispatchingAndroidInjectorProvider != null;
    this.mDispatchingAndroidInjectorProvider = mDispatchingAndroidInjectorProvider;
    assert mFragmentDispatchingAndroidInjectorProvider != null;
    this.mFragmentDispatchingAndroidInjectorProvider = mFragmentDispatchingAndroidInjectorProvider;
  }

  public static MembersInjector<CustomApplication> create(
      Provider<DispatchingAndroidInjector<Activity>> mDispatchingAndroidInjectorProvider,
      Provider<DispatchingAndroidInjector<Fragment>> mFragmentDispatchingAndroidInjectorProvider) {
    return new CustomApplication_MembersInjector(
        mDispatchingAndroidInjectorProvider, mFragmentDispatchingAndroidInjectorProvider);
  }

  @Override
  public void injectMembers(CustomApplication instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.mDispatchingAndroidInjector = mDispatchingAndroidInjectorProvider.get();
    instance.mFragmentDispatchingAndroidInjector =
        mFragmentDispatchingAndroidInjectorProvider.get();
  }

  public static void injectMDispatchingAndroidInjector(
      CustomApplication instance,
      Provider<DispatchingAndroidInjector<Activity>> mDispatchingAndroidInjectorProvider) {
    instance.mDispatchingAndroidInjector = mDispatchingAndroidInjectorProvider.get();
  }

  public static void injectMFragmentDispatchingAndroidInjector(
      CustomApplication instance,
      Provider<DispatchingAndroidInjector<Fragment>> mFragmentDispatchingAndroidInjectorProvider) {
    instance.mFragmentDispatchingAndroidInjector =
        mFragmentDispatchingAndroidInjectorProvider.get();
  }
}
