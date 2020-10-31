package ru.projectosnova.config;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigStorageRepository extends JpaRepository<ConfigStorage,String> {
}
