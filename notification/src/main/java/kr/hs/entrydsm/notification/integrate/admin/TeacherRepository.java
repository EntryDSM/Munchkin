package kr.hs.entrydsm.notification.integrate.admin;

import kr.hs.entrydsm.notification.domain.entity.Teacher;

public interface TeacherRepository {
    Teacher findById(String id);
    boolean isTeacher();
}
