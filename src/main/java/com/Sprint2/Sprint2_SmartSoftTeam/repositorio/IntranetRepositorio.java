package com.Sprint2.Sprint2_SmartSoftTeam.repositorio;
import com.Sprint2.Sprint2_SmartSoftTeam.modelos.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntranetRepositorio extends JpaRepository<Empresa,Integer> {
}
