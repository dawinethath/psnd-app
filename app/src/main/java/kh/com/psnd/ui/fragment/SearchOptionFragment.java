package kh.com.psnd.ui.fragment;

import android.view.View;

import core.lib.base.BaseFragment;
import core.lib.utils.Log;
import io.reactivex.annotations.NonNull;
import kh.com.psnd.R;
import kh.com.psnd.databinding.FragmentSearchOptionBinding;
import kh.com.psnd.network.model.DepartmentType_label_2;
import kh.com.psnd.network.model.Department_label_3;
import kh.com.psnd.network.model.GeneralComm_label_1;
import kh.com.psnd.network.model.OfficeName_label_5;
import kh.com.psnd.network.model.OfficeType_label_4;
import kh.com.psnd.network.model.SectorName_label_7;
import kh.com.psnd.network.model.SectorType_label_6;
import kh.com.psnd.network.request.RequestDepartmentType_label_2;
import kh.com.psnd.network.request.RequestDepartment_label_3;
import kh.com.psnd.network.request.RequestOfficeName_label_5;
import kh.com.psnd.network.request.RequestOfficeType_label_4;
import kh.com.psnd.network.request.RequestSectorName_label_7;
import kh.com.psnd.network.response.ResponseDepartmentType_Label_2;
import kh.com.psnd.network.response.ResponseDepartment_Label_3;
import kh.com.psnd.network.response.ResponseGeneralComm_Label_1;
import kh.com.psnd.network.response.ResponseOfficeName_Label_5;
import kh.com.psnd.network.response.ResponseOfficeType_Label_4;
import kh.com.psnd.network.response.ResponseSectionName_Label_7;
import kh.com.psnd.network.response.ResponseSectorType_Label_6;
import kh.com.psnd.network.task.TaskDepartmentType_label_2;
import kh.com.psnd.network.task.TaskDepartment_label_3;
import kh.com.psnd.network.task.TaskGeneralComm_label_1;
import kh.com.psnd.network.task.TaskOfficeName_label_5;
import kh.com.psnd.network.task.TaskOfficeType_label_4;
import kh.com.psnd.network.task.TaskSectorName_label_7;
import kh.com.psnd.network.task.TaskSectorType_label_6;
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
                    showSelected(binding.searchSelect1);
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
        val request = new RequestDepartmentType_label_2(generalComm.getId());
        val task    = new TaskDepartmentType_label_2(request);
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
                    showSelected(binding.searchSelect2);
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

    private void loadValueLabel_3(GeneralComm_label_1 generalComm, DepartmentType_label_2 departmentType) {
        binding.progressBar.setVisibility(View.VISIBLE);
        val request = new RequestDepartment_label_3(generalComm.getId(), departmentType.getDepartmentTypeId());
        val task    = new TaskDepartment_label_3(request);
        getCompositeDisposable().add(task.start(task.new SimpleObserver() {

            public Class<?> clazzResponse() {
                return null;
            }

            @Override
            public void onNext(@NonNull Response result) {
                Log.i("LOG >> onNext >> result : " + result);
                if (result.isSuccessful()) {
                    val data = (ResponseDepartment_Label_3) result.body();
                    binding.searchSelect3.setupUI(data.getResult(), callback);
                    showSelected(binding.searchSelect3);
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

    private void loadValueLabel_4(Department_label_3 department) {
        binding.progressBar.setVisibility(View.VISIBLE);
        val request = new RequestOfficeType_label_4(department.getDepartmentId());
        val task    = new TaskOfficeType_label_4(request);
        getCompositeDisposable().add(task.start(task.new SimpleObserver() {

            public Class<?> clazzResponse() {
                return null;
            }

            @Override
            public void onNext(@NonNull Response result) {
                Log.i("LOG >> onNext >> result : " + result);
                if (result.isSuccessful()) {
                    val data = (ResponseOfficeType_Label_4) result.body();
                    binding.searchSelect4.setupUI(data.getResult(), callback);
                    showSelected(binding.searchSelect4);
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

    private void loadValueLabel_5(Department_label_3 department, OfficeType_label_4 officeType) {
        binding.progressBar.setVisibility(View.VISIBLE);
        val request = new RequestOfficeName_label_5(department.getDepartmentId(), officeType.getOfficeTypeId());
        val task    = new TaskOfficeName_label_5(request);
        getCompositeDisposable().add(task.start(task.new SimpleObserver() {

            public Class<?> clazzResponse() {
                return null;
            }

            @Override
            public void onNext(@NonNull Response result) {
                Log.i("LOG >> onNext >> result : " + result);
                if (result.isSuccessful()) {
                    val data = (ResponseOfficeName_Label_5) result.body();
                    binding.searchSelect5.setupUI(data.getResult(), callback);
                    showSelected(binding.searchSelect5);
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

    private void loadValueLabel_6() {
        binding.progressBar.setVisibility(View.VISIBLE);
        val task = new TaskSectorType_label_6();
        getCompositeDisposable().add(task.start(task.new SimpleObserver() {

            public Class<?> clazzResponse() {
                return null;
            }

            @Override
            public void onNext(@NonNull Response result) {
                Log.i("LOG >> onNext >> result : " + result);
                if (result.isSuccessful()) {
                    val data = (ResponseSectorType_Label_6) result.body();
                    binding.searchSelect6.setupUI(data.getResult(), callback);
                    showSelected(binding.searchSelect6);
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

    private void loadValueLabel_7(OfficeName_label_5 officeName, SectorType_label_6 sectorType) {
        binding.progressBar.setVisibility(View.VISIBLE);
        val request = new RequestSectorName_label_7(officeName.getOfficeId(), sectorType.getSectorTypeId());
        val task    = new TaskSectorName_label_7(request);
        getCompositeDisposable().add(task.start(task.new SimpleObserver() {

            public Class<?> clazzResponse() {
                return null;
            }

            @Override
            public void onNext(@NonNull Response result) {
                Log.i("LOG >> onNext >> result : " + result);
                if (result.isSuccessful()) {
                    val data = (ResponseSectionName_Label_7) result.body();
                    binding.searchSelect7.setupUI(data.getResult(), callback);
                    showSelected(binding.searchSelect7);
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
                binding.searchSelect1.setTag(object);
                val item = (GeneralComm_label_1) object;
                loadValueLabel_2(item);
            }
            else if (object instanceof DepartmentType_label_2) {
                binding.searchSelect2.setTag(object);
                val item        = (DepartmentType_label_2) object;
                val generalComm = (GeneralComm_label_1) binding.searchSelect1.getTag();
                loadValueLabel_3(generalComm, item);
            }
            else if (object instanceof Department_label_3) {
                binding.searchSelect3.setTag(object);
                val item = (Department_label_3) object;
                loadValueLabel_4(item);
            }
            else if (object instanceof OfficeType_label_4) {
                binding.searchSelect4.setTag(object);
                val item       = (OfficeType_label_4) object;
                val department = (Department_label_3) binding.searchSelect3.getTag();
                loadValueLabel_5(department, item);
            }
            else if (object instanceof OfficeName_label_5) {
                binding.searchSelect5.setTag(object);
                val item = (OfficeName_label_5) object;
                loadValueLabel_6();
            }
            else if (object instanceof SectorType_label_6) {
                binding.searchSelect6.setTag(object);
                val item       = (SectorType_label_6) object;
                val officeName = (OfficeName_label_5) binding.searchSelect5.getTag();
                loadValueLabel_7(officeName, item);
            }
            else if (object instanceof SectorName_label_7) {
                binding.searchSelect7.setTag(object);
            }
        }
    };

    private void showSelected(View selectedView) {
        if (binding.searchSelect1 == selectedView) {
            binding.searchSelect2.setTag(null);
            binding.searchSelect3.setTag(null);
            binding.searchSelect4.setTag(null);
            binding.searchSelect5.setTag(null);
            binding.searchSelect6.setTag(null);
            binding.searchSelect7.setTag(null);

            binding.searchSelect2.setVisibility(View.INVISIBLE);
            binding.searchSelect3.setVisibility(View.INVISIBLE);
            binding.searchSelect4.setVisibility(View.INVISIBLE);
            binding.searchSelect5.setVisibility(View.INVISIBLE);
            binding.searchSelect6.setVisibility(View.INVISIBLE);
            binding.searchSelect7.setVisibility(View.INVISIBLE);
        }
        else if (binding.searchSelect2 == selectedView) {
            binding.searchSelect3.setTag(null);
            binding.searchSelect4.setTag(null);
            binding.searchSelect5.setTag(null);
            binding.searchSelect6.setTag(null);
            binding.searchSelect7.setTag(null);

            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.INVISIBLE);
            binding.searchSelect4.setVisibility(View.INVISIBLE);
            binding.searchSelect5.setVisibility(View.INVISIBLE);
            binding.searchSelect6.setVisibility(View.INVISIBLE);
            binding.searchSelect7.setVisibility(View.INVISIBLE);
        }
        else if (binding.searchSelect3 == selectedView) {
            binding.searchSelect4.setTag(null);
            binding.searchSelect5.setTag(null);
            binding.searchSelect6.setTag(null);
            binding.searchSelect7.setTag(null);

            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.VISIBLE);
            binding.searchSelect4.setVisibility(View.INVISIBLE);
            binding.searchSelect5.setVisibility(View.INVISIBLE);
            binding.searchSelect6.setVisibility(View.INVISIBLE);
            binding.searchSelect7.setVisibility(View.INVISIBLE);
        }
        else if (binding.searchSelect4 == selectedView) {
            binding.searchSelect5.setTag(null);
            binding.searchSelect6.setTag(null);
            binding.searchSelect7.setTag(null);

            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.VISIBLE);
            binding.searchSelect4.setVisibility(View.VISIBLE);
            binding.searchSelect5.setVisibility(View.INVISIBLE);
            binding.searchSelect6.setVisibility(View.INVISIBLE);
            binding.searchSelect7.setVisibility(View.INVISIBLE);
        }
        else if (binding.searchSelect5 == selectedView) {
            binding.searchSelect6.setTag(null);
            binding.searchSelect7.setTag(null);

            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.VISIBLE);
            binding.searchSelect4.setVisibility(View.VISIBLE);
            binding.searchSelect5.setVisibility(View.VISIBLE);
            binding.searchSelect6.setVisibility(View.INVISIBLE);
            binding.searchSelect7.setVisibility(View.INVISIBLE);
        }
        else if (binding.searchSelect6 == selectedView) {
            binding.searchSelect7.setTag(null);

            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.VISIBLE);
            binding.searchSelect4.setVisibility(View.VISIBLE);
            binding.searchSelect5.setVisibility(View.VISIBLE);
            binding.searchSelect6.setVisibility(View.VISIBLE);
            binding.searchSelect7.setVisibility(View.INVISIBLE);
        }
        else if (binding.searchSelect7 == selectedView) {
            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.VISIBLE);
            binding.searchSelect4.setVisibility(View.VISIBLE);
            binding.searchSelect5.setVisibility(View.VISIBLE);
            binding.searchSelect6.setVisibility(View.VISIBLE);
            binding.searchSelect7.setVisibility(View.VISIBLE);
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