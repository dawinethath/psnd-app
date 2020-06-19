package kh.com.psnd.mock;

import java.util.ArrayList;
import java.util.List;

import kh.com.psnd.network.model.Commissariat;
import kh.com.psnd.network.model.DepartmentType;
import lombok.val;

public class MockDepartment {
    public static List<Commissariat> getDepartment() {
        val list = new ArrayList<Commissariat>();
        {
            val department = new Commissariat();
            department.setId("1");
            department.setName("អគ្គស្នងការដ្ឋាននគរបាលជាតិ");
            department.addDepartmentType(new DepartmentType("1", "ស្នង.ន.ប.រាជធានី-ខេត្ត"));
            department.addDepartmentType(new DepartmentType("2", "ន.ក.ណ.នគរបាលព្រំដែន"));
            department.addDepartmentType(new DepartmentType("3", "ន.ក.ណ.ស.ធ្នាប់សាធា."));
            department.addDepartmentType(new DepartmentType("4", "ន.ក.ណ.ន.បយុត្តិធម័"));
            department.addDepartmentType(new DepartmentType("5", "ន.ក.ណ.សន្តិសុខ"));
            department.addDepartmentType(new DepartmentType("6", "នាយកដ្ឋាន"));
            department.addDepartmentType(new DepartmentType("7", "សេនាធិការដ្ឋាន"));
            list.add(department);
        }
        {
            val department = new Commissariat();
            department.setId("2");
            department.setName("អគ្គនាយកដ្ឋានភស្តុភារ និងហិរញ្ញវត្ថុ");
            department.addDepartmentType(new DepartmentType("7", "នាយកដ្ឋាន"));
            department.addDepartmentType(new DepartmentType("13", "លេខាធិការដ្ឋាន"));
            list.add(department);
        }
        {
            val department = new Commissariat();
            department.setId("3");
            department.setName("អគ្គលេខាធិការដ្ឋាន");
            department.addDepartmentType(new DepartmentType("9", "នាយកដ្ឋាន"));
            department.addDepartmentType(new DepartmentType("15", "លេខាធិការដ្ឋាន"));
            list.add(department);
        }
        {
            val department = new Commissariat();
            department.setId("4");
            department.setName("បណ្ឌិត្យសភានគរបាលកម្ពុជា");
            list.add(department);
        }
        {
            val department = new Commissariat();
            department.setId("5");
            department.setName("អគ្គលេខាធិការដ្ឋានគណៈកម្មាធិការជាតិប្រយុទ្ធប្រឆាំងអំពើជួញដូរមនុស្ស");
            list.add(department);
        }
        {
            val department = new Commissariat();
            department.setId("6");
            department.setName("គណៈកម្មាធិការប្រយុទ្ធប្រឆាំងផលិតផលក្លែងក្លាយដែលបង្កគ្រោះថ្នាក់មានហានិភយខ្ពស់ដល់សុខភាព និងសុវត្ថិភាពសង្គម");
            list.add(department);
        }

        return list;
    }
}
