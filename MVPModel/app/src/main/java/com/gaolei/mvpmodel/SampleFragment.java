//package com.gaolei.mvpmodel;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//public class SampleFragment extends BaseFragment implements SampleContract.View{
//
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    private String mParam1;
//    private String mParam2;
//
//    private SampleContract.HomePresenter mPresenter;
//
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment SampleFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static SampleFragment newInstance(String param1, String param2) {
//        SampleFragment fragment = new SampleFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        TextView textView = new TextView(getActivity());
//        textView.setText(R.string.hello_blank_fragment);
//        return textView;
//    }
//
//    @Override
//    public void setPresenter(SampleContract.Presenter presenter) {
//        mPresenter = presenter;
//    }
//
//    @Override
//    public void showLoading() {
//
//    }
//
//    @Override
//    public void refreshUI(MessageListEntity.CategoryData data) {
//        //change UI
//    }
//
//    @Override
//    public void showError() {
//        //change UI
//    }
//}
//
//所有。商业转载请联系作者获得授权，非商业转载请注明出处。