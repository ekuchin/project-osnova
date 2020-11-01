package ru.projectosnova.config;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigStoreRepository extends JpaRepository<ConfigStore,String> {
}
