package kh.com.psnd.ui.fragment;

import android.content.Context;
import android.view.View;

import androidx.annotation.Nullable;

import core.lib.dialog.BaseDialog;
import core.lib.utils.Log;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import kh.com.psnd.R;
import kh.com.psnd.database.config.AppDatabase;
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
import kh.com.psnd.network.request.RequestGeneralComm_label_1;
import kh.com.psnd.network.request.RequestOfficeName_label_5;
import kh.com.psnd.network.request.RequestOfficeType_label_4;
import kh.com.psnd.network.request.RequestSearch;
import kh.com.psnd.network.request.RequestSectorName_label_7;
import kh.com.psnd.network.request.RequestSectorType_label_6;
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
import lombok.Setter;
import lombok.val;
import retrofit2.Response;

public class SearchOptionFragment extends BaseDialog<FragmentSearchOptionBinding> {

    @Setter
    private Callback callback = null;

    public SearchOptionFragment(@androidx.annotation.NonNull Context context, @NonNull Callback callback, @Nullable CompositeDisposable compositeDisposable) {
        super(context, compositeDisposable);
        this.callback = callback;
    }

//    public static SearchOptionFragment newInstance(@androidx.annotation.NonNull Callback callback) {
//        val fragment = new SearchOptionFragment();
//        fragment.setCallback(callback);
//        return fragment;
//    }

    @Override
    public void setupUI() {
        loadValueLabel_1();
        binding.btnSearch.setOnClickListener(__ -> {
            val obj1 = (GeneralComm_label_1) binding.searchSelect1.getTag();
            // val obj2 = (DepartmentType_label_2) binding.searchSelect2.getTag();
            val obj3 = (Department_label_3) binding.searchSelect3.getTag();
            val obj4 = (OfficeType_label_4) binding.searchSelect4.getTag();
            val obj5 = (OfficeName_label_5) binding.searchSelect5.getTag();
            // val obj6 = (SectorType_label_6) binding.searchSelect6.getTag();
            val obj7 = (SectorName_label_7) binding.searchSelect7.getTag();

            if (obj1 != null && obj3 != null && obj4 != null && obj5 != null) {
                val filter = new RequestSearch.Filter();
                filter.setGeneralId(obj1.getId());
                filter.setGeneralName(obj1.getName());

                filter.setDepartmentId(obj3.getDepartmentId());
                filter.setDepartmentName(obj3.getDepartmentName());

                filter.setOfficeId(obj5.getOfficeId());
                filter.setOfficeName(obj5.getOfficeName());

                if (obj7 != null) {
                    filter.setSectorId(obj7.getSectorId());
                    filter.setSectorName(obj7.getSectorName());
                }
                callback.onSearch(filter);
                dismiss();
            }
        });
    }

    private void loadValueLabel_1() {
        binding.progressBar.setVisibility(View.VISIBLE);
        preloadResult(binding.searchSelect1);
        val task = new TaskGeneralComm_label_1();
        getCompositeDisposable().add(task.start(task.new SimpleObserver() {

            public Class<?> clazzResponse() {
                return null;
            }

            @Override
            public void onReceiveResult(@NonNull RequestGeneralComm_label_1 request, @NonNull Response result) throws Exception {
                if (result.isSuccessful()) {
                    val data = (ResponseGeneralComm_Label_1) result.body();
                    AppDatabase.getInstance().generalCommDao_label_1().insertAll(data.getResultArrays());
                }
            }

            @Override
            public void onNext(@NonNull Response result) {
                Log.i("LOG >> onNext >> result : " + result);
                if (result.isSuccessful()) {
                    val data = (ResponseGeneralComm_Label_1) result.body();
                    binding.searchSelect1.setupUI(data.getResult(), dropdownCallback);
                    showSelectedResult(binding.searchSelect1);
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
        preloadResult(binding.searchSelect2);
        val request = new RequestDepartmentType_label_2(generalComm.getId());
        val task    = new TaskDepartmentType_label_2(request);
        getCompositeDisposable().add(task.start(task.new SimpleObserver() {

            public Class<?> clazzResponse() {
                return null;
            }

            @Override
            public void onReceiveResult(@NonNull RequestDepartmentType_label_2 request, @NonNull Response result) throws Exception {
                if (result.isSuccessful()) {
                    val data = (ResponseDepartmentType_Label_2) result.body();
                    AppDatabase.getInstance().departmentTypeDao_label_2().insertAll(data.getResultArrays());
                }
            }

            @Override
            public void onNext(@NonNull Response result) {
                Log.i("LOG >> onNext >> result : " + result);
                if (result.isSuccessful()) {
                    val data = (ResponseDepartmentType_Label_2) result.body();
                    binding.searchSelect2.setupUI(data.getResult(), dropdownCallback);
                    showSelectedResult(binding.searchSelect2);
//                    if (data.getResult().size() == 1) {
//                        binding.searchSelect2.selectItem(0);
//                    }
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
        preloadResult(binding.searchSelect3);
        val request = new RequestDepartment_label_3(generalComm.getId(), departmentType.getDepartmentTypeId());
        val task    = new TaskDepartment_label_3(request);
        getCompositeDisposable().add(task.start(task.new SimpleObserver() {

            public Class<?> clazzResponse() {
                return null;
            }

            @Override
            public void onReceiveResult(@NonNull RequestDepartment_label_3 request, @NonNull Response result) throws Exception {
                if (result.isSuccessful()) {
                    val data = (ResponseDepartment_Label_3) result.body();
                    AppDatabase.getInstance().departmentDao_label_3().insertAll(data.getResultArrays());
                }
            }

            @Override
            public void onNext(@NonNull Response result) {
                Log.i("LOG >> onNext >> result : " + result);
                if (result.isSuccessful()) {
                    val data = (ResponseDepartment_Label_3) result.body();
                    binding.searchSelect3.setupUI(data.getResult(), dropdownCallback);
                    showSelectedResult(binding.searchSelect3);
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
        preloadResult(binding.searchSelect4);
        val request = new RequestOfficeType_label_4(department.getDepartmentId());
        val task    = new TaskOfficeType_label_4(request);
        getCompositeDisposable().add(task.start(task.new SimpleObserver() {

            public Class<?> clazzResponse() {
                return null;
            }

            @Override
            public void onReceiveResult(@NonNull RequestOfficeType_label_4 request, @NonNull Response result) throws Exception {
                if (result.isSuccessful()) {
                    val data = (ResponseOfficeType_Label_4) result.body();
                    AppDatabase.getInstance().officeTypeDao_label_4().insertAll(data.getResultArrays());
                }
            }

            @Override
            public void onNext(@NonNull Response result) {
                Log.i("LOG >> onNext >> result : " + result);
                if (result.isSuccessful()) {
                    val data = (ResponseOfficeType_Label_4) result.body();
                    binding.searchSelect4.setupUI(data.getResult(), dropdownCallback);
                    showSelectedResult(binding.searchSelect4);
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
        preloadResult(binding.searchSelect5);
        val request = new RequestOfficeName_label_5(department.getDepartmentId(), officeType.getOfficeTypeId());
        val task    = new TaskOfficeName_label_5(request);
        getCompositeDisposable().add(task.start(task.new SimpleObserver() {

            public Class<?> clazzResponse() {
                return null;
            }

            @Override
            public void onReceiveResult(@NonNull RequestOfficeName_label_5 request, @NonNull Response result) throws Exception {
                if (result.isSuccessful()) {
                    val data = (ResponseOfficeName_Label_5) result.body();
                    AppDatabase.getInstance().officeNameDao_label_5().insertAll(data.getResultArrays());
                }
            }

            @Override
            public void onNext(@NonNull Response result) {
                Log.i("LOG >> onNext >> result : " + result);
                if (result.isSuccessful()) {
                    val data = (ResponseOfficeName_Label_5) result.body();
                    binding.searchSelect5.setupUI(data.getResult(), dropdownCallback);
                    showSelectedResult(binding.searchSelect5);
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
        preloadResult(binding.searchSelect6);
        val task = new TaskSectorType_label_6();
        getCompositeDisposable().add(task.start(task.new SimpleObserver() {

            public Class<?> clazzResponse() {
                return null;
            }

            @Override
            public void onReceiveResult(@NonNull RequestSectorType_label_6 request, @NonNull Response result) throws Exception {
                if (result.isSuccessful()) {
                    val data = (ResponseSectorType_Label_6) result.body();
                    AppDatabase.getInstance().sectorTypeDao_label_6().insertAll(data.getResultArrays());
                }
            }

            @Override
            public void onNext(@NonNull Response result) {
                Log.i("LOG >> onNext >> result : " + result);
                if (result.isSuccessful()) {
                    val data = (ResponseSectorType_Label_6) result.body();
                    binding.searchSelect6.setupUI(data.getResult(), dropdownCallback);
                    showSelectedResult(binding.searchSelect6);
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
        preloadResult(binding.searchSelect7);
        val request = new RequestSectorName_label_7(officeName.getOfficeId(), sectorType.getSectorTypeId());
        val task    = new TaskSectorName_label_7(request);
        getCompositeDisposable().add(task.start(task.new SimpleObserver() {

            public Class<?> clazzResponse() {
                return null;
            }

            @Override
            public void onReceiveResult(@NonNull RequestSectorName_label_7 request, @NonNull Response result) throws Exception {
                if (result.isSuccessful()) {
                    val data = (ResponseSectionName_Label_7) result.body();
                    AppDatabase.getInstance().sectorName_label_7().insertAll(data.getResultArrays());
                }
            }

            @Override
            public void onNext(@NonNull Response result) {
                Log.i("LOG >> onNext >> result : " + result);
                if (result.isSuccessful()) {
                    val data = (ResponseSectionName_Label_7) result.body();
                    binding.searchSelect7.setupUI(data.getResult(), dropdownCallback);
                    showSelectedResult(binding.searchSelect7);
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

    private AutoCompleteDropdownView.Callback dropdownCallback = new AutoCompleteDropdownView.Callback() {
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

    private void preloadResult(View selectedView) {
        binding.groupSearch.setVisibility(View.INVISIBLE);
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

            binding.searchSelect2.setVisibility(View.INVISIBLE);
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
            binding.searchSelect3.setVisibility(View.INVISIBLE);
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
            binding.searchSelect4.setVisibility(View.INVISIBLE);
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
            binding.searchSelect5.setVisibility(View.INVISIBLE);
            binding.searchSelect6.setVisibility(View.INVISIBLE);
            binding.searchSelect7.setVisibility(View.INVISIBLE);
        }
        else if (binding.searchSelect6 == selectedView) {
            binding.searchSelect7.setTag(null);

            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.VISIBLE);
            binding.searchSelect4.setVisibility(View.VISIBLE);
            binding.searchSelect5.setVisibility(View.VISIBLE);
            binding.searchSelect6.setVisibility(View.INVISIBLE);
            binding.searchSelect7.setVisibility(View.INVISIBLE);
            binding.groupSearch.setVisibility(View.VISIBLE);
        }
        else if (binding.searchSelect7 == selectedView) {
            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.VISIBLE);
            binding.searchSelect4.setVisibility(View.VISIBLE);
            binding.searchSelect5.setVisibility(View.VISIBLE);
            binding.searchSelect6.setVisibility(View.VISIBLE);
            binding.searchSelect7.setVisibility(View.INVISIBLE);
            binding.groupSearch.setVisibility(View.VISIBLE);
        }
    }

    private void showSelectedResult(View selectedView) {
        binding.groupSearch.setVisibility(View.INVISIBLE);
        if (binding.searchSelect1 == selectedView) {
            binding.searchSelect2.setVisibility(View.INVISIBLE);
            binding.searchSelect3.setVisibility(View.INVISIBLE);
            binding.searchSelect4.setVisibility(View.INVISIBLE);
            binding.searchSelect5.setVisibility(View.INVISIBLE);
            binding.searchSelect6.setVisibility(View.INVISIBLE);
            binding.searchSelect7.setVisibility(View.INVISIBLE);
        }
        else if (binding.searchSelect2 == selectedView) {
            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.INVISIBLE);
            binding.searchSelect4.setVisibility(View.INVISIBLE);
            binding.searchSelect5.setVisibility(View.INVISIBLE);
            binding.searchSelect6.setVisibility(View.INVISIBLE);
            binding.searchSelect7.setVisibility(View.INVISIBLE);
        }
        else if (binding.searchSelect3 == selectedView) {
            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.VISIBLE);
            binding.searchSelect4.setVisibility(View.INVISIBLE);
            binding.searchSelect5.setVisibility(View.INVISIBLE);
            binding.searchSelect6.setVisibility(View.INVISIBLE);
            binding.searchSelect7.setVisibility(View.INVISIBLE);
        }
        else if (binding.searchSelect4 == selectedView) {
            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.VISIBLE);
            binding.searchSelect4.setVisibility(View.VISIBLE);
            binding.searchSelect5.setVisibility(View.INVISIBLE);
            binding.searchSelect6.setVisibility(View.INVISIBLE);
            binding.searchSelect7.setVisibility(View.INVISIBLE);
        }
        else if (binding.searchSelect5 == selectedView) {
            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.VISIBLE);
            binding.searchSelect4.setVisibility(View.VISIBLE);
            binding.searchSelect5.setVisibility(View.VISIBLE);
            binding.searchSelect6.setVisibility(View.INVISIBLE);
            binding.searchSelect7.setVisibility(View.INVISIBLE);
        }
        else if (binding.searchSelect6 == selectedView) {
            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.VISIBLE);
            binding.searchSelect4.setVisibility(View.VISIBLE);
            binding.searchSelect5.setVisibility(View.VISIBLE);
            binding.searchSelect6.setVisibility(View.VISIBLE);
            binding.searchSelect7.setVisibility(View.INVISIBLE);
            binding.groupSearch.setVisibility(View.VISIBLE);
        }
        else if (binding.searchSelect7 == selectedView) {
            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.VISIBLE);
            binding.searchSelect4.setVisibility(View.VISIBLE);
            binding.searchSelect5.setVisibility(View.VISIBLE);
            binding.searchSelect6.setVisibility(View.VISIBLE);
            binding.searchSelect7.setVisibility(View.VISIBLE);
            binding.groupSearch.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_search_option;
    }

    public interface Callback {
        void onSearch(RequestSearch.Filter filter);
    }
}