package kh.com.psnd.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.text.InputType;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.List;

import core.lib.dialog.BaseDialog;
import core.lib.utils.ApplicationUtil;
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
import kh.com.psnd.network.model.Position_label_9;
import kh.com.psnd.network.model.Rank_label_8;
import kh.com.psnd.network.model.StaffFilter;
import kh.com.psnd.network.model.SectorName_label_7;
import kh.com.psnd.network.model.SectorType_label_6;
import kh.com.psnd.network.request.RequestDepartmentType_label_2;
import kh.com.psnd.network.request.RequestDepartment_label_3;
import kh.com.psnd.network.request.RequestGeneralComm_label_1;
import kh.com.psnd.network.request.RequestOfficeName_label_5;
import kh.com.psnd.network.request.RequestOfficeType_label_4;
import kh.com.psnd.network.request.RequestPosition_label_9;
import kh.com.psnd.network.request.RequestRank_label_8;
import kh.com.psnd.network.request.RequestSectorName_label_7;
import kh.com.psnd.network.request.RequestSectorType_label_6;
import kh.com.psnd.network.response.ResponseDepartmentType_Label_2;
import kh.com.psnd.network.response.ResponseDepartment_Label_3;
import kh.com.psnd.network.response.ResponseGeneralComm_Label_1;
import kh.com.psnd.network.response.ResponseOfficeName_Label_5;
import kh.com.psnd.network.response.ResponseOfficeType_Label_4;
import kh.com.psnd.network.response.ResponsePosition_Label_9;
import kh.com.psnd.network.response.ResponseRank_Label_8;
import kh.com.psnd.network.response.ResponseSectionName_Label_7;
import kh.com.psnd.network.response.ResponseSectorType_Label_6;
import kh.com.psnd.network.task.TaskDepartmentType_label_2;
import kh.com.psnd.network.task.TaskDepartment_label_3;
import kh.com.psnd.network.task.TaskGeneralComm_label_1;
import kh.com.psnd.network.task.TaskOfficeName_label_5;
import kh.com.psnd.network.task.TaskOfficeType_label_4;
import kh.com.psnd.network.task.TaskPosition_label_9;
import kh.com.psnd.network.task.TaskRank_label_8;
import kh.com.psnd.network.task.TaskSectorName_label_7;
import kh.com.psnd.network.task.TaskSectorType_label_6;
import kh.com.psnd.ui.view.AutoCompleteDropdownView;
import lombok.Setter;
import lombok.val;
import retrofit2.Response;

public class SearchOptionFragment extends BaseDialog<FragmentSearchOptionBinding> {

    @Setter
    private Callback callback      = null;
    private boolean  showFirstTime = true;

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
    public void dismiss() {
        binding.progressBar.setVisibility(View.GONE);
        super.dismiss();
    }

    public void show(StaffFilter filter) {
        checkLastFilter(filter);
        super.show();
    }

    @Override
    public void show() {
        if (showFirstTime) {
            checkLastFilter();
        }
        showFirstTime = false;
        super.show();
    }

    @Override
    public void setupUI() {
        loadValueLabel_1();
        binding.searchSelect9.setInputType(InputType.TYPE_CLASS_TEXT);
        binding.btnSearch.setOnClickListener(__ -> {
            val obj1 = (GeneralComm_label_1) binding.searchSelect1.getTag();
            val obj2 = (DepartmentType_label_2) binding.searchSelect2.getTag();
            val obj3 = (Department_label_3) binding.searchSelect3.getTag();
            val obj4 = (OfficeType_label_4) binding.searchSelect4.getTag();
            val obj5 = (OfficeName_label_5) binding.searchSelect5.getTag();
            val obj6 = (SectorType_label_6) binding.searchSelect6.getTag();
            val obj7 = (SectorName_label_7) binding.searchSelect7.getTag();
            val obj8 = (Rank_label_8) binding.searchSelect8.getTag();
            val obj9 = (Position_label_9) binding.searchSelect9.getTag();

            if (obj1 != null && obj2 != null && obj3 != null && obj4 != null && obj5 != null) {
                val filter = new StaffFilter();
                filter.setGeneralComm_label_1(obj1);
                filter.setDepartmentType_label_2(obj2);
                filter.setDepartment_label_3(obj3);
                filter.setOfficeType_label_4(obj4);
                filter.setOfficeName_label_5(obj5);

                if (obj6 != null) {
                    filter.setSectorType_label_6(obj6);
                }
                if (obj7 != null) {
                    filter.setSectorName_label_7(obj7);
                }
                if (obj8 != null) {
                    filter.setRank_label_8(obj8);
                }
                if (obj9 != null) {
                    filter.setPosition_label_9(obj9);
                }
                filter.saveLastFilter();
                callback.onSearch(filter);
                dismiss();
            }
        });
    }

    private void checkLastFilter() {
        val filter = StaffFilter.getLastFilter();
        if (filter != null) {
            checkLastFilter(filter);
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void checkLastFilter(@androidx.annotation.NonNull StaffFilter filter) {
        new AsyncTask<Void, Void, Void>() {

            List<GeneralComm_label_1> listGeneral = null;
            GeneralComm_label_1 findGeneral = null;

            List<DepartmentType_label_2> listDepartmentType = null;
            DepartmentType_label_2 findDepartmentType = null;

            List<Department_label_3> listDepartment = null;
            Department_label_3 findDepartment = null;

            List<OfficeType_label_4> listOfficeType = null;
            OfficeType_label_4 findOfficeType = null;

            List<OfficeName_label_5> listOfficeName = null;
            OfficeName_label_5 findOfficeName = null;

            List<SectorType_label_6> listSectorType = null;
            SectorType_label_6 findSectorType = null;

            List<SectorName_label_7> listSectorName = null;
            SectorName_label_7 findSectorName = null;

            List<Rank_label_8> listRank = null;
            Rank_label_8 findRank = null;

            List<Position_label_9> listPosition = null;
            Position_label_9 findPosition = null;

            @Override
            protected void onPostExecute(Void aVoid) {
                Log.i("listRank : " + listRank);
                Log.i("findRank : " + findRank);

                Log.i("listPosition : " + listPosition);
                Log.i("findPosition : " + findPosition);

                if (findGeneral != null && findGeneral != null) {
                    binding.searchSelect1.setupUI(listGeneral, dropdownCallback);
                    binding.searchSelect1.selectItem(findGeneral);
                    showSelectedResult(binding.searchSelect1);

                    if (listDepartmentType != null && findDepartmentType != null) {
                        binding.searchSelect2.setupUI(listDepartmentType, dropdownCallback);
                        binding.searchSelect2.selectItem(findDepartmentType);
                        showSelectedResult(binding.searchSelect2);

                        if (listDepartment != null && findDepartment != null) {
                            binding.searchSelect3.setupUI(listDepartment, dropdownCallback);
                            binding.searchSelect3.selectItem(findDepartment);
                            showSelectedResult(binding.searchSelect3);

                            if (listOfficeType != null && findOfficeType != null) {
                                binding.searchSelect4.setupUI(listOfficeType, dropdownCallback);
                                binding.searchSelect4.selectItem(findOfficeType);
                                showSelectedResult(binding.searchSelect4);

                                if (listOfficeName != null && findOfficeName != null) {
                                    binding.searchSelect5.setupUI(listOfficeName, dropdownCallback);
                                    binding.searchSelect5.selectItem(findOfficeName);
                                    showSelectedResult(binding.searchSelect5);

                                    if (listSectorType != null) {
                                        binding.searchSelect6.setupUI(listSectorType, dropdownCallback);
                                        if (findSectorType != null) {
                                            binding.searchSelect6.selectItem(findSectorType);
                                        }
                                        showSelectedResult(binding.searchSelect6);

                                        if (listSectorName != null && findSectorName != null) {
                                            binding.searchSelect7.setupUI(listSectorName, dropdownCallback);
                                            binding.searchSelect7.selectItem(findSectorName);
                                            showSelectedResult(binding.searchSelect7);

                                            if (listRank != null) {
                                                binding.searchSelect8.setupUI(listRank, dropdownCallback);
                                                if (findRank != null) {
                                                    binding.searchSelect8.selectItem(findRank);
                                                }
                                                showSelectedResult(binding.searchSelect8);

                                                if (listPosition != null) {
                                                    binding.searchSelect9.setupUI(listPosition, dropdownCallback);
                                                    if (findPosition != null) {
                                                        binding.searchSelect9.selectItem(findPosition);
                                                    }
                                                    showSelectedResult(binding.searchSelect9);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            @Override
            protected Void doInBackground(Void... voids) {
                val t1 = System.currentTimeMillis();
                val db = AppDatabase.getInstance();
                listGeneral = db.generalCommDao_label_1().findAll();
                findGeneral = db.generalCommDao_label_1().findAllById(filter.getGeneralId());
                if (findGeneral != null) {
                    listDepartmentType = db.departmentTypeDao_label_2().findAllByGeneralCommId(findGeneral.getId());
                    findDepartmentType = db.departmentTypeDao_label_2().findAllById(filter.getDepartmentTypeId());
                    if (findDepartmentType != null) {
                        listDepartment = db.departmentDao_label_3().findAllByDepartmentTypeIdAndGeneralId(findGeneral.getId(), findDepartmentType.getDepartmentTypeId());
                        findDepartment = db.departmentDao_label_3().findAllById(filter.getDepartmentId());
                        if (findDepartment != null) {
                            listOfficeType = db.officeTypeDao_label_4().findAllByDepartmentId(findDepartment.getDepartmentId());
                            findOfficeType = db.officeTypeDao_label_4().findAllById(filter.getOfficeTypeId());
                            if (findOfficeType != null) {
                                listOfficeName = db.officeNameDao_label_5().findAllDepartmentIdAndOfficeTypeId(findDepartment.getDepartmentId(), findOfficeType.getOfficeTypeId());
                                findOfficeName = db.officeNameDao_label_5().findAllById(filter.getOfficeId());
                                if (findOfficeName != null) {
                                    listSectorType = db.sectorTypeDao_label_6().findAll();
                                    findSectorType = db.sectorTypeDao_label_6().findAllById(filter.getSectorTypeId());
                                    if (findSectorType != null) {
                                        listSectorName = db.sectorName_label_7().findAllBySectorTypeIdAndOfficeId(findSectorType.getSectorTypeId(), findOfficeName.getOfficeId());
                                        findSectorName = db.sectorName_label_7().findAllById(filter.getSectorId());
                                        if (findSectorName != null) {
                                            listRank = db.rankDao_label_8().findAll();
                                            findRank = db.rankDao_label_8().findAllById(filter.getRankId());
                                            if (findRank != null) {
                                                listPosition = db.positionDao_label_9().findAll();
                                                findPosition = db.positionDao_label_9().findAllById(filter.getPositionId());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                val t2 = System.currentTimeMillis();
                Log.i("Time query : " + (t2 - t1));
                return null;
            }
        }.execute();
    }

    private void loadValueLabel_1() {
        Log.i("loadValueLabel_1");
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
        Log.i("loadValueLabel_2");
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
        Log.i("loadValueLabel_3");
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
        Log.i("loadValueLabel_4");
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
        Log.i("loadValueLabel_5");
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
        Log.i("loadValueLabel_6");
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
        Log.i("loadValueLabel_7");
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

    private void loadValueLabel_8() {
        Log.i("loadValueLabel_8");
        binding.progressBar.setVisibility(View.VISIBLE);
        preloadResult(binding.searchSelect8);
        val task = new TaskRank_label_8();
        getCompositeDisposable().add(task.start(task.new SimpleObserver() {

            public Class<?> clazzResponse() {
                return null;
            }

            @Override
            public void onReceiveResult(@NonNull RequestRank_label_8 request, @NonNull Response result) throws Exception {
                if (result.isSuccessful()) {
                    val data = (ResponseRank_Label_8) result.body();
                    AppDatabase.getInstance().rankDao_label_8().insertAll(data.getResultArrays());
                }
            }

            @Override
            public void onNext(@NonNull Response result) {
                Log.i("LOG >> onNext >> result : " + result);
                if (result.isSuccessful()) {
                    val data = (ResponseRank_Label_8) result.body();
                    binding.searchSelect8.setupUI(data.getResult(), dropdownCallback);
                    showSelectedResult(binding.searchSelect8);
                    binding.scrollView.smoothScrollTo(0, binding.searchSelect8.getBottom());
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

    private void loadValueLabel_9() {
        Log.i("loadValueLabel_9");
        binding.progressBar.setVisibility(View.VISIBLE);
        preloadResult(binding.searchSelect9);
        val task = new TaskPosition_label_9();
        getCompositeDisposable().add(task.start(task.new SimpleObserver() {

            public Class<?> clazzResponse() {
                return null;
            }

            @Override
            public void onReceiveResult(@NonNull RequestPosition_label_9 request, @NonNull Response result) throws Exception {
                if (result.isSuccessful()) {
                    val data = (ResponsePosition_Label_9) result.body();
                    AppDatabase.getInstance().positionDao_label_9().insertAll(data.getResultArrays());
                }
            }

            @Override
            public void onNext(@NonNull Response result) {
                Log.i("LOG >> onNext >> result : " + result);
                if (result.isSuccessful()) {
                    val data = (ResponsePosition_Label_9) result.body();
                    binding.searchSelect9.setupUI(data.getResult(), dropdownCallback);
                    showSelectedResult(binding.searchSelect9);
                    binding.scrollView.smoothScrollTo(0, binding.searchSelect9.getBottom());
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
                loadValueLabel_8();
            }
            else if (object instanceof Rank_label_8) {
                binding.searchSelect8.setTag(object);
                loadValueLabel_9();
            }
            else if (object instanceof Position_label_9) {
                ApplicationUtil.dismissKeyboard(getContext(), binding.searchSelect9);
                binding.searchSelect9.setTag(object);
            }
        }
    };

    private void preloadResult(View selectedView) {
        showGroupSearch(View.INVISIBLE);
        if (binding.searchSelect1 == selectedView) {
            binding.searchSelect2.setTag(null);
            binding.searchSelect3.setTag(null);
            binding.searchSelect4.setTag(null);
            binding.searchSelect5.setTag(null);
            binding.searchSelect6.setTag(null);
            binding.searchSelect7.setTag(null);
            binding.searchSelect8.setTag(null);
            binding.searchSelect9.setTag(null);

            binding.searchSelect2.setVisibility(View.INVISIBLE);
            binding.searchSelect3.setVisibility(View.INVISIBLE);
            binding.searchSelect4.setVisibility(View.INVISIBLE);
            binding.searchSelect5.setVisibility(View.INVISIBLE);
            binding.searchSelect6.setVisibility(View.INVISIBLE);
            binding.searchSelect7.setVisibility(View.INVISIBLE);
            binding.searchSelect8.setVisibility(View.INVISIBLE);
            binding.searchSelect9.setVisibility(View.INVISIBLE);
        }
        else if (binding.searchSelect2 == selectedView) {
            binding.searchSelect2.setTag(null);
            binding.searchSelect3.setTag(null);
            binding.searchSelect4.setTag(null);
            binding.searchSelect5.setTag(null);
            binding.searchSelect6.setTag(null);
            binding.searchSelect7.setTag(null);
            binding.searchSelect8.setTag(null);
            binding.searchSelect9.setTag(null);

            binding.searchSelect2.setVisibility(View.INVISIBLE);
            binding.searchSelect3.setVisibility(View.INVISIBLE);
            binding.searchSelect4.setVisibility(View.INVISIBLE);
            binding.searchSelect5.setVisibility(View.INVISIBLE);
            binding.searchSelect6.setVisibility(View.INVISIBLE);
            binding.searchSelect7.setVisibility(View.INVISIBLE);
            binding.searchSelect8.setVisibility(View.INVISIBLE);
            binding.searchSelect9.setVisibility(View.INVISIBLE);
        }
        else if (binding.searchSelect3 == selectedView) {
            binding.searchSelect3.setTag(null);
            binding.searchSelect4.setTag(null);
            binding.searchSelect5.setTag(null);
            binding.searchSelect6.setTag(null);
            binding.searchSelect7.setTag(null);
            binding.searchSelect8.setTag(null);
            binding.searchSelect9.setTag(null);

            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.INVISIBLE);
            binding.searchSelect4.setVisibility(View.INVISIBLE);
            binding.searchSelect5.setVisibility(View.INVISIBLE);
            binding.searchSelect6.setVisibility(View.INVISIBLE);
            binding.searchSelect7.setVisibility(View.INVISIBLE);
            binding.searchSelect8.setVisibility(View.INVISIBLE);
            binding.searchSelect9.setVisibility(View.INVISIBLE);
        }
        else if (binding.searchSelect4 == selectedView) {
            binding.searchSelect4.setTag(null);
            binding.searchSelect5.setTag(null);
            binding.searchSelect6.setTag(null);
            binding.searchSelect7.setTag(null);
            binding.searchSelect8.setTag(null);
            binding.searchSelect9.setTag(null);

            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.VISIBLE);
            binding.searchSelect4.setVisibility(View.INVISIBLE);
            binding.searchSelect5.setVisibility(View.INVISIBLE);
            binding.searchSelect6.setVisibility(View.INVISIBLE);
            binding.searchSelect7.setVisibility(View.INVISIBLE);
            binding.searchSelect8.setVisibility(View.INVISIBLE);
            binding.searchSelect9.setVisibility(View.INVISIBLE);
        }
        else if (binding.searchSelect5 == selectedView) {
            binding.searchSelect5.setTag(null);
            binding.searchSelect6.setTag(null);
            binding.searchSelect7.setTag(null);
            binding.searchSelect8.setTag(null);
            binding.searchSelect9.setTag(null);

            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.VISIBLE);
            binding.searchSelect4.setVisibility(View.VISIBLE);
            binding.searchSelect5.setVisibility(View.INVISIBLE);
            binding.searchSelect6.setVisibility(View.INVISIBLE);
            binding.searchSelect7.setVisibility(View.INVISIBLE);
            binding.searchSelect8.setVisibility(View.INVISIBLE);
            binding.searchSelect9.setVisibility(View.INVISIBLE);
        }
        else if (binding.searchSelect6 == selectedView) {
            binding.searchSelect6.setTag(null);
            binding.searchSelect7.setTag(null);
            binding.searchSelect8.setTag(null);
            binding.searchSelect9.setTag(null);

            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.VISIBLE);
            binding.searchSelect4.setVisibility(View.VISIBLE);
            binding.searchSelect5.setVisibility(View.VISIBLE);
            binding.searchSelect6.setVisibility(View.INVISIBLE);
            binding.searchSelect7.setVisibility(View.INVISIBLE);
            binding.searchSelect8.setVisibility(View.INVISIBLE);
            binding.searchSelect9.setVisibility(View.INVISIBLE);
            showGroupSearch(View.VISIBLE);
        }
        else if (binding.searchSelect7 == selectedView) {
            binding.searchSelect7.setTag(null);
            binding.searchSelect8.setTag(null);
            binding.searchSelect9.setTag(null);

            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.VISIBLE);
            binding.searchSelect4.setVisibility(View.VISIBLE);
            binding.searchSelect5.setVisibility(View.VISIBLE);
            binding.searchSelect6.setVisibility(View.VISIBLE);
            binding.searchSelect7.setVisibility(View.INVISIBLE);
            binding.searchSelect8.setVisibility(View.INVISIBLE);
            binding.searchSelect9.setVisibility(View.INVISIBLE);
            showGroupSearch(View.VISIBLE);
        }
        else if (binding.searchSelect8 == selectedView) {
            binding.searchSelect8.setTag(null);
            binding.searchSelect9.setTag(null);

            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.VISIBLE);
            binding.searchSelect4.setVisibility(View.VISIBLE);
            binding.searchSelect5.setVisibility(View.VISIBLE);
            binding.searchSelect6.setVisibility(View.VISIBLE);
            binding.searchSelect7.setVisibility(View.VISIBLE);
            binding.searchSelect8.setVisibility(View.INVISIBLE);
            binding.searchSelect9.setVisibility(View.INVISIBLE);
            showGroupSearch(View.VISIBLE);
        }
        else if (binding.searchSelect9 == selectedView) {
            binding.searchSelect9.setTag(null);

            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.VISIBLE);
            binding.searchSelect4.setVisibility(View.VISIBLE);
            binding.searchSelect5.setVisibility(View.VISIBLE);
            binding.searchSelect6.setVisibility(View.VISIBLE);
            binding.searchSelect7.setVisibility(View.VISIBLE);
            binding.searchSelect8.setVisibility(View.VISIBLE);
            binding.searchSelect9.setVisibility(View.INVISIBLE);
            showGroupSearch(View.VISIBLE);
        }
    }

    private void showGroupSearch(int visibility) {
        switch (visibility) {
            case View.VISIBLE:
                binding.btnSearch.setEnabledWithAlpha(true);
                break;
            case View.INVISIBLE:
            case View.GONE:
                binding.btnSearch.setEnabledWithAlpha(false);
                break;
        }
    }

    private void showSelectedResult(View selectedView) {
        Log.i(selectedView);
        showGroupSearch(View.INVISIBLE);
        if (binding.searchSelect1 == selectedView) {
            binding.searchSelect2.setVisibility(View.INVISIBLE);
            binding.searchSelect3.setVisibility(View.INVISIBLE);
            binding.searchSelect4.setVisibility(View.INVISIBLE);
            binding.searchSelect5.setVisibility(View.INVISIBLE);
            binding.searchSelect6.setVisibility(View.INVISIBLE);
            binding.searchSelect7.setVisibility(View.INVISIBLE);
            binding.searchSelect8.setVisibility(View.INVISIBLE);
            binding.searchSelect9.setVisibility(View.INVISIBLE);
        }
        else if (binding.searchSelect2 == selectedView) {
            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.INVISIBLE);
            binding.searchSelect4.setVisibility(View.INVISIBLE);
            binding.searchSelect5.setVisibility(View.INVISIBLE);
            binding.searchSelect6.setVisibility(View.INVISIBLE);
            binding.searchSelect7.setVisibility(View.INVISIBLE);
            binding.searchSelect8.setVisibility(View.INVISIBLE);
            binding.searchSelect9.setVisibility(View.INVISIBLE);
        }
        else if (binding.searchSelect3 == selectedView) {
            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.VISIBLE);
            binding.searchSelect4.setVisibility(View.INVISIBLE);
            binding.searchSelect5.setVisibility(View.INVISIBLE);
            binding.searchSelect6.setVisibility(View.INVISIBLE);
            binding.searchSelect7.setVisibility(View.INVISIBLE);
            binding.searchSelect8.setVisibility(View.INVISIBLE);
            binding.searchSelect9.setVisibility(View.INVISIBLE);
        }
        else if (binding.searchSelect4 == selectedView) {
            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.VISIBLE);
            binding.searchSelect4.setVisibility(View.VISIBLE);
            binding.searchSelect5.setVisibility(View.INVISIBLE);
            binding.searchSelect6.setVisibility(View.INVISIBLE);
            binding.searchSelect7.setVisibility(View.INVISIBLE);
            binding.searchSelect8.setVisibility(View.INVISIBLE);
            binding.searchSelect9.setVisibility(View.INVISIBLE);
        }
        else if (binding.searchSelect5 == selectedView) {
            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.VISIBLE);
            binding.searchSelect4.setVisibility(View.VISIBLE);
            binding.searchSelect5.setVisibility(View.VISIBLE);
            binding.searchSelect6.setVisibility(View.INVISIBLE);
            binding.searchSelect7.setVisibility(View.INVISIBLE);
            binding.searchSelect8.setVisibility(View.INVISIBLE);
            binding.searchSelect9.setVisibility(View.INVISIBLE);
        }
        else if (binding.searchSelect6 == selectedView) {
            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.VISIBLE);
            binding.searchSelect4.setVisibility(View.VISIBLE);
            binding.searchSelect5.setVisibility(View.VISIBLE);
            binding.searchSelect6.setVisibility(View.VISIBLE);
            binding.searchSelect7.setVisibility(View.INVISIBLE);
            binding.searchSelect8.setVisibility(View.INVISIBLE);
            binding.searchSelect9.setVisibility(View.INVISIBLE);
            showGroupSearch(View.VISIBLE);
        }
        else if (binding.searchSelect7 == selectedView) {
            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.VISIBLE);
            binding.searchSelect4.setVisibility(View.VISIBLE);
            binding.searchSelect5.setVisibility(View.VISIBLE);
            binding.searchSelect6.setVisibility(View.VISIBLE);
            binding.searchSelect7.setVisibility(View.VISIBLE);
            binding.searchSelect8.setVisibility(View.INVISIBLE);
            binding.searchSelect9.setVisibility(View.INVISIBLE);
            showGroupSearch(View.VISIBLE);
        }
        else if (binding.searchSelect8 == selectedView) {
            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.VISIBLE);
            binding.searchSelect4.setVisibility(View.VISIBLE);
            binding.searchSelect5.setVisibility(View.VISIBLE);
            binding.searchSelect6.setVisibility(View.VISIBLE);
            binding.searchSelect7.setVisibility(View.VISIBLE);
            binding.searchSelect8.setVisibility(View.VISIBLE);
            binding.searchSelect9.setVisibility(View.INVISIBLE);
            showGroupSearch(View.VISIBLE);
        }
        else if (binding.searchSelect9 == selectedView) {
            binding.searchSelect2.setVisibility(View.VISIBLE);
            binding.searchSelect3.setVisibility(View.VISIBLE);
            binding.searchSelect4.setVisibility(View.VISIBLE);
            binding.searchSelect5.setVisibility(View.VISIBLE);
            binding.searchSelect6.setVisibility(View.VISIBLE);
            binding.searchSelect7.setVisibility(View.VISIBLE);
            binding.searchSelect8.setVisibility(View.VISIBLE);
            binding.searchSelect9.setVisibility(View.VISIBLE);
            showGroupSearch(View.VISIBLE);
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_search_option;
    }

    public interface Callback {
        void onSearch(StaffFilter filter);
    }
}