package com.gaolei.mvpmodel.base.fragment;

import com.gaolei.mvpmodel.base.mpresenter.BasePresenter;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class BaseMvpFragment_MembersInjector<P extends BasePresenter>
    implements MembersInjector<BaseMvpFragment<P>> {
  private final Provider<P> mPresenterProvider;

  public BaseMvpFragment_MembersInjector(Provider<P> mPresenterProvider) {
    assert mPresenterProvider != null;
    this.mPresenterProvider = mPresenterProvider;
  }

  public static <P extends BasePresenter> MembersInjector<BaseMvpFragment<P>> create(
      Provider<P> mPresenterProvider) {
    return new BaseMvpFragment_MembersInjector<P>(mPresenterProvider);
  }

  @Override
  public void injectMembers(BaseMvpFragment<P> instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.mPresenter = mPresenterProvider.get();
  }
}
