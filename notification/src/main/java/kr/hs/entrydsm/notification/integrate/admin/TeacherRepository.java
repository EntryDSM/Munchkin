package kr.hs.entrydsm.notification.integrate.admin;

import kr.hs.entrydsm.notification.entity.Teacher;

public interface TeacherRepository {
    Teacher findById(String userId);
}
