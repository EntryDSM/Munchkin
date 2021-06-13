package kr.hs.entrydsm.admin.entity.admin;

import java.util.Optional;

public interface AdminRepository {
    Optional<Admin> findById(String id);
    Admin save(Admin admin);
}
