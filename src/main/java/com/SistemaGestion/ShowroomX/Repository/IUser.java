package com.SistemaGestion.ShowroomX.Repository;

import com.SistemaGestion.ShowroomX.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUser extends JpaRepository<User, Long> {

    User findByNameUser(String userName);
}
