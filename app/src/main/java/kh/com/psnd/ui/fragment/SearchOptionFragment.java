package kh.com.psnd.ui.fragment;

import android.view.View;

import core.lib.base.BaseFragment;
import core.lib.utils.Log;
import io.reactivex.annotations.NonNull;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentSearchOptionBinding;
import kh.com.psnd.network.model.DepartmentType_label_2;
import kh.com.psnd.network.model.GeneralComm_label_1;
import kh.com.psnd.network.response.ResponseDepartmentType_Label_2;
import kh.com.psnd.network.response.ResponseGeneralComm_Label_1;
import kh.com.psnd.network.task.TaskDepartmentType_label_2;
import kh.com.psnd.network.task.TaskGeneralComm_label_1;
import kh.com.psnd.ui.view.AutoCompleteDropdownView;
import lombok.val;
import retrofit2.Response;

public class SearchOptionFragment extends BaseFragment<FragmentSearchOptionBinding> {

    @Override
    public void setupUI() {
        binding.toolbar.setTitle(R.string.search_detail_label_1);
        loadValueLabel_1();
    }

    private void loadValueLabel_1() {
        binding.progressBar.setVisibility(View.VISIBLE);
        val task = new TaskGeneralComm_label_1();
        getCompositeDisposable().add(task.start(task.new SimpleObserver() {

            public Class<?> clazzResponse() {
                return null;
            }

            @Override
            public void onNext(@NonNull Response result) {
                Log.i("LOG >> onNext >> result : " + result);
                if (result.isSuccessful()) {
                    val data = (ResponseGeneralComm_Label_1) result.body();
                    binding.searchSelect1.setupUI(data.getResult(), callback);
                }
                binding.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(e);
                binding.progressBar.setVisibility(View.GONE);
            }
        }));
    }

    private void loadValueLabel_2(GeneralComm_label_1 generalComm) {
        binding.progressBar.setVisibility(View.VISIBLE);
        val task = new TaskDepartmentType_label_2();
        getCompositeDisposable().add(task.start(task.new SimpleObserver() {

            public Class<?> clazzResponse() {
                return null;
            }

            @Override
            public void onNext(@NonNull Response result) {
                Log.i("LOG >> onNext >> result : " + result);
                if (result.isSuccessful()) {
                    val data = (ResponseDepartmentType_Label_2) result.body();
                    binding.searchSelect2.setupUI(data.getResult(), callback);
                    showSelection(binding.searchSelect2);
                }
                binding.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(e);
                binding.progressBar.setVisibility(View.GONE);
            }
        }));
    }

    private AutoCompleteDropdownView.Callback callback = new AutoCompleteDropdownView.Callback() {
        @Override
        public void onSelected(Object object) {
            Log.i(object.getClass());
            if (object instanceof GeneralComm_label_1) {
                val item = (GeneralComm_label_1) object;
                loadValueLabel_2(item);
            }
            else if (object instanceof DepartmentType_label_2) {
                val item = (DepartmentType_label_2) object;
            }
        }
    };

    private void showSelection(View selectedView) {
        if (binding.searchSelect1 == selectedView) {
            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.GONE);
            binding.searchSelect4.setVisibility(View.GONE);
            binding.searchSelect5.setVisibility(View.GONE);
            binding.searchSelect6.setVisibility(View.GONE);
            binding.searchSelect7.setVisibility(View.GONE);
            binding.searchSelect8.setVisibility(View.GONE);
            binding.searchSelect9.setVisibility(View.GONE);
            binding.searchSelect10.setVisibility(View.GONE);
        }
        else if (binding.searchSelect2 == selectedView) {
            binding.searchSelect3.setVisibility(View.VISIBLE);
            binding.searchSelect4.setVisibility(View.GONE);
            binding.searchSelect5.setVisibility(View.GONE);
            binding.searchSelect6.setVisibility(View.GONE);
            binding.searchSelect7.setVisibility(View.GONE);
            binding.searchSelect8.setVisibility(View.GONE);
            binding.searchSelect9.setVisibility(View.GONE);
            binding.searchSelect10.setVisibility(View.GONE);
        }
        else if (binding.searchSelect3 == selectedView) {
            binding.searchSelect4.setVisibility(View.VISIBLE);
            binding.searchSelect5.setVisibility(View.GONE);
            binding.searchSelect6.setVisibility(View.GONE);
            binding.searchSelect7.setVisibility(View.GONE);
            binding.searchSelect8.setVisibility(View.GONE);
            binding.searchSelect9.setVisibility(View.GONE);
            binding.searchSelect10.setVisibility(View.GONE);
        }
        else if (binding.searchSelect4 == selectedView) {
            binding.searchSelect5.setVisibility(View.VISIBLE);
            binding.searchSelect6.setVisibility(View.GONE);
            binding.searchSelect7.setVisibility(View.GONE);
            binding.searchSelect8.setVisibility(View.GONE);
            binding.searchSelect9.setVisibility(View.GONE);
            binding.searchSelect10.setVisibility(View.GONE);
        }
        else if (binding.searchSelect5 == selectedView) {
            binding.searchSelect6.setVisibility(View.VISIBLE);
            binding.searchSelect7.setVisibility(View.GONE);
            binding.searchSelect8.setVisibility(View.GONE);
            binding.searchSelect9.setVisibility(View.GONE);
            binding.searchSelect10.setVisibility(View.GONE);
        }
        else if (binding.searchSelect6 == selectedView) {
            binding.searchSelect7.setVisibility(View.VISIBLE);
            binding.searchSelect8.setVisibility(View.GONE);
            binding.searchSelect9.setVisibility(View.GONE);
            binding.searchSelect10.setVisibility(View.GONE);
        }
        else if (binding.searchSelect7 == selectedView) {
            binding.searchSelect8.setVisibility(View.VISIBLE);
            binding.searchSelect9.setVisibility(View.GONE);
            binding.searchSelect10.setVisibility(View.GONE);
        }
        else if (binding.searchSelect8 == selectedView) {
            binding.searchSelect9.setVisibility(View.VISIBLE);
            binding.searchSelect10.setVisibility(View.GONE);
        }
        else if (binding.searchSelect9 == selectedView) {
            binding.searchSelect10.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_search_option;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}