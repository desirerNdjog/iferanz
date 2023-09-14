package com.kimia_technologies.dao_implement;

import com.kimia_technologies.dao_service.UserDaoService;
import com.kimia_technologies.dto.UserResponse;
import com.kimia_technologies.mapper.UserResponseMapper;
import com.kimia_technologies.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
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
public class UserDaoImpl implements UserDaoService {
    private final EntityManager entityManager;
    private final UserResponseMapper mapper;

    @Override
    public Page<UserResponse> findAll(String element, int page, int size) {
        try{
            Pageable pageable = PageRequest.of(page, size);
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            List<Predicate> predicates = new ArrayList<>();

            if (element != null){
                predicates.add(criteriaBuilder.like(root.get("email"), "%"+element+"%"));
            }

            if (element != null){
                predicates.add(criteriaBuilder.like(root.get("phone"), "%"+element+"%"));
            }

            criteriaQuery.where(
                    criteriaBuilder.or(
                            predicates.toArray(predicates.toArray(new Predicate[0]))
                    )
            );

            List<User> list = entityManager.createQuery(criteriaQuery)
                    .setFirstResult((int) pageable.getOffset())
                    .setMaxResults(pageable.getPageSize())
                    .getResultList();

            CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
            countQuery.select(criteriaBuilder.count(countQuery.from(User.class)));
            Long count = entityManager.createQuery(countQuery).getSingleResult();

            Page<User> finalResul = new PageImpl<>(list, pageable, count);

            return finalResul.map(mapper);
        }catch (NoResultException exception){
            return null;
        }
    }

    @Override
    public User findByEmailOrPhone(String element) {
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            List<Predicate> predicates = new ArrayList<>();

          if (element != null){
              predicates.add(criteriaBuilder.equal(root.get("email"), element));
          }

          if (element != null){
              predicates.add(criteriaBuilder.equal(root.get("phone"), element));
          }

          criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
          TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery);
          return typedQuery.getSingleResult();
        }catch (NoResultException exception){
            return null;
        }
    }

}
