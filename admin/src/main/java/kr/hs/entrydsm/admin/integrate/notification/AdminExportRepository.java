package kr.hs.entrydsm.admin.integrate.notification;

import kr.hs.entrydsm.admin.entity.admin.Admin;
import kr.hs.entrydsm.common.context.beans.Published;

@Published
public interface AdminExportRepository {
    Admin findById(String id);
}
