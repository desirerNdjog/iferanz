package com.kimia_technologies.dao_implement;

import com.kimia_technologies.dao_service.ProfilDaoService;
import com.kimia_technologies.dto.ProfilResponse;
import com.kimia_technologies.mapper.ProfilResponseMapper;
import com.kimia_technologies.models.Profil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Desire Junior NDJOG
 * @version 1.0
 * @project ifiranz_backend
 * @since 08/09/2023
 */

@Repository
@RequiredArgsConstructor
public class ProfilDaoImpl implements ProfilDaoService {
    private final EntityManager entityManager;
    private final ProfilResponseMapper mapper;


    @Override
    public Page<ProfilResponse> findAll(String name, int page, int size) {
        try{
            Pageable pageable = PageRequest.of(page, size);
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Profil> criteriaQuery = criteriaBuilder.createQuery(Profil.class);
            Root<Profil> root = criteriaQuery.from(Profil.class);
            List<Predicate> predicates = new ArrayList<>();

            if (name != null){
                predicates.add(criteriaBuilder.like(root.get("name"), "%"+name+"%"));
            }

            criteriaQuery.where(
                    predicates.toArray(new Predicate[predicates.size()])
            );

            List<Profil> list = entityManager.createQuery(criteriaQuery)
                    .setFirstResult((int) pageable.getOffset())
                    .setMaxResults(pageable.getPageSize())
                    .getResultList();

            CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
            countQuery.select(criteriaBuilder.count(countQuery.from(Profil.class)));
            Long count = entityManager.createQuery(countQuery).getSingleResult();

            Page<Profil> finalResul = new PageImpl<>(list, pageable, count);

            return finalResul.map(mapper);
        }catch (NoResultException exception){
            return null;
        }
    }
}
