Exam/
│
├── src/main/java/
│   │
│   ├── bean/                ← Model (Entities)
│   │   ├── Student.java
│   │   ├── Teacher.java
│   │   ├── Subject.java
│   │   ├── School.java
│   │   ├── ClassGroup.java
│   │   ├── ClassNum.java
│   │   ├── Test.java
│   │   ├── TestRow.java
│   │   └── User.java
│   │
│   ├── dao/                ← Database Layer
│   │   ├── Dao.java
│   │   ├── StudentDao.java
│   │   ├── TeacherDao.java
│   │   ├── SubjectDao.java
│   │   ├── SchoolDao.java
│   │   ├── ClassNumDao.java
│   │   ├── TestDao.java
│   │   └── TestListStudentDao.java
│   │
│   ├── scoremanager/       ← Controller (Business Logic)
│   │   ├── LoginAction.java
│   │   ├── LoginExecuteAction.java
│   │   │
│   │   ├── main/
│   │   │   ├── ClassListAction.java
│   │   │   ├── ClassRegistAction.java
│   │   │   ├── ClassEditAction.java
│   │   │   ├── StudentListAction.java
│   │   │   ├── StudentCreateAction.java
│   │   │   ├── StudentUpdateAction.java
│   │   │   ├── TeacherListAction.java
│   │   │   ├── TeacherRegistAction.java
│   │   │   ├── TestListAction.java
│   │   │   └── ...
│   │
│   └── tool/              ← Framework / Filters / Front Controller
│       ├── Action.java
│       ├── FrontController.java
│       └── EncodingFilter.java
│
│
├── src/main/webapp/        ← VIEW (JSP files)
│   │
│   ├── common/            ← Shared layout
│   │   ├── header.jsp
│   │   ├── footer.jsp
│   │   ├── navigation.jsp
│   │   └── base.jsp
│   │
│   ├── scoremanager/
│   │   ├── index.jsp
│   │   ├── login.jsp
│   │   │
│   │   └── main/
│   │       ├── menu.jsp
│   │       ├── classList.jsp
│   │       ├── student_list.jsp
│   │       ├── teacherList.jsp
│   │       ├── test_list.jsp
│   │       ├── student_create.jsp
│   │       ├── student_update.jsp
│   │       ├── teacherRegist.jsp
│   │       ├── test_regist.jsp
│   │       └── ...
│   │
│   ├── error.jsp
│   │
│   └── META-INF/
│       └── context.xml
│
│
├── WEB-INF/
│   ├── lib/
│   │   ├── h2-2.1.214.jar
│   │   ├── jstl-api.jar
│   │   └── jstl.jar
│   │
│   └── web.xml
│
│
├── build/
├── .classpath
├── .project
└── .settings/
