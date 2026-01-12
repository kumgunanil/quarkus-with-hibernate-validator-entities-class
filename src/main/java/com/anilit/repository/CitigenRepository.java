package com.anilit.repository;

import com.anilit.entity.CitizenNew;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CitigenRepository implements PanacheRepository<CitizenNew> {

}
