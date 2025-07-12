package jp.ne.papapa.copilot_instructions.repository;

import jp.ne.papapa.copilot_instructions.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * ユーザーリポジトリ
 * ユーザー情報のCRUD操作を提供
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * 複数の条件でユーザーを検索（ページング対応）
     * @param email メールアドレス（部分一致、nullの場合は条件に含めない）
     * @param firstName 名前（部分一致、nullの場合は条件に含めない）
     * @param lastName 苗字（部分一致、nullの場合は条件に含めない）
     * @param status ユーザーステータス（完全一致、nullの場合は条件に含めない）
     * @param pageable ページング情報
     * @return 検索結果のページ
     */
    @Query("SELECT u FROM User u WHERE " +
           "(:email IS NULL OR u.email LIKE %:email%) AND " +
           "(:firstName IS NULL OR u.firstName LIKE %:firstName%) AND " +
           "(:lastName IS NULL OR u.lastName LIKE %:lastName%) AND " +
           "(:status IS NULL OR u.status = :status)")
    Page<User> findUsersWithCriteria(
        @Param("email") String email,
        @Param("firstName") String firstName, 
        @Param("lastName") String lastName,
        @Param("status") User.UserStatus status,
        Pageable pageable
    );
    
    /**
     * 指定されたステータスのユーザー数をカウント
     * @param status カウント対象のユーザーステータス
     * @return 該当するユーザー数
     */
    @Query("SELECT COUNT(u) FROM User u WHERE u.status = :status")
    long countByStatus(@Param("status") User.UserStatus status);
}
