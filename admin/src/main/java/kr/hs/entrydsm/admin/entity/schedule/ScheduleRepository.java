package kr.hs.entrydsm.admin.entity.schedule;

import java.util.List;

public interface ScheduleRepository {
    List<Schedule> findAllBy();
    Schedule findByYearAndType(String year, Type type);
}
