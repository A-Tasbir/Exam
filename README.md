javaSystemDev_login(アリフ)Test/
│
├── src/
│   ├── bean/
│   │   ├── ClassGroup.java
│   │   ├── ClassNum.java
│   │   ├── School.java
│   │   ├── Student.java
│   │   ├── Subject.java
│   │   ├── Teacher.java
│   │   ├── Test.java
│   │   ├── TestRow.java
│   │   └── User.java
│   │
│   ├── dao/
│   │   ├── ClassNumDao.java
│   │   ├── Dao.java
│   │   ├── SchoolDao.java
│   │   ├── StudentDao.java
│   │   ├── SubjectDao.java
│   │   ├── TeacherDao.java
│   │   ├── TestDao.java
│   │   └── TestListStudentDao.java
│   │
│   ├── scoremanager/
│   │   ├── LoginAction.java
│   │   ├── LoginExecuteAction.java
│   │   │
│   │   └── main/
│   │       ├── ClassEditAction.java
│   │       ├── ClassEditExecuteAction.java
│   │       ├── ClassListAction.java
│   │       ├── ClassRegistAction.java
│   │       ├── ClassRegistExecuteAction.java
│   │       ├── LogoutAction.java
│   │       ├── MenuAction.java
│   │       ├── StudentCreateAction.java
│   │       ├── StudentCreateExecuteAction.java
│   │       ├── StudentCsvUploadAction.java
│   │       ├── StudentCsvUploadExecuteAction.java
│   │       ├── StudentListAction.java
│   │       ├── StudentUpdateAction.java
│   │       ├── StudentUpdateExecuteAction.java
│   │       ├── TeacherEditAction.java
│   │       ├── TeacherEditExecuteAction.java
│   │       ├── TeacherEditFinalAction.java
│   │       ├── TeacherListAction.java
│   │       ├── TeacherRegistAction.java
│   │       ├── TeacherRegistExecuteAction.java
│   │       ├── TestListAction.java
│   │       ├── TestListStudentExecuteAction.java
│   │       ├── TestRegistAction.java
│   │       └── TestRegistExecuteAction.java
│   │
│   └── tool/
│       ├── Action.java
│       ├── EncodingFilter.java
│       └── FrontController.java
│
├── WebContent/   (or src/main/webapp if Maven)
│   ├── META-INF/
│   │   ├── MANIFEST.MF
│   │   └── context.xml
│   │
│   ├── WEB-INF/
│   │   ├── web.xml   (⚠️ not present in WAR, may be missing or annotation-based)
│   │   ├── classes/ (compiled .class files)
│   │   └── lib/
│   │       ├── h2-2.1.214.jar
│   │       ├── jakarta.servlet.jsp.jstl-3.0.1.jar
│   │       └── jakarta.servlet.jsp.jstl-api-3.0.0.jar
│   │
│   ├── common/
│   │   ├── base.jsp
│   │   ├── footer.jsp
│   │   ├── header.jsp
│   │   └── navigation.jsp
│   │
│   ├── scoremanager/
│   │   ├── index.jsp
│   │   ├── login.jsp
│   │   │
│   │   └── main/
│   │       ├── classEdit.jsp
│   │       ├── classEditComplete.jsp
│   │       ├── classList.jsp
│   │       ├── classRegist.jsp
│   │       ├── classRegistComplete.jsp
│   │       ├── logout.jsp
│   │       ├── menu.jsp
│   │       ├── studentCsvUpload.jsp
│   │       ├── studentCsvUploadComplete.jsp
│   │       ├── studentCsvUploadFail.jsp
│   │       ├── student_create.jsp
│   │       ├── student_create_done.jsp
│   │       ├── student_list.jsp
│   │       ├── student_update.jsp
│   │       ├── student_update_done.jsp
│   │       ├── teacherAdminConfirm.jsp
│   │       ├── teacherEdit.jsp
│   │       ├── teacherEditComplete.jsp
│   │       ├── teacherList.jsp
│   │       ├── teacherRegist.jsp
│   │       ├── teacherRegistComplete.jsp
│   │       ├── test_list.jsp
│   │       ├── test_list_student.jsp
│   │       ├── test_regist.jsp
│   │       └── test_regist_done.jsp
│   │
│   └── error.jsp
