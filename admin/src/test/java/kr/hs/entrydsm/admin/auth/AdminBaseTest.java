package kr.hs.entrydsm.admin.auth;

import kr.hs.entrydsm.admin.entity.admin.Admin;
import kr.hs.entrydsm.admin.entity.admin.Permission;

import java.util.ArrayList;
import java.util.List;

public class AdminBaseTest {

    protected static final Admin TEACHER_ADMIN = Admin.builder()
            .id("asdf1234")
            .password("teacheradmin")
            .name("본부교무실")
            .permission(Permission.TEACHER)
            .build();

    protected static final Admin OFFICE_ADMIN = Admin.builder()
            .id("asdf4567")
            .password("officeadmin")
            .name("행정실")
            .permission(Permission.OFFICE)
            .build();

    private static final List<Admin> ADMINS = new ArrayList<>();

    protected static boolean addAccount(Admin admin) {
        boolean exists = ADMINS.stream().anyMatch(u -> u.getId().equals(admin.getId()));
        boolean requestCheck = admin.getId().length() > 8 || admin.getName().length() > 5;

        if (exists) return false;
        if(requestCheck) return false;

        ADMINS.add(admin);
        return true;
    }

}
