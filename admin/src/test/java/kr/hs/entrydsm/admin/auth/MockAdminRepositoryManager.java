package kr.hs.entrydsm.admin.auth;

import kr.hs.entrydsm.admin.entity.admin.Admin;
import kr.hs.entrydsm.admin.entity.admin.AdminRepository;

import java.util.Optional;

import static kr.hs.entrydsm.admin.auth.AdminBaseTest.TEACHER_ADMIN;


public class MockAdminRepositoryManager implements AdminRepository {

    @Override
    public Optional<Admin> findById(String id) {
        return Optional.ofNullable(TEACHER_ADMIN);
    }

    @Override
    public Admin save(Admin admin) {
        return null;
    }

}
